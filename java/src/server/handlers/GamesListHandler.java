package server.handlers;

import java.io.IOException;
import java.io.OutputStream;

import server.facades.GamesFacade;
import shared.communicator.ListGamesResults;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GamesListHandler implements HttpHandler 
{
	private GamesFacade thisFacade = new GamesFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		ListGamesResults result = thisFacade.list();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String gsonObject = gson.toJson(result.getGames());
		ex.getResponseHeaders().add("Content-Type", "application/json");
		ex.sendResponseHeaders(200, gsonObject.length());
		OutputStream out = ex.getResponseBody();
		out.write(gsonObject.getBytes());
		out.flush();
		out.close();
	}

}
