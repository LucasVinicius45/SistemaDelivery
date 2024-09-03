package br.com.restaurante.sistemadelivery.model;

public class Cliente {
	
	/**
	 * Definindo campos da classe Cliente
	 */
	private String nome;
	private Endereco endereco;

	
	/**
	 * Construtor
	 * @param nome
	 */
	public Cliente(String nome, Endereco endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}

	/**
	 * GET E SET
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	/**
	 * Apresenta informações sobre o Cliente
	 */
	public void apresentarCliente() {
		System.out.println("Cliente: " + this.nome + ", Endereço: " + this.getEndereco());
	}

	

}
