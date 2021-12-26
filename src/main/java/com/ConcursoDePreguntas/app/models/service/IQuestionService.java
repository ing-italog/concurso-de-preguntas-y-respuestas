package com.ConcursoDePreguntas.app.models.service;

import java.util.List;

import com.ConcursoDePreguntas.app.models.entity.QuestionsAndAnswers;

public interface IQuestionService {
	
	public List<QuestionsAndAnswers> findAll();
	
	public QuestionsAndAnswers findOne(Long id);

	public void save(QuestionsAndAnswers questionAndAnswers);

}
