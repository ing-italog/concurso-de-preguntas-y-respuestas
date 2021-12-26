package com.ConcursoDePreguntas.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ConcursoDePreguntas.app.models.entity.ItemMenu;
import com.ConcursoDePreguntas.app.models.entity.Player;
import com.ConcursoDePreguntas.app.models.service.IPlayerService;

@Controller
@SessionAttributes("questions_answers")
public class ConcursoController {

	ItemMenu itemMenu = new ItemMenu();
	String[] elementosMenu = itemMenu.getElementosMenu();
	String[] keyMenu = itemMenu.getKeyMenu();

	@Autowired
	private IPlayerService playerService;

	@GetMapping(value = { "", "/", "/home" })
	public String home(Model model) {

		long id = 5;
		model.addAttribute("lastPlayer", playerService.findOne(id));
		id++;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.addAttribute(keyMenu[i], elementosMenu[i]);
		}

		return "index";
	}

	@RequestMapping(value = "/game")
	public String crearPLayer(Map<String, Object> model) {

		Player player = new Player();
		model.put("player", player);
		model.put("titlePag", "INGRESAR NOMBRE");
		;

		for (int i = 0; i < elementosMenu.length; i++) {
			model.put(keyMenu[i], elementosMenu[i]);
		}
		return "windows/game";
	}

	@GetMapping("/historyScore")
	public String historicalScore(Model model) {
		for (int i = 0; i < elementosMenu.length; i++) {
			model.addAttribute(keyMenu[i], elementosMenu[i]);
		}
		model.addAttribute("players", playerService.findAll());

		return "windows/score";
	}
}
