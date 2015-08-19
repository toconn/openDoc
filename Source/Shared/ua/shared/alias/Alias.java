package ua.shared.alias;

import ua.core.util.IName;

public class Alias implements IName {
	
	private String name			= null;
	private String value		= null;
	private String description	= null;
	private String type			= null;
	
	public Alias (String name, String description, String type, String value) {
		
		this.name = name;
		this.type = type;
		this.value = value;

		this.description = description;
	}
	
	public Alias cloneMe() {
		
		return new Alias (name, description, type, value);
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String getValue() {
		
		return value;
	}

	public void setValue (String value) {
		
		this.value = value;
	}

	public String getDescription() {
		
		return description;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public String getType() {
		
		return type;
	}

	public void setType(String type) {
		
		this.type = type;
	}

	public String toString() {
		
		return "Alias [name=" + name + ", description=" + description + ", type=" + type + ", value=" + value + "]";
	}
}
