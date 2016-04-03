package com.asindo;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.asindo.entrees.GestionTouches;
import com.asindo.etats.Etat;
import com.asindo.etats.EtatJeu;
import com.asindo.vue.Fenetre;

/**
 * @author 
 *
 */
public class Jeu implements Runnable {
	
	private Fenetre fenetre;
	private int width, height;
	private String titre;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// Etat
	private Etat etatJeu;
	
	// Entrees
	private GestionTouches gestionTouches;
	
	// Vitesse du jeu
	private long temps_pause = 450;		// en ms
	
	// Infos jeu
	private int nbConnectes = 1;
	private int malus = 0;
	private String messagePourServeur = "";
	
	
	public Jeu(String titre, int width, int height) {
		this.titre = titre;
		this.width = width;
		this.height = height;
		gestionTouches = new GestionTouches(); 
	}
	
	/*
	 * Initialise le graphisme
	 */
	private void init() {
		fenetre = new Fenetre(titre, width, height);
		fenetre.getFrame().addKeyListener(gestionTouches);
		
		etatJeu = new EtatJeu(this);
		Etat.setEtat(etatJeu);			// etat courant
	}
	
	/*
	 * 
	 */
//	int x = 0;
	private void tick() {
//		x += 1;
		if(Etat.getEtat() != null) {
			Etat.getEtat().tick();
		}
	}
	
	/*
	 * Dessine quelque chose sur l'écran
	 */
	private void render() {
		bs = fenetre.getCanvas().getBufferStrategy();
		if(bs == null) {
			fenetre.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();	// comme une brosse (permet de dessinner sur le canvas)
		
		// effacer l'écran
		g.clearRect(0, 0, width, height);
		
		// dessiner
//		g.fillRect(x, 0, 50, 50);
		if(Etat.getEtat() != null) {
			Etat.getEtat().render(g);
		}
		
		
		// fini de dessiner
		bs.show();					
		g.dispose();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		init();
		
		/*
		 * Boucle du jeu
		 */
		while(running) {
			try {
				tick();
				render();
				
				Thread.sleep(temps_pause);
			}
			catch (InterruptedException e) {
				return;
			}
			
		}

		stop();
	}
	
	public synchronized void start() {
		if( running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// GET/SET
	public GestionTouches getGestionTouches() {
		return gestionTouches;
	}

	public void setGestionTouches(GestionTouches gestionTouches) {
		this.gestionTouches = gestionTouches;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

	public long getTemps_pause() {
		return temps_pause;
	}

	public void setTemps_pause(long temps_pause) {
		this.temps_pause = temps_pause;
	}

	public int getNbConnectes() {
		return nbConnectes;
	}

	public void setNbConnectes(int nbConnectes) {
		this.nbConnectes = nbConnectes;
	}

	public String getMessagePourServeur() {
		return messagePourServeur;
	}

	public void setMessagePourServeur(String messagePourServeur) {
		this.messagePourServeur = messagePourServeur;
	}

	public Etat getEtatJeu() {
		return etatJeu;
	}

	public void setEtatJeu(Etat etatJeu) {
		this.etatJeu = etatJeu;
	}

	public int getMalus() {
		return malus;
	}

	public void setMalus(int malus) {
		this.malus = malus;
	}
	
}
