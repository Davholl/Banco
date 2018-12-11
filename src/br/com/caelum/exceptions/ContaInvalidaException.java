package br.com.caelum.exceptions;

public class ContaInvalidaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994009340283788745L;
	
	public ContaInvalidaException(String msg){
		super(msg);
	}
	
}
