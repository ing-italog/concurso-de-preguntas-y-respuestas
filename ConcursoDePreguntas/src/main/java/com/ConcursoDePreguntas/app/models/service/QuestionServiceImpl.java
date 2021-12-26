package com.ConcursoDePreguntas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ConcursoDePreguntas.app.models.dao.IQuestionDao;
import com.ConcursoDePreguntas.app.models.entity.QuestionsAndAnswers;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private IQuestionDao questionDao;

	@Override
	@Transactional(readOnly = true)
	public List<QuestionsAndAnswers> findAll() {
		return (List<QuestionsAndAnswers>) questionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public QuestionsAndAnswers findOne(Long id) {
		return questionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(QuestionsAndAnswers q) {
		questionDao.save(q);

	}

}
