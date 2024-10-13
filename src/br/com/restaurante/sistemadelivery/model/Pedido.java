package br.com.restaurante.sistemadelivery.model;

public class Pedido extends GerenciadorDelivery {

	/**
	 * Campos da classe Pedido
	 */
	private long id;
	private String numeroPedido;
	private String pin;
	private Estabelecimento estabelecimento;
	private Cliente cliente;
	private Motoboy motoboy;
	
	
	/**
	 * Construtor
	 * @param numeroPedido
	 * @param pin
	 */
	public Pedido(String numeroPedido, String pin) {
		super();
		this.numeroPedido = numeroPedido;
		this.pin = pin;
	}

	/**
	 * GET E SET
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Motoboy getMotoboy() {
		return motoboy;
	}

	public void setMotoboy(Motoboy motoboy) {
		this.motoboy = motoboy;
	}
	
	/**
	 * Sobrescrita para printar uma pensagem sobre o pedido
	 */
	@Override
	public String toString() {
		return getNumeroPedido() + ": Motoboy " + getMotoboy().getNome() + ", pegou o pedido no Estabelecimento " + getEstabelecimento().getNome() + " e está indo para o cliente " + getCliente().getNome();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
