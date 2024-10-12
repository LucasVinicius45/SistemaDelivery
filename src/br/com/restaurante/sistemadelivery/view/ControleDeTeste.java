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

		Estabelecimento estabelecimento = criarEstabelecimento();
		DeliveryController estabelecimentoController = new DeliveryController(true);
		estabelecimentoController.incluirEstabelecimento(estabelecimento.getEndereco(), estabelecimento);
		
		List<Cliente> listaCliente = criarListaClientes(); 
		List<Motoboy> listaMotoboy = criarListaMotoboys();
		mostrarListaMotoboy(listaMotoboy); 
		mostrarListaCliente(listaCliente);
		GerenciadorDelivery gerenciador = new GerenciadorDelivery();
		gerenciador.setListaPedido(new ArrayList <Pedido>());
		 try { gerenciador.criarPedidos(listaCliente, estabelecimento, listaMotoboy);
		 } catch (DeliveryException e) { System.err.println("Erro: " + e.getMessage()); // Para tratar o erro de forma apropriada }
		 
	}
	}

	// Cria Lista de Clientes
	public static List<Cliente> criarListaClientes() {
		List<Cliente> listaCliente = new ArrayList<>();

		listaCliente.add(new Cliente("Chico", new Endereco("São Paulo", "Tatuapé", "030300")));
		listaCliente.add(new Cliente("Tony", new Endereco("São Paulo", "Belém", "050200")));
		listaCliente.add(new Cliente("Ricardo", new Endereco("São Paulo", "Brás", "087234")));
		listaCliente.add(new Cliente("Murilo", new Endereco("São Paulo", "Mooca", "070700")));

		return listaCliente;
	}

	// Cria o estabelecimento
	public static Estabelecimento criarEstabelecimento() {
		Endereco endereco = new Endereco("São Paulo", "Tatuapé", "020700");
		Estabelecimento estabelecimento = new Estabelecimento("Mata Fome Sushi 5", "Sushi", endereco, "(11) 2336-7321");
		return estabelecimento;
	}

	// cria uma lista de Motoboys
	public static List<Motoboy> criarListaMotoboys() {
		List<Motoboy> listaMotoboy = new ArrayList<>();

		listaMotoboy.add(new Motoboy("Hugo", "Motocicleta", false));
		listaMotoboy.add(new Motoboy("Felipe", "Motocicleta", false));
		listaMotoboy.add(new Motoboy("Guilherme", "Motocicleta", false));
		listaMotoboy.add(new Motoboy("Yan", "Motocicleta", true));

		return listaMotoboy;
	}

	// Mostra a lista de Clientes
	public static void mostrarListaCliente(List<Cliente> listaCliente) {
		System.out.println("\n--- CLIENTES ---\n");
		for (Cliente cliente : listaCliente) {
			cliente.apresentarCliente();
		}
	}

	//
	public static void mostrarListaMotoboy(List<Motoboy> listaMotoboy) {
		System.out.println("\n--- MOTOBOYS ---\n");
		for (Motoboy motoboy : listaMotoboy) {
			motoboy.apresentarMotoboy();
		}
	}
}
