package br.com.gestao.vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() { super("O job nao foi encontrado");
    }
}
