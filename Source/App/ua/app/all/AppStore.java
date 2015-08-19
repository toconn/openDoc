package ua.app.all;

import ua.core.service.parameters.*;
import ua.core.util.*;

public class AppStore {
		
	// App Data...
	
	public static UaProperties 	appInfo 					= new UaProperties();
	public static UaProperties 	appSettings 				= new UaProperties();
	public static UaProperties 	configSettings 				= new UaProperties();
	public static UaMap <Param>	paramMap					= new UaMap <Param>();
	public static ParamDefPack	paramDefPack				= null;
	public static String[][]	versionHistoryArrayArray	= null;
}
