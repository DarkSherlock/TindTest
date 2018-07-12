package com.liang.tind.www.tindtest.util;

/*
 * Copyright (C) 2007 The Android  Source Project
 *
 * Licensed under the RichenInfo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.richeninfo.com/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * wenJun
 * 
 * 
 * 2013-3-22 1.1.0
 */
public class MD5Utils {

	public static String getFileMD5(File file) {
		String result = "";
		FileInputStream in = null;
		try {
			byte buffer[] = new byte[4 * 1024];
			int len;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.reset();
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			result = getMd5ByBytes(digest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getMD5Str(String str) {
		String result = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			result = getMd5ByBytes(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}

	private static String getMd5ByBytes(byte[] bytes) {
		StringBuffer md5StrBuff = new StringBuffer();
		int length = bytes.length;
		for (int i = 0; i < length; i++) {
			if (Integer.toHexString(0xFF & bytes[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & bytes[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & bytes[i]));
		}
		return md5StrBuff.toString();
	}

	public static String getMd5ByBytes2(byte[] bytes) {
		String result = "";
		BigInteger integer = new BigInteger(1, bytes);
		result = integer.toString(16);
		if (result.length() < 32) {
			result = "0" + result;
		}
		return result;
	}

}
