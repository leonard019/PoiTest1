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
		String local = "zh";         // �a�ϻy��
		String country = "TW";  // ��a�N�X
		 
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");  // �u�n�^��r���j�p�g, ��l���n
		Matcher matcher = pattern.matcher(local);
		 
		if(matcher.find()) {
		  System.out.println("�a�� matcher OK");
		} else {
		  System.out.println("�a�� matcher NO OK");
		}
		 
		matcher = pattern.matcher(country);
		if(matcher.matches()) {
		  System.out.println("��a matcher OK");
		} else {
		  System.out.println("��a matcher NO OK");
		}
	}
	
	/**
	 * �۲Ū��O"<html>" "</html>" "<h0>"���C
	 *	���۲Ŧr�� "<123>"
	 */
	@Test
	public void checkHTML(){
		String input="<html>";
		Pattern pattern =Pattern.compile("</?[a-z][a-z0-9]*[^<>]*>");
		Matcher matcher = pattern.matcher(input);
		
		System.out.println(input+":"+matcher.matches() );
	}
	
	
	/**
	 * �۲Ū��� "<A HREF="www.google.com.tw">Test</A>" "<P></P> "<PRE>Test</PRE>" "<H0></H0>" 
	*	���۲Ŧr�� "<abc></def>" "<123></123>"
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
		
		
		// 2.�������Ʀr��
		String input="12345678900";
		Pattern pattern = Pattern.compile("^-?\\d+$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		
		System.out.println(input+":"+matcher.matches() );
	}
	
	@Test
	public void checkAllParrtern(){
		String input="20161303";
		// B221101 ��J������e���~-�п�J���T�����
		Pattern pattern = Pattern.compile("\\d{1,8}");
		Matcher matcher = pattern.matcher(input);
		
		input="AAAA123";
		// 2.�������^��r���μƦr
		Pattern pattern2 = Pattern.compile("[a-zA-Z0-9]+",Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(input);
		
		System.out.println("checkAllParrtern 1:"+matcher.matches() );
		System.out.println("checkAllParrtern 2:"+matcher2.matches() );
		System.out.println("isNumericAll:"+pattern1.isNumericAll("-123"));
	}
	
	/**
	 * �P�_�r�q�O�_���Ʀr �]�t�t��
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
