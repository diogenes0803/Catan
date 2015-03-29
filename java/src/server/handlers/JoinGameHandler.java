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
		List<String> cookies = ex.getRequestHeaders().get("Cookie");
		User userInfo = null;
		for(String cookie : cookies) {
			if(cookie.contains("catan.user=")) {
				String userCookie = cookie.substring(11, cookie.length());
				String decoded = URLDecoder.decode(userCookie);
				userInfo = gson.fromJson(decoded, User.class);
			}
		}
		
		if(userInfo != null) {
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
			JoinGameParams params = gson.fromJson(qry, JoinGameParams.class);
			JoinGameResults result = thisFacade.join(params, userInfo);
			OutputStream out = ex.getResponseBody();
			ex.getResponseHeaders().add("Content-Type", "text/html");
			String body = "";
			if(result.isSuccess()) {
				List<String> gameCookies = new ArrayList<String>();
				gameCookies.add(" catan.game="+params.getId()+";Path=/;");
				ex.getResponseHeaders().put("Set-cookie", gameCookies);
				body = "Success";
				ex.sendResponseHeaders(200, body.length());
			}
			else {
				body = "The player could not be added to the specified game.";
				ex.sendResponseHeaders(400, body.length());
			}
			out.write(body.getBytes());
			out.flush();
			out.close();
		}
	}
}