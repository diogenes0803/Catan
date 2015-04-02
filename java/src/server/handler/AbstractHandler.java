package server.handler;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.command.IllegalCommandException;
import shared.model.ModelException;
import shared.model.serialization.Serializer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractHandler<ReqType, RespType, FacadeType> implements HttpHandler {
    private static Logger logger = Logger.getLogger("catanserver");

    private Class<ReqType> local_reqClass;
    private FacadeType local_facade; // the class where the request data is forwarded


    protected final FacadeType getFacade() {
        return local_facade;
    }


    protected final FacadeType setFacade(FacadeType facade) {
        return local_facade = facade;
    }


    public AbstractHandler(Class<ReqType> reqClass, FacadeType facade) {
        local_reqClass = reqClass;
        local_facade = facade;
    }


    protected abstract RespType exchangeData(ReqType requestData) throws MissingCookieException, IllegalCommandException, ModelException, IOException;


    protected void processRequestCookies(CookieJar cookies, ReqType requestData) throws IOException, MissingCookieException {
        // cookies are not processed by default
    }


    protected ReqType getRequestParameters(HttpExchange exch) throws IOException {
        try (InputStreamReader requestBody = new InputStreamReader(exch.getRequestBody())) {
            return Serializer.instance().fromJson(requestBody, local_reqClass);
        }
    }


    protected void generateResponse(HttpExchange exch, RespType responseData) throws IOException {
        try (OutputStreamWriter responseBody = new OutputStreamWriter(exch.getResponseBody())) {
            // if a valid response was generated, send it back
            if (responseData != null) {
                exch.getResponseHeaders().add("Content-Type", "application/json");
                String response = Serializer.instance().toJson(responseData);

                exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
                responseBody.write(response);
            } else {
                exch.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, -1);
            }
        }
    }


    @Override
    public final void handle(HttpExchange exch) throws IOException {
        logger.entering("server.RequestHandler", "handle");

        logger.finer("Received HTTP request for " + this.getFacade().getClass().getSimpleName()
                + " on " + this.getClass().getName() + " from " + exch.getRemoteAddress() + '.');

        try {
            ReqType reqData = getRequestParameters(exch);

            // read the cookies, if there are any
            processRequestCookies(new CookieJar(exch.getRequestHeaders().get("Cookie")), reqData);

            RespType respData = exchangeData(reqData);

            generateResponse(exch, respData);

            //logger.finer("Responding to request with: " + respData);
        } catch (JsonSyntaxException | MalformedJsonException e) {
            logger.log(Level.INFO, "Received an improperly formatted request.", e);
            sendErrorResponse(exch, HttpURLConnection.HTTP_BAD_REQUEST, e);
        } catch (MissingCookieException e) {
            logger.log(Level.INFO, "Client does not have the correct cookies set to perform the desired action.", e);
            sendErrorResponse(exch, HttpURLConnection.HTTP_UNAUTHORIZED, e);
        } catch (IllegalCommandException | ModelException e) {
            logger.log(Level.WARNING, "Client attempted an illegal action. (But don't worry, we stopped them!)", e);
            sendErrorResponse(exch, HttpURLConnection.HTTP_BAD_METHOD, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An I/O error occurred.", e);
            sendErrorResponse(exch, HttpURLConnection.HTTP_INTERNAL_ERROR, e);
            throw e;
        } catch (Throwable e) {
            logger.log(Level.SEVERE, "An exception occurred!", e);
        } finally {
            logger.exiting("server.RequestHandler", "handle");
        }
    }


    private void sendErrorResponse(HttpExchange exch, int httpResponse, Throwable e) throws IOException {
        try (OutputStreamWriter responseBody = new OutputStreamWriter(exch.getResponseBody())) {
            exch.getResponseHeaders().add("Content-type", "text/plain");
            exch.sendResponseHeaders(httpResponse, 0);
            responseBody.write(e.getMessage());
        }
    }


    protected void sendResponse(HttpExchange exch, int httpResponse, String message) throws IOException {
        try (OutputStreamWriter responseBody = new OutputStreamWriter(exch.getResponseBody())) {
            exch.getResponseHeaders().add("Content-type", "text/plain");
            exch.sendResponseHeaders(httpResponse, message.length());
            responseBody.write(message);
        }
    }


    protected static int readUserID(String cookie) throws IOException {
        try (JsonReader reader = new JsonReader(new StringReader(cookie))) {
            reader.beginObject();

            while (reader.hasNext()) {
                String name = reader.nextName();

                if (name.equalsIgnoreCase("playerID")) {
                    return reader.nextInt();
                }
                else {
                    reader.skipValue();
                }
            }

            reader.endObject();

            throw new IOException("Player ID not found in cookie.");
        }
    }
}
