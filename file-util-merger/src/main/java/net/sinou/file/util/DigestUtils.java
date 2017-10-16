package net.sinou.file.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** Centralise a few utilitary methods to work with file digest */
public class DigestUtils {

	public static byte[] getMD5Digest(File file) {
		byte[] bytes = new byte[2048];
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			int numBytes;
			while ((numBytes = bis.read(bytes)) != -1) {
				md.update(bytes, 0, numBytes);
			}
			return md.digest();
		} catch (IOException | NoSuchAlgorithmException e) {
			throw new RuntimeException("Couldn't create digest for " + file.getAbsolutePath(), e);
		}
	}

	public static char[] getMd5DigestAsHexString(File file) {
		byte[] bytes = getMD5Digest(file);
		return getHexChars(bytes);
	}

	/**
	 * A faster way to translate the byte array, thanks to
	 * http://www.rgagnon.com/javadetails/java-0596.html
	 */
	static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
			(byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e',
			(byte) 'f' };

	private static char[] getHexChars(byte[] raw) {
		try {
			byte[] hex = new byte[2 * raw.length];
			int index = 0;

			for (byte b : raw) {
				int v = b & 0xFF;
				hex[index++] = HEX_CHAR_TABLE[v >>> 4];
				hex[index++] = HEX_CHAR_TABLE[v & 0xF];
			}
			String res = new String(hex, "ASCII");
			System.out.println("Digest: " + res);
			return res.toCharArray();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Could not transcipt bytes md5 to corresponding hex String", e);
		}
	}

	private DigestUtils() {
	}
}
