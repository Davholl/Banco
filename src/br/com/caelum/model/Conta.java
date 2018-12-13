package br.com.caelum.model;

import java.math.BigDecimal;

import br.com.caelum.exceptions.OperacaoInvalidaException;
import br.com.caelum.exceptions.SaldoInsuficienteException;

public class Conta {
	private int numero;
	private BigDecimal saldo;
	private Cliente cliente;
	
	public String toString(){
		return this.cliente.getNome();
	}

	public Conta (int numero, Cliente cliente){
		this.saldo = new BigDecimal(0);
		this.cliente = cliente;
		this.setNumero(numero);
	}
	
	public void depositar(BigDecimal valor){
		if (valor.compareTo(new BigDecimal(0)) < 0){
			throw new IllegalArgumentException("Saldo Invalido");
		}
		this.saldo = this.saldo.add(valor);
	}
	
	public void sacar(BigDecimal valor){
		if (this.saldo.compareTo(valor) < 0){
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		
		if (valor.compareTo(new BigDecimal(0)) <= 0 ){
			throw new OperacaoInvalidaException("Não pode sacar 0 ou negativo");
		}
		this.saldo = this.saldo.subtract(valor);
	}
	
	public void transferir(BigDecimal valor, Conta conta){
		if (conta == this){
			throw new OperacaoInvalidaException("Operacao Invalida: Nao e possivel transferir para a mesma conta");
		}
		this.sacar(valor);
		conta.depositar(valor);
		
	}
	
	public boolean isEstilo(){
		if (this.getSaldo().compareTo(new BigDecimal(1000)) >= 0){
			return true;
		}
		return false;
	}
	public BigDecimal getSaldo(){
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
