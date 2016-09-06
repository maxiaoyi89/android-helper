package com.Multigold.CunJinBao.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流操作工具类
 * @author lxm
 *
 */
public class IOUtil {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
			}
		}
		return true;
	}
	
	/**
	 * 流转字符串, 异常返回 ""
	 */
	public static String stream2String(InputStream inputstream) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
				
		int len = -1;
		byte[] buf = new byte[1024];
		
		try {
			while((len=inputstream.read(buf))!=-1) {
				bos.write(buf, 0, len);
			}
			
			return bos.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(inputstream);
			close(bos);
		}
		return "";
	}
}
