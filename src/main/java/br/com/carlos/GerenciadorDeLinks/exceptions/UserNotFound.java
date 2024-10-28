package br.com.carlos.GerenciadorDeLinks.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
}
