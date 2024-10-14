package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Endereco;

public interface ClienteDAO {
	
	public void criarTabelaCliente();
		
	public void incluirCliente(Endereco enderecoID, String nome, String Email);
}
