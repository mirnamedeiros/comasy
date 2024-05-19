package pds.comasy.exceptions;

public class AuthenticationInvalidException extends Exception {

    public AuthenticationInvalidException() {
        super("A autenticação falhou devido a credenciais inválidas. Por favor, verifique o seu nome de usuário e senha e tente novamente.");
    }
}
