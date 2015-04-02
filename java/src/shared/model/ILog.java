package shared.model;

import client.communication.LogEntry;

import java.io.Serializable;
import java.util.List;


public interface ILog extends Serializable {

    public void addMessage(IPlayer player, String message);


    public List<LogEntry> getLogEntries();


    public interface ILogMessage extends Serializable {

        public IPlayer getPlayer();


        public String getMessage();
    }
}