package com.ConcursoDePreguntas.app.models.entity;

public class ItemMenu {

	private String[] elementosMenu = {"INICIO","JUGAR PARTIDA", "VER RANKING", "CONFIGURACIONES", "titlePag"};
	
	private String[] keyMenu = {"title","game", "score", "config", "INGRESAR NOMBRE"};
	
	public String[] getElementosMenu() {
		return elementosMenu;
	}
	public String[] getKeyMenu() {
		return keyMenu;
	}
}
