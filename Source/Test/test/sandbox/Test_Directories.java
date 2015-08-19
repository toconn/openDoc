package test.sandbox;

import java.io.File;


public class Test_Directories {

	public static void main (String[] args) {
		
		File dir = new File("D:\\Multimedia\\Ceol\\Ceol");

		String[] children = dir.list();
		
		if (children == null) {
			
		    System.out.println ("Nothing to see here.");
		    System.out.println ("");
		    
		}
		else {
			
		    for (int i=0; i<children.length; i++) {
		    	
		    	System.out.println (children[i]);
		    }
		}
	}
}
