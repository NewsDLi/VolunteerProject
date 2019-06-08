package com.volunteer.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtil {

	/**
	 * 加解密使用的key
	 */
	public static final String KEY = "qweert";

	/**
	 * 加密
	 * 
	 * @param content 需要加密的内容
	 * @param password  加密密码
	 * @return
	 */ 
	public static String AESEncode(String password, String content) { 
	        try {            
	                KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
	                kgen.init(128, new SecureRandom(password.getBytes())); 
	                SecretKey secretKey = kgen.generateKey(); 
	                byte[] enCodeFormat = secretKey.getEncoded(); 
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES"); 
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码器 
	                byte[] byteContent = content.getBytes("utf-8"); 
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
	                byte[] result = cipher.doFinal(byteContent); 
	                return Base64.getEncoder().encodeToString(result); // 加密 
	        } catch (NoSuchAlgorithmException e) { 
	                e.printStackTrace(); 
	        } catch (NoSuchPaddingException e) { 
	                e.printStackTrace(); 
	        } catch (InvalidKeyException e) { 
	                e.printStackTrace(); 
	        } catch (UnsupportedEncodingException e) { 
	                e.printStackTrace(); 
	        } catch (IllegalBlockSizeException e) { 
	                e.printStackTrace(); 
	        } catch (BadPaddingException e) { 
	                e.printStackTrace(); 
	        } 
	        return null; 
	} 

	/**解密
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 */ 
	public static String AESDncode(String password, String content) { 
	        try { 
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
	                 kgen.init(128, new SecureRandom(password.getBytes())); 
	                 SecretKey secretKey = kgen.generateKey(); 
	                 byte[] enCodeFormat = secretKey.getEncoded(); 
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");             
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器 
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化 
	                byte[] result = cipher.doFinal(Base64.getDecoder().decode(content)); 
	                return new String(result); // 加密 
	        } catch (NoSuchAlgorithmException e) { 
	                e.printStackTrace(); 
	        } catch (NoSuchPaddingException e) { 
	                e.printStackTrace(); 
	        } catch (InvalidKeyException e) { 
	                e.printStackTrace(); 
	        } catch (IllegalBlockSizeException e) { 
	                e.printStackTrace(); 
	        } catch (BadPaddingException e) { 
	                e.printStackTrace(); 
	        } 
	        return null; 
	} 
	
	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */ 
	public static String parseByte2HexStr(byte buf[]) { 
	        StringBuffer sb = new StringBuffer(); 
	        for (int i = 0; i < buf.length; i++) { 
	                String hex = Integer.toHexString(buf[i] & 0xFF); 
	                if (hex.length() == 1) { 
	                        hex = '0' + hex; 
	                } 
	                sb.append(hex.toUpperCase()); 
	        } 
	        return sb.toString(); 
	}
	
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */ 
	public static byte[] parseHexStr2Byte(String hexStr) { 
	        if (hexStr.length() < 1) 
	                return null; 
	        byte[] result = new byte[hexStr.length()/2]; 
	        for (int i = 0;i< hexStr.length()/2; i++) { 
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16); 
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16); 
	                result[i] = (byte) (high * 16 + low); 
	        } 
	        return result; 
	}

	public static void main(String[] args) {
		String content = "140522199606012741"; 
		String password = "qweert"; 
		//加密 
		System.out.println("加密前：" + content); 
		String encryptResult = AESEncode(password, content); 
		
		Base64.getDecoder().decode(encryptResult);
		//解密 
		String decryptResult = AESDncode(password,encryptResult); 
		System.out.println("解密后：" + decryptResult); 
	}
	
}
