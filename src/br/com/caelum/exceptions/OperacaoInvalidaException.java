package br.com.caelum.exceptions;

public class OperacaoInvalidaException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5322085208456382361L;

	public OperacaoInvalidaException(String msg){
		super(msg);
	}
}
