package com.guru.util;

public class Joueur {
	
	private int id;
	private int malus;
	
	public Joueur(int id, int malus) {
		this.id = id;
		this.malus = malus;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMalus() {
		return malus;
	}
	public void setMalus(int malus) {
		this.malus = malus;
	}
}
