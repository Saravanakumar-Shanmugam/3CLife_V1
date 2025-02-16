package com.utils;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Properties;

import org.json.JSONObject;

import com.base.BaseAction;
import com.config.ConfigReader;
import com.config.PlaywrightConfig;
import com.constants.AppConstants;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;

import io.qameta.allure.Allure;

public class AllureUtils {

    public static void logStep(String stepDescription) {
        Allure.step(stepDescription);
    }

    public static void attachScreenshot(Page page) {
        try {
            Path screenshotPath = Paths.get("screenshots",
                    BaseAction.getActiveTestMethod() + "-" + page.context().browser().browserType().name() + ".png");
            Files.createDirectories(screenshotPath.getParent());
            byte[] screenshotBytes = page.screenshot();
            Files.write(screenshotPath, screenshotBytes);
            try (InputStream screenshotStream = Files.newInputStream(screenshotPath)) {
                Allure.addAttachment("Screenshot: " + BaseAction.getActiveTestMethod() + "-"
                        + page.context().browser().browserType().name(), "image/png", screenshotStream, ".png");
            }
        } catch (Exception e) {
            System.err.println("Error attaching screenshot: " + e.getMessage());
        }
    }

    public static void attachVideo(Page page) {
        try {
            if (page.video() != null) {
                if (ConfigReader.getDebugMode()) {
                    PlaywrightConfig.getContext().tracing().stop(
                            new Tracing.StopOptions().setPath(Paths.get("trace-" + BaseAction.getActiveTestMethod()
                                    + "-" + page.context().browser().browserType().name() + ".zip")));
                }
                if (ConfigReader.isHeadlessMode() || ConfigReader.getDebugMode()) {
                    PlaywrightConfig.getContext().close();
                
                    Path videoPath = page.video().path();
                    Path targetVideoPath = Paths.get("videos", BaseAction.getActiveTestMethod() + "-"
                            + page.context().browser().browserType().name() + ".webm");
                    Files.move(videoPath, targetVideoPath, StandardCopyOption.REPLACE_EXISTING);
                    byte[] videoBytes = Files.readAllBytes(targetVideoPath);
                    Allure.addAttachment(
                            "Video: " + BaseAction.getActiveTestMethod() + "-"
                                    + page.context().browser().browserType().name(),
                            "video/webm", new ByteArrayInputStream(videoBytes), ".webm");
                }
            }
        } catch (Exception e) {
            System.err.println("Error capturing video: " + e.getMessage());
        }
    }

    public static void addTestMetadata(String key, String value) {
        Allure.label(key, value);
    }

    public static void attachEnvironmentInfo() {
        try {
            Path resultsDir = Paths.get(AppConstants.ALLURE_RESULTS_PATH);

            if (!Files.exists(resultsDir)) {
                Files.createDirectories(resultsDir);
            }

            Path environmentPath = Paths.get(AppConstants.ALLURE_RESULTS_PATH, "environment.properties");

            Properties props = new Properties();
            props.put("OS", System.getProperty("os.name"));
            props.put("Java Version", System.getProperty("java.version"));
            props.put("User", System.getProperty("user.name"));
            props.put("Browser", String.join(", ", ConfigReader.getBrowsers()));

            try (FileWriter writer = new FileWriter(environmentPath.toFile())) {
                props.store(writer, "Environment Info");
            }
        } catch (IOException e) {
            System.err.println("Error attaching environment info: " + e.getMessage());
        }
    }

    public static void addExecutorsInfo() {
        try {
            Path resultsDir = Paths.get(AppConstants.ALLURE_RESULTS_PATH);

            // Ensure the allure-results directory exists
            if (!Files.exists(resultsDir)) {
                Files.createDirectories(resultsDir);
            }

            Path executorsPath = Paths.get(AppConstants.ALLURE_RESULTS_PATH, "executor.json");

            // Automatically fetch details or use fallbacks
            String name = Optional.ofNullable(System.getenv("EXECUTOR_NAME")).orElse("Default CI");
            String type = Optional.ofNullable(System.getenv("EXECUTOR_TYPE")).orElse("Local");
            String url = Optional.ofNullable(System.getenv("EXECUTOR_URL")).orElse("http://localhost");
            String buildName = Optional.ofNullable(System.getenv("BUILD_NAME")).orElse("Default Build");
            String buildUrl = Optional.ofNullable(System.getenv("BUILD_URL")).orElse("http://localhost/build");
            String reportUrl = Optional.ofNullable(System.getenv("REPORT_URL")).orElse("http://localhost/report");

            // Create JSON for executors
            JSONObject executors = new JSONObject();
            executors.put("name", name);
            executors.put("type", type);
            executors.put("url", url);
            executors.put("buildName", buildName);
            executors.put("buildUrl", buildUrl);
            executors.put("reportUrl", reportUrl);

            // Write the JSON file
            try (FileWriter writer = new FileWriter(executorsPath.toFile())) {
                writer.write(executors.toString(4)); // Pretty print JSON
            }

            System.out.println("Executors file created at: " + executorsPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error creating executors.json: " + e.getMessage());
        }
    }
}
