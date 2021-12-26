package com.ConcursoDePreguntas.app.models.service;

import java.util.List;

import com.ConcursoDePreguntas.app.models.entity.Player;

public interface IPlayerService {
	
	public List<Player> findAll();
	
	public void save(Player player);	

	public Player findOne(Long id);

}
