package com.Automation.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        try {
        	FileInputStream fis = new FileInputStream(filePath);
        	XSSFWorkbook workBook = new XSSFWorkbook(fis);
        	XSSFSheet sheet= workBook.getSheet(sheetName);
        	int rowCount = sheet.getPhysicalNumberOfRows();
        	int colCount = sheet.getRow(0).getLastCellNum();
        	
        	
        	data = new Object[rowCount-1][colCount];
        	for (int i = 1; i < rowCount; i++) { // skip header row
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i-1][j] = getCellValueAsString(cell);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

	public static Object[][] getFilteredData(String filePath, String sheetName, String isValidValue) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workBook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();
            
            List<Object[]> filteredData = new ArrayList<>();
            
            for (int i = 1; i < rowCount; i++) { // Skip header row
                Row row = sheet.getRow(i);
                String isValid = getCellValueAsString(row.getCell(2));
                
                if (isValid.equalsIgnoreCase(isValidValue)) {
                    Object[] rowData = new Object[colCount];
                    for (int j = 0; j < colCount; j++) {
                        rowData[j] = getCellValueAsString(row.getCell(j));
                    }
                    filteredData.add(rowData);
                }
            }
            
            data = new Object[filteredData.size()][colCount];
            filteredData.toArray(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
    }

	private static String getCellValueAsString(Cell cell) {
	    if (cell == null) 
	    	return "";

	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            return String.valueOf(cell.getNumericCellValue());
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case FORMULA:
	            return cell.toString(); // fallback to string representation
	        default:
	            return "";
	    }
    }
}
