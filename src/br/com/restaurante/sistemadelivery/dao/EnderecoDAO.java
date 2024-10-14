package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Endereco;

public interface EnderecoDAO {
	
	public void criarSequenceEndereco();
	
	public void criarTabelaEndereco();
	
	public void criarTriggerEndereco();
	
	public Endereco incluirEndereco(Endereco endereco);
	
	
}
