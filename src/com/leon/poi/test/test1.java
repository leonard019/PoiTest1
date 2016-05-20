package com.leon.poi.test;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class test1 {

	public static void main(String[] args) throws IOException {
		 File file = new File("E:/openworkbook.xlsx");
	      FileInputStream fIP = new FileInputStream(file);
	      //Get the workbook instance for XLSX file 
	      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
	      if(file.isFile() && file.exists())
	      {
	         System.out.println(
	         "openworkbook.xlsx file open successfully.");
	      }
	      else
	      {
	         System.out.println(
	         "Error to open openworkbook.xlsx file.");
	      }
	}
	
	@Test
	public void test1()throws IOException 
	{
		//讀檔
		 File file = new File("E:/openworkbook.xlsx");
	      FileInputStream fIP = new FileInputStream(file);
	      //Get the workbook instance for XLSX file 
	      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
	      if(file.isFile() && file.exists())
	      {
	         System.out.println(
	         "openworkbook.xlsx file open successfully.");
	      }
	      else
	      {
	         System.out.println(
	         "Error to open openworkbook.xlsx file.");
	      }
	}

	
	@Test
	public void createPoi() throws IOException {
		  //Create Blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      //Create file system using specific name
	      FileOutputStream out = new FileOutputStream(
	      new File("E:/createworkbook.xlsx"));
	      //write operation workbook using file out object 
	      workbook.write(out);
	      out.close();
	      System.out.println("createworkbook.xlsx written successfully");
	}
	
	
	@Test
	public void createExcel1() throws IOException{
		  //Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      //Create a blank sheet
	      XSSFSheet spreadsheet = workbook.createSheet( 
	      " Employee Info ");
	      //Create row object
	      XSSFRow row;
	      //This data needs to be written (Object[])
	      Map < String, Object[] > empinfo = 
	      new TreeMap < String, Object[] >();
	      empinfo.put( "1", new Object[] { 
	      "EMP ID", "EMP NAME", "DESIGNATION" });
	      empinfo.put( "2", new Object[] { 
	      "tp01", "Gopal", "Technical Manager" });
	      empinfo.put( "3", new Object[] { 
	      "tp02", "Manisha", "Proof Reader" });
	      empinfo.put( "4", new Object[] { 
	      "tp03", "Masthan", "Technical Writer" });
	      empinfo.put( "5", new Object[] { 
	      "tp04", "Satish", "Technical Writer" });
	      empinfo.put( "6", new Object[] { 
	      "tp05", "Krishna", "Technical Writer" });
	      //Iterate over data and write to sheet
	      Set < String > keyid = empinfo.keySet();
	      int rowid = 0;
	      for (String key : keyid)
	      {
	         row = spreadsheet.createRow(rowid++);
	         Object [] objectArr = empinfo.get(key);
	         int cellid = 0;
	         for (Object obj : objectArr)
	         {
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }
	      //Write the workbook in file system
	      FileOutputStream out = new FileOutputStream( 
	      new File("E:/Writesheet.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println( "Writesheet.xlsx written successfully" );
	}
	
	static XSSFRow row;
	//不要跟我談理想！我來工作就是要錢！如果說理想是什麼？那就是不工作又有錢！
	@Test
	public void readXlsFromFile() throws IOException{
		  FileInputStream fis = new FileInputStream(new File("E:/WriteSheet.xlsx"));
		  XSSFWorkbook workbook = new XSSFWorkbook(fis);
		  XSSFSheet spreadsheet = workbook.getSheetAt(0);
			      Iterator < Row > rowIterator = spreadsheet.iterator();
			      while (rowIterator.hasNext()) 
			      {
			         row = (XSSFRow) rowIterator.next();
			         Iterator < Cell > cellIterator = row.cellIterator();
			         while ( cellIterator.hasNext()) 
			         {
			            Cell cell = cellIterator.next();
			            switch (cell.getCellType()) 
			            {
			               case Cell.CELL_TYPE_NUMERIC:
			               System.out.print( 
			               cell.getNumericCellValue() + " \t\t " );
			               break;
			               case Cell.CELL_TYPE_STRING:
			               System.out.print(
			               cell.getStringCellValue() + " \t\t " );
			               break;
			            }
			         }
			         System.out.println();
			      }
			      fis.close();
			      
	}
	
	
	@Test
	public void drawFont() throws IOException{
		
		 XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook.createSheet("Fontstyle");
	      XSSFRow row = spreadsheet.createRow(2);
	      //Create a new font and alter it.
	      XSSFFont font = workbook.createFont();
	      font.setFontHeightInPoints((short) 30);
	      font.setFontName("IMPACT");
	      font.setItalic(true);
	      font.setColor(HSSFColor.BRIGHT_GREEN.index);
	      //Set font into style
	      XSSFCellStyle style = workbook.createCellStyle();
	      style.setFont(font);
	      // Create a cell with a value and set style to it.
	      XSSFCell cell = row.createCell(1);
	      cell.setCellValue("Font Style");
	      cell.setCellStyle(style);
	      FileOutputStream out = new FileOutputStream(new File("E:/fontstyle.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println("fontstyle.xlsx written successfully");
	      
	}
	
	@Test
	public void createDegree() throws IOException{
	    XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook.createSheet(
	      "Text direction");
	      XSSFRow row = spreadsheet.createRow(2);
	      XSSFCellStyle myStyle = workbook.createCellStyle();
	      myStyle.setRotation((short) 0);
	      XSSFCell cell = row.createCell(1);
	      cell.setCellValue("0D angle");
	      cell.setCellStyle(myStyle);
	      //30 degrees
	      myStyle=workbook.createCellStyle();
	      myStyle.setRotation((short) 30);
	      cell = row.createCell(3);
	      cell.setCellValue("30D angle");
	      cell.setCellStyle(myStyle);
	      //90 degrees
	      myStyle=workbook.createCellStyle();
	      myStyle.setRotation((short) 90);
	      cell = row.createCell(5);
	      cell.setCellValue("90D angle");
	      cell.setCellStyle(myStyle);
	      //120 degrees
	      myStyle=workbook.createCellStyle();
	      myStyle.setRotation((short) 120);
	      cell = row.createCell(7);
	      cell.setCellValue("120D angle");
	      cell.setCellStyle(myStyle);
	      //270 degrees
	      myStyle = workbook.createCellStyle();
	      myStyle.setRotation((short) 270);
	      cell = row.createCell(9);
	      cell.setCellValue("270D angle");
	      cell.setCellStyle(myStyle);
	      //360 degrees
	      myStyle=workbook.createCellStyle();
	      myStyle.setRotation((short) 360);
	      cell = row.createCell(12);
	      cell.setCellValue("360D angle");
	      cell.setCellStyle(myStyle);
	      FileOutputStream out = new FileOutputStream(new File("E:/textdirection.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println( 
	      "textdirection.xlsx written successfully");
	}
	
	
	@Test
	public void  Formula1() throws IOException{
		   XSSFWorkbook workbook = new XSSFWorkbook(); 
		      XSSFSheet spreadsheet = workbook.createSheet("formula");
		      XSSFRow row = spreadsheet.createRow(1);
		      XSSFCell cell = row.createCell(1);
		      cell.setCellValue("A =" );
		      cell = row.createCell(2);
		      cell.setCellValue(2);
		      row = spreadsheet.createRow(2);
		      cell = row.createCell(1);
		      cell.setCellValue("B =");
		      cell = row.createCell(2);
		      cell.setCellValue(4);
		      row = spreadsheet.createRow(3);
		      cell = row.createCell(1);
		      cell.setCellValue("Total =");
		      cell = row.createCell(2);
		      // Create SUM formula
		      cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		      cell.setCellFormula("SUM(C2:C3)" );
		      cell = row.createCell(3);
		      cell.setCellValue("SUM(C2:C3)");
		      row = spreadsheet.createRow(4);
		      cell = row.createCell(1);
		      cell.setCellValue("POWER =");
		      cell=row.createCell(2);
		      // Create POWER formula
		      cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		      cell.setCellFormula("POWER(C2,C3)");
		      cell = row.createCell(3);
		      cell.setCellValue("POWER(C2,C3)");
		      row = spreadsheet.createRow(5);
		      cell = row.createCell(1);
		      cell.setCellValue("MAX =");
		      cell = row.createCell(2);
		      // Create MAX formula
		      cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		      cell.setCellFormula("MAX(C2,C3)");
		      cell = row.createCell(3);
		      cell.setCellValue("MAX(C2,C3)");
		      row = spreadsheet.createRow(6);
		      cell = row.createCell(1);
		      cell.setCellValue("FACT =");
		      cell = row.createCell(2);
		      // Create FACT formula
		      cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		      cell.setCellFormula("FACT(C3)");
		      cell = row.createCell(3);
		      cell.setCellValue("FACT(C3)");
		      row = spreadsheet.createRow(7);
		      cell = row.createCell(1);
		      cell.setCellValue("SQRT =");
		      cell = row.createCell(2);
		      // Create SQRT formula
		      cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		      cell.setCellFormula("SQRT(C5)");
		      cell = row.createCell(3);
		      cell.setCellValue("SQRT(C5)");
		      workbook.getCreationHelper()
		      .createFormulaEvaluator()
		      .evaluateAll();
		      FileOutputStream out = new FileOutputStream(new File("E:/formula.xlsx"));
		      workbook.write(out);
		      out.close();
		      System.out.println("fromula.xlsx written successfully");
	}
}
