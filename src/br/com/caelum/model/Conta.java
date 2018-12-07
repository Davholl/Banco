package br.com.caelum.model;

import br.com.caelum.exceptions.SaldoInsuficiente;

public class Conta {
	private double saldo;
	
	public void depositar(double valor){
		if (valor < 0){
			throw new IllegalArgumentException("Saldo Invalido");
		}
		this.saldo += valor;
	}
	
	public void sacar(double valor){
		if (valor > this.saldo){
			throw new SaldoInsuficiente("Saldo insuficiente");
		}
		if (valor <= 0){
			throw new IllegalArgumentException("Não pode sacar 0 ou negativo");
		}
		this.saldo -= valor;
	}
	
	public void transferir(double valor, Conta conta){
		this.sacar(valor);
		conta.depositar(valor);
		
	}
	public double getSaldo(){
		return saldo;
	}
}
