package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

import server.data.User;
import server.handlers.AcceptTradeHandler;
import server.handlers.BuildCityHandler;
import server.handlers.BuildRoadHandler;
import server.handlers.BuildSettlementHandler;
import server.handlers.BuyDevCardHandler;
import server.handlers.CreateGameHandler;
import server.handlers.DiscardCardsHandler;
import server.handlers.FinishTurnHandler;
import server.handlers.GamesListHandler;
import server.handlers.GetModelHandler;
import server.handlers.Handlers;
import server.handlers.JoinGameHandler;
import server.handlers.MaritimeTradehandler;
import server.handlers.MonopolyHandler;
import server.handlers.MonumentHandler;
import server.handlers.OfferTradeHandler;
import server.handlers.PlaySoldierHandler;
import server.handlers.RoadBuildingHandler;
import server.handlers.RobPlayerHandler;
import server.handlers.RollNumberHandler;
import server.handlers.SendChatHandler;
import server.handlers.UserLoginHandler;
import server.handlers.UserRegisterHandler;
import server.handlers.YearOfPlentyHandler;
import server.model.ServerModel;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server 
{
	private static int SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	private HttpServer server;
	private static Logger logger;
	
	public static Map<Integer, ServerModel> models = new HashMap<Integer, ServerModel>();
	public static Map<String, User> users = new HashMap<String, User>();
	
	private Server(int port) 
	{
		SERVER_PORT_NUMBER = port;
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, ClassNotFoundException, SQLException 
	{
		new Server(SERVER_PORT_NUMBER).run();
	}
	
	static
	{
		try
		{
			initLog();
		}
		catch (IOException e)
		{
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}
	
	private static void initLog() throws IOException 
	{		
		Level logLevel = Level.FINE;
		
		logger = Logger.getLogger("serverhandler"); 
		logger.setLevel(logLevel);
		logger.setUseParentHandlers(false);
		
		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(logLevel);
		consoleHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(consoleHandler);

        FileHandler fileHandler = new FileHandler("log.txt", false);
        fileHandler.setLevel(logLevel);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

	}

	
	private void run() throws FileNotFoundException, ClassNotFoundException, SQLException
	{
		logger.info("Initializing HTTP Server");
		
		try 
		{
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) 
		{
			logger.log(Level.SEVERE, e.getMessage(), e);			
			return;
		}
		
		server.setExecutor(null);
		
		server.createContext("/user/login", userLoginHandler);
		server.createContext("/user/register", userRegisterHandler);
		server.createContext("/games/list", gamesListHandler);
		server.createContext("/games/create", createGameHandler);
		server.createContext("/games/join", joinGameHandler);
		server.createContext("/game/model", getModelHandler);
		server.createContext("/moves/buildSettlement", buildSettlementHandler);
		server.createContext("/moves/buildRoad", buildRoadHandler);
		server.createContext("/moves/sendChat", sendChatHandler);
		server.createContext("/moves/rollNumber", rollNumberHandler);
		server.createContext("/moves/robPlayer", robPlayerHandler);
		server.createContext("/moves/finishTurn", finishTurnHandler);
		server.createContext("/moves/buyDevCard", buyDevCardHandler);
		server.createContext("/moves/Year_of_Plenty", yearOfPlentyHandler);
		server.createContext("/moves/Road_Building", roadBuildingHandler);
		server.createContext("/moves/Soldier", soldierHandler);
		server.createContext("/moves/Monopoly", monopolyHandler);
		server.createContext("/moves/Monument", monumentHandler);
		server.createContext("/moves/buildCity", buildCityHandler);
		server.createContext("/moves/offerTrade", offerTradeHandler);
		server.createContext("/moves/acceptTrade", acceptTradeHandler);
		server.createContext("/moves/maritimeTrade", maritimeTradeHandler);
		server.createContext("/moves/discardCards", discardCardsHandler);
		server.createContext("/docs/api/data", new Handlers.JSONAppender("")); 
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));
		
		
		logger.info("Attempting to start. . .");

		server.start();

        logger.info("Server is up.");
	}
	private HttpHandler gamesListHandler = new GamesListHandler();
	private HttpHandler createGameHandler = new CreateGameHandler();
	private HttpHandler joinGameHandler = new JoinGameHandler();
	private HttpHandler userLoginHandler = new UserLoginHandler();
	private HttpHandler userRegisterHandler = new UserRegisterHandler();
	private HttpHandler getModelHandler = new GetModelHandler();
	private HttpHandler buildSettlementHandler = new BuildSettlementHandler();
	private HttpHandler buildRoadHandler = new BuildRoadHandler();
	private HttpHandler rollNumberHandler = new RollNumberHandler();
	private HttpHandler sendChatHandler = new SendChatHandler();
	private HttpHandler robPlayerHandler = new RobPlayerHandler();
	private HttpHandler finishTurnHandler = new FinishTurnHandler();
	private HttpHandler buyDevCardHandler = new BuyDevCardHandler();
	private HttpHandler yearOfPlentyHandler = new YearOfPlentyHandler();
	private HttpHandler roadBuildingHandler = new RoadBuildingHandler();
	private HttpHandler soldierHandler = new PlaySoldierHandler();
	private HttpHandler monopolyHandler = new MonopolyHandler();
	private HttpHandler monumentHandler = new MonumentHandler();
	private HttpHandler buildCityHandler = new BuildCityHandler();
	private HttpHandler offerTradeHandler = new OfferTradeHandler();
	private HttpHandler acceptTradeHandler = new AcceptTradeHandler();
	private HttpHandler maritimeTradeHandler = new MaritimeTradehandler();
	private HttpHandler discardCardsHandler = new DiscardCardsHandler();
	
	
}
