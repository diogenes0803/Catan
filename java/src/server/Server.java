package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import server.data.User;
import server.handlers.GamesListHandler;
import server.handlers.Handlers;
import server.model.ServerModel;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server 
{
	private static int SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	private HttpServer server;
	public static Logger logger;
	
	public static Map<Integer, ServerModel> models = new HashMap<Integer, ServerModel>();
	public static Map<Integer, User> users = new HashMap<Integer, User>();
	
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
		
		server.createContext("/games/list", GamesListHandler);
		server.createContext("/docs/api/data", new Handlers.JSONAppender("")); 
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));
		
		logger.info("Starts HTTP Server");

		server.start();
	}
	private HttpHandler GamesListHandler = new GamesListHandler();
	
	
}