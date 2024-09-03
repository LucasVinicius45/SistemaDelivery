package br.com.restaurante.sistemadelivery.model;

public class Estabelecimento {
	
	/**
	 * Campos
	 */
	private String nome;
	private Endereco endereco;
	private String tel;
	
	/**
	 * Construtor
	 * @param endereco
	 * @param tel
	 */
	public Estabelecimento(String nome, Endereco endereco, String tel) {
		super();
		this.setNome(nome);
		this.endereco = endereco;
		this.tel = tel;
	}
	/**
	 * GET E SET
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
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public void apresentarEstabelecimento() {
		System.out.println("Nome do restaurante:" + this.nome + "\nEndereço: " + this.endereco + "\nTel: " + this.tel);
	}
}
