package com.dbdbdeep.modoostar.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/****************************************************************
 * 암호화 처리 관련 유틸리티<br/>
 * history: 2011-03-01 / Create
 * 
 * @author 이광호 (hoya4232@gmail.com)
 * @version 1.0.0
 ****************************************************************/
public class EncryptHelper {
	private static byte[] mBase64EncMap, mBase64DecMap;

	static {
		byte[] base64Map = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D',
				(byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I',
				(byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
				(byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S',
				(byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X',
				(byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c',
				(byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h',
				(byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm',
				(byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r',
				(byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w',
				(byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1',
				(byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
				(byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/' };
		mBase64EncMap = base64Map;
		mBase64DecMap = new byte[128];
		for (int i = 0; i < mBase64EncMap.length; i++)
			mBase64DecMap[mBase64EncMap[i]] = (byte) i;
	}

	/*************************************************************
	 * MD5 암호화 처리
	 * 
	 * @param s
	 *            - 암호화 할 문자열
	 *************************************************************/
	public static String getMD5(String s) {
		MessageDigest m = null;
		String hash = null;

		try {
			/*
			 * m = MessageDigest.getInstance("MD5"); m.update(s.getBytes(), 0,
			 * s.length()); hash = new BigInteger(1, m.digest()).toString(16);
			 */

			m = MessageDigest.getInstance("MD5");

			String eip;
			byte[] bip;
			String temp = "";
			String tst = s;

			bip = m.digest(tst.getBytes());
			for (int i = 0; i < bip.length; i++) {
				eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
				if (eip.length() < 2)
					eip = "0" + eip;
				temp = temp + eip;
			}
			hash = temp;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash;
	}

	/**
	 * Encodes the given byte[] using the Base64-encoding, as specified in
	 * RFC-2045 (Section 6.8).
	 * 
	 * @param aData
	 *            the data to be encoded
	 * @return the Base64-encoded <var>aData</var>
	 * @exception IllegalArgumentException
	 *                if NULL or empty array is passed
	 */
	public static String base64Encode(String szData) {
		if ((szData == null) || (szData.length() == 0))
			throw new IllegalArgumentException(
					"Can not encode NULL or empty byte array.");

		byte[] aData = szData.getBytes();

		byte encodedBuf[] = new byte[((aData.length + 2) / 3) * 4];

		// 3-byte to 4-byte conversion
		int srcIndex, destIndex;
		for (srcIndex = 0, destIndex = 0; srcIndex < aData.length - 2; srcIndex += 3) {
			encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex] >>> 2) & 077];
			encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] >>> 4)
					& 017 | (aData[srcIndex] << 4) & 077];
			encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex + 2] >>> 6)
					& 003 | (aData[srcIndex + 1] << 2) & 077];
			encodedBuf[destIndex++] = mBase64EncMap[aData[srcIndex + 2] & 077];
		}

		// Convert the last 1 or 2 bytes
		if (srcIndex < aData.length) {
			encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex] >>> 2) & 077];
			if (srcIndex < aData.length - 1) {
				encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] >>> 4)
						& 017 | (aData[srcIndex] << 4) & 077];
				encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex + 1] << 2) & 077];
			} else {
				encodedBuf[destIndex++] = mBase64EncMap[(aData[srcIndex] << 4) & 077];
			}
		}

		// Add padding to the end of encoded data
		while (destIndex < encodedBuf.length) {
			encodedBuf[destIndex] = (byte) '=';
			destIndex++;
		}

		String result = new String(encodedBuf);
		return result;
	}

	/**
	 * Decodes the given Base64-encoded data, as specified in RFC-2045 (Section
	 * 6.8).
	 * 
	 * @param aData
	 *            the Base64-encoded aData.
	 * @return the decoded <var>aData</var>.
	 * @exception IllegalArgumentException
	 *                if NULL or empty data is passed
	 */
	public static String base64Decode(String aData) {
		if ((aData == null) || (aData.length() == 0))
			throw new IllegalArgumentException(
					"Can not decode NULL or empty string.");

		byte[] data = aData.getBytes();

		// Skip padding from the end of encoded data
		int tail = data.length;
		while (data[tail - 1] == '=')
			tail--;

		byte decodedBuf[] = new byte[tail - data.length / 4];

		// ASCII-printable to 0-63 conversion
		for (int i = 0; i < data.length; i++)
			data[i] = mBase64DecMap[data[i]];

		// 4-byte to 3-byte conversion
		int srcIndex, destIndex;
		for (srcIndex = 0, destIndex = 0; destIndex < decodedBuf.length - 2; srcIndex += 4, destIndex += 3) {
			decodedBuf[destIndex] = (byte) (((data[srcIndex] << 2) & 255) | ((data[srcIndex + 1] >>> 4) & 003));
			decodedBuf[destIndex + 1] = (byte) (((data[srcIndex + 1] << 4) & 255) | ((data[srcIndex + 2] >>> 2) & 017));
			decodedBuf[destIndex + 2] = (byte) (((data[srcIndex + 2] << 6) & 255) | (data[srcIndex + 3] & 077));
		}

		// Handle last 1 or 2 bytes
		if (destIndex < decodedBuf.length)
			decodedBuf[destIndex] = (byte) (((data[srcIndex] << 2) & 255) | ((data[srcIndex + 1] >>> 4) & 003));
		if (++destIndex < decodedBuf.length)
			decodedBuf[destIndex] = (byte) (((data[srcIndex + 1] << 4) & 255) | ((data[srcIndex + 2] >>> 2) & 017));

		return new String(decodedBuf);
	}
}
