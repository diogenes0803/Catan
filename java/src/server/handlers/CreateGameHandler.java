package server.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import server.facades.GamesFacade;
import shared.communicator.CreateGameParams;
import shared.communicator.CreateGameResults;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateGameHandler implements HttpHandler 
{
	private GamesFacade thisFacade = new GamesFacade();
	@Override
	public void handle(HttpExchange ex) throws IOException 
	{
		Gson gson = new Gson();
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
		CreateGameParams params = gson.fromJson(qry, CreateGameParams.class);
		CreateGameResults result = thisFacade.create(params);
		
		String gsonObject = gson.toJson(result);

		ex.getResponseHeaders().add("Content-Type", "application/json");
		ex.sendResponseHeaders(200, gsonObject.length());
		OutputStream out = ex.getResponseBody();
		out.write(gsonObject.getBytes());
		out.flush();
		out.close();
	}
}
