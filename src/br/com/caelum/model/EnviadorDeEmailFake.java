package br.com.caelum.model;

import java.util.List;

public class EnviadorDeEmailFake implements EnviadorDeEmail {
	
	public void enviarEmails(List<Cliente> destinos){
		//destinos.stream().forEach(conta -> System.out.println("Enviando email a " + conta.getEmail()));
		for (Cliente cliente : destinos) {
			System.out.println("nao estamos enviando email - eh so fake" + cliente.getEmail());
		}
	}
}
