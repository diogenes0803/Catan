/**
 * 
 */
package server.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import server.Server;
import server.data.User;
import server.facades.MovesFacade;
import server.model.ServerModel;
import shared.communicator.SendChatParams;
import shared.models.jsonholder.JsonModelHolder;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * @author campbeln
 *
 */
public class SendChatHandler implements HttpHandler {
MovesFacade movesFacade = new MovesFacade();
	
	/* (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	@Override
	public void handle(HttpExchange ex) throws IOException {
		// TODO Auto-generated method stub

		Gson gson = new Gson(); 
		
		String cookies = ex.getRequestHeaders().get("Cookie").get(0);
		User userInfo = null;
		int gameId = -1;
		String[] cookiesArray = cookies.split(";");
		for(String thisCookie : cookiesArray) {
			
			if(thisCookie.contains("catan.user=")) {
				int index = thisCookie.indexOf("=");
				String userCookie = thisCookie.substring(index+1, thisCookie.length());
				String decoded = URLDecoder.decode(userCookie);
				userInfo = gson.fromJson(decoded, User.class);
			}
			else if(thisCookie.contains("catan.game=")) {
				int index = thisCookie.indexOf("=");
				String userCookie = thisCookie.substring(index+1, thisCookie.length());
				String decoded = URLDecoder.decode(userCookie);
				gameId = gson.fromJson(decoded, Integer.class);

			}
				
		}
		
		String qry;
		String encoding = "ISO-8859-1";
		InputStream in = ex.getRequestBody();
		try {
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    byte buf[] = new byte[4096];
		    for (int n = in.read(buf); n > 0; n = in.read(buf)) {
		        out.write(buf, 0, n);
		    }
		    qry = new String(out.toByteArray(), encoding);
		} finally {
		    in.close();
		}
		
		SendChatParams params = gson.fromJson(qry, SendChatParams.class);
		movesFacade.sendChat(params, gameId);
		ServerModel model = Server.models.get(gameId);
		ex.getResponseHeaders().add("Content-Type", "application/json");
		String jsonObject = gson.toJson(model.toJsonModel(), JsonModelHolder.class);
		int index = jsonObject.indexOf(",\"number\":0");
		StringBuilder jsonObjectBuilder = new StringBuilder(jsonObject);
		jsonObjectBuilder.delete(index, index+11);
		jsonObject = jsonObjectBuilder.toString();
		ex.sendResponseHeaders(200, jsonObject.length());
		
		OutputStream out = ex.getResponseBody();
		out.write(jsonObject.getBytes());
		out.flush();
		out.close();
	}

}
