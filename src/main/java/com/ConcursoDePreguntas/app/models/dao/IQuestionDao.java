package com.ConcursoDePreguntas.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ConcursoDePreguntas.app.models.entity.QuestionsAndAnswers;

public interface IQuestionDao extends CrudRepository<QuestionsAndAnswers, Long>{

}
