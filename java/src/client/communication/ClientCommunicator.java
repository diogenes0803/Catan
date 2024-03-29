package client.communication;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * <p>Description: The Client Communicator takes in commands for the server, serializes them, sends them to the server,
 * receives the response, and then passes it to the server facade.<br> Current implementation uses Gson for
 * serializing.<br> The deserialize function must be called in server facade due to gson.fromJson() needing to know what
 * class to convert json string into.</p>
 *
 * @author oxbor, dilleter
 */


public class ClientCommunicator {

    private Gson gson;
    private String host;
    private String port;


    public ClientCommunicator(String host, String port) {
        this.host = host;
        this.port = port;
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls(); //enable serialization of variables that have null values, otherwise they'll be skipped.
        gson = builder.create();
    }

    /**
     * <p>send json mssage to web server. command will be a facade command like "/sendChat". <br> No object is returned
     * other than error messages. </p>
     *
     * @return boolean true meaning message sent, or Exception thrown indicating error.
     * @post
     */
    public HttpURLResponse post(String urlCommand, Object params, String cookie) throws ClientException {
        URL url;
        HttpURLConnection connection = null;
        HttpURLResponse result = new HttpURLResponse();
        try {
            //Create connection
            url = new URL("http://" + host + ":" + port + urlCommand);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            String message = serializeObject(params);

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(message.getBytes().length));

            connection.setRequestProperty("Content-Language", "en-US");


            //Adds whatever is in the cookie string (if it's not null) to the httpHeader

            if (cookie.length() > 0) {
                //System.out.println("cookie: '"+cookie+"'");
                connection.addRequestProperty("Cookie", cookie);
            }

            connection.setUseCaches(false);

            connection.setDoOutput(true);

            //System.out.println(connection.getRequestProperties());
            connection.connect();
            //System.out.println("Connection estabished");
            OutputStream requestBody = connection.getOutputStream();

            requestBody.write(message.getBytes());

            requestBody.close();//closing the stream causes the msg to be sent.

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // return the object returned by the server
                String e_result = convertStreamToString(new BufferedInputStream(
                        connection.getInputStream()));
                result.setResponseBody(e_result);
                result.setResponseLength(e_result.length());
                result.setResponseCode(connection.getResponseCode());
                result.setHeaderFields(connection.getHeaderFields());
                //System.out.println("e_result = \""+e_result+"\"");

            } else {
                // SERVER RETURNED AN ERROR
                String e_result = convertStreamToString(new BufferedInputStream(
                        connection.getErrorStream()));

                result.setResponseBody(e_result);
                result.setResponseLength(e_result.length());
                result.setResponseCode(connection.getResponseCode());
                //System.out.println("e_result = \""+e_result+"\"");
            }

        } catch (Exception e) {
            throw new ClientException("ClientCommunicator.get() Error:\n" + e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
                //System.out.println("Connection closed.");
            }
        }
        return result;
    }//end get



    /**
     * For getting json objects from server. WIll also create a cookie if urlCommand says to do so.
     *
     * @params urlCommand  string like "/players/ return <p>a string containing json response</p>
     * @post
     */
    public HttpURLResponse get(String urlCommand, String cookie) throws ClientException {
        HttpURLConnection connection = null;
        assert urlCommand != null;
        
        HttpURLResponse result = new HttpURLResponse();
        try {
            // Write request body to OutputStream ...


            String recent_url = "http://" + host + ":" + port + urlCommand;
            URL url = new URL(recent_url);

            connection = (HttpURLConnection) url.openConnection();
            //System.out.println("ClientCommuniator.get.connection established");
            
            connection.setRequestMethod("GET");
            
            connection.setDoOutput(true);
            
            connection.setRequestProperty("Content-Language", "en-US");
            
            
            //Adds whatever is in the cookie string (if it's not null) to the httpHeader 
            //System.out.println("ClientCommuniator.get.cookie:\n\""+cookie+"\"");
            if (cookie.length() != 0) {
                connection.setRequestProperty("Cookie", cookie);
            }

            connection.connect();

            //System.out.println("Connection.connect successful.");
            BufferedOutputStream requestBody = new BufferedOutputStream(
                    connection.getOutputStream());

            requestBody.close();
            //System.out.println("RequetBody.close was successful.");
            
            
            // Read response body from InputStream ...

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // return the object returned by the server
                String e_result = convertStreamToString(new BufferedInputStream(
                        connection.getInputStream()));
                result.setResponseBody(e_result);
                result.setResponseLength(e_result.length());
                result.setResponseCode(connection.getResponseCode());
                result.setHeaderFields(connection.getHeaderFields());
                //System.out.println("e_result = \""+e_result+"\"");

            } else {
                // SERVER RETURNED AN ERROR
                String e_result = convertStreamToString(new BufferedInputStream(
                        connection.getErrorStream()));

                result.setResponseBody(e_result);
                result.setResponseLength(e_result.length());
                result.setResponseCode(connection.getResponseCode());
                //System.out.println("e_result = \""+e_result+"\"");
            }

        } catch (Exception e) {
            throw new ClientException("ClientCommunicator.get() Error:\n" + e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
                //System.out.println("Connection closed.");
            }
        }
        return result;
    }//end get

    //=========================================================================
    //convert object to json string using Gson library.
    public String serializeObject(Object requestObject) {
        return gson.toJson(requestObject);
    }


    //convert json to Object using Gson library. To be called in ServerProxy
    public Object deserializeObject(String result, Object newObject) {
        return gson.fromJson(result, newObject.getClass());
    }

    @SuppressWarnings("resource")
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String result = (s.hasNext() ? s.next() : "");
        s.close();
        return result;
    }
}//end class









/*
 * old code
 * 
public class ClientCommunicator {

    private String urlPrefix;
    private static final String HTTP_POST = "POST";
    
    public ClientCommunicator(String serverHost, int serverPort) {
        urlPrefix = "http://" + serverHost + ":" + serverPort;
    }
    

    public HttpURLResponse post(String commandName, Object postData) throws ClientException
    {
        assert commandName != null;
        assert postData != null;
        
        HttpURLResponse result = new HttpURLResponse();
        
        try {
            URL url = new URL(urlPrefix + commandName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setDoOutput(true);
            connection.connect();
            Gson gson = new Gson();
            String json = gson.toJson(postData);
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();
            
            result.setResponseCode(connection.getResponseCode());
            result.setResponseLength(connection.getContentLength());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                DataInputStream read = new DataInputStream(connection.getInputStream());
                String input = read.readUTF();
                result.setResponseBody(input);
            } else {
                throw new ClientException(String.format("doPost failed: %s (http code %d)",
                                            commandName, connection.getResponseCode()));
            }
        }
        catch (IOException e) {
            throw new ClientException(String.format("doPost failed: %s", e.getMessage()), e);
        }
        
        return result;
    }
  
    public Object get(String urlPrefix)
    {
        return null;
    }
}
 */









