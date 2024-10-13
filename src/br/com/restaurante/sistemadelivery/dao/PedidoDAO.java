package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Pedido;

public interface PedidoDAO {
	
public void criarTabelaPedido();
	
	public void incluirPedido(Pedido pedido);
}
