package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.constants.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlConfigReader {
    private static Map<String, Object> yamlData;

    static {
        try (InputStream input = YamlConfigReader.class.getClassLoader().getResourceAsStream("environment.yaml")) {
            if (input == null) {
                throw new RuntimeException("Unable to find environment.yaml");
            }
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            yamlData = objectMapper.readValue(input, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment.yaml", e);
        }
    }

    public static String getProperty(String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> data = yamlData;

        for (int i = 0; i < keys.length - 1; i++) {
            data = (Map<String, Object>) data.get(keys[i]);
            if (data == null) {
                throw new RuntimeException("Key " + key + " not found in environment.yaml");
            }
        }
        Object value = data.get(keys[keys.length - 1]);
        if (value == null) {
            throw new RuntimeException("Key " + key + " not found in environment.yaml");
        }
        return value.toString();
    }

    public static String getLoginEmail() {
        return getProperty(AppConstants.LOGIN_EMAIL);
    }

    public static String getLoginPassword() {
        return getProperty(AppConstants.LOGIN_PASSWORD);
    }

    public static String getSmtpServer() {
        return getProperty(AppConstants.SMTP_SERVER);
    }

    public static int getSmtpPort() {
        return Integer.parseInt(getProperty(AppConstants.SMTP_PORT));
    }

    public static String getSmtpUsername() {
        return getProperty(AppConstants.SMTP_USERNAME);
    }

    public static String getSmtpPassword() {
        return getProperty(AppConstants.SMTP_PASSWORD);
    }
}
