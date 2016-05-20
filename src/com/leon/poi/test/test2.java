package com.leon.poi.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class test2 {

	public static void main(String[] args) {
		

	}
	
	@Test
	public void hyperlinkEX() throws IOException{
		  XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook.createSheet("Hyperlinks");
	      XSSFCell cell;
	      CreationHelper createHelper = workbook
	      .getCreationHelper();
	      XSSFCellStyle hlinkstyle = workbook.createCellStyle();
	      XSSFFont hlinkfont = workbook.createFont();
	      hlinkfont.setUnderline(XSSFFont.U_SINGLE);
	      hlinkfont.setColor(HSSFColor.BLUE.index);
	      hlinkstyle.setFont(hlinkfont);
	      //URL Link
	      cell = spreadsheet.createRow(1)
	      .createCell((short) 1);
	      cell.setCellValue("URL Link");
	      XSSFHyperlink link = (XSSFHyperlink)createHelper
	      .createHyperlink(Hyperlink.LINK_URL);
	      link.setAddress("http://www.gitbook.net/" );
	      cell.setHyperlink((XSSFHyperlink) link);
	      cell.setCellStyle(hlinkstyle);
	      //Hyperlink to a file in the current directory
	      cell = spreadsheet.createRow(2)
	      .createCell((short) 1);
	      cell.setCellValue("File Link");
	      link = (XSSFHyperlink)createHelper
	      .createHyperlink(Hyperlink.LINK_FILE);
	      link.setAddress("E:/cellstyle.xlsx");
	      cell.setHyperlink(link);
	      cell.setCellStyle(hlinkstyle);
	      //e-mail link
	      cell = spreadsheet.createRow(3)
	      .createCell((short) 1);
	      cell.setCellValue("Email Link");
	      link = (XSSFHyperlink)createHelper
	      .createHyperlink(Hyperlink.LINK_EMAIL);
	      link.setAddress( 
	      "mailto:contact@gitbook.net?"
	      +"subject=Hyperlink");
	      cell.setHyperlink(link);
	      cell.setCellStyle(hlinkstyle);
	      FileOutputStream out = new FileOutputStream(new File("E:/hyperlink.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println("hyperlink.xlsx written successfully");
	}
	
	
	@Test
	public void excelDatabase()throws IOException, Throwable{
		
		  Class.forName("com.mysql.jdbc.Driver");
	      Connection connect = DriverManager.getConnection( 
	      "jdbc:mysql://localhost:3306/point_shop" , 
	      "root" , 
	      "abc123"
	      );
	      Statement statement = connect.createStatement();
	      ResultSet resultSet = statement.executeQuery("select * from tb_goods");
//	      .executeQuery("select * from emp_tbl");
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook
	      .createSheet("employe db");
	      XSSFRow row=spreadsheet.createRow(1);
	      XSSFCell cell;
	      cell=row.createCell(1);
	      cell.setCellValue("EMP ID");
	      cell=row.createCell(2);
	      cell.setCellValue("EMP NAME");
	      cell=row.createCell(3);
	      cell.setCellValue("Description");
	      cell=row.createCell(4);
	      cell.setCellValue("ORIGINAL_POINT");
	      cell=row.createCell(5);
	      cell.setCellValue("NOW_POINT");
	      
	      cell=row.createCell(6);
	      cell.setCellValue("PUBLISHED");
	      cell=row.createCell(7);
	      cell.setCellValue("IS_DELETE");
	      cell=row.createCell(8);
	      cell.setCellValue("VERSION");
	      
	      int i=2;
	      while(resultSet.next())
	      {
	         row=spreadsheet.createRow(i);
	         cell=row.createCell(1);
	         cell.setCellValue(resultSet.getInt("id"));
	         cell=row.createCell(2);
	         cell.setCellValue(resultSet.getString("name"));
	         cell=row.createCell(3);
	         cell.setCellValue(resultSet.getString("description"));
	         cell=row.createCell(4);
	         cell.setCellValue(resultSet.getString("original_point"));
	         cell=row.createCell(5);
	         cell.setCellValue(resultSet.getString("now_point"));
	         
	         cell=row.createCell(6);
	         cell.setCellValue(resultSet.getString("published"));
	         cell=row.createCell(7);
	         cell.setCellValue(resultSet.getString("is_delete"));
	         cell=row.createCell(8);
	         cell.setCellValue(resultSet.getString("version"));
	         i++;
	      }
	      FileOutputStream out = new FileOutputStream(new File("E:/exceldatabase.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println("exceldatabase.xlsx written successfully");
	      
	}

}
