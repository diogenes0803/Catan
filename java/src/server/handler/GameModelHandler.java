package server.handler;

import com.google.gson.stream.MalformedJsonException;
import com.sun.net.httpserver.HttpExchange;
import server.command.IllegalCommandException;
import server.facade.IGameFacade;
import shared.communication.GameModelParam;
import shared.model.IGame;
import shared.model.ModelException;
import shared.model.serialization.Serializer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;


public class GameModelHandler extends AbstractInGameHandler<GameModelParam, IGameFacade> {

    public GameModelHandler(IGameFacade facade) {
        super(GameModelParam.class, facade);
    }


    @Override
    public IGame exchangeData(GameModelParam requestData) throws MissingCookieException, IllegalCommandException, ModelException {
        IGame theGame = getFacade().model(requestData);
        if (requestData.version == null || theGame.getVersion() != requestData.version) {
            return theGame;
        }
        else {
            return null; // null means the client has the latest and doesn't need a new game model
        }
    }


    @Override
    protected GameModelParam getRequestParameters(HttpExchange exch) throws IOException {
        String query = exch.getRequestURI().getQuery();
        if (query == null || query.isEmpty()) {
            return new GameModelParam(null);
        }
        else {
            try {
                int number = Integer.parseInt(query.substring(query.indexOf('=') + 1));
                // TODO: do we have to handle if they pass in the correct model number? (returns true in this case)
                return new GameModelParam(number);
            }
            catch (NumberFormatException e) {
                throw new MalformedJsonException(e);
            }
        }
    }


    @Override
    protected void generateResponse(HttpExchange exch, IGame responseData) throws IOException {
        try (OutputStreamWriter responseBody = new OutputStreamWriter(exch.getResponseBody())) {
            exch.getResponseHeaders().add("Content-Type", "application/json");
            // here, null means the client doesn't need to see the model
            String response;
            if (responseData != null) {
                response = Serializer.instance().toJson(responseData);
            }
            else {
                response = Serializer.instance().toJson(true);
            }

            exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
            responseBody.write(response);
        }
    }
}
