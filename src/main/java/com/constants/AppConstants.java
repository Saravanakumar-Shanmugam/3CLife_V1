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

	// Configuration Keys
	public static final String BASE_URL_KEY = "baseURL";
	public static final String BROWSERS_KEY = "browsers";
	public static final String HEADLESS_MODE_KEY = "headlessMode";
	public static final String USERNAME_KEY = "emailid";
	public static final String PASSWORD_KEY = "password";
	public static final String SECRET_KEY_KEY = "SecretKey";
	public static final String LOGIN_ENDPOINT_KEY = "login.endpoint";
	public static final String DEBUG_MODE_KEY = "Debug.Mode";
	public static final String WAIT_TIMEOUT_MILLIS_KEY = "wait.timeout.millis"; 


	// URL End points
	public static final String LOGIN_BYPASS_URL_PATTERN = "clientPipeline";

	// Excel
	public static final String EXCEL_FILE_PATH = Paths.get("src", "test", "resources", "Input_data", "ClientPopup.xls")
			.toString();
	public static final String SHEET_NAME = "Clientpopup";
	public static final String VALIDATION_SHEET_NAME = "Validations";
    public static final String OPTIONS_VALIDATION = "Options Validation";


	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String US_ONLY_OWNER_MESSAGE = "This product requires the Owner to be a US Citizen or Resident Alien.";
	public static final String US_ONLY_ANNUITANT_MESSAGE = "This product requires the Annuitant to be a US Citizen or Resident Alien.";
	public static final String US_ONLY_JOINT_ANNUITANT_MESSAGE = "This product requires the Joint Annuitant to be a US Citizen or Resident Alien.";
	public static final String FUNDING_ERROR_MESSAGE = "You have not completed this page. The Total Initial Payment must equal the Total Premium value in order to complete the application.";
	public static final String BENEFICIARIES_ERROR_MESSAGE = "You have not completed this page. Total Percentage for all Primary Beneficiaries must equal 100% and Contingent Beneficiaries must also sum to 100% if specified.";

}
