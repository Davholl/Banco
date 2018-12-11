package br.com.caelum.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.exceptions.EscroticeException;
import br.com.caelum.exceptions.OperacaoInvalidaException;
import br.com.caelum.exceptions.ReceitaIndisponivelException;

public class Banco {
	private List<Conta> contas = new ArrayList<Conta>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private EnviadorDeEmail sender;
	private Receita receita;

	public Banco(EnviadorDeEmail enviadorDeEmail, Receita receita) {
		this.sender = enviadorDeEmail;
		this.receita = receita;
	}

	public void adicionaConta(Conta c) {
		if (contas.contains(c)) {
			throw new OperacaoInvalidaException("Essa conta já está no banco");
		}
		contas.add(c);
	}

	public List<Conta> getContas() {
		return contas;
	}

	public List<Conta> getContasEstilo() {

		return contas.stream().filter(conta -> conta.isEstilo())
				.collect(Collectors.toList());
	}

	public void enviarEmailEstiloAll() {
		sender.enviarEmails(getClientesEstilo());
		// return contasEstilo;
	}

	public List<Cliente> getClientesEstilo() {
		List<Conta> contasEstilo = getContasEstilo();
		List<Cliente> clientesEstilo = new ArrayList<Cliente>();
		for (Conta conta : contasEstilo) {
			clientesEstilo.add(conta.getCliente());
		}
		return clientesEstilo;
	}

	public List<Cliente> getClientesComCpfValido() {
		try {
		return contas
				.stream()
				.filter(conta -> receita.estaRegular(conta.getCliente()
						.getCpf())).map(Conta::getCliente)
				.collect(Collectors.toList());
		}catch(EscroticeException ee){
			throw new ReceitaIndisponivelException(ee);
		}
	}

}
