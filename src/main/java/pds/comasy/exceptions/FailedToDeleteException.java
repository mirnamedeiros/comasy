package pds.comasy.exceptions;

public class FailedToDeleteException extends Exception {

    public FailedToDeleteException() {
        super("Failed to delete entity.");
    }

    public FailedToDeleteException(String message) {
        super(message);
    }
}
