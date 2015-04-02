package client.login;

import client.base.*;
import client.misc.*;
import client.network.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;




public class LoginController extends Controller implements ILoginController {
    private final static Logger logger = Logger.getLogger("catan");

	private IMessageView messageView;
	private IAction loginAction;
    private IGameAdministrator m_admin;
	

	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;

        m_admin = GameAdministrator.getInstance();
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	

	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	

	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
        String username = getLoginView().getLoginUsername();
        String password = getLoginView().getLoginPassword();

        boolean success = false;
        try {
            success = m_admin.login(username, password);
        } catch (NetworkException e) {
            logger.log(Level.WARNING, "Sign in failed.", e);
        }

		// If log in succeeded
        if (success) {
            getLoginView().closeTopModal();
            loginAction.execute();
        } else {
            getMessageView().showModal();
            getMessageView().setTitle("Error!");
            getMessageView().setMessage("Sign in failed.");
        }
	}

	@Override
	public void register() {
        String username = getLoginView().getRegisterUsername();
        String password = getLoginView().getRegisterPassword();
        String passwordRepeat = getLoginView().getRegisterPasswordRepeat();

        final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]{3,7}$");
        final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9_-]{5,}$");

        Matcher usernameMatch = usernamePattern.matcher(username);
        Matcher passwordMatch = passwordPattern.matcher(password);

        if(usernameMatch.matches() && passwordMatch.matches()) {
            if (password.equals(passwordRepeat)) {
                boolean success = false;
                try {
                    success = m_admin.register(username, password);
                } catch (NetworkException e) {
                    logger.log(Level.WARNING, "Register failed.", e);
                }

                // If register succeeded
                if (success) {
                    getLoginView().closeTopModal();
                    loginAction.execute();
                } else {
                    getMessageView().showModal();
                    getMessageView().setTitle("Error!");
                    getMessageView().setMessage("Register failed.");
                }
            } else {
                getMessageView().showModal();
                getMessageView().setTitle("Warning!");
                getMessageView().setMessage("Passwords don't match.");
            }
        } else {
            getMessageView().showModal();
            getMessageView().setTitle("Warning!");
            getMessageView().setMessage("Invalid username or password.");
        }
	}

    @Override
    public void update(Observable o, Object arg) {

    }
}

