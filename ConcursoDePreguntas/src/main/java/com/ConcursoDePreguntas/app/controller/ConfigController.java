package com.ConcursoDePreguntas.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ConcursoDePreguntas.app.models.entity.ItemMenu;
import com.ConcursoDePreguntas.app.models.entity.QuestionsAndAnswers;
import com.ConcursoDePreguntas.app.models.service.IQuestionService;

@Controller
@SessionAttributes("questions_answers")
public class ConfigController {

	@Autowired
	private IQuestionService questionService;

	ItemMenu itemMenu = new ItemMenu();
	String[] elementosMenu = itemMenu.getElementosMenu();
	String[] keyMenu = itemMenu.getKeyMenu();

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String config(Model model) {

		for (int i = 0; i < elementosMenu.length; i++) {
			model.addAttribute(keyMenu[i], elementosMenu[i]);
		}
		model.addAttribute("titleConf", "CONFIGURACIONES");

		model.addAttribute("questions", questionService.findAll());

		return "windows/config";
	}

	@RequestMapping(value = "/config/{id}")
	public String editQuestion(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		} else {
			return "redirect:/config";
		}
		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("title", "EDITAR PREGUNTA Y RESPUESTAS");

		return "windows/editQuestion";
	}

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public String save(@Valid QuestionsAndAnswers q, BindingResult result, Model model, SessionStatus status) {

		// Verifica si existe errores al llenar los campos
		if (result.hasErrors()) {
			return "redirect:/config";
		}

		questionService.save(q);
		status.setComplete();

		return "redirect:/config";
	}
}
