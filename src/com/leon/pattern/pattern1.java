package com.leon.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class pattern1 {

	
	@Test
	public void test1(){
		
		 System.out.println(Pattern.matches("(?>[ab]*)\\w\\w", "aabbcc")); //# true
	     System.out.println(Pattern.matches("(?:[ab]*)\\w\\w", "aabbcc")); //# true
	     System.out.println(Pattern.matches("(?>[ab]*)\\w\\w", "aabbaa")); //# false!
	     System.out.println(Pattern.matches("(?:[ab]*)\\w\\w", "aabbaa")); //# true
	        
	}
	
	
	@Test
	public void testCountry(){
		String local = "zh";         // 地區語言
		String country = "TW";  // 國家代碼
		 
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");  // 只要英文字母大小寫, 其餘不要
		Matcher matcher = pattern.matcher(local);
		 
		if(matcher.find()) {
		  System.out.println("地區 matcher OK");
		} else {
		  System.out.println("地區 matcher NO OK");
		}
		 
		matcher = pattern.matcher(country);
		if(matcher.matches()) {
		  System.out.println("國家 matcher OK");
		} else {
		  System.out.println("國家 matcher NO OK");
		}
	}
	
	/**
	 * 相符的是"<html>" "</html>" "<h0>"等。
	 *	不相符字串 "<123>"
	 */
	@Test
	public void checkHTML(){
		String input="<html>";
		Pattern pattern =Pattern.compile("</?[a-z][a-z0-9]*[^<>]*>");
		Matcher matcher = pattern.matcher(input);
		
		System.out.println(input+":"+matcher.matches() );
	}
	
	
	/**
	 * 相符的有 "<A HREF="www.google.com.tw">Test</A>" "<P></P> "<PRE>Test</PRE>" "<H0></H0>" 
	*	不相符字串 "<abc></def>" "<123></123>"
	 */
	@Test
	public void checkHTML2(){
		String input="<P></P>";
		Pattern pattern =Pattern.compile("<([A-Z][A-Z0-9]*)[^>]*>(.*?)</\1>");
		Matcher matcher = pattern.matcher(input);
		
		System.out.println(input+":"+matcher.matches() );
		
	}
	
	@Test
	public void checkT(){
		
		
		// 2.必須為數字型
		String input="12345678900";
		Pattern pattern = Pattern.compile("^-?\\d+$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		
		System.out.println(input+":"+matcher.matches() );
	}
	
	@Test
	public void checkAllParrtern(){
		String input="20161303";
		// B221101 輸入日期內容錯誤-請輸入正確的日期
		Pattern pattern = Pattern.compile("\\d{1,8}");
		Matcher matcher = pattern.matcher(input);
		
		input="AAAA123";
		// 2.必須為英文字母或數字
		Pattern pattern2 = Pattern.compile("[a-zA-Z0-9]+",Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(input);
		
		System.out.println("checkAllParrtern 1:"+matcher.matches() );
		System.out.println("checkAllParrtern 2:"+matcher2.matches() );
		System.out.println("isNumericAll:"+pattern1.isNumericAll("-123"));
	}
	
	/**
	 * 判斷字段是否為數字 包含負數
	 * 
	 * @param field
	 * @return boolean
	 */
	public static boolean isNumericAll(String field) {
		Pattern pattern = Pattern.compile("-?[0-9]*");
		Matcher isNum = pattern.matcher(field);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
}
