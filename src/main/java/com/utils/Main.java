package com.utils;

import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) {
        try {
            // Original data (e.g., Gmail password)
            String gmailPassword = "$@ravan123";

            // Generate a secret key
            SecretKey secretKey = Encrypter.generateKey();
            String encodedKey = Encrypter.getEncodedKey(secretKey);

            System.out.println("Generated Secret Key (Base64): " + encodedKey);

            // Encrypt the password
            String encryptedPassword = Encrypter.encrypt(gmailPassword, secretKey);
            System.out.println("Encrypted Password: " + encryptedPassword);

            // Decrypt the password
            SecretKey decodedKey = Decrypter.getKeyFromEncodedString(encodedKey);
            String decryptedPassword = Decrypter.decrypt(encryptedPassword, decodedKey);
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
