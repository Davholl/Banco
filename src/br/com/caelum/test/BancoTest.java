package br.com.caelum.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.exceptions.EscroticeException;
import br.com.caelum.exceptions.OperacaoInvalidaException;
import br.com.caelum.exceptions.ReceitaIndisponivelException;
import br.com.caelum.model.Banco;
import br.com.caelum.model.Cliente;
import br.com.caelum.model.Conta;
import br.com.caelum.model.EnviadorDeEmail;
import br.com.caelum.model.Receita;


@RunWith(MockitoJUnitRunner.class)
public class BancoTest {
	private Banco banco;
	private Conta conta1;
	private Conta conta2;
	private Conta conta3;
	
	private Cliente cliente;
	private Cliente cliente1;
	private Cliente cliente2;
	
	@Mock
	private EnviadorDeEmail enviadorDeEmail;
	@Mock
	private Receita receita;
	
	@Before
	public void setUp(){
		banco = new Banco(enviadorDeEmail, receita);
		cliente = new Cliente("Jose da Silva", 2l, "js@gmail.com");
		cliente1 = new Cliente("Pedro Pereira", 3l, "pp@gmail.com");
		cliente2 = new Cliente("Joao Martins", 4l, "jm@gmail.com");
		
		conta1 = new Conta(cliente);
		conta2 = new Conta(cliente1);
		conta3 = new Conta(cliente2);
	}

	@Test
	public void deve_adicionar_contas(){
		banco.adicionaConta(conta1);
		Assert.assertTrue(banco.getContas().contains(conta1));
	}
	
	@Test(expected=OperacaoInvalidaException.class)
	public void nao_deve_adicionar_a_mesma_conta_duas_vezes(){
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta1);
	}
	
	@Test
	public void deve_retornar_lista_de_todas_as_contas_com_saldo_maior_que_mil(){
		conta1.depositar(500);
		conta2.depositar(1100);
		conta3.depositar(5000);
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		Assert.assertEquals(2, banco.getContasEstilo().size());
		
		Assert.assertEquals(3, banco.getContas().size());
	}
	
	@Test
	public void deve_notificar_por_email_contas_estilo(){
		conta1.depositar(500);
		conta2.depositar(1100);
		conta3.depositar(5000);
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		
		banco.enviarEmailEstiloAll();
		

		Mockito.verify(enviadorDeEmail).enviarEmails(banco.getClientesEstilo());
	}
	
	@Test
	public void deve_listar_contas_com_cpf_valido(){
		conta1.depositar(500);
		conta2.depositar(1100);
		conta3.depositar(5000);
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		Mockito.when(receita.estaRegular(conta1.getCliente().getCpf())).thenReturn(Boolean.TRUE);
		Mockito.when(receita.estaRegular(conta2.getCliente().getCpf())).thenReturn(Boolean.FALSE);
		Mockito.when(receita.estaRegular(conta3.getCliente().getCpf())).thenReturn(Boolean.TRUE);
		
		Assert.assertEquals(2, banco.getClientesComCpfValido().size());
	}
	
	@Test(expected=ReceitaIndisponivelException.class)
	public void deve_jogar_o_receitaIndisponivelException_quando_receber_escroticeException(){
		
		Mockito.doThrow(EscroticeException.class).when(receita).estaRegular(conta1.getCliente().getCpf());
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		banco.getClientesComCpfValido();
	}
	
	
	
}
