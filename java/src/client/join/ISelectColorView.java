package client.join;

import client.base.*;
import shared.definitions.*;


public interface ISelectColorView extends IOverlayView
{
	

	void setColorEnabled(CatanColor color, boolean enable);
	

	CatanColor getSelectedColor();

    void refresh();
}

