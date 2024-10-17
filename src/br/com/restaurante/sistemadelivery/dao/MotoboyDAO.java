package br.com.restaurante.sistemadelivery.dao;

import br.com.restaurante.sistemadelivery.model.Motoboy;

public interface MotoboyDAO {
	
	public void criarTabelaMotoboy();
	
	public void criarSequenceMotoboy();
	
	public void criarTriggerMotoboy();
	
	public void incluirMotoboy(Motoboy motoboy);
}
