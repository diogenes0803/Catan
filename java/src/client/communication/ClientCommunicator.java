package client.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

/**
 * Description: The Client Communicator takes in commands for the server, serializes them, sends them to the server, receives the response,
 * deserializes the response, and then passes it to the server facade
 * @author oxbor
 *
 */
public class ClientCommunicator {

	private String urlPrefix;
	private static final String HTTP_POST = "POST";
	
	public ClientCommunicator(String serverHost, int serverPort) {
		urlPrefix = "http://" + serverHost + ":" + serverPort;
	}
	
	/**
	 * 
	 * @throws ClientException 
	 * @post
	 */
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
	/**
	 * 
	 * @post
	 */
	public Object get(String urlPrefix)
	{
		return null;
	}
}
