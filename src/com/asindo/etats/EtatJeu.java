package com.asindo.etats;

import java.awt.Graphics;

import com.asindo.Grille;
import com.asindo.Jeu;
import com.asindo.Piece;

public class EtatJeu extends Etat {
	
	private Jeu jeu;
	private Grille grille;
	private Piece p;
	
	public EtatJeu(Jeu jeu) {
		this.jeu = jeu;
		grille = new Grille();
		p = new Piece();
		p.cree_piece();
		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		if(jeu.getGestionTouches().gauche) {
			p.gauche(grille.getGrille());
			jeu.getGestionTouches().gauche = false;
		}
		
		if(jeu.getGestionTouches().droite) {
			p.droite(grille.getGrille());
			jeu.getGestionTouches().droite = false;
		}
		
		if(jeu.getGestionTouches().haut) {
			p.tourned(grille.getGrille());
			jeu.getGestionTouches().haut = false;
		}
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		p.affiche_piece(grille.getGrille());
		grille.affiche(g);
		p.descend(grille.getGrille());
		grille.setScore(p.getScore());
		
		// Nbr connectés
		grille.setNbConnectes(jeu.getNbConnectes());					// En ligne = ' '
		jeu.getFenetre().setNbConnectes(jeu.getNbConnectes());			// Menu 'Deux joueurs'
		
		// Malus --> serveur
		jeu.setMessagePourServeur(String.valueOf(p.getTypeMalus()));
		
		// Serveur --> Malus
//		grille.setTypeMalus(String.valueOf(jeu.getMalus()));			// Affichage
//		if(jeu.getMalus() == 1 || jeu.getMalus() == 2) p.setMalus(true);

		if(jeu.getMalus() == 1) {
			grille.setTypeMalus(String.valueOf(jeu.getMalus()));			// Affichage
			p.setMalus1(true);
		}
		if(jeu.getMalus() == 2) {
			grille.setTypeMalus(String.valueOf(jeu.getMalus()));			// Affichage
			jeu.setTemps_pause(new Long(150));								// Vitesse du jeu x k
		}
		if(jeu.getMalus() == 0) {
			grille.setTypeMalus("0");			// Affichage
			p.setMalus1(false);
			p.setMalus2(false);
		}
		
//		System.out.println("COMPTEUR = " +compteur_malus+ "MALUS = " +jeu.getMalus());
		
	}

	// GET/SET
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public Piece getP() {
		return p;
	}

	public void setP(Piece p) {
		this.p = p;
	}
	
}
