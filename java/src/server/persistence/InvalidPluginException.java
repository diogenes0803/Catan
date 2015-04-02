package server.persistence;


@SuppressWarnings("serial")
public class InvalidPluginException extends Exception {

	InvalidPluginException() {}


	InvalidPluginException(String message) {
		super(message);
	}


	InvalidPluginException(Throwable cause) {
		super(cause);
	}


	InvalidPluginException(String message, Throwable cause) {
		super(message, cause);
	}
}
