package client.login;

import client.base.IOverlayView;


public interface ILoginView extends IOverlayView
{
	

	String getLoginUsername();
	

	String getLoginPassword();
	

	String getRegisterUsername();
	

	String getRegisterPassword();
	

	String getRegisterPasswordRepeat();
	
}

