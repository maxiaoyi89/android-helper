package com.ssyijiu.ahelper;

import android.database.Cursor;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * 流操作工具类
 * @author lxm
 *
 */
public class IOUtil {
	/** 关闭流 */
	public static boolean close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException ignored) {
			}
		}
		return true;
	}

	/** 关闭流Cursor */
	public static boolean close(Cursor cursor) {
		if (cursor != null) {
				cursor.close();
		}
		return true;
	}


	/**
	 * 流转字节数组, 异常返回 null
     */
	public static byte[] stream2Bytes(InputStream in) {
		if (!(in instanceof BufferedInputStream)) {
			in = new BufferedInputStream(in);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len;
		byte[] buf = new byte[1024];

		try {
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(in);
			close(out);
		}
		return null;
	}

	/**
	 * 流转字符串, 异常返回 ""
	 */
	public static String stream2String(InputStream in,String charset) {
		if(TextUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}

		if (!(in instanceof BufferedInputStream)) {
			in = new BufferedInputStream(in);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len;
		byte[] buf = new byte[1024];
		
		try {
			while((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			return out.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(in);
			close(out);
		}
		return "";
	}

	/**
	 * 流转字符串, 异常返回 ""
	 */
	public static String stream2String(InputStream in) throws IOException {
		return stream2String(in, "UTF-8");
	}

}
