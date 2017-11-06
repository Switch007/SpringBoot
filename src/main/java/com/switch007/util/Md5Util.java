package com.switch007.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public static String getMd5(String text) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] b = md5.digest(text.getBytes());
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
