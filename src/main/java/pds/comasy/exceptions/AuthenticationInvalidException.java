package pds.comasy.exceptions;

public class AuthenticationInvalidException extends Exception {

    public AuthenticationInvalidException() {
        super("Authentication failed due to invalid credentials. Please check your username and password and try again.");
    }
}
