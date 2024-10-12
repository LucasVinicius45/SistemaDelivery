package br.com.restaurante.sistemadelivery.util;

import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAO;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAOImpl;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoSushiDAOImpl;

public class EstabelecimentoDAOFactory {
	
	public static EstabelecimentoDAO criarEstabelecimentoDAO(boolean isSushi) {
		
		if(isSushi) {
			return new EstabelecimentoSushiDAOImpl();
		}
		return new EstabelecimentoDAOImpl();
	}
	}

