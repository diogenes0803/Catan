package client.base;


public interface IOverlayView extends IView
{
	

	void showModal();
	

	void closeTopModal();


    void closeThisModal();


	boolean isModalShowing();
}

