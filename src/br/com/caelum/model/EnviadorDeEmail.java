package br.com.caelum.model;

import java.util.List;

public interface EnviadorDeEmail {

	public abstract void enviarEmails(List<Cliente> destinos);

}