package br.com.restaurante.sistemadelivery.util;

import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAO;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAOImpl;

public class EstabelecimentoDAOFactory {
	
	public static EstabelecimentoDAO criarEstabelecimentoDAO(boolean isSushi) {
		
		return new EstabelecimentoDAOImpl();
	}
	}

