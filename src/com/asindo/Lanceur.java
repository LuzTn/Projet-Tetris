package com.asindo;

public class Lanceur {
	
	public static void main(String[] args) {
		
		Jeu jeu = new Jeu("Tetris", 230, 300);			// 15*10 colonnes = 150 | 15*20 lignes = 300
		
		jeu.start();
		
	}
	
	
}
