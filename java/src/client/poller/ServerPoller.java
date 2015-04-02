package client.poller;

import client.network.IServerProxy;
import client.network.NetworkException;
import shared.model.ModelException;
import shared.model.ModelInitializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerPoller implements IServerPoller {
    private final static Logger logger = Logger.getLogger("catan");

    private static final int c_millisecondsPerSecond = 1000;
    private static final int c_defaultPollingSeconds = 3;

    private IServerProxy m_serverProxy;
    private Timer m_timer;
    private int m_pollCount = 0;

    private static ServerPoller m_serverPoller = null;

    public static ServerPoller getInstance() {
        if (m_serverPoller == null) {
            m_serverPoller = new ServerPoller();
        }

        return m_serverPoller;
    }

    private ServerPoller() {
        m_timer = new Timer(c_defaultPollingSeconds * c_millisecondsPerSecond, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                ++m_pollCount;
            }
        });
    }

    public void startPolling() {
        m_timer.start();
    }

    public void stopPolling() {
        m_timer.stop();
    }

//    public ServerPoller(IServerProxy serverProxy) {
//        this(serverProxy, c_defaultPollingSeconds);
//    }
//
//    public ServerPoller(IServerProxy serverProxy, int secondsBetweenPolls) {
//        m_serverProxy = serverProxy;
//        m_modelSerializer = new ModelInitializer();
//        m_timer = new Timer(c_defaultPollingSeconds * c_millisecondsPerSecond, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateGame();
//                ++m_pollCount;
//            }
//        });
//        m_timer.start();
//    }
    @Override
    public void updateGame() {
        try {
            new ModelInitializer().initializeClientModel(m_serverProxy.getGameState(), m_serverProxy.getPlayerId());
        } catch (ModelException | NetworkException e) {
            logger.log(Level.WARNING, "Polling failed.", e);
        }
    }

    @Override
    public void setProxy(IServerProxy serverProxy) {
        m_serverProxy = serverProxy;
    }

    public int getPollCount() {
        return m_pollCount;
    }


}
