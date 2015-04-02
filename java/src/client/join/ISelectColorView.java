package client.join;

import client.base.IOverlayView;
import shared.definitions.CatanColor;


public interface ISelectColorView extends IOverlayView
{
	

	void setColorEnabled(CatanColor color, boolean enable);
	

	CatanColor getSelectedColor();

    void refresh();
}

