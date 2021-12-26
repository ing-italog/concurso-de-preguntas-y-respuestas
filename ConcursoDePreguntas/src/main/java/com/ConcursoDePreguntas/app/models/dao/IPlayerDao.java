package com.ConcursoDePreguntas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ConcursoDePreguntas.app.models.entity.Player;


public interface IPlayerDao extends CrudRepository<Player, Long>{

}
