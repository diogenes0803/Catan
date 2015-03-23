package server.handlers;

import java.io.IOException;
import java.io.OutputStream;

import server.facades.UserFacade;
import shared.communicator.ListGamesResults;
import shared.communicator.UserLoginResults;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserLoginHandler implements HttpHandler  {
	
	private UserFacade thisFacade = new UserFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		UserLoginResults result = thisFacade.userLogin();
		Gson gson = new Gson();
		OutputStream out = ex.getResponseBody();
		String gsonObject = gson.toJson(result);
		ex.getResponseHeaders().add("Content-Type", "application/json");
		ex.sendResponseHeaders(200, gsonObject.length());
		out.write(gsonObject.getBytes());
		out.flush();
		out.close();
	}
}
