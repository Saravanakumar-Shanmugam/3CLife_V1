package com.actions;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.constants.AppConstants;
import com.utils.ExcelReader;

public class ExcelTestDataUtil {
	private static Map<String, Map<String, String>> data;
	private static Map<String, Map<String, String>> validationData;
	private static Map<String, Map<String, String>> optionsData;

	static {
		loadData();
	}

	private static void loadData() {
		try {
			data = ExcelReader.getData(AppConstants.EXCEL_FILE_PATH, AppConstants.SHEET_NAME);
			validationData = ExcelReader.getData(AppConstants.EXCEL_FILE_PATH, AppConstants.VALIDATION_SHEET_NAME);
			optionsData = ExcelReader.getData(AppConstants.EXCEL_FILE_PATH, AppConstants.OPTIONS_VALIDATION);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, Map<String, String>> getData() {
		return data;
	}

	public static Map<String, Map<String, String>> getValidationData() {
		return validationData;
	}
	
	public static Map<String, Map<String, String>> getOptionsData() {
		return optionsData;
	}
}
