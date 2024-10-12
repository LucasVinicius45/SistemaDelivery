package br.com.restaurante.sistemadelivery.controller;

import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAO;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAOImpl;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.util.EstabelecimentoDAOFactory;

public class EstabelecimentoController {

	private EstabelecimentoDAO estabelecimentoDAO;
	
	public EstabelecimentoController() {
		estabelecimentoDAO = new EstabelecimentoDAOImpl();
	}
	
	public EstabelecimentoController(boolean isSushi) {
		estabelecimentoDAO = EstabelecimentoDAOFactory.criarEstabelecimentoDAO(isSushi);
	}
	public void incluirEstabelecimento(Estabelecimento estabelecimento) {
		estabelecimentoDAO.incluirEstabelecimento(estabelecimento);;
	}

}
