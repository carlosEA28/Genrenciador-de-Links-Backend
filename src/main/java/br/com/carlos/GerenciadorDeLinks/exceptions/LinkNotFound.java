package br.com.carlos.GerenciadorDeLinks.exceptions;

public class LinkNotFound extends RuntimeException {
    public LinkNotFound() {
        super("Link not found");
    }
}
