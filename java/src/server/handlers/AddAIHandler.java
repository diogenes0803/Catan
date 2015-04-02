package server.handlers;

/**
 * Created by coblek on 4/1/15.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facades.GameFacade;
import shared.communicator.AddAIParams;
import shared.communicator.AddAIRequestParams;
import shared.communicator.AddAIResults;

public class AddAIHandler implements HttpHandler {

    private GameFacade gameFacade = new GameFacade();


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //AddAIResults result = gameFacade.addAI(new AddAIRequestParams().getRequestBody());

        AddAIRequestParams params = new AddAIRequestParams("LARGEST_ARMY");
        AddAIResults result = new AddAIResults();
        result.setSuccess(gameFacade.addAI(params));

        Gson gson = new Gson();

        String type;
        String encoding = "ISO-8859-1";
        InputStream in = exchange.getRequestBody();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte buf[] = new byte[4096];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            type = new String(out.toByteArray(), encoding);
        } finally {
            in.close();
        }

        Gson gson2 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String gsonObject = gson2.toJson(result);

        exchange.getResponseHeaders().set("Content-Type", "appliction/json");
        exchange.sendResponseHeaders(200, gsonObject.length());


    }

}
