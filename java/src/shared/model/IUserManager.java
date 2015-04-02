package shared.model;


public interface IUserManager {


    public IUser createUser(String username, String password);


    public boolean doesUserExist(String username);


    public IUser loginUser(String username, String password);


    public IUser getUser(int id) throws ModelException;


    public void loadUser(IUser user) throws ModelException;
}
