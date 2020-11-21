package co.events.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
	
	public static String hash(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = digest.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hash = no.toString(16);
			while (hash.length() < 32) {
				hash = "0" + hash;
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
