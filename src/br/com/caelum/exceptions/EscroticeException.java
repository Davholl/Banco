package br.com.caelum.exceptions;

public class EscroticeException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8117268521960983484L;
	
	public EscroticeException(){
		super ("Erro escroto enviado pela receita, cliente não pode ver");
	}

}
