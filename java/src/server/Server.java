package server;

import com.sun.net.httpserver.HttpServer;
import server.command.IllegalCommandException;
import server.debug.SwaggerHandler;
import server.facade.*;
import server.handler.*;
import server.persistence.*;
import shared.communication.*;
import shared.model.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;


public class Server {
	private static Logger logger;
	private static int MAX_WAITING_CONNECTIONS = 0; // take the default value
    public static int DEFAULT_PORT = 8081;
    public static int DEFAULT_COMMANDS_BETWEEN_CHECKPOINTS = 10;

	// Initialize the logger for the server
	static {
		initializeLog();
	}
	

	public static void initializeLog() {
		try {
			final Level logLevel = Level.FINE;
			
			logger = Logger.getLogger("catanserver");
			logger.setLevel(logLevel);
			logger.setUseParentHandlers(false);
			
			Handler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.FINEST); // the handlers display all messages: logger's level is what is changed
			consoleHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(consoleHandler);

			String startTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
			
			if (!Files.exists(Paths.get("logs"))) {
				Files.createDirectory(Paths.get("logs"));
			}
			FileHandler fileHandler = new FileHandler("logs/log_" + startTime + ".txt", false);
			fileHandler.setLevel(Level.FINEST);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			
			logger.info("Logging began at " + startTime + " at level " + logLevel.getName() + ".");
		} catch (IOException e) {
			System.err.println("There was a problem initializing the log: " + e.getMessage());
		}
	}
	
	// The server
	private HttpServer server;
	
	public Server() {}

    public HttpServer run() throws InvalidPluginException {
        IPersistenceManagerLoader loader = new PersistenceManagerLoader();
        return run(DEFAULT_PORT, loader.createPersistenceManager(DEFAULT_COMMANDS_BETWEEN_CHECKPOINTS));
    }


	public HttpServer run(int portNumber, IPersistenceManager persistenceManager) throws InvalidPluginException {
		logger.entering("server.Server", "run");
		
		try {
			logger.info("Initializing server.");
			server = HttpServer.create(new InetSocketAddress(portNumber), MAX_WAITING_CONNECTIONS);
		}
        catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			logger.severe("The server cannot initialize. Terminating.");
			logger.exiting("server.Server", "run");
			return null;
		}

        setupHandlers(server, persistenceManager);

		logger.info("Starting server on port " + portNumber + ".");
		server.start();
		
		logger.exiting("server.Server", "run");

        return server;
	}


    private void setupHandlers(HttpServer server, IPersistenceManager persistenceManager) {

        // load the persistent data
        IGameManager gameManager;
        IUserManager userManager;

        try {
            persistenceManager.startTransaction();
            userManager = persistenceManager.createUsersDAO().loadUsers();
            gameManager = persistenceManager.createGamesDAO().loadGames();
            persistenceManager.endTransaction(true);
        }
        catch (PersistenceException e) {
            persistenceManager.endTransaction(false);
            userManager = new UserManager();
            gameManager = new GameManager();
            logger.log(Level.WARNING, "Failed to load persistence data from disk.", e);
        }

        // setup the facades
        final IUserFacade userFacade = new UserFacade(userManager, persistenceManager);
        final IJoinGameFacade joinGameFacade = new JoinGameFacade(gameManager, userManager, persistenceManager);
        final IGameFacade gameFacade = new GameFacade(gameManager);
        final IMovesFacade movesFacade = new MovesFacade(gameManager, persistenceManager);
        final IUtilityFacade utilityFacade = new UtilityFacade();

        // Create HTTPHandlers for each type of request

        //
        // user
        //
        server.createContext("/user/login", new AbstractUserHandler(userFacade) {
            @Override
            protected IUser exchangeData(CredentialsParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().login(requestData);
            }
        });

        server.createContext("/user/register", new AbstractUserHandler(userFacade) {
            @Override
            protected IUser exchangeData(CredentialsParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().register(requestData);
            }
        });

        //
        // games (joining)
        //
		server.createContext("/games/join", new JoinHandler(joinGameFacade));

        server.createContext("/games/list", new AbstractHandler<Void, GameInfo[], IJoinGameFacade>(Void.class, joinGameFacade) {
            @Override
            protected GameInfo[] exchangeData(Void requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().list();
            }
        });

        server.createContext("/games/create", new AbstractHandler<CreateGameRequestParams, GameInfo, IJoinGameFacade>(CreateGameRequestParams.class, joinGameFacade) {
            @Override
            protected GameInfo exchangeData(CreateGameRequestParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().create(requestData);
            }
        });

        server.createContext("/games/save", new AbstractHandler<SaveGameRequestParams, String, IJoinGameFacade>(SaveGameRequestParams.class, joinGameFacade) {
            @Override
            protected String exchangeData(SaveGameRequestParams requestData) throws MissingCookieException, IllegalCommandException, ModelException, IOException {
                getFacade().save(requestData);
                return "Successfully saved game '" + requestData.name + "'";
            }
        });

        server.createContext("/games/load", new AbstractHandler<LoadGameRequestParams, String, IJoinGameFacade>(LoadGameRequestParams.class, joinGameFacade) {
            @Override
            protected String exchangeData(LoadGameRequestParams requestData) throws MissingCookieException, IllegalCommandException, ModelException, IOException {
                getFacade().load(requestData);
                return "Successfully loaded game '" + requestData.name + "'";
            }
        });

        //
        // game
        //
        server.createContext("/game/model", new GameModelHandler(gameFacade));

        server.createContext("/game/listAI", new AbstractHandler<Void, String[], IGameFacade>(Void.class, gameFacade) {
            @Override
            protected String[] exchangeData(Void requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().listAI();
            }
        });

        server.createContext("/game/addAI", new AbstractInGameHandler<AddAIRequestParams, IGameFacade>(AddAIRequestParams.class, gameFacade) {
            @Override
            protected IGame exchangeData(AddAIRequestParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                getFacade().addAI(requestData); // TODO: this is testing code
                return null;
            }
        });

        //
        // moves
        //
		server.createContext("/moves/sendChat", new AbstractMovesHandler<SendChatParams>(SendChatParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(SendChatParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().sendChat(requestData);
            }
        });

	    server.createContext("/moves/rollNumber", new AbstractMovesHandler<RollNumberParams>(RollNumberParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(RollNumberParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().rollNumber(requestData);
            }
        });

	    server.createContext("/moves/robPlayer", new AbstractMovesHandler<RobbingParams>(RobbingParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(RobbingParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().robPlayer(requestData);
            }
        });

	    server.createContext("/moves/finishTurn", new AbstractMovesHandler<PlayerIndexParam>(PlayerIndexParam.class, movesFacade) {
            @Override
            protected IGame exchangeData(PlayerIndexParam requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().finishTurn(requestData);
            }
        });

	    server.createContext("/moves/buyDevCard", new AbstractMovesHandler<PlayerIndexParam>(PlayerIndexParam.class, movesFacade) {
            @Override
            protected IGame exchangeData(PlayerIndexParam requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().buyDevCard(requestData);
            }
        });

	    server.createContext("/moves/Year_of_Plenty", new AbstractMovesHandler<YearOfPlentyParams>(YearOfPlentyParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(YearOfPlentyParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().yearOfPlenty(requestData);
            }
        });

	    server.createContext("/moves/Road_Building", new AbstractMovesHandler<RoadBuildingParams>(RoadBuildingParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(RoadBuildingParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().roadBuilding(requestData);
            }
        });

	    server.createContext("/moves/Soldier", new AbstractMovesHandler<RobbingParams>(RobbingParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(RobbingParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().soldier(requestData);
            }
        });

	    server.createContext("/moves/Monopoly", new AbstractMovesHandler<MonopolyParams>(MonopolyParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(MonopolyParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().monopoly(requestData);
            }
        });

	    server.createContext("/moves/Monument", new AbstractMovesHandler<PlayerIndexParam>(PlayerIndexParam.class, movesFacade) {
            @Override
            protected IGame exchangeData(PlayerIndexParam requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().monument(requestData);
            }
        });

	    server.createContext("/moves/buildRoad", new AbstractMovesHandler<BuildRoadParams>(BuildRoadParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(BuildRoadParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().buildRoad(requestData);
            }
        });

	    server.createContext("/moves/buildSettlement", new AbstractMovesHandler<BuildSettlementParams>(BuildSettlementParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(BuildSettlementParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().buildSettlement(requestData);
            }
        });

	    server.createContext("/moves/buildCity", new AbstractMovesHandler<BuildCityParams>(BuildCityParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(BuildCityParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().buildCity(requestData);
            }
        });

	    server.createContext("/moves/offerTrade", new AbstractMovesHandler<OfferTradeParams>(OfferTradeParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(OfferTradeParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().offerTrade(requestData);
            }
        });

	    server.createContext("/moves/acceptTrade", new AbstractMovesHandler<AcceptTradeParams>(AcceptTradeParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(AcceptTradeParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().acceptTrade(requestData);
            }
        });

	    server.createContext("/moves/maritimeTrade", new AbstractMovesHandler<MaritimeTradeParams>(MaritimeTradeParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(MaritimeTradeParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().maritimeTrade(requestData);
            }
        });

	    server.createContext("/moves/discardCards", new AbstractMovesHandler<DiscardCardParams>(DiscardCardParams.class, movesFacade) {
            @Override
            protected IGame exchangeData(DiscardCardParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                return getFacade().discardCards(requestData);
            }
        });

        //
        // util
        //
        server.createContext("/util/changeLogLevel", new AbstractHandler<Level, String, IUtilityFacade>(Level.class, utilityFacade) {
            @Override
            protected String exchangeData(Level requestData) throws MissingCookieException, IllegalCommandException, ModelException {
                getFacade().changeLogLevel(requestData);
                return "Logging level changed to " + requestData;
            }
        });

        // Handlers for the Swagger UI
        server.createContext("/docs/api/data", new SwaggerHandler.JSONAppender(""));
        server.createContext("/docs/api/view", new SwaggerHandler.BasicFile(""));
    }

    private static final String USAGE = "Usage:\njava Server [-clear] PORT_NUMBER PERSISTENCE_MANAGER COMMANDS_BETWEEN_CHECKPOINTS";
	

	public static void main(String[] args) {
        // Check if there are extra arguments
        if (args.length > 3) {
            System.err.println(USAGE);
            System.exit(1);
        }

        // process command line arguments
        String persistenceOption = args.length > 1 ? args[1] : null;
        if (persistenceOption == null) {
            logger.info("Not using a persistence manager.");
        }
        else {
            logger.info("Using persistence manager \"" + persistenceOption + "\"");
        }

        try {
            IPersistenceManagerLoader persistenceLoader = new PersistenceManagerLoader();
            persistenceLoader.loadPersistencePlugin(persistenceOption);

            // if called with -clear, simply clear and return
            if (args.length > 0 && args[0].equalsIgnoreCase("-clear")) {
                persistenceLoader.createPersistenceManager(0).clear();
                logger.info("Cleared persistence data for " +
                        (persistenceOption != null ? persistenceOption : "the default persistence manager."));
                return;
            }

            int portNumber = DEFAULT_PORT;
            int commandsBetweenCheckpoints = DEFAULT_COMMANDS_BETWEEN_CHECKPOINTS;

            if (args.length == 0) {
                System.out.println("No port number provided, using default (" + portNumber + ").");
            }
            else {
                portNumber = Integer.parseInt(args[0]);
                if (portNumber < 1024 || portNumber > 65535)
                    throw new IllegalArgumentException();

                if (args.length == 3) {
                    commandsBetweenCheckpoints = Integer.parseInt(args[2]);
                    if (commandsBetweenCheckpoints < 0) {
                        throw new IllegalArgumentException();
                    }
                }
            }

            new Server().run(portNumber, persistenceLoader.createPersistenceManager(commandsBetweenCheckpoints));
        }
        catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.err.println("" + args[0] + " is not a valid port number.");
            System.err.println("The port number must be an integer greater than 1023 and "
                    + "less than 65536.");
            System.err.println(USAGE);
        }
        catch (InvalidPluginException e) {
            System.err.println("Cannot load persistence server.plugin " + persistenceOption);
            e.printStackTrace();
        } catch (PersistenceException e) {
            System.err.println("Failed to clear persistence data.");
            e.printStackTrace();
        }
    }
}
