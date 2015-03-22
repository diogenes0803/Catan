package server.handlers;

import java.io.IOException;
import java.io.OutputStream;

import server.facades.GamesFacade;
import shared.communicator.ListGamesResults;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GamesListHandler implements HttpHandler 
{
	private GamesFacade thisFacade = new GamesFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		ListGamesResults result = thisFacade.list();
		ex.sendResponseHeaders(200, 0);
		Gson gson = new Gson();
		OutputStream out = ex.getResponseBody();
		out.write(gson.toJson(result).getBytes());
		out.flush();
		out.close();
	}
}
