package com.attornatus.exception;

public class PessoaException extends Exception {
	private static final long serialVersionUID = 1L;

	public PessoaException() {
    	   super("Erro, pessoa já cadastrada");
	}

}
