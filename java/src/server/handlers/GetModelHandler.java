package server.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import server.data.User;
import server.facades.GameFacade;
import shared.communicator.GameModelParam;
import shared.models.Game;
import shared.models.ModelException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetModelHandler implements HttpHandler {
	
	private GameFacade thisFacade = new GameFacade();

	@Override
	public void handle(HttpExchange ex) throws IOException {
	
		Gson gson = new Gson();
		int versionNumber = 0;
		List<String> cookies = ex.getRequestHeaders().get("Cookie");
		User userInfo = null;
		for(String cookie : cookies) {
			if(cookie.contains("catan.user=")) {
				String userCookie = cookie.substring(11, cookie.length());
				String decoded = URLDecoder.decode(userCookie);
				userInfo = gson.fromJson(decoded, User.class);
			}
			else if (cookie.contains("catan.game"))
			{
				String gameCookie = cookie.substring(11, cookie.length());
				String decoded = URLDecoder.decode(gameCookie);
				
				versionNumber = gson.fromJson(decoded, Integer.class);
			}
		}
		
		GameModelParam params = new GameModelParam(versionNumber);
		Game game = new Game();
		
			try {
				game = thisFacade.model(params);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ex.getResponseHeaders().add("Content-Type", "text/html");
			String body = "";
			if (game.getVersion() == versionNumber)
			{
				body = gson.toJson(true);
				ex.sendResponseHeaders(200, body.length());
			}

			else {
				body = gson.toJson(game);
				ex.sendResponseHeaders(200, body.length());
			}
			OutputStream out = ex.getResponseBody();
			out.write(body.getBytes());
			out.flush();
			out.close();
		
	}

}
