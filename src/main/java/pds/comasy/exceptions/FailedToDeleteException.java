package pds.comasy.exceptions;

public class FailedToDeleteException extends Exception {

    public FailedToDeleteException() {
        super("Falha ao excluir a entidade.");
    }

    public FailedToDeleteException(String message) {
        super(message);
    }
}
