package com.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {

	 private static final String ALGORITHM = "AES";

	    // Method to generate a secret key (you can save this key securely)
	    public static SecretKey generateKey() throws Exception {
	        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
	        keyGen.init(128); // AES supports 128, 192, or 256-bit keys
	        return keyGen.generateKey();
	    }

	    // Method to encrypt data
	    public static String encrypt(String data, SecretKey key) throws Exception {
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    // Save the key as a Base64-encoded string for reuse
	    public static String getEncodedKey(SecretKey key) {
	        return Base64.getEncoder().encodeToString(key.getEncoded());
	    }
	    
	public static String vector = "-JaNdRgUkXp2s5v8";
	public static String secretkey = "(G+KbPeSgVkYp3s6v9y!B&E)H@McQfTj";

	public static String CHARSET = "UTF-8";
	public static String ALGORITHM_NAME = "AES";

	public static String PADDING = "AES/CBC/PKCS5PADDING";

	public static String encryptData(String plainText) {
		if (plainText == null || plainText.trim().isEmpty()) {
			throw new IllegalArgumentException("The plain text cannot be null or empty.");
		}
		try {
			IvParameterSpec ivParameterSpec;
			SecretKeySpec secretKeySpec;
				ivParameterSpec = new IvParameterSpec(vector.getBytes(CHARSET));
				secretKeySpec = new SecretKeySpec(secretkey.getBytes(CHARSET), ALGORITHM_NAME);
			Cipher cipher = Cipher.getInstance(PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encrypted = cipher.doFinal(plainText.getBytes(CHARSET));

// Use Java's built-in Base64 encoder
			return Base64.getEncoder().encodeToString(encrypted);

		} catch (Exception e) {
			throw new RuntimeException("Failed to encrypt the given plain text.", e);
		}
	}
}
