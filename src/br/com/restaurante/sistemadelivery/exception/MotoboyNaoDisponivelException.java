package br.com.restaurante.sistemadelivery.exception;

//Exceção personalizada
public class MotoboyNaoDisponivelException extends Exception {
    public MotoboyNaoDisponivelException(String mensagem) {
        super(mensagem);
    }
}