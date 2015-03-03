package shared.models;

/**
 * Model that contains User informations
 *
 * @author HojuneYoo
 */

public class User {

    private int userId;
    private String userName;

    public User() {
        userId = 0;
        userName = "";
    }

    public User(String user, int id) {
        userId = id;
        userName = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
