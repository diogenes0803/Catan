package client.join;

import client.base.*;


public interface INewGameView extends IOverlayView
{
	

	void setTitle(String value);
	

	String getTitle();
	

	void setRandomlyPlaceNumbers(boolean value);
	

	boolean getRandomlyPlaceNumbers();
	

	void setRandomlyPlaceHexes(boolean value);
	

	boolean getRandomlyPlaceHexes();
	

	void setUseRandomPorts(boolean value);
	

	boolean getUseRandomPorts();
	
}

