package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import server.Server;
import server.data.User;
import shared.models.Game;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetModelHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange ex) throws IOException {
		Gson gson = new Gson();
		List<String> cookies = ex.getRequestHeaders().get("Cookie");
		User userInfo = null;
		int gameId = -1;
		for(String cookie : cookies) {
			if(cookie.contains("catan.user=")) {
				String rawCookie = cookie.substring(11, cookie.length());
				String[] splitedCookie = rawCookie.split(";");
				String userCookie = splitedCookie[0];
				String decodedUser = URLDecoder.decode(userCookie);
				if(cookie.contains("catan.game=")) {
					String gameCookie = splitedCookie[1];
					gameId = Integer.parseInt(gameCookie.substring(11, gameCookie.length()));
				}
				
				userInfo = gson.fromJson(decodedUser, User.class);
			}
		}
		Game model = Server.models.get(gameId);
		for(int i=0; i < 4; i++) {
			if(model.getPlayers()[i].getPlayerId() == userInfo.getPlayerID()) {
				OutputStream out = ex.getResponseBody();
				ex.getResponseHeaders().add("Content-Type", "application/json");
				String jsonObject = gson.toJson(model.toJsonModel());
				ex.sendResponseHeaders(200, jsonObject.length());
				out.write(jsonObject.getBytes());
				out.flush();
				out.close();
			}
		}
		
	}

}
