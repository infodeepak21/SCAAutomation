package dataLib;

/*
   ExcelLib is responsible for taking the data from the excel sheet and passing the data in our application at the time of test.
   This file contains simple methods in form of :-
   	a.) count the total number of row
   	b.) count the total number of cell
   	c.) reading the data from a particular cell
   	d.) writing the data to a particular cell
   	
   	In order to pass the data in the test the user has to follow below steps :-
   	 1.) Use @DataProvider form testNg
   	 2.) Provide a name to @DataProvider 
   	     Eg. @DataProvider(name="readData")
   	         This will be further passed under @Test(dataProvider="readData")
   	 3.) Create a simple method to get data from the sheet 
   	     Eg. String[][] getData() throws IOException {
			 String filePath = "<FileName>";
			 String sheetName = "<SheetName>";
			 int rowNum =1;
			 int row = ExcelLib.getRowCount(filePath, sheetName);
			 int column = ExcelLib.getCellCount(filePath, sheetName, rowNum);
			 String credentials[][] = new String[row][column];
			 for(int i =1 ; i <= row ; i++) {
				for(int j = 0; j< column ; j++) {
					credentials[i-1][j] =ExcelLib.getCellData(filePath, sheetName, i, j);
				}
			 }
				return (credentials);
			 }
   	  DataProvider always returns two dimensional array so above is the best practice to utilize the same.
   	         
   	
   author Abhishek Shandilya
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import commonUtilities.BaseTest;
import commonUtilities.PropertyManager;

public class ExcelLib extends BaseTest {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	/*
	 * This method is responsible to get the total number of rows
	 * 
	 * @Params FilePath , SheetName
	 * 
	 * @Returns last row number
	 */
	public static int getRowCount(String filePath, String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowCount;
	}

	/*
	 * This method is responsible to get the total number of cells
	 * 
	 * @Params FilePath , SheetName , row number
	 * 
	 * @Returns last cell number
	 */
	public static int getCellCount(String filePath, String sheetName, int rowNum) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
	}

	/*
	 * This method is responsible to get cell Data.
	 * 
	 * @Params FilePath , SheetName , row number , column number
	 * 
	 * @Returns data present on that particular cell
	 */
	public static String getCellData(String filePath, String sheetName, int rowNum, int column) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(column);
		String data;
		try {
			DataFormatter format = new DataFormatter();
			String cellData = format.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
			e.printStackTrace();
		} finally {
			wb.close();
			fis.close();
		}
		return data;
	}

	/*
	 * This method is responsible to write the data on a particular cell.
	 * 
	 * @Params FilePath , SheetName , row number , column number , Data to be
	 * written
	 */
	public static void setCellData(String filePath, String sheetName, String[] data)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		PropertyManager.loadConfig();
		fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount + 1);
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = newRow.createCell(j);
			cell.setCellValue(data[j]);
			style = wb.createCellStyle();
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			cell.setCellStyle(style);
		}
		fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fis.close();

	}
}
