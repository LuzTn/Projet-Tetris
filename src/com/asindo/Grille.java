package com.asindo;

import java.awt.Color;
import java.awt.Graphics;

public class Grille {

	private int [][] grille;
	private int score = 0;
	private int nbConnectes = 0;
	private String typeMalus = "";

	public Grille() {
		grille = new int [10][24];

		// Initialisations
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				grille[i][j] = 0;
			}
		}
		// Initialisations
		for (int i = 0; i < 10; i++) {
			grille[i][20] = 1;
		}
	}

	/*
	 * Affiche la grille
	 */
	public void affiche(Graphics g) {
		
		for(int k = 0; k < 10; k++) {
			for(int l = 0; l < 20; l++) {
				if(grille[k][l] == 0) {
					g.setColor(Color.white);
					g.draw3DRect(15*k, 15*l, 15, 15, true);
				}
				else {
					switch (grille[k][l]) {
					case 1:	g.setColor(new Color(0, 192, 0));	g.fill3DRect(15*k, 15*l, 15, 15, true); break; //vert
					case 2:	g.setColor(Color.pink);				g.fill3DRect(15*k, 15*l, 15, 15, true); break; //rouge
					case 3:	g.setColor(new Color(0, 128, 224));	g.fill3DRect(15*k, 15*l, 15, 15, true); break; //bleu
					case 4:	g.setColor(new Color(0, 192, 192));	g.fill3DRect(15*k, 15*l, 15, 15, true); break; //cyan
					case 5:	g.setColor(Color.orange);           g.fill3DRect(15*k, 15*l, 15, 15, true); break; //orange
					case 6:	g.setColor(Color.darkGray);			g.fill3DRect(15*k, 15*l, 15, 15, true); break; //gris
					case 7:	g.setColor(Color.magenta);          g.fill3DRect(15*k, 15*l, 15, 15, true); break; //magenta
					}
				}
			}
		}
		
		afficheScore(g);
		afficheEnLigne(g);
		afficheMalus(g);
	}

	/*
	 * Affiche score
	 */
	public void afficheScore(Graphics g) {
		g.setColor(Color.magenta);
		g.drawString("SCORE : " +score, 160, 120);
		
	}
	
	/*
	 * Affiche joueur en ligne
	 */
	public void afficheEnLigne(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawString("En ligne : " +nbConnectes, 160, 150);
		
	}
	/*
	 * Affiche malus
	 */
	public void afficheMalus(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawString("Malus : "+typeMalus, 160, 180);
	}
	
	// GET/SET
	public int[][] getGrille() {
		return grille;
	}

	public void setGrille(int[][] grille) {
		this.grille = grille;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbConnectes() {
		return nbConnectes;
	}

	public void setNbConnectes(int nbConnectes) {
		this.nbConnectes = nbConnectes;
	}

	public String getTypeMalus() {
		return typeMalus;
	}

	public void setTypeMalus(String typeMalus) {
		this.typeMalus = typeMalus;
	}

}
