package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;

public interface EstabelecimentoDAO {
	
	public void criarTabelaEstabelecimento();
	
	public void incluirEstabelecimento(Endereco enderecoID, Estabelecimento estabelecimento);
}
