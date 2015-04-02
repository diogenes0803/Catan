package client.network;


public class NetworkException extends Exception {

    NetworkException() {}


    NetworkException(String message) {
        super(message);
    }


    NetworkException(Throwable cause) {
        super(cause);
    }


    NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
