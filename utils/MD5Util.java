package com.ssyijiu.ahelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ssyijiu on 2016/8/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class MD5Util {

	private final static char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	/**
	 * MD5加密
	 * @param data 明文
	 * @return 密文
	 */
	public static String getMD5(String data) {
        return getMD5(data.getBytes());
    }
	
	/**
	 * MD5加密
	 * @param data 明文数组
	 * @return 密文
	 */
	public static String getMD5(byte[] data) {
	     
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md.update(data);
            // 获得密文
            byte[] digest = md.digest();
            // 把密文转换成十六进制的字符串形式
            return new String(bytes2Hex(digest));
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
		return "";
    }
	
	
	/**
	 * 非标准MD5加密，防止大数据库暴力破解
	 * @param data 明文
	 * @return 密文
	 */
	public static String getMD5NoStandard(String data) {
        return getMD5NoStandard(data.getBytes());
    }
	
	/**
	 * 非标准MD5加密，防止大数据库暴力破解
	 * @param data 明文数组
	 * @return 密文
	 */
	public static String getMD5NoStandard(byte[] data) {
	     
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md.update(data);
            // 获得密文
            byte[] digest = md.digest();
            // 把密文转换成十六进制的字符串形式
            return new String(bytes2HexNoStandard(digest));
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  
		return "";
    }
    
    /**
     * 获取一个文件的MD5校验码
     * @param path 文件路径
     * @return MD5检验码
     */
    public static String getMD5File(String path) {

		FileInputStream in = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			in = new FileInputStream(path);

			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = in.read(buffer)) != -1) {
				md.update(buffer, 0, len);
			}

			byte digest[] = md.digest();

	        return bytes2Hex(digest); 

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		return "";
	}
    
    
    /**
     * 一个byte转为2个hex字符
     */
    private static String bytes2Hex(byte[] src) {
        char[] res = new char[src.length * 2];
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[src[i] & 0x0f];
        }
        return new String(res);
    }
    
    /**
     * 一个byte转为2个hex字符，非标准
     */
    private static String bytes2HexNoStandard(byte[] src) {
    	
        char[] res = new char[src.length * 2];
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[(src[i]>>1) >>> 4 & 0x0f];
            res[j++] = hexDigits[(src[i]<<1) & 0x0f];
        }
        return new String(res);
    }
}
