package com.guru.util;

public class Score implements Comparable<Score> {
	
	private String pseudo;
	private int score;
	
	public Score() {

	}
	
	public Score(String nom, int score) {
		this.pseudo = nom;
		this.score = score;
	}
	
	// GET/SET
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Score o) {
		
		int s = ((Score) o).getScore(); 
		
		return s - this.score;
	}
	
	
	
	
}
