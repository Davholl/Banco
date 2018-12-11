package br.com.caelum.exceptions;

public class ReceitaIndisponivelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3651079595649573697L;
	
	public ReceitaIndisponivelException(EscroticeException ee){
		super("Receita indisponivel, cliente pode ver isso", ee);
	}

}
