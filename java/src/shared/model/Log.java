package shared.model;

import client.communication.LogEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Log implements ILog, Iterable<Log.LogMessage> {
    private List<LogMessage> m_log;


    public Log() {
        m_log = new ArrayList<>();
    }


    @Override
    public void addMessage(IPlayer player, String message) {
        assert player != null && message != null;
        m_log.add(new LogMessage(player, message));
    }


    @Override
    public List<LogEntry> getLogEntries() {
        List<LogEntry> logEntries = new ArrayList<>(m_log.size());

        for (LogMessage message : m_log) {
            logEntries.add(new LogEntry(message.getPlayer().getColor(), message.getMessage()));
        }

        return logEntries;
    }


    @Override
    public Iterator<LogMessage> iterator() {
        return Collections.unmodifiableCollection(m_log).iterator();
    }

    public class LogMessage implements ILog.ILogMessage {
        private IPlayer m_player;
        private String m_message;


        private LogMessage(IPlayer player, String message) {
            m_player = player;
            m_message = message;
        }


        @Override
        public IPlayer getPlayer() {
             return m_player;
        }


        @Override
        public String getMessage() {
            return m_message;
        }
    }
}
