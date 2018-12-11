package br.com.caelum.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.caelum.exceptions.EscroticeException;
import br.com.caelum.exceptions.ReceitaIndisponivelException;
import br.com.caelum.model.Cliente;
import br.com.caelum.model.Receita;

public class ReceitaTest {
	private Receita receita;
	
	private Cliente cliente1;
	private Cliente cliente2;
	
	@Before
	public void setUp(){
		receita = new Receita();
		cliente1 = new Cliente("Pedro Pereira", 3l, "pp@gmail.com");
		cliente2 = new Cliente("Joao Martins", 4l, "jm@gmail.com");
	}
	
	@Test
	public void deve_identificar_cpf_valido(){
		Assert.assertTrue(receita.estaRegular(cliente2.getCpf()));
	}
	
	@Test
	public void deve_identificar_cpf_invalido(){
		Assert.assertFalse(receita.estaRegular(cliente1.getCpf()));
	}
	
}
