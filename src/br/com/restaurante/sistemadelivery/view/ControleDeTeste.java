package br.com.restaurante.sistemadelivery.view;

import java.util.ArrayList;
import java.util.List;

import br.com.restaurante.sistemadelivery.controller.DeliveryController;
import br.com.restaurante.sistemadelivery.dao.PedidoDAOImpl;
import br.com.restaurante.sistemadelivery.exception.DeliveryException;
import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.model.GerenciadorDelivery;
import br.com.restaurante.sistemadelivery.model.Motoboy;
import br.com.restaurante.sistemadelivery.model.Pedido;

public class ControleDeTeste {

    public static void main(String[] args) {

    	Estabelecimento estabelecimento = criarEstabelecimento();
    	
        // Cria script sql
    	
        DeliveryController clienteController = new DeliveryController();
        DeliveryController tabelaController = new DeliveryController();
        DeliveryController estabelecimentoController = new DeliveryController(true);
        DeliveryController motoboyController = new DeliveryController();
        PedidoDAOImpl pedidoDAO = new PedidoDAOImpl();
        
        tabelaController.criarTabelaEndereco();
        clienteController.criarTabelaCliente();
        estabelecimentoController.criarTabelaEstabelecimento();
        motoboyController.criarTabelaMotoboy();
        pedidoDAO.criarTabelaPedido();
        tabelaController.criarSequenceEndereco();
        tabelaController.criarTriggerEndereco();
        motoboyController.criarSequenceMotoboy();
        motoboyController.criarTriggerMotoboy();
        pedidoDAO.criarSequencePedido();
        pedidoDAO.criarTriggerPedido();
       
        
        // Incluindo no Banco de Dados
        
        List<Cliente> listaCliente = criarListaClientes();
        List<Motoboy> listaMotoboy = criarListaMotoboys();
        
        for (Cliente cliente : listaCliente) {
        	
             clienteController.incluirCliente(cliente.getEndereco(), cliente.getNome(), cliente.getEmail());
            
        }
        
        estabelecimentoController.incluirEstabelecimento(estabelecimento.getEndereco(), estabelecimento);
            
        for (Motoboy motoboy : listaMotoboy) {
            motoboyController.incluirMotoboy(motoboy);
        }
        mostrarListaMotoboy(listaMotoboy);
        mostrarListaCliente(listaCliente);
        // Gerenciamento e criação dos pedidos
        GerenciadorDelivery gerenciador = new GerenciadorDelivery();
        gerenciador.setListaPedido(new ArrayList<>());  // Inicializa a lista de pedidos

        try {
            // Criação dos pedidos e exibição
            gerenciador.criarPedidos(listaCliente, estabelecimento, listaMotoboy);
        } catch (DeliveryException e) {
            System.err.println("Erro: " + e.getMessage()); // Trata exceção caso não haja motoboys disponíveis
        }
        
         // Exibição e inserção dos pedidos no banco de dados
        DeliveryController pedidoController = new DeliveryController();
        for (Pedido pedido : gerenciador.getListaPedido()) {
            pedidoController.incluirPedido(pedido);
            System.out.println("Pedido inserido: " + pedido.toString());
        }
    }

    // Cria uma lista de Clientes
    public static List<Cliente> criarListaClientes() {
        List<Cliente> listaCliente = new ArrayList<>();
        listaCliente.add(new Cliente("Alberto", "Alberto@gmail.com", new Endereco("Minas Gerais", "Belo Horizonte", "030300")));
        listaCliente.add(new Cliente("Alfredo", "Alfredo@gmail.com", new Endereco("Pará", "Belém", "050200")));
        listaCliente.add(new Cliente("Augusto", "Augusto@gmail.com", new Endereco("São Paulo", "Brás", "087234")));
        listaCliente.add(new Cliente("José", "Jose@gmail.com", new Endereco("São Paulo", "Mooca", "070700")));
        return listaCliente;
    }

    // Cria o Estabelecimento
    public static Estabelecimento criarEstabelecimento() {
        Endereco endereco = new Endereco("São Paulo", "Tatuapé", "070300");
        return new Estabelecimento("Mata Fome Sushi 51", "Sushi", endereco, "(11) 2336-7321");
    }

    // Cria uma lista de Motoboys
    public static List<Motoboy> criarListaMotoboys() {
        List<Motoboy> listaMotoboy = new ArrayList<>();
        listaMotoboy.add(new Motoboy("Hugo", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Felipe", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Guilherme", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Yan", "Motocicleta", true));  // Já ocupado
        return listaMotoboy;
    }

    // Mostra a lista de Clientes
    public static void mostrarListaCliente(List<Cliente> listaCliente) {
        System.out.println("\n--- CLIENTES ---\n");
        for (Cliente cliente : listaCliente) {
            cliente.apresentarCliente();
        }
    }

    // Mostra a lista de Motoboys
    public static void mostrarListaMotoboy(List<Motoboy> listaMotoboy) {
        System.out.println("\n--- MOTOBOYS ---\n");
        for (Motoboy motoboy : listaMotoboy) {
            motoboy.apresentarMotoboy();
        }
    }
}
