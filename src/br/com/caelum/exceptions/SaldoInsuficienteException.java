package br.com.caelum.exceptions;

public class SaldoInsuficienteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6992110079522104400L;

	public SaldoInsuficienteException(String msg){
		super(msg);
	}
}
