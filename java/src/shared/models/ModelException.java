package shared.models;

/**
 * An exception that is thrown when there are problems talking to the server.
 *
 * This class INTENTIONALLY uses package visibility for its constructors.
 * Only Model classes should be able to create ModelExceptions.
 * @author Wyatt
 */
public class ModelException extends Exception {
    /**
     * Construct a ModelException with no specified message.
     */
    ModelException() {}

    /**
     * Construct an exception with a specified message.
     * @param message a message containing details about the problem
     */
    ModelException(String message) {
        super(message);
    }

    /**
     * Construct a ModelException with another exception as its cause.
     * @param cause the exception that triggered this exception
     */
    ModelException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct a ModelException with another exception as a cause and a
     * specified message.
     * @param message a message to add to the exception
     * @param cause the exception that triggered this exception
     */
    ModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
