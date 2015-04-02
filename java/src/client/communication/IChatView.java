package client.communication;

import client.base.IView;

import java.util.List;


public interface IChatView extends IView
{
	

	void setEntries(List<LogEntry> entries);
}

