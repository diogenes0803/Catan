package client.main;

import javax.swing.*;

import client.catan.*;
import client.login.*;
import client.join.*;
import client.misc.*;
import client.base.*;
import client.network.*;
import client.poller.ServerPoller;
import shared.model.Game;
import shared.model.GameModelFacade;
import shared.model.ServerModelFacade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;


@SuppressWarnings("serial")
public class Catan extends JFrame
{
	private static Logger logger;

    private static Catan instance;

    private static IHttpCommunicator communicator;

    static {
        initializeLog();
    }


    private static void initializeLog() {
        try {
            final Level logLevel = Level.FINE;

            logger = Logger.getLogger("catan");
            logger.setLevel(logLevel);
            logger.setUseParentHandlers(false);

            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(logLevel);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);

            String startTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());

            if (!Files.exists(Paths.get("logs"))) {
                Files.createDirectory(Paths.get("logs"));
            }
            FileHandler fileHandler = new FileHandler("logs/log_" + startTime + ".log", false);
            fileHandler.setLevel(logLevel);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.info("Catan Client started at " + startTime + ".");
        } catch (IOException e) {
            System.err.println("There was a problem initializing the log: " + e.getMessage());
        }
    }

    private CatanPanel catanPanel;

    public Catan() {
        client.base.OverlayView.setWindow(this);

        this.setTitle("Settlers of Catan");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        catanPanel = new CatanPanel();
        this.setContentPane(catanPanel);
		
		display();
	}

    public void setupNetworkCommunication() {
        // Initialize the servier proxies
        GameAdminServerProxy gameAdminProxy = new GameAdminServerProxy(communicator);
        GameAdministrator.getInstance().setGameAdminServerProxy(gameAdminProxy);

        ServerProxy proxy = new ServerProxy(communicator);
        ServerModelFacade.getInstance().setServerProxy(proxy);
        ServerPoller.getInstance().setProxy(proxy);
    }

    public void initializeGUI(boolean showLoginDialog) {
        PlayerWaitingView playerWaitingView = new PlayerWaitingView();
        final PlayerWaitingController playerWaitingController = new PlayerWaitingController(playerWaitingView);
        playerWaitingView.setController(playerWaitingController);

        JoinGameView joinView = new JoinGameView();
        NewGameView newGameView = new NewGameView();
        SelectColorView selectColorView = new SelectColorView();
        MessageView joinMessageView = new MessageView();
		final JoinGameController joinController = new JoinGameController(
                joinView,
                newGameView,
                selectColorView,
                joinMessageView);

        joinController.setJoinAction(new IAction() {
            @Override
            public void execute()
            {
                ServerPoller.getInstance().updateGame();
                ServerPoller.getInstance().startPolling();
                playerWaitingController.start();
            }
        });
        joinView.setController(joinController);
        newGameView.setController(joinController);
        selectColorView.setController(joinController);
        joinMessageView.setController(joinController);

        LoginView loginView = new LoginView();
        MessageView loginMessageView = new MessageView();
        LoginController loginController = new LoginController(loginView,
                loginMessageView);
        loginController.setLoginAction(new IAction() {
            @Override
            public void execute()
            {
                joinController.start();
            }
        });
        loginView.setController(loginController);

        if (showLoginDialog) {
            loginController.start();
        }
        else {
            joinController.start();
        }
    }

    public static void leaveGame() {
        assert instance != null : "Never initialized Catan instance.";
        ServerPoller.getInstance().stopPolling();
        GameModelFacade.instance().newGame();
        communicator.clearGameCookie();

        instance.dispose();

        instance = new Catan();
        instance.setupNetworkCommunication();
        instance.initializeGUI(false);
    }
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

        // Could easily add command line arguments for server IP and port
        communicator = new HttpCommunicator();

        instance = new Catan();
        instance.setupNetworkCommunication();

        // allow for using the test proxy
        if (args.length == 1 && args[0].equals("--fake-poller")) {
            ServerPoller poller = ServerPoller.getInstance();
            poller.setProxy(new TestServerProxy());
            poller.startPolling();
            poller.updateGame();
        }

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                instance.initializeGUI(true);
			}
		});
	}
}

