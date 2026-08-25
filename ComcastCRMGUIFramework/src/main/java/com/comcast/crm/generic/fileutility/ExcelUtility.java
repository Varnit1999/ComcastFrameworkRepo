package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Exception {

		FileInputStream fis = new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}

	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;

	}

	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Exception {
		FileInputStream fis = new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./testData/TestData.xlsx");
		wb.write(fos);
		wb.close();

	}
}
