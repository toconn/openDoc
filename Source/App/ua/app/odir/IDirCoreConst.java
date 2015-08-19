package ua.app.odir;

import ua.core.util.Message;


public interface IDirCoreConst {

	//////////////////////////////////////////////////////////////////
	// App Info
	//////////////////////////////////////////////////////////////////
		
	public static final String	APP_VERSION 			= "1.06.00";

	public static final String	APP_CREATION_DATE		= "2011-01-11";
	public static final String	APP_BUILD_DATE			= "2015-04-06";
	public static final String	APP_BUILD_NUMBER		= "28";
	
	public static final String	APP_AUTHOR				= "Tadhg Ua Conaill";

	
	//////////////////////////////////////////////////////////////////
	// App Constants
	//////////////////////////////////////////////////////////////////
	
	public static final	String	DATA_FILE_DIR					= "All";
		

	//////////////////////////////////////////////////////////////////
	// Parameters
	//////////////////////////////////////////////////////////////////
	
	public static final String	PARAM_SUBDIRECTORY				= "subdir";
	public static final String	PARAM_DIRECTORIES_OPEN_MATCH	= "omatch";
	public static final String	PARAM_DIRECTORIES_OPEN_MATCH2	= "o";
	public static final String	PARAM_DIRECTORIES_SEARCH_LIST	= "list";
	public static final String	PARAM_DIRECTORIES_SEARCH_LIST2	= "l";
	public static final String	PARAM_DIRECTORIES_SEARCH_MATCH	= "match";
	public static final String	PARAM_DIRECTORIES_SEARCH_MATCH2	= "m";
	public static final String	PARAM_CONFIG_EDIT				= "edit.config";
	public static final String	PARAM_SHELL_EDIT				= "edit.shell";
	
	public static final String	PARAM_PARENT_ESCAPE_CHAR		= "/";

	
	//////////////////////////////////////////////////////////////////
	// Messages
	//////////////////////////////////////////////////////////////////

	public static Message	MESSAGE_OPENING						= new Message ("A10010020", "Opening {0}...");
	public static Message	MESSAGE_CONFIG_EDIT					= new Message ("A10010021", "Opening configuration file...");
	public static Message	MESSAGE_SHELL_EDIT					= new Message ("A10010022", "Opening shell file...");

}
