package br.com.restaurante.sistemadelivery.view;

import java.util.ArrayList;
import java.util.List;

import br.com.restaurante.sistemadelivery.controller.DeliveryController;
import br.com.restaurante.sistemadelivery.exception.DeliveryException;
import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.model.GerenciadorDelivery;
import br.com.restaurante.sistemadelivery.model.Motoboy;
import br.com.restaurante.sistemadelivery.model.Pedido;

public class ControleDeTeste {

    public static void main(String[] args) {

        // Criação do estabelecimento
        Estabelecimento estabelecimento = criarEstabelecimento();
        
        // Criação da lista de clientes e exibição
        List<Cliente> listaCliente = criarListaClientes();
        mostrarListaCliente(listaCliente);
        
        DeliveryController clienteController = new DeliveryController();
        
        // Inserção dos clientes no banco de dados
        for (Cliente cliente : listaCliente) {
            clienteController.incluirCliente(cliente.getEndereco(), cliente.getNome(), cliente.getEmail());
        }
        
        // Criação e inserção do estabelecimento no banco de dados
        DeliveryController estabelecimentoController = new DeliveryController(true);
        estabelecimentoController.incluirEstabelecimento(estabelecimento.getEndereco(), estabelecimento);
        
        // Criação da lista de motoboys e exibição
        List<Motoboy> listaMotoboy = criarListaMotoboys();
        mostrarListaMotoboy(listaMotoboy);
        
        DeliveryController motoboyController = new DeliveryController();
        
        // Inserção dos motoboys no banco de dados
        for (Motoboy motoboy : listaMotoboy) {
            motoboyController.incluirMotoboy(motoboy);
        }
        
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
        listaCliente.add(new Cliente("Chico", "Chico@gmail.com", new Endereco("São Paulo", "Tatuapé", "030300")));
        listaCliente.add(new Cliente("Tony", "Tony@gmail.com", new Endereco("São Paulo", "Belém", "050200")));
        listaCliente.add(new Cliente("Ricardo", "Ricardo@gmail.com", new Endereco("São Paulo", "Brás", "087234")));
        listaCliente.add(new Cliente("Murilo", "Murilo@gmail.com", new Endereco("São Paulo", "Mooca", "070700")));
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
