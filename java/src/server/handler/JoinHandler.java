package server.handler;

import com.sun.net.httpserver.HttpExchange;
import server.command.IllegalCommandException;
import server.facade.IJoinGameFacade;
import shared.communication.JoinGameRequestParams;
import shared.model.ModelException;

import java.io.IOException;
import java.net.HttpURLConnection;


public class JoinHandler extends AbstractHandler<JoinGameRequestParams, Integer, IJoinGameFacade> {

    public JoinHandler(IJoinGameFacade joinGameFacade) {
        super(JoinGameRequestParams.class, joinGameFacade);
    }

    @Override
    protected void generateResponse(HttpExchange exch, Integer responseData) throws IOException {
        if (responseData != null) {
            CookieJar cookies = new CookieJar();
            cookies.addCookie("catan.game", responseData.toString());
            cookies.addCookie("path", "/");

            exch.getResponseHeaders().add("Set-cookie", cookies.toString());

            sendResponse(exch, HttpURLConnection.HTTP_OK, "You successfully joined game " + responseData + ".");
        }
        else {
            sendResponse(exch, HttpURLConnection.HTTP_BAD_REQUEST, "Cannot join game.");
        }
    }

    @Override
    protected Integer exchangeData(JoinGameRequestParams requestData) throws MissingCookieException, IllegalCommandException, ModelException {
        return getFacade().join(requestData);
    }

    @Override
    protected void processRequestCookies(CookieJar cookies, JoinGameRequestParams requestData) throws IOException, MissingCookieException {
        for (Cookie cookie : cookies) {
            if (cookie.nameIs("catan.user")) {
                requestData.setUserId(readUserID(cookie.getValue()));
                return;
            }
        }

        throw new MissingCookieException("The user cookie is not set.");
    }
}

