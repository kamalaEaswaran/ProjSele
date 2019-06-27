package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class readExcel {
	
	//public static void main(String[] args) throws IOException {
	public String[][] getdatafromExcel() throws IOException{
		//File file = new File("D:\\eclipse\\projSel\\src\\main\\java\\resources\\InputData.xlsx");
		File file = new File(System.getProperty("user.dir")+"/src/main/java/resources/InputData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		/* To read XLS files, an HSSF implementation is provided by POI library.
		*To read XLSX, XSSF implementation of POI library will be the choice.XSSFWorkbook is imported from import org.apache.poi.xssf.usermodel.XSSFWorkbook;
		* include poi-ooxml library.
		*/
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		
		int noofRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		int i=1;
		Row row = sheet.getRow(i);
		String[][] str = new String[noofRows][row.getLastCellNum()];
		
		
		while(i<=noofRows){
			row = sheet.getRow(i);
			for(int j =0; j<row.getLastCellNum();j++) {
				System.out.println(row.getCell(j).getStringCellValue());
				str[i-1][j]=row.getCell(j).getStringCellValue();
			}
			i++;
		}
		System.out.println(str[0][0]+" "+str[0][1]);
		System.out.println(str[1][0]+" "+str[1][1]);
		return str;
		
		
		
		
	}

		
	
}
