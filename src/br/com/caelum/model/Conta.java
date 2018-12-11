package br.com.caelum.model;

import br.com.caelum.exceptions.OperacaoInvalidaException;
import br.com.caelum.exceptions.SaldoInsuficienteException;

public class Conta {
	private double saldo;
	private Cliente cliente;
	
	public String toString(){
		return this.cliente.getNome();
	}

	public Conta (Cliente cliente){
		this.cliente = cliente;
	}
	
	public void depositar(double valor){
		if (valor < 0){
			throw new IllegalArgumentException("Saldo Invalido");
		}
		this.saldo += valor;
	}
	
	public void sacar(double valor){
		if (valor > this.saldo){
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		if (valor <= 0){
			throw new OperacaoInvalidaException("Não pode sacar 0 ou negativo");
		}
		this.saldo -= valor;
	}
	
	public void transferir(double valor, Conta conta){
		if (conta == this){
			throw new OperacaoInvalidaException("Operacao Invalida: Nao e possivel transferir para a mesma conta");
		}
		this.sacar(valor);
		conta.depositar(valor);
		
	}
	
	public boolean isEstilo(){
		if (this.getSaldo() >= 1000){
			return true;
		}
		return false;
	}
	public double getSaldo(){
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
