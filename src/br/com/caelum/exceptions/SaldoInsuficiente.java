package br.com.caelum.exceptions;

public class SaldoInsuficiente extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficiente(String msg){
		super(msg);
	}
}
