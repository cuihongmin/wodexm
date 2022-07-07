package com.cui.user.service.impl;
import com.cui.common.utils.BaseResult;
import com.cui.common.utils.UUIDUtils;
import com.cui.user.dao.CertificatesFilesMapper;
import com.cui.user.entity.CertificatesFiles;
import com.cui.user.service.CertificatesFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;


@Service
public class CertificatesFilesServiceImpl implements CertificatesFilesService {

	
	@Autowired
	private CertificatesFilesMapper certificatesFilesMapper;
	
	public BaseResult saveFiles(String file) {
		long startTime=System.currentTimeMillis();
		System.out.println("开始时间："+startTime);
		CertificatesFiles certificatesFiles = new CertificatesFiles();
		certificatesFiles.setId(UUIDUtils.getUUID());
		//certificatesFiles.setFileFlow(file);
		certificatesFiles.setModular(file);
		certificatesFilesMapper.insert(certificatesFiles);
		long endTime=System.currentTimeMillis();
		System.out.println("结束时间："+startTime);
		System.out.println("程序运行时间： "+(endTime - startTime)+"ms");
		return BaseResult.success(certificatesFiles.getId());
	}
	

	@Override
	public BaseResult getFileById(String id) {
		String file = certificatesFilesMapper.getFileById(id);
		return BaseResult.success(file);
	}



/**
* @Author cuihongmin
 *
* 证件图片批量上传
* @Param
* @return */
	@Override
	public BaseResult batchSaveFiles(List<String> file) {
		List<CertificatesFiles> list = new ArrayList<CertificatesFiles>();
		List<String> uuids = new ArrayList<String>();
		for (String string : file) {
			String uuid = UUIDUtils.getUUID();
			CertificatesFiles certificatesFiles = new CertificatesFiles();
			certificatesFiles.setId(uuid);
			//certificatesFiles.setFileFlow(string);
			certificatesFiles.setModular(string);
			list.add(certificatesFiles);
			uuids.add(uuid);
		}
		int count = certificatesFilesMapper.insertList(list);
		if(count>0){
			return BaseResult.success(uuids);
		}
		return BaseResult.fail("501", "上传失败");
	}

/**
* @Author cuihongmin
 *
* 证件图片上传2.0
* @Param
* @return */
	@Override
	public BaseResult savePicTures(String file) {
		CertificatesFiles certificatesFiles = new CertificatesFiles();
		String uuid = UUIDUtils.getUUID();
		certificatesFiles.setId(uuid);
		//certificatesFiles.setFileFlow(file);
		certificatesFiles.setModular(file);
		int count = certificatesFilesMapper.insert(certificatesFiles);
		if(count>0){
			return BaseResult.success(uuid);
		}
		return BaseResult.fail("501", "上传失败");
	}


	@Override
	public BaseResult getPicTureById(String id) {
		String file = certificatesFilesMapper.getPicTureById(id);
		return BaseResult.success(file);
	}


//	@Override
//	public BaseResult getList(Integer page, Integer rows) {
//		List<CertificatesFiles> list = certificatesFilesMapper.getList(page, rows);
//		for (CertificatesFiles certificatesFiles : list) {
//			BASE64Decoder decoder =new BASE64Decoder();
//			try {
//		        //解码过程，即将base64字符串转换成二进制流
//		        byte[] imageByte=decoder.decodeBuffer(certificatesFiles.getFileFlow());
//		        System.out.println("===="+imageByte);
//		        //生成图片路径和文件名
//		        // 自定义的文件名称
//	            String trueFileName=String.valueOf(System.currentTimeMillis());
//	            String fileType = ".jpg";
//	            String relative =  DateUtil.formatDate( new Date(), DateUtil.FORMAT.DATE_8CHAR);
//	             // 设置存放图片文件的路径
//	             String fileName = trueFileName+fileType;
//	             //String pathString ="/data/"+"/"+relative+"/";
//		         String pathString ="D://";
//	             File newFile =new File(pathString);
//	             if(!newFile.exists()) {
//	             	newFile.mkdirs();
//	             }
//		        OutputStream out =new FileOutputStream(pathString + fileName);
//		        out.write(imageByte);
//		        out.flush();
//		        out.close();
//		        //String data = "http://192.168.200.136"+"/"+relative+"/"+fileName;
//		        String data = "https://www.hm-w.com/pic-proxy"+"/"+relative+"/"+fileName;
//		        //String data = relative+"/"+fileName;
//		        certificatesFilesMapper.updateModularById(certificatesFiles.getId(), data);
//		        System.out.println("data"+data);
//		    } catch (IOException e) {
//		        // TODO Auto-generated catch block
//		    }
//		}
//		return BaseResult.success();
//	}
//
	
	 
	
	
}
