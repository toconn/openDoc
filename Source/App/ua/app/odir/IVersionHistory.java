package ua.app.odir;

public class IVersionHistory {

	public static String[][] VERSION_HISTORY_ARRAY_ARRAY = {
		
		{"1.00.00", "2011-01-11", "Coding proper starts.",			"TOC"},	
		{"1.00.00", "2011-03-07", "v01.00.00 released.",			"TOC"},	
		{"1.01.00", "2011-03-10", "Updated help messages.",			"TOC"},	
		{"1.02.00", "2011-04-14", "Restructured code to combine udir functions across applications.",			"TOC"},	
		{"1.02.00", "2011-04-14", "Removed requirements for escape characters in data files.",					"TOC"},	
		{"1.02.00", "2011-04-14", "Removed requirements for ',' in data files.",								"TOC"},	
		{"1.03.00", "2011-06-24", "Added embedded environment variables in commands.",							"TOC"},	
		{"1.03.00", "2011-06-24", "Added search across multiple paths in environment (separated by ;).",		"TOC"},	
		{"1.03.00", "2011-06-24", "Added aliasing within data, shell files.",									"TOC"},	
		{"1.03.01", "2011-06-24", "Changed component configuration reporting settings.",						"TOC"},	
		{"1.03.02", "2011-06-30", "Updated opening message (now displays description).",						"TOC"},	
		{"1.03.02", "2011-06-30", "Environment variable dereferencing moved from Shell to uDir classes.",		"TOC"},
		{"1.03.02", "2011-06-30", "Shell service updated to dereference only shell command, not parameters.",	"TOC"},
		{"1.03.03", "2011-07-05", "Installed latest Shell Service.",											"TOC"},
		{"1.03.04", "2011-07-09", "Fixed subdirectory double alias bug.",										"TOC"},
		{"1.03.05", "2011-07-19", "Added -list.match option.",													"TOC"},
		{"1.03.06", "2011-07-21", "Changed uApp to continue when file does not exist but report it only on an error.",	"TOC"},
		{"1.03.07", "2011-07-21", "Fixed extra skip line bug on -list option.",									"TOC"},
		{"1.03.07", "2011-07-21", "Added ability for uApp to execute shell types without parameters.",			"TOC"},
		{"1.03.08", "2011-07-27", "Minor core updates and error handling message updates.",						"TOC"},
		{"1.04.00", "2012-07-15", "Restructored Directories (split dependencies into separate folders.",		"TOC"},
		{"1.04.00", "2012-07-15", "Renamed uX to oX for openX.",												"TOC"},
		{"1.04.00", "2012-07-15", "Changed '-list.match' to '-match'. This is simpler and quicker",				"TOC"},
		{"1.04.01", "2013-04-01", "Dropped superfluous empty lines in console output (minor).",					"TOC"},
		{"1.04.02", "2013-04-22", "Reworked execute method to handle commands with spaces (once and for all).",	"TOC"},
		{"1.04.03", "2013-05-28", "Added check for OS X environment variables (names with underscores).",		"TOC"},
		{"1.04.04", "2013-05-29", "Fixed bug where app would crash if no environment variables.",				"TOC"},
		{"1.04.05", "2013-07-23", "Fixed OS X bug where class directories had 'file:' prepended to them.",		"TOC"},
	};
}
