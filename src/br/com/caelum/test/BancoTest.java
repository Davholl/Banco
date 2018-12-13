package br.com.caelum.test;

import java.math.BigDecimal;

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
	private Conta conta4;
	
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
		
		conta1 = new Conta(1, cliente);
		conta2 = new Conta(1, cliente1);
		conta3 = new Conta(1, cliente2);
		conta4 = new Conta(2, cliente);
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
		conta1.depositar(new BigDecimal(500));
		conta2.depositar(new BigDecimal(1100));
		conta3.depositar(new BigDecimal(5000));
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		Assert.assertEquals(2, banco.getContasEstilo().size());
		
		Assert.assertEquals(3, banco.getContas().size());
	}
	
	@Test
	public void deve_notificar_por_email_contas_estilo(){
		conta1.depositar(new BigDecimal(500));
		conta2.depositar(new BigDecimal(1100));
		conta3.depositar(new BigDecimal(5000));
		
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		
		banco.enviarEmailEstiloAll();
		

		Mockito.verify(enviadorDeEmail).enviarEmails(banco.getClientesEstilo());
	}
	
	@Test
	public void deve_listar_contas_com_cpf_valido(){
		conta1.depositar(new BigDecimal(500));
		conta2.depositar(new BigDecimal(1100));
		conta3.depositar(new BigDecimal(5000));
		
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
	
	@Test
	public void deve_retornar_a_conta_com_o_maior_saldo(){
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		conta1.depositar(new BigDecimal(2000));
		conta2.depositar(new BigDecimal(3000));
		conta3.depositar(new BigDecimal(4000));
		
		Assert.assertEquals(conta3.getSaldo(), banco.getMaiorSaldo().getSaldo());
	}
	
	@Test
	public void deve_retornar_a_media_dos_saldos_do_banco(){
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		
		conta1.depositar(new BigDecimal(2000));
		conta2.depositar(new BigDecimal(3000));
		conta3.depositar(new BigDecimal(4000));
		
		Assert.assertEquals(new BigDecimal(3000), banco.getMediaSaldos());
	}
	
	@Test
	public void deve_agrupar_contas_do_mesmo_cliente_em_um_map(){
		//Conta1 e conta4 sao do mesmo cliente, contas 2 e 3 sao de clientes unicos
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		banco.adicionaConta(conta4);
		
		Assert.assertEquals(3,banco.getContasPorCliente().size());
		
		Assert.assertEquals(2, banco.getContasPorCliente().get(conta1.getCliente().getNome()).size());
		
	}
	
	public void deve_agrupar_somente_o_numero_de_contas_existentes_de_cada_cliente(){
		banco.adicionaConta(conta1);
		banco.adicionaConta(conta2);
		banco.adicionaConta(conta3);
		banco.adicionaConta(conta4);
		
		Assert.assertEquals(2, banco.getContasPorCliente().get(cliente));
	}
	
	
}
