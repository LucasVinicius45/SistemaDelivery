package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Endereco;

public interface EnderecoDAO {
	
	public void criarTabelaEndereco();
	
	public void incluirEndereco(Endereco endereco);
}
