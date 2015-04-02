package server.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import server.data.User;
import server.facades.GamesFacade;
import shared.communicator.JoinGameParams;
import shared.communicator.JoinGameResults;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class JoinGameHandler implements HttpHandler 
{
	private GamesFacade thisFacade = new GamesFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		
		Gson gson = new Gson();
		String body = "";
		String cookies = ex.getRequestHeaders().get("Cookie").get(0);
		User userInfo = null;
		int gameId = -1;
		String[] cookiesArray = cookies.split(";");
		for(String thisCookie : cookiesArray) {
			if(thisCookie.contains("catan.user=")) {
				String userCookie = thisCookie.substring(12, thisCookie.length());
				String decoded = URLDecoder.decode(userCookie, "UTF-8");
				userInfo = gson.fromJson(decoded, User.class);
			}
			else if(thisCookie.contains("catan.game=")) {
				//gameId = Integer.parseInt(thisCookie.substring(11, thisCookie.length()));
				//for some reason there's already a catan.game cookie, I'm not sure if that's actually supposed to be there
			}
				
		}
		
		if(userInfo != null && gameId == -1) {
			
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
			
			System.out.println("first place");
			JoinGameParams params = gson.fromJson(qry, JoinGameParams.class);
			System.out.println("Second place");
			JoinGameResults result = thisFacade.join(params, userInfo);
			System.out.println("Succes if");
			
			ex.getResponseHeaders().add("Content-Type", "text/html");
			
			
			if(result.isSuccess()) {
				
				List<String> gameCookies = new ArrayList<String>();
				gameCookies.add("catan.game="+params.getId()+";Path=/;");
				ex.getResponseHeaders().put("Set-cookie", gameCookies);
				body = "Success";
				ex.sendResponseHeaders(200, body.length());
			}
			else {
				body = "The player could not be added to the specified game.";
				ex.sendResponseHeaders(400, body.length());
			}
			
			OutputStream out = ex.getResponseBody();
			out.write(body.getBytes());
			out.flush();
			out.close();
		}
		else {
			body = "The player could not be added to the specified game.";
			ex.sendResponseHeaders(400, body.length());
			OutputStream out = ex.getResponseBody();
			out.write(body.getBytes());
			out.flush();
			out.close();
		}
	}
}