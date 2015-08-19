package test.unit;

import ua.core.util.StringUtils;


public class Test_StringUtils extends TestBase {

	public static void main (String [] args) {
		
		testIsContains();
	}
	
	public static void testIsContains() {
		
		print ("Test - isContains");
		print ("");
		
		testIsContains_Test ("1234567890", "T");
		testIsContains_Test ("1234567890", "3");
		testIsContains_Test ("1234567890", "345");
		testIsContains_Test ("1234567890", "356");

		testIsContains_Test ("12", "345");
	}
	
	public static void testIsContains_Test (String text, String searchText) {
		
		print ("Text : " + text);
		print ("Search : " + searchText);
		print ("Result : " + StringUtils.isContains (text, searchText));
		print ("");
		
	}
}
