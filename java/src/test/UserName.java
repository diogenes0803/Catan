package test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;




/**
 * Description: Contains username constraints etc.
 * @author dbilleter (appropriated it for junit testing),Jonathan
 *
 */
public class UserName {
    String name;
    
    public UserName(String name){
        assertTrue("Name cannot be null.",name != null);
        assertTrue("Name cannot be empty.", name.length() > 0);
        if (validateName(name)){
            this.name = name;
        }else
            fail("Error: Username '"+name+"' has a non-AlphaNumeric character.");
    }

    private boolean validateName(String name2) 
    {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasNonAlphaNum = p.matcher(name2).find();
        
        return !hasNonAlphaNum;
    }

    public String getName() {
        return name;
    }
    
    
    public String toString(){return getName();}
    
}

