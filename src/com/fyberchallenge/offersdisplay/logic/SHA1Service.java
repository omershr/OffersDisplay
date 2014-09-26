package com.fyberchallenge.offersdisplay.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Service {

	public boolean authenticate(String message, String messageSignature) {
		return hash(message).equals(messageSignature);
	}

	public String hash(String input) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString().toLowerCase();
	}
}
