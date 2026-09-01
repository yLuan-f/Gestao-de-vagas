package br.com.gestao.vagas.exceptions;

public class AuthCredentialsException extends RuntimeException{
    public AuthCredentialsException() { super("Invalid credentials"); }
}
