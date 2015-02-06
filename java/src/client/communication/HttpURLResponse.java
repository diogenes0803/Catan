/**
 * 
 */
package client.communication;

/**
 * Contains the generic response from an http request.
 * @author Scott Woodfield
 */
public class HttpURLResponse {
	//Domain Implementation
	/**
	 * The actual response code.  Can be any user defined integer (usually defined as integer constants in a class) but is usually a response code
	 * defined in HTTPURLConnection.
	 */
	private int responseCode;
	
	/**
	 * The length of the body in the response message.<br>
	 * <ol>
	 * 		<li>-1 means that the body is empty</li>
	 * 		<li>0 means that the body is of arbitrary size</li>
	 * 		<li> &gt; 0 means that the body length is exactly the number of bytes indicated.
	 * </ol>
	 */
	private int responseLength;
	
	/**
	 * A user defined response object.  May be null.
	 */
	private Object responseBody;
	
	//Constructors
	public HttpURLResponse() {
		this.responseCode = 0;
		this.responseLength = 0;
		this.responseBody = null;
	}
	
	public HttpURLResponse (int responseCode, int responseLength, Object responseBody) {
		this.responseCode = responseCode;
		this.responseLength = responseLength;
		this.responseBody = responseBody;
	}

	//Queries
	public int getResponseCode() {
		return responseCode;
	}

	public int getResponseLength() {
		return responseLength;
	}

	public Object getResponseBody() {
		return responseBody;
	}
	
	public boolean equals(Object response) {
		boolean result = response != null &&
				response instanceof HttpURLResponse;
		if(result) {
			HttpURLResponse httpURLResponse = (HttpURLResponse)response;
			result = httpURLResponse.responseCode == responseCode &&
					 httpURLResponse.responseLength == responseLength &&
					 (
							 (httpURLResponse.responseBody == null && responseBody == null) ||
							 (httpURLResponse.responseBody != null && responseBody != null &&
							 	httpURLResponse.responseBody.equals(responseBody)
							 )
					 );
		}
		return result;
	}

	//Commands
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

}
