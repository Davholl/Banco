package br.com.caelum.model;

import java.util.List;

public class EnviadorDeEmailReal implements EnviadorDeEmail {

	/* (non-Javadoc)
	 * @see br.com.caelum.model.EnviadorDeEmail#enviarEmails(java.util.List)
	 */
	@Override
	public void enviarEmails(List<Cliente> destinos){
		//destinos.stream().forEach(conta -> System.out.println("Enviando email a " + conta.getEmail()));
		for (Cliente cliente : destinos) {
			System.out.println("tcp 25 envinado email an real fudendo com clientre" + cliente.getEmail());
		}
	}
}
