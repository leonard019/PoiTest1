package com.leon.pattern;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class hello {

	public static Logger logger=Logger.getLogger(hello.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("hello!!!");
		BasicConfigurator.configure();
	     logger.info("Hello World");          // the old SysO-statement
	        
	}

}
