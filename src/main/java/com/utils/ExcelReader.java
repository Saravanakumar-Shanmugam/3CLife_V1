package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static Map<String, Map<String, String>> getData(String excelFilePath, String sheetName)
            throws InvalidFormatException, IOException {
        Sheet sheet = getSheetByName(excelFilePath, sheetName);
        return readSheet(sheet);
    }

    private static Sheet getSheetByName(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {
        return getWorkBook(excelFilePath).getSheet(sheetName);
    }

    private static Workbook getWorkBook(String excelFilePath) throws IOException, InvalidFormatException {
        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
        if (excelFilePath.endsWith(".xls")) {
            return new HSSFWorkbook(fileInputStream);
        } else if (excelFilePath.endsWith(".xlsx")) {
            return new XSSFWorkbook(fileInputStream);
        } else {
            throw new InvalidFormatException("The specified file is not Excel format");
        }
    }

    private static Map<String, Map<String, String>> readSheet(Sheet sheet) {
        Map<String, Map<String, String>> data = new LinkedHashMap<>();
        int totalRows = sheet.getPhysicalNumberOfRows();

        for (int currentRow = 0; currentRow < totalRows; currentRow++) {
            Row row = sheet.getRow(currentRow);
            if (row == null) continue;

            Cell keyCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); // First column as keys
            String key = getCellValueAsString(keyCell);

            if (key.isEmpty()) continue; // Skip rows without a valid key

            for (int currentColumn = 1; currentColumn < row.getLastCellNum(); currentColumn++) {
                Cell testCaseCell = sheet.getRow(0).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String testCaseKey = getCellValueAsString(testCaseCell); // First row as column headers (TestCase-No)

                Cell valueCell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String value = getCellValueAsString(valueCell);

                data.computeIfAbsent(testCaseKey, k -> new LinkedHashMap<>()).put(key, value);
            }
        }

        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("MM/dd/yyyy").format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
