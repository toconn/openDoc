package test.unit;

import ua.app.all.AppUI;
import ua.app.all.IAppConst;
import ua.core.service.log.LogService;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.ui.all.UIFactory;
import ua.core.util.Message;


public class Test_ExceptionRuntime {

	/**
	 * @param args
	 */
	public static void main (String[] args) {

		// Setup...
		
		AppUI.init (UIFactory.getUIConsole (IAppConst.LOCALE_DEFAULT));
		LogService.init();
		
		// Throw exception
		
		try {
			
			throw new ExceptionRuntime (new Message ("", "Throwing test ExceptionRuntime"));
			
		}
		catch (ExceptionRuntime e) {
			
			AppUI.print ("ExceptionRuntime thrown.");
		}

	}

}
