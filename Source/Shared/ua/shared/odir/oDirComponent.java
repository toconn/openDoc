package ua.shared.odir;

import java.util.List;

import ua.app.all.AppStore;
import ua.app.all.AppUI;
import ua.core.service.environment.EnvironmentService;
import ua.core.service.shell.IShellConst;
import ua.core.service.shell.ShellService;
import ua.core.exceptions.ExceptionInvalidAction;
import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.util.StringList;
import ua.core.util.StringUtils;
import ua.core.util.file.DirectoryUtils;
import ua.core.util.file.FilenameFilterContains;
import ua.core.util.file.FilenameFilterStartsWith;
import ua.core.util.file.FileUtils;
import ua.shared.alias.Alias;
import ua.shared.alias.AliasComponent;
import ua.shared.alias.AliasFilterMatchString;
import ua.shared.alias.AliasFilterNameStartsWith;


public class oDirComponent {
	
	private char				oDirType			= IODirComponentConst.ODIR_TYPE_DIR;	// Set any old default value... it's a char field.
	private String				dataFileName		= null;
	private StringList			dataFileNameList	= null;	
	
	private AliasComponent		aliasData			= null;

	
	public oDirComponent (char oDirType) throws ExceptionItemNotFound, ExceptionRuntime {
		

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	settingConfigFile		= null;
		String	settingConfigFileName	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		
		this.oDirType = oDirType;
		
		// Set the data file name.
		
		switch (oDirType) {
			
			case IODirComponentConst.ODIR_TYPE_APP:
				
				dataFileName			= IODirComponentConst.DATA_FILE_OAPP;
				
				settingConfigFile		= IODirComponentConst.COMPONENT_ODIR_APP_CONFIG_FILE;
				settingConfigFileName	= IODirComponentConst.COMPONENT_ODIR_APP_CONFIG_FILE_NAME;
				
				break;
				
			case IODirComponentConst.ODIR_TYPE_DIR:
				
				this.dataFileName		= IODirComponentConst.DATA_FILE_ODIR;
				
				settingConfigFile		= IODirComponentConst.COMPONENT_ODIR_DIR_CONFIG_FILE;
				settingConfigFileName	= IODirComponentConst.COMPONENT_ODIR_DIR_CONFIG_FILE_NAME;
				
				break;
				
			case IODirComponentConst.ODIR_TYPE_DOC:
				
				this.dataFileName		= IODirComponentConst.DATA_FILE_ODOC;
				
				settingConfigFile		= IODirComponentConst.COMPONENT_ODIR_DOC_CONFIG_FILE;
				settingConfigFileName	= IODirComponentConst.COMPONENT_ODIR_DOC_CONFIG_FILE_NAME;
				
				break;
				
			case IODirComponentConst.ODIR_TYPE_WEB:
				
				this.dataFileName		= IODirComponentConst.DATA_FILE_OWEB;
				
				settingConfigFile		= IODirComponentConst.COMPONENT_ODIR_WEB_CONFIG_FILE;
				settingConfigFileName	= IODirComponentConst.COMPONENT_ODIR_WEB_CONFIG_FILE_NAME;
				
				break;

			default:

				// To Do XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				
				break;
		}
		
		
		// Locate File...
		
		this.dataFileNameList = EnvironmentService.locateFileList (IODirComponentConst.DATA_FILE_DIR, dataFileName);
		
		
		// Save in config settings...
		
		AppStore.configSettings.setProperty (settingConfigFileName, this.dataFileName);
		
		if (this.dataFileNameList != null) {
			
			AppStore.configSettings.setProperty (settingConfigFile, this.dataFileNameList.toString());
		}
		else {
			
			AppStore.configSettings.setProperty (settingConfigFile, "None");		// To Do: Message
		}
		

		// Load data...
		
		this.aliasData = new AliasComponent (this.dataFileNameList);
		
	}
	
	
	public void displayOpenMessage (Alias alias, String itemValue) {
		
		if (alias != null && StringUtils.isNonEmpty (alias.getDescription())) {
			
			AppUI.print (IODirComponentConst.MESSAGE_OPENING.cloneMe (alias.getDescription()));
			AppUI.print (itemValue);		
			
		}
		else {
			
			AppUI.print (IODirComponentConst.MESSAGE_OPENING.cloneMe (itemValue));
		}

		AppUI.print();
	}
	
	
	public void editDataFile() throws ExceptionRuntime, ExceptionItemNotFound {
		
		ShellService.executeByFileType (this.dataFileName);
	}
	
	
	public List <StringList> getAliasDataListList() {

		return aliasData.getAliasStringListList();
	}
	
	
	public List <StringList> getAliasDataListListMatch (String matchText) {

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		return aliasData.getAliasStringListList (new AliasFilterMatchString (matchText));
	}
	
	
	public List <StringList> getAliasDataListListStartsWith (String startsWith) {

		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		return aliasData.getAliasStringListList (new AliasFilterNameStartsWith (startsWith));
	}
	
	
	public String getDataFileName() {
		
		return dataFileName;
	}
	
	
	/**
	 * Open the file with the default behavior (open as a file, directory, application or web) unless explicitly set in the data file.
	 * 
	 * @param aliasName
	 * @throws ExceptionRuntime 
	 * @throws ExceptionInvalidAction 
	 */
	public void open (String aliasName) throws ExceptionItemNotFound, ExceptionRuntime, ExceptionInvalidAction {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias	alias		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		alias = this.aliasData.getAliasActual (aliasName);
		
		if (alias != null) {
		
			// Open alias dependant on type...

			open (alias);
		}
		else {
		
			throw new ExceptionItemNotFound (IODirComponentConst.MESSAGE_ALIAS_NOT_FOUND.cloneMe (aliasName));
		}
	}
	
	public void openFirstMatch (String aliasName) throws ExceptionItemNotFound, ExceptionRuntime, ExceptionInvalidAction {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias	alias		= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		alias = this.aliasData.getAliasFirstMatch (aliasName);
		
		if (alias != null) {
		
			// Open alias dependant on type...

			open (alias);
		}
		else {
		
			throw new ExceptionItemNotFound (IODirComponentConst.MESSAGE_ALIAS_NOT_FOUND.cloneMe (aliasName));
		}
	}
	
	
	public void openApp (Alias alias) throws ExceptionItemNotFound, ExceptionRuntime, ExceptionInvalidAction {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	appName			= null;
		
		boolean	fileNotFound	= false;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		try {
			
			appName = EnvironmentService.expandEnvironmentString (StringUtils.stripQuotes (alias.getValue()));
			
			fileNotFound = ! FileUtils.isFileExists (appName);
			
			// Open application...
			
			displayOpenMessage (alias, appName);
			
			if (alias.getType() == null)
				
				// See if appName is actually a shell type...
				
				if (ShellService.hasTypeAndCommand (appName))
					
					// Is a shell type. Launch type directly...

					ShellService.executeType (appName);
			
				else
				
					// Not a shell type
					
					// Execute command...
				
					ShellService.execute (appName);

			else
				
				ShellService.executeByType (alias.getType(), appName);

		}
		catch (ExceptionItemNotFound e) {
			
			// Alias document does not exist...
			if (fileNotFound) {
				
				AppUI.print (IODirComponentConst.MESSAGE_ALIAS_FILE_NOT_FOUND.cloneMe (alias.getName(), appName));
			}
			
			throw e;
		}
		catch (ExceptionRuntime e) {
			
			// Alias document does not exist...
			if (fileNotFound) {
				
				AppUI.print (IODirComponentConst.MESSAGE_ALIAS_FILE_NOT_FOUND.cloneMe (alias.getName(), appName));
			}
			
			throw e;
		}
	}

	
	public void openDirectory (Alias alias) throws ExceptionRuntime {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	fileName	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		fileName = EnvironmentService.expandEnvironmentString (StringUtils.stripQuotes (alias.getValue()));
		
		if (FileUtils.isFileExists (fileName)) {
		
			try {
				
				// Open directory...
				
				displayOpenMessage (alias, fileName);
				
				ShellService.executeByType (IShellConst.SHELL_TYPE_DIR, fileName);
			}
			catch (ExceptionItemNotFound e) {
				
				// Shell alias 'dir' does not exist...

				AppUI.print (e.getMessageList());
			}
		}
		else {
			
			// Alias directory does not exist...
			
			AppUI.print (IODirComponentConst.MESSAGE_ALIAS_DIRECTORY_NOT_FOUND.cloneMe (alias.getName(), fileName));
		}
	}

	
	public void openDoc (Alias alias) throws ExceptionRuntime, ExceptionItemNotFound {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	docName	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		docName = EnvironmentService.expandEnvironmentString (StringUtils.stripQuotes (alias.getValue()));
		
		if (isFileExists (docName)) {
			
			// Open application...
			
			displayOpenMessage (alias, docName);
			
			if (alias.getType() == null) {
				
				ShellService.executeByFileType (docName);
			}
			else {
				
				ShellService.executeByType (alias.getType(), docName);
			}
		}
		else {
			
			// Alias document does not exist...
			
			AppUI.print (IODirComponentConst.MESSAGE_ALIAS_FILE_NOT_FOUND.cloneMe (alias.getName(), docName));
		}
	}

	
	public void openWeb (Alias alias) throws ExceptionItemNotFound, ExceptionRuntime {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		String	webName	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////


		// Open web page...
		
		webName	=  EnvironmentService.expandEnvironmentString (alias.getValue());
		
		displayOpenMessage (alias, webName);
		
		if (alias.getType() == null) {
			
			ShellService.executeByType (IShellConst.SHELL_TYPE_BROWSER, webName);
		}
		else {
			
			ShellService.executeByType (alias.getType(), webName);
		}
	}
	
	
	public void openSubdirectory (String aliasName, String subdirectory) throws ExceptionRuntime {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias		alias				= null;

		String		parentDirectory		= null;
		String		completeDirectory	= null;
		
		StringList	directoryStringList	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		// Lookup parent alias...
		
		if (aliasName != null) {

			alias = this.aliasData.getAliasActual (aliasName);
		}
		
		if (alias != null) {

			parentDirectory		= EnvironmentService.expandEnvironmentString (alias.getValue());
			
			if (FileUtils.isFileExists (parentDirectory)) {
				
				// Find subdirectory...
				
				// Search for exact match...
				
				if (FileUtils.isFileExists (parentDirectory + FileUtils.getFileSeparator() + subdirectory)) {
					
					completeDirectory = parentDirectory + FileUtils.getFileSeparator() + subdirectory;
				}
				
				
				// Search for match starting with...
				
				if (completeDirectory == null) {
					
					directoryStringList = DirectoryUtils.getDirectoryFileStringList (parentDirectory, new FilenameFilterStartsWith (subdirectory));
					
					if (directoryStringList.size() > 0) {
						
						completeDirectory = parentDirectory + FileUtils.getFileSeparator() + directoryStringList.get (0);
					}
				}
				
				
				// Search for match containing...
				
				if (completeDirectory == null) {
					
					directoryStringList = DirectoryUtils.getDirectoryFileStringList (parentDirectory, new FilenameFilterContains (subdirectory));

					if (directoryStringList.size() > 0) {
						
						completeDirectory = parentDirectory + FileUtils.getFileSeparator() + directoryStringList.get (0);
					}
				}
					
			
				if (completeDirectory != null) {
					
					// Match found. Open...
					
					try {
						
						// Open directory...
						
						displayOpenMessage (alias, completeDirectory);
						
						ShellService.executeByType (IShellConst.SHELL_TYPE_DIR, completeDirectory);
					}
					catch (ExceptionItemNotFound e) {
						
						// Shell alias 'dir' does not exist...
		
						AppUI.print (e.getMessageList());
					}
				}
				else {
					
					// Subdirectory not found...
					
					AppUI.print (IODirComponentConst.MESSAGE_ALIAS_SUBDIRECTORY_NOT_FOUND.cloneMe (aliasName, subdirectory));
				}
			}
			else {
				
				// Parent directory does not exist...
				
				AppUI.print (IODirComponentConst.MESSAGE_ALIAS_DIRECTORY_NOT_FOUND.cloneMe (aliasName, parentDirectory));
			}

		}
		else {
			
			// Alias does not exist...
			
			AppUI.print (IODirComponentConst.MESSAGE_ALIAS_NOT_FOUND.cloneMe (aliasName));
		}
	}
	
	
	private boolean isFileExists (String fileName) {
		
		return FileUtils.isFileExists (fileName);
	}
	
	private void open (Alias alias) throws ExceptionItemNotFound, ExceptionRuntime, ExceptionInvalidAction {

		// Open alias dependant on type...

		switch (oDirType) {
			
			case IODirComponentConst.ODIR_TYPE_APP:
				
				openApp (alias);
				break;
				
			case IODirComponentConst.ODIR_TYPE_DIR:
				
				openDirectory (alias);
				break;
				
			case IODirComponentConst.ODIR_TYPE_DOC:
				
				openDoc (alias);
				break;
				
			case IODirComponentConst.ODIR_TYPE_WEB:
				
				openWeb (alias);
				break;
		}
	}
}
