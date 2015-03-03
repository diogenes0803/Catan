package client.communication;

import client.base.IView;

import java.util.List;

/**
 * Chat view interface
 */
public interface IChatView extends IView {

    /**
     * Sets the chat messages to be displayed in the view.
     *
     * @param entries The chat messages to display
     */
    void setEntries(List<LogEntry> entries);
}

