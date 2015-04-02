package client.communication;

import client.base.IView;

import java.util.List;


public interface IGameHistoryView extends IView
{
	

	void setEntries(List<LogEntry> entries);
}

