package client.communication;

import java.util.List;

import client.base.*;


public interface IGameHistoryView extends IView
{
	

	void setEntries(List<LogEntry> entries);
}

