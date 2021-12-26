package com.ConcursoDePreguntas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ConcursoDePreguntas.app.models.dao.IPlayerDao;
import com.ConcursoDePreguntas.app.models.entity.Player;

@Service
public class PlayerServiceImpl implements IPlayerService {

	@Autowired
	private IPlayerDao playerDao;

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAll() {

		return (List<Player>) playerDao.findAll();
	}

	@Override
	public void save(Player player) {
		playerDao.save(player);

	}

	@Override
	@Transactional
	public Player findOne(Long id) {

		return playerDao.findById(id).orElse(null);
	}

}
