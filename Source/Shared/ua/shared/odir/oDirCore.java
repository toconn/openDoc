package ua.shared.odir;


import ua.app.all.AppUI;
import ua.core.service.shell.ShellService;
import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.IExceptionConst;


public class oDirCore {
	

	public static void editConfig (oDirComponent oDirModel) {
		
		try {
			
			oDirModel.editDataFile();
		}
		catch (ExceptionRuntime e) {
	
			AppUI.print (e.getMessageList());
		}
		catch (ExceptionItemNotFound e) {
	
			AppUI.print (IExceptionConst.MESSAGE_FILE_NOT_FOUND.cloneMe (oDirModel.getDataFileName()));
		}
	}
	

	public static void editShell() {
		
		try {
			
			ShellService.executeByFileType (ShellService.getConfigFileName());
		}
		catch (ExceptionRuntime e) {
	
			AppUI.print (e.getMessageList());
		}
		catch (ExceptionItemNotFound e) {
	
			AppUI.print (IExceptionConst.MESSAGE_FILE_NOT_FOUND.cloneMe (ShellService.getConfigFileName()));
		}
	}
}
