package Lib;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	public ExcelDataConfig(String excelPath){
		
		//This method is going to initiate the objects present FileInputStream and WorkBook class.
		//Initiated object is going to use to read the excel data from provided getData.
		
		try {
			File src = new File(excelPath);
			
			FileInputStream fis = new FileInputStream(src);
			
			wb = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} //Try catch surrounding is basically used to avoid exceptional error.
		
	}
	
	public String getData(int sheetNum, int row, int column){
		
		//This method is actually going and reading the data from excel saved in the given path and returning the value = data.
		
		sheet1 = wb.getSheetAt(sheetNum);
		String data= sheet1.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	

}