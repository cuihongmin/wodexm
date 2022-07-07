package com.cui.common.utils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * @project ldbao_web
 * @description:$<p>产生验证码工具类</p>$
 * @author yekai $<a href="mailto:yekairush@163.com">联系作者</a>$
 * @version $id:ImageVerifyCodeUtil.java,Revision:v1.0,Date:2015-1-12 上午09:47:30
 *          $
 */
public class VerifyCodeUtil {

	/**
	 * 验证码类型
	 */
	public static final int TYPE_NUM = 0;

	private VerifyCodeUtil() {
	}

	/**
	 * 产生验证码
	 * 
	 * @param type
	 * @param length
	 * @return
	 */
	public static String generateTextCode(int type, int length) {
		if (length <= 0) {
			return "";
		}
		StringBuffer verifyCode = new StringBuffer();
		int i = 0;
		Random random = new Random();
		switch (type) {
		case TYPE_NUM:
			while (i < length) {
				int t = random.nextInt(10);
				verifyCode.append(t);
				i++;
			}
		}
		return verifyCode.toString();
	}

	/**
	 * 生成随机颜色
	 */
	private static Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}

	/**
	 * 生成图片验证码
	 * 
	 * @param type
	 * @param length
	 * @param width
	 * @param height
	 * @param interLine
	 * @param randomLocation
	 * @param backColor
	 * @param foreColor
	 * @param lineColor
	 * @return
	 */
	public static BufferedImage generateImageCode(int type, int length,
			int width, int height, int interLine, boolean randomLocation,
			Color backColor, Color foreColor, Color lineColor) {
		String textCode = generateTextCode(type, length);
		return generateImageCode(textCode, width, height, interLine,
				randomLocation, backColor, foreColor, lineColor);
	}

	/**
	 * 
	 * 生成验证码图片
	 * 
	 * @param textCode
	 * @param width
	 * @param height
	 * @param interLine
	 * @param randomLocation
	 * @param backColor
	 * @param foreColor
	 * @param lineColor
	 * @return
	 */
	public static BufferedImage generateImageCode(String textCode, int width,
			int height, int interLine, boolean randomLocation, Color backColor,
			Color foreColor, Color lineColor) {
		// 创建内存图像
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(null == backColor ? generateRandomColor() : backColor);
		graphics.fillRect(0, 0, width, height);
		// 画干扰线
		Random random = new Random();
		if (interLine > 0) {
			int x = 0, y = 0, x1 = width, y1 = 0;
			for (int i = 0; i < interLine; i++) {
				graphics.setColor(null == lineColor ? generateRandomColor()
						: lineColor);
				y = random.nextInt(height);
				y1 = random.nextInt(height);
				graphics.drawLine(x, y, x1, y1);
			}
		}
		// 设定字体
		int fsize = (int) (height * 0.8);
		int fx = height - fsize;
		int fy = fsize;
		graphics.setFont(new Font("Default", Font.PLAIN, fsize));
		for (int i = 0; i < textCode.length(); i++) {
			fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height)
					: fy;
			graphics.setColor(null == foreColor ? generateRandomColor()
					: foreColor);
			graphics.drawString(textCode.charAt(i) + "", fx, fy);
			fx += fsize * 0.9;
		}
		graphics.dispose();
		return bufferedImage;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 生成验证码
		String code = com.cui.common.utils.VerifyCodeUtil.generateTextCode(0, 4);
		System.err.println(code);
		// 生成随机颜色
		Color color = com.cui.common.utils.VerifyCodeUtil.generateRandomColor();
		System.out.println(color);
		// 生成图片验证码
		BufferedImage bufferedImage = com.cui.common.utils.VerifyCodeUtil.generateImageCode(1, 4,
				100, 100, 50, true,
				Color.red, Color.blue, Color.darkGray);
		System.out.println(bufferedImage);
		// 生成验证码图片
		String A = "SFSLL";
		BufferedImage bufferedImage1 = com.cui.common.utils.VerifyCodeUtil.generateImageCode(A, 100,
				100, 50, true, Color.red, Color.blue, Color.darkGray);
		System.out.println(bufferedImage1);
	}

}
