package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import server.Server;
import server.data.User;
import shared.models.Game;
import shared.models.jsonholder.JsonModelHolder;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetModelHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange ex) throws IOException {
		
		Gson gson = new Gson();
		String cookies = ex.getRequestHeaders().get("Cookie").get(0);
		User userInfo = null;
		int gameId = -1;
		String[] cookiesArray = cookies.split(";");
		for(String thisCookie : cookiesArray) {
			
			if(thisCookie.contains("catan.user=")) {
				String userCookie = thisCookie.substring(11, thisCookie.length());
				String decoded = URLDecoder.decode(userCookie);
				userInfo = gson.fromJson(decoded, User.class);
			}
			else if(thisCookie.contains("catan.game=")) {
				String userCookie = thisCookie.substring(12, thisCookie.length());
				String decoded = URLDecoder.decode(userCookie);
				gameId = gson.fromJson(decoded, Integer.class);

			}
				
		}

		Game model = Server.models.get(gameId);
		//for(int i=0; i < 4; i++) {
			//if(model.getPlayers()[i].getPlayerId() == userInfo.getPlayerID()) {
				
				ex.getResponseHeaders().add("Content-Type", "application/json");
				String jsonObject = gson.toJson(model, Game.class);

				ex.sendResponseHeaders(200, jsonObject.length());
				
				OutputStream out = ex.getResponseBody();
				out.write(jsonObject.getBytes());
				out.flush();
				out.close();
			//}
		//}
		
	}

}
