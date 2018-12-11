package br.com.caelum.model;


public class Receita {
	public boolean estaRegular(long cpf){
			System.out.println("Esse aqui é a verdadeira receita");
			if (cpf%2 == 0){
				return true;
			}
			return false;
	}
}

