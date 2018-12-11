package br.com.caelum.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.exceptions.OperacaoInvalidaException;
import br.com.caelum.exceptions.SaldoInsuficienteException;
import br.com.caelum.model.Cliente;
import br.com.caelum.model.Conta;

public class ContaTest {
	
	private Conta conta1;
	private Conta conta2;
	
	private Cliente cliente1;
	private Cliente cliente2;
	
	@Before
	public void setUp(){
		cliente1 = new Cliente("Jose da Silva", 1234l, "js@gmail.com");
		cliente2 = new Cliente("Pedro Pereira", 2345l, "pp@gmail.com");
		conta1 = new Conta(cliente1);
		conta2 = new Conta(cliente2);
	}
	
	@Test
	public void deve_possuir_cliente(){
		Assert.assertEquals(false, conta1.getCliente());
	}
	
	
	@Test
	public void deve_depositar_valor_em_conta() {
		double valorDeposito = 100;
		conta1.depositar(valorDeposito);
		Assert.assertEquals(100, conta1.getSaldo(), 0.0001);
	}
	
	@Test
	public void deve_manter_conta_zerada(){
		conta1.depositar(0);
		Assert.assertEquals(0, conta1.getSaldo(), 0.0001);
	}
	
	@Test
	public void deve_somar_os_valores(){
		double valorDeposito = 100;
		conta1.depositar(valorDeposito);	
		
		valorDeposito = 200; 
		conta1.depositar(valorDeposito);
		
		Assert.assertEquals(300,conta1.getSaldo(),0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nao_deve_aceitar_deposito_negativo(){
		conta1.depositar(-50);
		Assert.assertEquals(0, conta1.getSaldo(),0.001);
	}
	
	@Test
	public void deve_sacar_valor(){
		conta1.depositar(100);
		conta1.sacar(20);
		Assert.assertEquals(80, conta1.getSaldo(), 0.0001);
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void nao_deve_sacar_mais_que_o_saldo(){
		conta1.depositar(100);
		conta1.sacar(200);
	}
	
	@Test(expected=OperacaoInvalidaException.class)
	public void nao_deve_sacar_zero(){
		conta1.depositar(100);
		conta1.sacar(0);
	}
	
	@Test(expected=OperacaoInvalidaException.class)
	public void nao_deve_sacar_negativo(){
		conta1.depositar(100);
		conta1.sacar(-30);
	}

	@Test
	public void deve_transferir_entre_duas_contas(){
		
		conta1.depositar(200);
		conta1.transferir(100, conta2);
		
		Assert.assertEquals(100, conta2.getSaldo(), 0.001);	
	}
	
	@Test(expected=OperacaoInvalidaException.class)
	public void nao_pode_transferir_para_si_mesmo(){
		conta1.depositar(200);
		conta1.transferir(100, conta1);
	}
	
	@Test
	public void conta_deve_ser_estilo_se_saldo_maior_do_1000(){
	}
	
}
