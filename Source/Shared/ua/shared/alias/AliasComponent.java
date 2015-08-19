package ua.shared.alias;

import java.util.ArrayList;
import java.util.List;

import ua.core.exceptions.ExceptionItemNotFound;
import ua.core.exceptions.ExceptionRuntime;
import ua.core.util.CollectionUtils;
import ua.core.util.IFilter;
import ua.core.util.StringList;
import ua.core.util.StringParser;
import ua.core.util.StringUtils;
import ua.core.util.UaMap;
import ua.core.util.file.UaFileReader;


public class AliasComponent {
	
	public UaMap <Alias>		aliasMap			= new UaMap<>();
	public UaMap <List<Alias>>	aliasDuplicatesMap	= new UaMap<>();
	
	
	public AliasComponent() {	}
	
	public AliasComponent (String fileName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		if (fileName != null) {			
			load (fileName);
		}
	}

	public AliasComponent (String fileName, String sectionName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		if (fileName != null) {
			load (fileName, sectionName);
		}
	}
	
	
	public AliasComponent (StringList fileNameList) throws ExceptionItemNotFound, ExceptionRuntime {
		
		this.aliasMap = new UaMap <Alias>();
		
		if (fileNameList != null) {
			
			for (String fileName: fileNameList) {

				load (fileName);
			}
		}
	}
	
	
	public AliasComponent (StringList fileNameList, String sectionName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		this.aliasMap = new UaMap <Alias>();
		
		if (fileNameList != null) {
			
			for (String fileName: fileNameList) {
			
				load (fileName, sectionName);
			}
		}
	}
	
	
	public AliasComponent (UaMap <Alias> aliasMap) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias	alias	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		// Clone data into component alias map.

		this.aliasMap = new UaMap <Alias>();
		
		for (String aliasName : aliasMap.keySet()) {
			
			alias = aliasMap.get (aliasName);
			
			if (alias != null) {
				
				this.aliasMap.put (aliasName, alias.cloneMe());
			}
		}
	}

	
	/**
	 * Returns the alias in its final form. This method will dereference double referenced aliases.
	 * 
	 * @param aliasName
	 * @return
	 */
	public Alias getAliasActual (String aliasName) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias alias;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		alias = getAlias (aliasName);
		
		
		// Check for double alias...
		
		if (alias != null && hasAlias (alias.getValue()) && ! StringUtils.isEqual (alias.getName(), alias.getValue())) {
			
			// Double alias found.
			
			// Look up actual alias...
			
			alias = getAlias (alias.getValue());
		}
		
		return alias;
	}
	
	public Alias getAliasFirstMatch (String aliasName) {
		
		Alias alias;
		List <Alias> aliases;
		
		IFilter filter;
		
		filter = new AliasFilterNameStartsWith (aliasName);
		aliases = getAliases (filter);
		
		if (CollectionUtils.isNonEmpty (aliases)) {
			alias = aliases.get (0);
		}
		else {
			alias = null;
		}
		
		return alias;
	}
	
	public String getValue (String aliasName) {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias		alias	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		alias = getAlias (aliasName);

		if (alias != null) {
			
			return alias.getValue();
		}
		else {
			
			return null;
		}
	}
	
	public Alias getAlias (String aliasName) {
		
		return this.aliasMap.get (aliasName);
	}
	
	public ArrayList <Alias> getAliases() {

		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias				alias		= null;
		ArrayList <Alias>	aliasList	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		aliasList	= new ArrayList <Alias>();
		
		for (String key : this.aliasMap.getSortedKeyList()) {
			
			alias = this.aliasMap.get (key);
			aliasList.add (alias.cloneMe());
		}

		return aliasList;
	}
	
	public List <Alias> getAliases (IFilter filter) {

		Alias			alias;
		List <Alias>	aliases;
		
		aliases = new ArrayList<>();
				
		for (String key : this.aliasMap.getSortedKeyList()) {
			
			alias = this.aliasMap.get (key);
			
			if (filter.accept (alias)) {
				aliases.add (alias);
			}
		}
		
		return aliases;
	}	
	
	public List <StringList> getAliasStringListList() {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		List <Alias>		aliases;
		List <StringList>	valueStringListList;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		aliases = getAliases();
		
		valueStringListList	= new ArrayList <StringList>();
		
		for (Alias alias : aliases) {

			valueStringListList.add (new StringList (new String[] {alias.getName(), alias.getDescription(), alias.getType(), alias.getValue()}));
		}

		return valueStringListList;
	}
	
	public List <StringList> getAliasStringListList (IFilter filter) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		List <Alias>		aliases;
		List <StringList>	valueStringListList;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////
		
		aliases = getAliases (filter);
		

		valueStringListList	= new ArrayList <StringList>();
		
		for (Alias alias : aliases) {
			valueStringListList.add (new StringList (new String[] {alias.getName(), alias.getDescription(), alias.getType(), alias.getValue()}));
		}

		return valueStringListList;
	}
	
	
	public boolean hasAlias (String aliasName) {
		
		if (aliasName != null) {
		
			return this.aliasMap.hasKey (aliasName);
		}
		else {
			
			return false;
		}
	}
	
	
	/**
	 * Add a new alias to the alias collection.
	 * 
	 * @param alias
	 */
	public void put (Alias alias) {
	
		if (alias != null && alias.getName() != null) {
			this.aliasMap.put (alias.getName(), alias);
		}
	}

	
	/**
	 * Add a new alias to the alias collection.
	 * 
	 * @param aliasName
	 * @param description
	 * @param type
	 * @param value
	 */
	public void put (String aliasName, String description, String type, String value) {
		
		//////////////////////////////////////////////////////////////////
		// Declarations
		//////////////////////////////////////////////////////////////////

		Alias	alias	= null;


		//////////////////////////////////////////////////////////////////
		// Code
		//////////////////////////////////////////////////////////////////

		alias = new Alias (aliasName, description, type,  value);
		this.aliasMap.put (aliasName, alias);
	}


	private void addAlias (UaMap <Alias> aliasMap, UaMap <List <Alias>> aliasDuplicatesMap, Alias alias) {
		
		String aliasName;
		List <Alias> duplicatesList;
		
		aliasName = alias.getName();
		
		// Check for and Manage Duplicates

		if (aliasMap.containsKey (aliasName)) {
			
			if (aliasDuplicatesMap.containsKey (aliasName)) {
				
				// Pre-existing duplicate:
				
				duplicatesList = aliasDuplicatesMap.get (aliasName);
			}
			else {
				
				// New duplicate:
				
				duplicatesList = new ArrayList<>();
				duplicatesList.add (aliasMap.get (aliasName));
			}
			
			duplicatesList.add (alias);
		}
		
		// Add Alias.
		
		aliasMap.put (aliasName, alias);
	}
	
	private void load (String fileName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		UaFileReader			fileReader			= null;
		String					lineString			= null;
		StringList				lineParseStringList	= null;
		
		StringParser			parser				= null;
		
		Alias					alias				= null;
		
		UaMap <Alias>			aliasMap			= null;
		UaMap <List <Alias>>	aliasDuplicatesMap	= null;
		

		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		// Find File...
		
		parser = new StringParser (IAliasConst.FILE_SEPARATOR_STRING_LIST);
		
		try {
		
			aliasMap = new UaMap<>();
			aliasDuplicatesMap = new UaMap<>();
			
			// Open File...
			
			fileReader = new UaFileReader (fileName);
			fileReader.open();
			
			lineString = fileReader.readLine();
			
			while (lineString != null) {
				
				if (StringUtils.isEmpty (lineString) || StringUtils.isStartsWith (lineString, "#") || StringUtils.isStartsWith (lineString, "//")) {
					
					// #, //, Comment, Blank
					
					// Ignore.
				}
				else if (StringUtils.isStartsWith (lineString, IAliasConst.HEADER_OPEN_CHAR) && StringUtils.isEndsWith (lineString, IAliasConst.HEADER_CLOSE_CHAR)) {
					
					// new section...
					
					// Ignore.
				}
				else {
					
					// Process line...

					lineParseStringList = parser.parse (lineString);
					
					if (lineParseStringList.size() == 2) {
						
						// Probably an alias. (description is optional).
						
						alias = new Alias (lineParseStringList.get (0), null, null, lineParseStringList.get (1));
					
						// Store...
						
						addAlias (aliasMap, aliasDuplicatesMap, alias);
					}
					if (lineParseStringList.size() == 3) {
						
						alias = new Alias (lineParseStringList.get (0), lineParseStringList.get (1), null, lineParseStringList.get (2));
					
						// Store...
						
						addAlias (aliasMap, aliasDuplicatesMap, alias);
					}
					else if (lineParseStringList.size() >= 4) {
						
						alias = new Alias (lineParseStringList.get (0), lineParseStringList.get (1), lineParseStringList.get (2), lineParseStringList.get (3));
					
						// Store...
						
						addAlias (aliasMap, aliasDuplicatesMap, alias);
					}
				}
				
				// Read next line...
				
				lineString = fileReader.readLine();
			};

			this.aliasMap.putAll (aliasMap);
			this.aliasDuplicatesMap.putAll (aliasDuplicatesMap);
		}
		finally {
			
			// Close file...
			
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
		
	private void load (String fileName, String sectionName) throws ExceptionItemNotFound, ExceptionRuntime {
		
		///////////////////////////////////////////
		// Declarations:
		///////////////////////////////////////////
		
		UaFileReader			fileReader			= null;
		String					lineString			= null;
		StringList				lineParseStringList	= null;
		
		boolean					inSection			= false;
		
		StringParser			parser				= null;
		
		Alias					alias				= null;
		
		UaMap <Alias>			aliasMap			= null;
		UaMap <List <Alias>>	aliasDuplicatesMap	= null;

		
		///////////////////////////////////////////
		// Code:
		///////////////////////////////////////////
		
		// Find File...
		
		parser = new StringParser (IAliasConst.FILE_SEPARATOR_STRING_LIST);
		
		try {
			
			aliasMap = new UaMap<>();
			aliasDuplicatesMap = new UaMap<>();
			
			// Open File...
			
			fileReader = new UaFileReader (fileName);
			fileReader.open();
			
			lineString = fileReader.readLine();
			
			while (lineString != null) {
				
				if (StringUtils.isEmpty (lineString) || StringUtils.isStartsWith (lineString, "#") || StringUtils.isStartsWith (lineString, "//")) {
					
					// #, //, Comment, Blank
					
					// Ignore.
				}
				else if (StringUtils.isStartsWith (lineString, IAliasConst.HEADER_OPEN_CHAR) && StringUtils.isEndsWith (lineString, IAliasConst.HEADER_CLOSE_CHAR)) {
					
					// new section...
					
					if (StringUtils.isEqual (lineString, IAliasConst.HEADER_OPEN_CHAR + sectionName + IAliasConst.HEADER_CLOSE_CHAR)) {
					
						// Inside correct section...
						inSection = true;
					}
					else {
						
						inSection = false;
					}
				}
				else {
					
					// Process line...
					
					if (inSection) {
					
						lineParseStringList = parser.parse(lineString);
						
						if (lineParseStringList.size() == 3) {
							
							alias = new Alias (lineParseStringList.get (0), lineParseStringList.get (1), null, lineParseStringList.get (2));
						
							// Store...
							
							addAlias (aliasMap, aliasDuplicatesMap, alias);
						}
						else if (lineParseStringList.size() >= 4) {
							
							alias = new Alias (lineParseStringList.get (0), lineParseStringList.get (1), lineParseStringList.get (2), lineParseStringList.get (3));
						
							// Store...
							
							addAlias (aliasMap, aliasDuplicatesMap, alias);
						}
					}
				}
				
				// Read next line...
				
				lineString = fileReader.readLine();
			};
			
			this.aliasMap.putAll (aliasMap);
			this.aliasDuplicatesMap.putAll (aliasDuplicatesMap);
		}
		finally {
			
			// Close file...
			
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
}
