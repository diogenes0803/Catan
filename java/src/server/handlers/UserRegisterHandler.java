package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import server.facades.UserFacade;
import server.jsonConverters.RegisterUserConverter;
import server.jsonConverters.UserLoginConverter;
import shared.communicator.UserLoginResults;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserRegisterHandler implements HttpHandler  {
	
	private UserFacade thisFacade = new UserFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		RegisterUserConverter converter = new RegisterUserConverter();
		UserLoginResults result = thisFacade.registerUser(converter.convert(ex.getRequestBody()));
		
		Gson gson = new Gson();
		
		ex.getResponseHeaders().add("Content-Type", "application/json");
		String body = "";
		String gsonObject = gson.toJson(body);
		
		if (result != null)
		{
			//also need to set the cookie look at http://www.programcreek.com/java-api-examples/index.php?api=com.sun.net.httpserver.Headers
			List<String> cookies = new ArrayList<String>();
			cookies.add("catan.user=%7B%22name%22%3A%22"+result.getName()+
					"%22%2C%22password%22%3A%22"+result.getPassword()+"%22%2C%22playerID%22%3A"
					+result.getPlayerId()+"%7D;Path=/;");
			ex.getResponseHeaders().put("Set-cookie", cookies);
			body = "Success";
			gsonObject = gson.toJson(body);
			ex.sendResponseHeaders(200, gsonObject.length());
		}
		else
		{
			body = "Invalid request/username already taken";
			gsonObject = gson.toJson(body);
			ex.sendResponseHeaders(400, gsonObject.length());
		}
		

		//This method must be called after sendResponseHeaders
		OutputStream out = ex.getResponseBody();
		out.write(gsonObject.getBytes()); 
		out.flush();	
		out.close();
	}
}

