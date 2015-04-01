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
import shared.communicator.BuildSettlementParams;
import shared.communicator.BuyDevCardParams;
import shared.models.CatanModel;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * @author campbeln
 *
 */
public class BuyDevCardHandler implements HttpHandler {

	MovesFacade movesFacade = new MovesFacade();
	
	/* (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {

		Gson gson = new Gson(); 
		
		String cookies = exchange.getRequestHeaders().get("Cookie").get(0);
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
		
		String qry;
		String encoding = "ISO-8859-1";
		InputStream in = exchange.getRequestBody();
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
		
		BuyDevCardParams params = gson.fromJson(qry, BuyDevCardParams.class);
		CatanModel result = movesFacade.buyDevCard(params, gameId);
		String resultGson = gson.toJson(Server.models.get(gameId));
		
		
		exchange.getResponseHeaders().add("Content-Type", "text/html");
		String body = "";
		if(result != null) {  //Facade passes back null if command is invalid
			body = resultGson;
			exchange.sendResponseHeaders(200, body.length());
		}
		else {
			body = "Invalid Command";
			exchange.sendResponseHeaders(400, body.length());
		}
		OutputStream out = exchange.getResponseBody();
		out.write(body.getBytes());
		out.flush();
		out.close();
	}

}
