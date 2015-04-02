package shared.model;


public class ModelException extends Exception {

    ModelException() {}


    ModelException(String message) {
        super(message);
    }


    ModelException(Throwable cause) {
        super(cause);
    }


    ModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
