package client.base;

import javax.swing.*;


@SuppressWarnings("serial")
public class PanelView extends JPanel implements IView
{
	
	private IController controller;
	
	public IController getController()
	{
		return controller;
	}
	
	public void setController(IController controller)
	{
		this.controller = controller;
	}
	
}

