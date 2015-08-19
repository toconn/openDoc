package test.unit;

import ua.core.util.file.FileUtils;


public class Test_FileExtension extends TestBase {

	public static void main (String [] args) {
		
		testFileExtension();
	}
	
	public static void testFileExtension() {
		
		testFileExtension_Test ("FileThis");
		testFileExtension_Test ("FileThis.");
		testFileExtension_Test ("FileThis.1");
		testFileExtension_Test ("FileThis.rtf");
	}
	
	public static void testFileExtension_Test (String fileName) {
		
		print ("File: " + fileName);
		print ("Ext:  " + FileUtils.getExtension (fileName));
		print ("");
	}
}
