package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.crypto.SecretKey;

import com.constants.AppConstants;
import com.utils.Decrypter;


public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    // Get property by key with a default value
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Get property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Get the base URL
    public static String getBaseURL() {
        String baseURL = getProperty(AppConstants.BASE_URL_KEY);
        if (baseURL == null || baseURL.isEmpty()) {
            throw new RuntimeException("baseURL is not defined in the configuration file.");
        }
        return baseURL;
    }

    // Get headless mode setting
    public static boolean isHeadlessMode() {
        String isHeadlessMode = getProperty(AppConstants.HEADLESS_MODE_KEY);
        return isHeadlessMode != null ? Boolean.parseBoolean(isHeadlessMode) : false; 
    }

    // Get list of browsers
    public static List<String> getBrowsers() {
        String browsers = getProperty(AppConstants.BROWSERS_KEY);
        return List.of(browsers.split(","));
    }

    // Get Debug mode setting
    public static Boolean getDebugMode() {
        String debugMode = getProperty(AppConstants.DEBUG_MODE_KEY);
        return debugMode != null ? Boolean.parseBoolean(debugMode) : false;
    }

    // Get the username
    public static String getUsername() {
        String username = getProperty(AppConstants.USERNAME_KEY);		  
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("Email id or UserName is not defined in the configuration file.");
        }
        return username;
    }

    // Get the login endpoint
    public static String getLoginEndpoint() {
        String endpoint = getProperty(AppConstants.LOGIN_ENDPOINT_KEY);
        if (endpoint == null || endpoint.isEmpty()) {
            throw new RuntimeException("Login endpoint is not defined in the configuration file.");
        }
        return endpoint;
    }

    // Get the password (decrypted)
    public static String getPassword() {
        String decryptedPassword = null;
        try {
            String password = getProperty(AppConstants.PASSWORD_KEY);
            SecretKey decodedKey = Decrypter.getKeyFromEncodedString(getProperty(AppConstants.SECRET_KEY_KEY));
            decryptedPassword = Decrypter.decrypt(password, decodedKey);
            if (decryptedPassword == null || decryptedPassword.isEmpty()) {
                throw new RuntimeException("Password is not defined or is invalid.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedPassword;
    }
    
    public static int getWaitTimeoutMillis() {
        return Integer.parseInt(properties.getProperty(AppConstants.WAIT_TIMEOUT_MILLIS_KEY, "30000")); 
    }
}
