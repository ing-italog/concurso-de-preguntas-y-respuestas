package com.ConcursoDePreguntas.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
import com.ConcursoDePreguntas.app.models.entity.Player;
import com.ConcursoDePreguntas.app.models.entity.QuestionsAndAnswers;
import com.ConcursoDePreguntas.app.models.service.IPlayerService;
import com.ConcursoDePreguntas.app.models.service.IQuestionService;

@Controller
@SessionAttributes("questions_answers")
public class gameController {

	// Este es un elemento auxiliar para obtener el ultimo usuario registrado
	// Inicia en 5 ya que el juego trae 5 usuarios de base
	private int idLast = 5;

	private ItemMenu itemMenu = new ItemMenu();
	private String[] elementosMenu = itemMenu.getElementosMenu();
	private String[] keyMenu = itemMenu.getKeyMenu();
	
	@Autowired
	private IPlayerService playerService;

	@Autowired
	private IQuestionService questionService;

	@RequestMapping(value = "/game", method = RequestMethod.POST)
	public String save(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		Random numRandom = new Random();

		int id = 1 + numRandom.nextInt(5);

		model.addAttribute("id", id);

		for (int i = 0; i < elementosMenu.length; i++) {
			model.addAttribute(keyMenu[i], elementosMenu[i]);
		}
		// Vigila que los campo nombre del jugador no este vacio
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo no puede estar vacio");
			});
			model.addAttribute("error", errores);

			model.addAttribute("titlePag", "INGRESAR NOMBRE");

			for (int i = 0; i < elementosMenu.length; i++) {
				model.addAttribute(keyMenu[i], elementosMenu[i]);
			}

			return "windows/game";

		}

		playerService.save(player);
		status.setComplete();

		// El ultimo elemento en la lista se obtiene sumando el ultimo elemento mas 1
		// unidad
		idLast++;

		return "redirect:/gameStart1/" + id;

	}

	@RequestMapping(value = "/endGame", method = RequestMethod.POST)
	public String endGame(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 2000));
		player.setCategoria(5);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/retirarse1", method = RequestMethod.POST)
	public String retirarse1(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 150));
		player.setCategoria(1);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/retirarse2", method = RequestMethod.POST)
	public String retirarse2(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 200));
		player.setCategoria(2);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/retirarse3", method = RequestMethod.POST)
	public String retirarse3(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 450));
		player.setCategoria(3);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/retirarse4", method = RequestMethod.POST)
	public String retirarse4(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 750));
		player.setCategoria(4);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/retirarse5", method = RequestMethod.POST)
	public String retirarse5(@Valid Player player, BindingResult result, Model model, SessionStatus status) {

		player.setCash((long) (player.getCash() + 1250));
		player.setCategoria(5);
		playerService.save(player);

		return "redirect:/";
	}

	@RequestMapping(value = "/gameStart1/{id}")
	public String gameStart1(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("player", playerService.findOne((long) idLast));

		return "windows/games/gameStart1";
	}

	@RequestMapping(value = "/gameStart2/{id}")
	public String gameStart2(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("player", playerService.findOne((long) idLast));

		return "windows/games/gameStart2";
	}

	@RequestMapping(value = "/gameStart3/{id}")
	public String gameStart3(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("player", playerService.findOne((long) idLast));

		return "windows/games/gameStart3";
	}

	@RequestMapping(value = "/gameStart4/{id}")
	public String gameStart4(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("player", playerService.findOne((long) idLast));

		return "windows/games/gameStart4";
	}

	@RequestMapping(value = "/gameStart5/{id}")
	public String gameStart5(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		QuestionsAndAnswers questionAnswer = null;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}

		if (id > 0) {
			questionAnswer = questionService.findOne(id);
		}

		model.put("questionAnswer", questionAnswer);
		model.put("player", playerService.findOne((long) idLast));

		return "windows/games/gameStart5";
	}

}
