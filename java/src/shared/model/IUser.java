package shared.model;

import java.io.Serializable;


public interface IUser extends Serializable {


    public String getUsername();


    public String getPassword();


    public int getId();
}
