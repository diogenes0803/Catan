package client.login;

import java.util.Observable;

import shared.communicator.RegisterUserParams;
import shared.communicator.RegisterUserResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;
import client.base.Controller;
import client.base.IAction;
import client.communication.ServerProxy;
import client.misc.IMessageView;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// TODO: log in user
		String username = getLoginView().getLoginUsername();
		String password = getLoginView().getLoginPassword();
		UserLoginResults result = ServerProxy.getInstance().userLogin(new UserLoginParams(username, password));
		if(result.isSuccess()) {

		// If log in succeeded
		getLoginView().closeModal();
		loginAction.execute();
		}
		
		else {
			messageView.setTitle("Error!");
			messageView.setMessage("Sign in failed.");
			messageView.showModal();
		}
		
	}

	@Override
	public void register() {
		
		// TODO: register new user (which, if successful, also logs them in)
		if(getLoginView().getRegisterPassword().equals(getLoginView().getRegisterPasswordRepeat())) {
			String username = getLoginView().getRegisterUsername();
			String password = getLoginView().getRegisterPassword();
			RegisterUserResults result = ServerProxy.getInstance().registerUser(new RegisterUserParams(username, password));
			if(result.isSuccess()) {
				// If register succeeded
				getLoginView().closeModal();
				loginAction.execute();
			}
			else {
				messageView.setTitle("Warning!");
				messageView.setMessage("Invalid username or password.");
				messageView.showModal();
			}
		}
		else {
			messageView.setTitle("Warning!");
			messageView.setMessage("Invalid username or password.");
			messageView.showModal();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

