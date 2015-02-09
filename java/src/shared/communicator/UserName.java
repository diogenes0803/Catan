package shared.communicator;

import java.util.regex.Pattern;


/**
 * Description: Contains username constraints etc.
 * @author Jonathan
 *
 */
public class UserName {
	String name;
	
	public UserName(String name)
	{
		if (validateName(name))
		{
			this.name = name;
		}
	}

	private boolean validateName(String name2) 
	{
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		boolean isAlphNum = p.matcher(name2).find();
		
		return isAlphNum;
	}
	
}
