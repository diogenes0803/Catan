package server.handlers;

import java.io.IOException;
import java.io.OutputStream;

import server.facades.UserFacade;
import server.jsonConverters.UserLoginConverter;
import shared.communicator.ListGamesResults;
import shared.communicator.UserLoginParams;
import shared.communicator.UserLoginResults;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserLoginHandler implements HttpHandler  {
	
	private UserFacade thisFacade = new UserFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		
		UserLoginConverter converter = new UserLoginConverter();
		UserLoginResults result = thisFacade.userLogin(converter.convert(ex.getRequestBody()));
		
	
		Gson gson = new Gson();
		OutputStream out = ex.getResponseBody();
		
		//String gsonObject = gson.toJson(result, UserLoginResults.class);
		
		
		ex.getResponseHeaders().add("Content-Type", "application/json");
		ex.sendResponseHeaders(200, 0);
		//out.write(gsonObject.getBytes());
		out.flush();	
		out.close();

	}
}
