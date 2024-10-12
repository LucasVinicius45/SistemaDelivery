package br.com.restaurante.sistemadelivery.exception;

//Exceção personalizada
public class DeliveryException extends Exception {
    public DeliveryException(String mensagem) {
        super(mensagem);
    }
}