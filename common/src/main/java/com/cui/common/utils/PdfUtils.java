package com.cui.common.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class PdfUtils {

	private static final Logger LOGGER = Logger.getLogger(com.cui.common.utils.PdfUtils.class);


	public static void toContactFile(String templatPath, String newPath,
			Map<String, String> data) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(templatPath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper ps = new PdfStamper(reader, bos);
		AcroFields fields = ps.getAcroFields();
		fillData(fields, data);
		ps.setFormFlattening(true);
		ps.close();
		OutputStream fos = new FileOutputStream(newPath);
		fos.write(bos.toByteArray());
		fos.flush();
		fos.close();
	}

	private Map<String, String> data() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("userName", "侯万敏");
		data.put("orderNo", "1231212312");
		data.put("userNo", "12321312312");
		data.put("bank", "上海银行");
		return data;
	}

	private static void fillData(AcroFields fields, Map<String, String> data)
			throws IOException, DocumentException {
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null || "null".equals(value)) {
				value = "";
			}
			fields.setField(key, value);
		}
	}

	private void addImage() {
		// try {
		// String fileName = "D:\\gloomyfish\\华夏信财出借人咨询与服务协议.pdf";
		// PdfReader reader = new PdfReader(fileName);
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// PdfStamper ps = new PdfStamper(reader, bos);
		// Image image = Image.getInstance("D:\\gloomyfish\\hello_world.png");
		//
		// ps.setFormFlattening(true);
		// ps.close();
		// OutputStream fos = new
		// FileOutputStream("D:\\gloomyfish\\华夏信财出借人咨询与服务协议1.pdf");
		// fos.write(bos.toByteArray());
		//
		// } catch (BadElementException e) {
		// // TODO Auto-generated catch block
		// LOGGER.error("addImage  BadElementException异常：",e);
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// LOGGER.error("addImage  MalformedURLException异常：",e);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// LOGGER.error("addImage  IOException异常：",e);
		// } catch (DocumentException e) {
		// // TODO Auto-generated catch block
		// LOGGER.error("addImage  DocumentException异常：",e);
		// }

		try {
			String InPdfFile = "D:\\gloomyfish\\华夏信财出借人咨询与服务协议.pdf";
			String markImagePath = "D:\\gloomyfish\\user.jpg";
			String outPdfFile = "D:\\gloomyfish\\华夏信财出借人咨询与服务协议1.pdf";
			addPdfMark(InPdfFile, outPdfFile, markImagePath, 50);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("addImage  Exception异常：", e);
		}
	}

	public static void addPdfMark(String InPdfFile, String outPdfFile,
			String markImagePath, int pageSize) throws Exception {
		PdfReader reader = new PdfReader(InPdfFile);
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
				outPdfFile));
		Image img = Image.getInstance(markImagePath);// 插入水印
		img.setAbsolutePosition(100, 390);
		for (int i = 1; i <= pageSize; i++) {
			// PdfContentByte under = stamp.getUnderContent(i);//在内容下方加水印
			PdfContentByte under = stamp.getOverContent(i);// 在内容上方加水印
			if (under != null && i == 14) {
				under.addImage(img);
				// under.addImage(img, 0, 0, 100, 50, 100, 250);
			}
		}
		stamp.close();// 关闭
		// File tempfile = new File(InPdfFile);
		// if (tempfile.exists()) {
		// tempfile.delete();
		// }
	}


}
