package br.com.restaurante.sistemadelivery.test;

import java.util.ArrayList;
import java.util.List;
import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.model.Motoboy;
import br.com.restaurante.sistemadelivery.model.Pedido;
import br.com.restaurante.sistemadelivery.model.GerenciadorDelivery;
import br.com.restaurante.sistemadelivery.model.GerenciadorDelivery.MotoboyNaoDisponivelException;

public class ControleDeTeste {

    public static void main(String[] args) {
        List<Cliente> listaCliente = criarListaClientes();
        List<Motoboy> listaMotoboy = criarListaMotoboys();
        Estabelecimento estabelecimento = criarEstabelecimento();
        mostrarListaMotoboy(listaMotoboy);
        mostrarListaCliente(listaCliente);
        GerenciadorDelivery gerenciador = new GerenciadorDelivery();
        gerenciador.setListaPedido(new ArrayList <Pedido>());
        
        try {
            gerenciador.criarPedidos(listaCliente, estabelecimento, listaMotoboy);
        } catch (MotoboyNaoDisponivelException e) {
            System.err.println("Erro: " + e.getMessage());
            // Aqui você pode tratar o erro de forma apropriada, como registrá-lo ou alertar o usuário.
        }
    }
    
    public static List<Cliente> criarListaClientes() {
        List<Cliente> listaCliente = new ArrayList<>();
        
       
        listaCliente.add(new Cliente("Chico", new Endereco("São Paulo", "Tatuapé", "030300")));
        listaCliente.add(new Cliente("Tony", new Endereco("São Paulo", "Belém", "050200")));
        listaCliente.add(new Cliente("Ricardo", new Endereco("São Paulo", "Brás", "087234")));
        listaCliente.add(new Cliente("Murilo", new Endereco("São Paulo", "Mooca", "070700")));
        
        return listaCliente;
    }
    
    public static Estabelecimento criarEstabelecimento() {
        Endereco endereco = new Endereco("São Paulo", "Tatuapé", "020500");
        Estabelecimento estabelecimento = new Estabelecimento("Mata Fome", endereco, "(11) 2446-7321");
        return estabelecimento;
    }
    
    public static List<Motoboy> criarListaMotoboys() {
        List<Motoboy> listaMotoboy = new ArrayList<>();
        
        listaMotoboy.add(new Motoboy("Hugo", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Felipe", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Guilherme", "Motocicleta", false));
        listaMotoboy.add(new Motoboy("Yan", "Motocicleta", true));
        
        return listaMotoboy;
    }
    
    public static void mostrarListaCliente(List<Cliente> listaCliente) {
        System.out.println("\n--- CLIENTES ---\n");
        for (Cliente cliente : listaCliente) {
            cliente.apresentarCliente();
        }
    }
    
    public static void mostrarListaMotoboy(List<Motoboy> listaMotoboy) {
        System.out.println("\n--- MOTOBOYS ---\n");
        for (Motoboy motoboy : listaMotoboy) {
            motoboy.apresentarMotoboy();
        }
    }
}
