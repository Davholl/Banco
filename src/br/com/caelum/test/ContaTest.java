package br.com.caelum.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.exceptions.SaldoInsuficiente;
import br.com.caelum.model.Conta;

public class ContaTest {

	@Test
	public void deve_depositar_valor_em_conta() {
		Conta c = new Conta();
		double valorDeposito = 100;
		c.depositar(valorDeposito);
		Assert.assertEquals(100, c.getSaldo(), 0.0001);
	}
	
	@Test
	public void deve_manter_conta_zerada(){
		Conta c = new Conta();
		c.depositar(0);
		Assert.assertEquals(0, c.getSaldo(), 0.0001);
	}
	
	@Test
	public void deve_somar_os_valores(){
		double valorDeposito = 100;
		Conta c = new Conta();
		c.depositar(valorDeposito);	
		
		valorDeposito = 200; 
		c.depositar(valorDeposito);
		
		Assert.assertEquals(300,c.getSaldo(),0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nao_deve_aceitar_deposito_negativo(){
		Conta c = new Conta();
		c.depositar(-50);
		Assert.assertEquals(0, c.getSaldo(),0.001);
	}
	
	@Test
	public void deve_sacar_valor(){
		Conta c = new Conta();
		c.depositar(100);
		c.sacar(20);
		Assert.assertEquals(80, c.getSaldo(), 0.0001);
	}
	
	@Test(expected=SaldoInsuficiente.class)
	public void nao_deve_sacar_mais_que_o_saldo(){
		Conta c = new Conta();
		c.depositar(100);
		c.sacar(200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nao_deve_sacar_zero(){
		Conta c = new Conta();
		c.depositar(100);
		c.sacar(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nao_deve_sacar_negativo(){
		Conta c = new Conta();
		c.depositar(100);
		c.sacar(-30);
	}

	@Test
	public void deve_transferir_entre_duas_contas(){
		Conta c1 = new Conta();
		Conta c2 = new Conta();
		
		c1.depositar(200);
		c1.transferir(100, c2);
		
		Assert.assertEquals(100, c2.getSaldo(), 0.001);	
	}
	@Test
	public void Nao_pode_transferir_para_si_mesmo(){
		
	}
	
}
