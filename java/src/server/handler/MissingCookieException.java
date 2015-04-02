package server.handler;


@SuppressWarnings("serial")
public class MissingCookieException extends Exception {

	public MissingCookieException() {}


	public MissingCookieException(String message) {
		super(message);
	}


	public MissingCookieException(Throwable cause) {
		super(cause);
	}


	public MissingCookieException(String message, Throwable cause) {
		super(message, cause);
	}
}
