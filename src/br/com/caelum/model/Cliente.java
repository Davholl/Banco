package br.com.caelum.model;

public class Cliente {
	private String nome;
	private long cpf;
	private String email;
	
	public Cliente(String nome, long cpf, String email){
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ "]";
	}
	
	
	
	
}
