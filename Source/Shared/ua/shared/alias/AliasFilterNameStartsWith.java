package ua.shared.alias;

import ua.core.util.IFilter;
import ua.core.util.StringUtils;


public class AliasFilterNameStartsWith implements IFilter {

	private String	startsWith	= null;
	
	
	public AliasFilterNameStartsWith (String startsWith) {
		
		this.startsWith = startsWith;
	}
	
	public boolean accept (Object data) {
		
		if (data instanceof Alias) {
			
			return StringUtils.isStartsWith (((Alias) data).getName(), this.startsWith);
		}
		else {
			
			return false;
		}
	}
}
