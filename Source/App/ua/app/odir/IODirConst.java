package ua.app.odir;
import ua.app.all.IAppConst;
import ua.core.service.parameters.ParamDef;
import ua.core.service.parameters.ParamDefPack;
import ua.core.util.Message;

public interface IODirConst extends IDirCoreConst {


	//////////////////////////////////////////////////////////////////
	// App Info
	//////////////////////////////////////////////////////////////////
		
	public static final String	APP_NAME 				= "oDir";
	
	
	//////////////////////////////////////////////////////////////////
	// App Constants
	//////////////////////////////////////////////////////////////////

	public static final String	DATA_FILE_NAME			= "directories.data";
	public static final String	DATA_SECTION_NAME		= "Directories";
		

	//////////////////////////////////////////////////////////////////
	// Parameters
	//////////////////////////////////////////////////////////////////
	
	public static final Message	PARAM_DESCRIPTION_MESSAGE	= new Message ("A10010000", "{0} [dir.name | /parent.dir dir.name | -subdir parent.dir dir.name] [-list [match]] [-match match] [-edit.config] [-edit.shell] [-help]", APP_NAME);
	
    public static final ParamDef	PARAM_DEFS[]	= {
    	
    	new ParamDef (PARAM_SUBDIRECTORY,				0, 			new Message ("A10010001",	"Open a subdirectory.")),
    	new ParamDef (PARAM_DIRECTORIES_OPEN_MATCH,		1, true,	new Message ("A10010008", 	"Open a directory that starts with match text.")),
    	new ParamDef (PARAM_DIRECTORIES_OPEN_MATCH2,	1, true,	new Message ("A10010006", 	"Same as -" + PARAM_DIRECTORIES_OPEN_MATCH)),
    	new ParamDef (PARAM_DIRECTORIES_SEARCH_LIST2,	1, true,	new Message ("A10010009", 	"Same as -" + PARAM_DIRECTORIES_SEARCH_LIST)),
    	new ParamDef (PARAM_DIRECTORIES_SEARCH_LIST,	1, true,	new Message ("A10010002", 	"List directorys. Find all entries that start with match text.")),
    	new ParamDef (PARAM_DIRECTORIES_SEARCH_LIST2,	1, true,	new Message ("A10010006", 	"Same as -" + PARAM_DIRECTORIES_SEARCH_LIST)),
    	new ParamDef (PARAM_DIRECTORIES_SEARCH_MATCH,	1, false,	new Message ("A10010005", 	"Match directorys. Find all entries that contain the match text.")),
    	new ParamDef (PARAM_DIRECTORIES_SEARCH_MATCH2,	1, true,	new Message ("A10010007", 	"Same as -" + PARAM_DIRECTORIES_SEARCH_MATCH)),
    	new ParamDef (PARAM_CONFIG_EDIT,				0, 			new Message ("A10010003", 	"Edit the configuration file.")),
    	new ParamDef (PARAM_SHELL_EDIT,					0, 			new Message ("A10010004", 	"Edit the shell configuration file.")),
    };
    
	public static final ParamDefPack	PARAM_DEF_PACK = new ParamDefPack (PARAM_DESCRIPTION_MESSAGE, IAppConst.PARAM_DEF_ARRAY_STANDARD, PARAM_DEFS, IAppConst.PARAM_IDENTIFIER, true);

}
