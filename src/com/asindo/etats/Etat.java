package com.asindo.etats;

import java.awt.Graphics;

public abstract class Etat {
	
	private static Etat etatCourrant = null;
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	//
//	private Jeu jeu;
//	
//	public Etat(Jeu jeu) {
//		this.jeu = jeu;
//	}
//	
	// GET/SET
	public static void setEtat(Etat etat) {
		etatCourrant = etat;
	}
	
	public static Etat getEtat() {
		return etatCourrant;
	}
	
	
}
