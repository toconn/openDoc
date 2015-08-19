package ua.app.odoc;

import java.util.*;

import ua.app.all.*;
import ua.app.odir.IODirConst;
import ua.app.odir.IVersionHistory;
import ua.core.service.parameters.IParamConst;
import ua.core.service.parameters.Param;
import ua.core.service.shell.ShellService;
import ua.core.exceptions.ExceptionConfigNotFound;
import ua.core.exceptions.ExceptionInvalidAction;
import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;
import ua.core.util.*;
import ua.shared.odir.IODirComponentConst;
import ua.shared.odir.oDirComponent;
import ua.shared.odir.oDirCore;


public class ODocApp implements IApp {

	
	private oDirComponent	oDirComponent	= null;
	
	
	public void config() throws ExceptionRuntime, ExceptionValidation {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		boolean						hasError			= false;
		ArrayList <Message>			messageList			= null;


		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		messageList = new ArrayList<Message>();

		// Service - Shell - Load
		// Service - User Directory - Load
		
		//////////////////////////////////////////////////////////////////
		// Error Handling
		//////////////////////////////////////////////////////////////////

		if (hasError) {

			throw new ExceptionValidation (messageList);
		}

	}

	
	public void displayData (List <StringList> aliasStringListList) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		StringList				displayStringList		= null;
		int []					columnSizeArray			= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		// Format Data...

		// Size Columns...
		
		columnSizeArray = new int [4];
		
		columnSizeArray = CollectionUtils.getColumnSizeMaxArray (aliasStringListList, columnSizeArray);
		
		
		// Display data...
		
		displayStringList = new StringList();
				
		
		// Path Alias Data...
		
		displayStringList.add ("Document List");
		displayStringList.add ("");
		
		displayStringList.add (CollectionUtils.getAlignedStringList (new StringList (new String[] {"Name", "Description", "Type", "Document"}), columnSizeArray, IAppConst.TEXT_INDENT, "  "));
		displayStringList.add ("");
		
		displayStringList.add (CollectionUtils.getAlignedStringList (aliasStringListList, columnSizeArray, IAppConst.TEXT_INDENT, "  "));
		displayStringList.add ("");
		
		
		// Display...
		
		AppUI.print (displayStringList);
	}

	
	public void init()  {

		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////


		AppStore.appInfo.setProperty (IAppConst.APP_NAME,			IODocConst.APP_NAME);
		AppStore.appInfo.setProperty (IAppConst.APP_VERSION,		IODirConst.APP_VERSION);
		AppStore.appInfo.setProperty (IAppConst.APP_AUTHOR,			IODirConst.APP_AUTHOR);
		AppStore.appInfo.setProperty (IAppConst.APP_CREATION_DATE,	IODirConst.APP_CREATION_DATE);
		AppStore.appInfo.setProperty (IAppConst.APP_BUILD_DATE,		IODirConst.APP_BUILD_DATE);
		AppStore.appInfo.setProperty (IAppConst.APP_BUILD_NUMBER,	IODirConst.APP_BUILD_NUMBER);

		AppStore.paramDefPack				= IODocConst.PARAM_DEF_PACK;
		AppStore.versionHistoryArrayArray	= IVersionHistory.VERSION_HISTORY_ARRAY_ARRAY;
		
		try {
		
			// Init Shell (load properties)
			
			try {
				
				ShellService.init();
			}
			catch (ExceptionConfigNotFound e) {

				// Shell.config not found. Exit.
				
				AppUI.print (e.getMessageList());
				
				return;
			}
			
			
			// Init PathAlias (load properties)...
			
			try {
				
				this.oDirComponent = new oDirComponent (IODirComponentConst.ODIR_TYPE_DOC);
			}
			catch (ExceptionItemNotFound e) {

				// Shell.config not found. Exit.

				AppUI.print (e.getMessageList());
				
				return;
			}
		}
		catch (ExceptionRuntime e) {
			
			AppUI.print ("Error initializing " + IAppConst.APP_NAME);
		}
	}
	
	
	public void open (String aliasName) {
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		try {
			this.oDirComponent.open (aliasName);
		}
		
		
		//////////////////////////////////////////////////////////////////
		// Error Handling
		//////////////////////////////////////////////////////////////////

		catch (ExceptionItemNotFound e) {
			
			// Shell alias 'dir' does not exist...

			AppUI.print (e.getMessageList());
		}
		catch (ExceptionRuntime e) {
			
			AppUI.print (e.getMessageList());
		}
		catch (ExceptionInvalidAction e) {
			
			AppUI.print (e.getMessageList());
		}
	}
	
	public void openFirstMatch (Param param) {
		
		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		try {

			if (param.hasSubParams()) {
				
				this.oDirComponent.openFirstMatch (param.getSubParam (0));
			}
		}
		
		
		//////////////////////////////////////////////////////////////////
		// Error Handling
		//////////////////////////////////////////////////////////////////

		catch (ExceptionItemNotFound e) {
			
			// Shell alias 'dir' does not exist...

			AppUI.print (e.getMessageList());
		}
		catch (ExceptionRuntime e) {
			
			AppUI.print (e.getMessageList());
		}
		catch (ExceptionInvalidAction e) {
			
			AppUI.print (e.getMessageList());
		}
	}
	
	
	public void run() throws ExceptionRuntime {

		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		Param		defaultParam	= null;
		Param		param			= null;

		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		AppUI.print();

		//////////////////////////////////////////////////////////////////
		// Process requests...
		//////////////////////////////////////////////////////////////////

		// List directories...
		
		param = (AppStore.paramMap.get (IODirConst.PARAM_DIRECTORIES_SEARCH_LIST));
		
		if (param == null) {
			param = AppStore.paramMap.get (IODirConst.PARAM_DIRECTORIES_SEARCH_LIST2);
		}
		
		if (param != null) {
			
			if (param.hasSubParams()) {
				
				displayData (oDirComponent.getAliasDataListListMatch (param.getSubParam (0)));
			}
			else {
				
				displayData (oDirComponent.getAliasDataListList());
			}
		}		

		
		// List Matching directories...
		
		if (AppStore.paramMap.containsKey (IODirConst.PARAM_DIRECTORIES_SEARCH_MATCH) || AppStore.paramMap.containsKey (IODirConst.PARAM_DIRECTORIES_SEARCH_MATCH2)) {
			
			displayData (oDirComponent.getAliasDataListListMatch (AppStore.paramMap.get (IODirConst.PARAM_DIRECTORIES_SEARCH_MATCH).getSubParam (0)));
		}		

		
		// Edit data file...
		
		if (AppStore.paramMap.containsKey (IODirConst.PARAM_CONFIG_EDIT)) {
			
			oDirCore.editConfig (this.oDirComponent);
		}		

		
		// Edit shell file...
		
		if (AppStore.paramMap.containsKey (IODirConst.PARAM_SHELL_EDIT)) {
			
			oDirCore.editShell();
		}		

		
		// Open Matching directory:
		
		if (AppStore.paramMap.containsKey (IODirConst.PARAM_DIRECTORIES_OPEN_MATCH)) {
			
			openFirstMatch (AppStore.paramMap.get (IODirConst.PARAM_DIRECTORIES_OPEN_MATCH));
		}

		if (AppStore.paramMap.containsKey (IODirConst.PARAM_DIRECTORIES_OPEN_MATCH2)) {
			
			openFirstMatch (AppStore.paramMap.get (IODirConst.PARAM_DIRECTORIES_OPEN_MATCH2));
		}
		
		
		// Open directory(s)...
		
		// Get default parameter.
		
		defaultParam = AppStore.paramMap.get (IParamConst.PARAM_DEFAULT);


		// loop through directory requests....
		
		if (defaultParam != null && defaultParam.hasSubParams()) {							
			
			for (String aliasName: defaultParam.getSubparamStringArray()) {
				
				// Open document.
			
				open (aliasName);
			}
		}
	}
}
