package ua.shared.alias;

import ua.core.util.StringList;

public interface IAliasConst {
	
	public static	char		HEADER_OPEN_CHAR	= '[';
	public static	char		HEADER_CLOSE_CHAR	= ']';
	
	public static	char		FILE_ESCAPE_CHAR	= 0;	// No escape character needed.
	
	public static	StringList	FILE_SEPARATOR_STRING_LIST = new StringList (new String[] {
		"\t",
	});
	
	
	/*
	 
	// Old escape characters. Not needed. 
	
	public static	char		FILE_ESCAPE_CHAR	= '/';		
	
	public static UaList <NVStringPair> FILE_ESCAPE_NVPAIR_LIST = new UaList <NVStringPair> (new NVStringPair[] {
		new NVStringPair ("/", "/"),
		new NVStringPair ("\\", "\\"),
		new NVStringPair (",", ","),
	});
	
	*/
	
}
