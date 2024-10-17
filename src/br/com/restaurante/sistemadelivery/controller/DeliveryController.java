package br.com.restaurante.sistemadelivery.controller;

import br.com.restaurante.sistemadelivery.dao.ClienteDAO;
import br.com.restaurante.sistemadelivery.dao.ClienteDAOImpl;
import br.com.restaurante.sistemadelivery.dao.EnderecoDAO;
import br.com.restaurante.sistemadelivery.dao.EnderecoDAOImpl;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAO;
import br.com.restaurante.sistemadelivery.dao.EstabelecimentoDAOImpl;
import br.com.restaurante.sistemadelivery.dao.MotoboyDAO;
import br.com.restaurante.sistemadelivery.dao.MotoboyDAOImpl;
import br.com.restaurante.sistemadelivery.dao.PedidoDAO;
import br.com.restaurante.sistemadelivery.dao.PedidoDAOImpl;
import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.model.Motoboy;
import br.com.restaurante.sistemadelivery.model.Pedido;
import br.com.restaurante.sistemadelivery.util.EstabelecimentoDAOFactory;

public class DeliveryController {

	private EstabelecimentoDAO estabelecimentoDAO;
	private EnderecoDAO enderecoDAO;
	private ClienteDAO clienteDAO;
	private MotoboyDAO motoboyDAO;
	private PedidoDAO pedidoDAO;
	
	public DeliveryController() {
		estabelecimentoDAO = new EstabelecimentoDAOImpl();
		enderecoDAO = new EnderecoDAOImpl();
		clienteDAO = new ClienteDAOImpl();
		motoboyDAO = new MotoboyDAOImpl();
		pedidoDAO = new PedidoDAOImpl();
		
	}
	
	public DeliveryController(boolean isSushi) {
		estabelecimentoDAO = EstabelecimentoDAOFactory.criarEstabelecimentoDAO(isSushi);
	}
	
	public void incluirEstabelecimento(Endereco enderecoID, Estabelecimento estabelecimento) {
		System.out.println("Tel: " + estabelecimento.getTel());
		estabelecimentoDAO.incluirEstabelecimento(enderecoID, estabelecimento);
	}
	
	public void criarTabelaEstabelecimento() {
		estabelecimentoDAO.criarTabelaEstabelecimento();
	}
	
	public void incluirEndereco(Endereco endereco) {
		enderecoDAO.incluirEndereco(endereco);
	}
	
	public void criarTabelaEndereco() {
		enderecoDAO.criarTabelaEndereco();
	}
	
	public void criarSequenceEndereco() {
		enderecoDAO.criarSequenceEndereco();
	}
	public void criarTriggerEndereco() {
		enderecoDAO.criarTriggerEndereco();
	}
	
	public void incluirCliente(Endereco enderecoID, String nome, String email) {
		clienteDAO.incluirCliente(enderecoID, nome, email);
	}
	
	public void criarTabelaCliente() {
		clienteDAO.criarTabelaCliente();
	}
	
	public void incluirMotoboy(Motoboy motoboy) {
		motoboyDAO.incluirMotoboy(motoboy);
	}
	
	public void criarTabelaMotoboy() {
		motoboyDAO.criarTabelaMotoboy();
	}
	
	public void criarSequenceMotoboy() {
		motoboyDAO.criarSequenceMotoboy();
	}
	
	public void criarTriggerMotoboy() {
		motoboyDAO.criarTriggerMotoboy();
	}
	
	public void incluirPedido(Pedido pedido) {
		pedidoDAO.incluirPedido(pedido);
	}
	
	public void criarTabelaPedido() {
		pedidoDAO.criarTabelaPedido();
	}
	
	public void criarSequencePedido() {
		pedidoDAO.criarSequencePedido();
	}
	
	public void criarTriggerPedido() {
		pedidoDAO.criarTriggerPedido();
	}


}
