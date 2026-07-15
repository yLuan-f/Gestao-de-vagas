package br.com.gestao.vagas.exceptions;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("O usuario ja existe");
    }
}
