package client.communication;

import java.util.List;

import client.base.*;


public interface IChatView extends IView
{
	

	void setEntries(List<LogEntry> entries);
}

