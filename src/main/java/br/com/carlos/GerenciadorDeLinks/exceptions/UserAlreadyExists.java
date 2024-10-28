package br.com.carlos.GerenciadorDeLinks.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User Already exists");
    }
}
