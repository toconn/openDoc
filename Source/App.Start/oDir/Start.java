import ua.core.ui.all.UIFactory;

import ua.app.all.*;
import ua.core.service.environment.*;
import ua.core.service.log.LogService;
import ua.core.service.parameters.*;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.exceptions.ExceptionValidation;


public class Start {

	public static void main (String paramArray[]) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations:
		//////////////////////////////////////////////////////////////////
		
		IApp app;
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		// app = new ua.app.oapp.OAppApp();
		app = new ua.app.odir.ODirApp();
		// app = new ua.app.odoc.ODocApp();
		// app = new ua.app.oweb.OWebApp();
		
		// Init System ///////////////////////////////////////////////////
		
		AppUI.init (UIFactory.getUIConsole (IAppConst.LOCALE_DEFAULT));
		
		AppStore.appSettings.setProperty (IAppConst.DIR_APP,				EnvironmentService.getAppDir (Start.class));
		AppStore.appSettings.setProperty (IAppConst.DIR_WORKING,			EnvironmentService.getWorkingDir());
		AppStore.appSettings.setProperty (IAppConst.DIR_DATA_USER_APPS, 	EnvironmentService.getAppDataUserAllDir());
		
		LogService.init();

		 
		// Run Appliction ////////////////////////////////////////////////
		
		try {
		
			AppUI.print();
			
			
    		// Init App....
    		
    		app.init();
    		
    		
    		// Process Parameters...
    		
    		AppStore.paramMap = ParamService.parse (AppStore.paramDefPack, paramArray);
    		
    		
    		// Process Configuration...
    		
    		app.config();
    		
    		
    		// Display Help, Info...
    		
    		AppService.showHelp (AppStore.paramMap);
    		
    		
    		// Start App...
    		
    		app.run();
		}
		
		
		//////////////////////////////////////////////////////////////////
		// Code:
		//////////////////////////////////////////////////////////////////
		
		catch (ExceptionValidation e) {
			
			LogService.error (e.getMessageList());
			
		}
		catch (ExceptionRuntime e) {
			
			// Do nothing. Should be logged already.
		}

	}
}
