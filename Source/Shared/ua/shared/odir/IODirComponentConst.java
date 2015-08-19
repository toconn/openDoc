package ua.shared.odir;

import ua.core.util.Message;


public interface IODirComponentConst {
	
	// Different names for each config file type to allow multiple instances to be run simultaneously...
	
	public static final	String	COMPONENT_ODIR_APP_CONFIG_FILE		= "Component.ODir.App.Config.File";
	public static final	String	COMPONENT_ODIR_APP_CONFIG_FILE_NAME	= "Component.ODir.App.Config.File.Name";
	public static final	String	COMPONENT_ODIR_DIR_CONFIG_FILE		= "Component.ODir.Dir.Config.File";
	public static final	String	COMPONENT_ODIR_DIR_CONFIG_FILE_NAME	= "Component.ODir.Dir.Config.File.Name";
	public static final	String	COMPONENT_ODIR_DOC_CONFIG_FILE		= "Component.ODir.Doc.Config.File";
	public static final	String	COMPONENT_ODIR_DOC_CONFIG_FILE_NAME	= "Component.ODir.Doc.Config.File.Name";
	public static final	String	COMPONENT_ODIR_WEB_CONFIG_FILE		= "Component.ODir.Web.Config.File";
	public static final	String	COMPONENT_ODIR_WEB_CONFIG_FILE_NAME	= "Component.ODir.Web.Config.File.Name";

	public static final	String	DATA_FILE_DIR	= "All";
		
	public static final	String	DATA_FILE_OAPP	= "applications.data";
	public static final	String	DATA_FILE_ODIR	= "directories.data";
	public static final	String	DATA_FILE_ODOC	= "documents.data";
	public static final	String	DATA_FILE_OWEB	= "web.data";
	
	public static final	char	ODIR_TYPE_APP	= 'a';
	public static final	char	ODIR_TYPE_DIR	= 'd';
	public static final	char	ODIR_TYPE_DOC	= 'o';
	public static final	char	ODIR_TYPE_WEB	= 'w';
	
	public static final Message	MESSAGE_ALIAS_NOT_FOUND						= new Message ("M10010011", "Unknown name ''{0}''.");
	public static final Message	MESSAGE_ALIAS_DIRECTORY_NOT_FOUND			= new Message ("M10010012", "The directory for ''{0}'' was not found: \"{1}\"");
	public static final Message	MESSAGE_ALIAS_FILE_NOT_FOUND				= new Message ("M10010013", "The file for ''{0}'' was not found: \"{1}\"");
	public static final Message	MESSAGE_ALIAS_SUBDIRECTORY_NOT_FOUND		= new Message ("M10010014", "The subdirectory for ''{0}'' was not found: \"{1}\"");
	
	public static final Message	MESSAGE_OPENING								= new Message ("M10010020", "Opening {0}...");
}
