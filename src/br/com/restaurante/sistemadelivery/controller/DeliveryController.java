package br.com.restaurante.sistemadelivery.controller;

import br.com.restaurante.sistemadelivery.dao.EnderecoDAO;
import br.com.restaurante.sistemadelivery.dao.EnderecoDAOImpl;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAO;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAOImpl;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.util.EstabelecimentoDAOFactory;

public class DeliveryController {

	private EstabelecimentoDAO estabelecimentoDAO;
	private EnderecoDAO enderecoDAO;
	
	public DeliveryController() {
		estabelecimentoDAO = new EstabelecimentoDAOImpl();
		enderecoDAO = new EnderecoDAOImpl();
		
	}
	
	public DeliveryController(boolean isSushi) {
		estabelecimentoDAO = EstabelecimentoDAOFactory.criarEstabelecimentoDAO(isSushi);
	}
	
	public void incluirEstabelecimento(Estabelecimento estabelecimento) {
		estabelecimentoDAO.incluirEstabelecimento(estabelecimento);
	}
	
	public void incluirEndereco(Endereco endereco) {
		enderecoDAO.incluirEndereco(endereco);;
	}

}
