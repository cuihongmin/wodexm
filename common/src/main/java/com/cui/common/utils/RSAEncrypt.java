package com.cui.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密解密
 * @author jada
 * @see
 */
public class RSAEncrypt {
	
	private static final String sKey = "2@j2&o(9";//加密方式，可以是任意字符
	
	/***
	 * <P>@Description: encryptDES加密</P> 
	 * @date  2016年6月16日 下午2:15:48
	 * @author xianlong@huaxiafinance.com
	 * @version V1.0
	 */
	public static String encryptDES(String encryptString) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(sKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		return new BASE64Encoder().encode(encryptedData);
	}

	/***
	 * <P>@Description: decryptDES解密</P> 
	 * @date  2016年6月16日 下午2:15:48
	 * @author xianlong@huaxiafinance.com
	 * @version V1.0
	 */
	public static String decryptDES(String decryptString) throws Exception {
		byte[] byteMi = new BASE64Decoder().decodeBuffer(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(sKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}
	
}