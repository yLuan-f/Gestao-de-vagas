package br.com.gestao.vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() { super("Company not found"); }
}
