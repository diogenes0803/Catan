package server.command;


@SuppressWarnings("serial")
public class IllegalCommandException extends Exception {

	IllegalCommandException() {}


	IllegalCommandException(String message) {
		super(message);
	}


	IllegalCommandException(Throwable cause) {
		super(cause);
	}


	IllegalCommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
