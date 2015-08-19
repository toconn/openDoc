package ua.shared.alias;

import ua.core.util.IFilter;
import ua.core.util.StringUtils;


public class AliasFilterMatchString implements IFilter {

	private String	containsText	= null;
	
	
	public AliasFilterMatchString (String containsText) {
		
		this.containsText = containsText;
	}
	
	public boolean accept (Object data) {
		
		if (data instanceof Alias) {
			
			return (StringUtils.contains (((Alias) data).getName(), this.containsText) || StringUtils.contains (((Alias) data).getDescription(), this.containsText));
		}
		else {
			
			return false;
		}
	}
}
