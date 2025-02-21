package com.constants;

import java.nio.file.Paths;

public class AppConstants {

	public static final String BROWSER_CHROMIUM = "chromium";
	public static final String BROWSER_CHROME = "chrome";
	public static final String BROWSER_FIREFOX = "firefox";
	public static final String BROWSER_WEBKIT = "webkit";
	public static final String AUTH_STATE_FILE_PATH = "authState.json";
	public static final String VIDEO_DIR_KEY = "videoDir";
	public static final String ALLURE_RESULTS_PATH = "allure-results";

	public static final String BASE_URL = "baseURL";
    public static final String BROWSERS = "browsers";
    public static final String HEADLESS_MODE = "headlessMode";
    public static final String WAIT_TIMEOUT = "wait.timeout.millis";
    public static final String DEBUG_MODE = "Debug.Mode";  // Added Debug Mode
    public static final String LOGIN_ENDPOINT = "login.endpoint";

    // Email Configuration Keys
    public static final String EMAIL_FROM = "email.from";
    public static final String EMAIL_TO = "email.to";
    public static final String EMAIL_SUBJECT = "email.subject";
    public static final String EMAIL_BODY = "email.body";

    // YAML Keys (Environment Configuration)
    public static final String LOGIN_EMAIL = "login.email";
    public static final String LOGIN_PASSWORD = "login.password";

    // SMTP Configuration Keys
    public static final String SMTP_SERVER = "smtp.server";
    public static final String SMTP_PORT = "smtp.port";
    public static final String SMTP_USERNAME = "smtp.username";
    public static final String SMTP_PASSWORD = "smtp.password";
    
    
    
    
	// URL End points
	public static final String LOGIN_BYPASS_URL_PATTERN = "clientPipeline";

	// Excel
	public static final String EXCEL_FILE_PATH = Paths.get("src", "test", "resources", "Input_data", "testData.xls")
			.toString();
	public static final String SHEET_NAME = "E-App Data's";
	public static final String VALIDATION_SHEET_NAME = "Validations";
    public static final String OPTIONS_VALIDATION = "Options Validation";
    public static final String FILE_DATA_PATH = "src/test/resources/file_data/";


	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String US_ONLY_OWNER_MESSAGE = "This product requires the Owner to be a US Citizen or Resident Alien.";
	public static final String US_ONLY_ANNUITANT_MESSAGE = "This product requires the Annuitant to be a US Citizen or Resident Alien.";
	public static final String US_ONLY_JOINT_ANNUITANT_MESSAGE = "This product requires the Joint Annuitant to be a US Citizen or Resident Alien.";
	public static final String FUNDING_ERROR_MESSAGE = "You have not completed this page. The Total Initial Payment must equal the Total Premium value in order to complete the application.";
	public static final String BENEFICIARIES_ERROR_MESSAGE = "You have not completed this page. Total Percentage for all Primary Beneficiaries must equal 100% and Contingent Beneficiaries must also sum to 100% if specified.";

}
