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
	public static final String DEBUG_MODE = "Debug.Mode"; // Added Debug Mode
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
	public static final String PRODUCT_AVAILABILITY_MESSAGE = "This product is available for sale only to US entities at this time.";

	public static final String PRODUCT_TRAINING_REQUIRED = "Product training is required of all writing agents prior to solicitation of this product but we do not have a certificate of completion recorded in our system for this agent. ";

	public static final String E_AND_O_REQUIRED = "Errors and Omissions insurance is required by all writing agents prior to solicitation of this product but the information in our records is expired or invalid. ";

	public static final String AML_TRAINING_REQUIRED = "AML training is required of all writing agents prior to solicitation of this product but we do not  yet have a record of current AML training for this agent.";

	public static final String AGENT_PRODUCT_TYPES = "What Types of Products Can I Sell You?";

	public static final String AGENT_PRODUCT_SALES_DISCLOSURE = "I am licensed to sell annuities to You in accordance with state law. If I recommend that You buy an annuity, it means I believe that it effectively meets Your financial situation, insurance needs, and financial objectives. Other financial products, such as life insurance or stocks, bonds and mutual funds, also may meet Your needs.";

	public static final String AGENT_PAYMENT_DISCLOSURE_TITLE = "How I’m Paid for My Work:";

	public static final String AGENT_PAYMENT_DISCLOSURE = "It’s important for You to understand how I’m paid for my work. Depending on the particular annuity You purchase, I may be paid a commission or a fee. Commissions are generally paid to Me by the insurance company while fees are generally paid to Me by the consumer. If You have questions about how I’m paid, please ask Me. ";

	public static final String COMMISSION = "Commission, which is usually paid by the insurance company or other sources. If other sources, describe:";
	public static final String FEES = "Fees (such as a fixed amount, an hourly rate, or a percentage of your payment), which are usually paid directly by the customer.";
	public static final String ANNUITIES = "Annuities from Two or More Insurers although I primarily sell annuities from:";
    public static final String PRE_EXISTING_RELATIONSHIP = "The Proposed Owner has a pre-existing relationship with agent / producer of";
    public static final String OTHER = "Other";


}


