package shared.models;

/**
 * UserManager Model contains information about the user that is logged in and has functionality to register new user
 * and login, out
 *
 * @author HojuneYoo
 */

public class UserManager {

    private boolean isLoggedIn;
    private User loggedInUser;

    public UserManager() {
        isLoggedIn = false;
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        isLoggedIn = true;
    }


}
