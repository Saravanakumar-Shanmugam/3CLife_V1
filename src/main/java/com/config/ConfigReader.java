package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.constants.AppConstants;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    private static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static boolean isDebugMode() {
        return Boolean.parseBoolean(getProperty(AppConstants.DEBUG_MODE));
    }

    public static String getBaseURL() {
        return getProperty(AppConstants.BASE_URL);
    }

    public static List<String> getBrowsers() {
        String browsers = getProperty(AppConstants.BROWSERS);
        return browsers != null ? List.of(browsers.split(",")) : List.of();
    }


    public static boolean isHeadlessMode() {
        return Boolean.parseBoolean(getProperty(AppConstants.HEADLESS_MODE));
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty(AppConstants.WAIT_TIMEOUT));
    }

    public static String getEmailFrom() {
        return getProperty(AppConstants.EMAIL_FROM);
    }

    public static String getEmailTo() {
        return getProperty(AppConstants.EMAIL_TO);
    }

    public static String getEmailSubject() {
        return getProperty(AppConstants.EMAIL_SUBJECT);
    }

    public static String getEmailBody() {
        return getProperty(AppConstants.EMAIL_BODY);
    }
}
