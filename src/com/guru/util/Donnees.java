package com.guru.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Donnees {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Score> scores = new ArrayList();

	public Donnees() {
		
		scores.clear();
		
		BufferedReader br = null;
		try {

			String ligneCourante;
			
			ClassLoader classLoader = getClass().getClassLoader();
			File fichier = new File(classLoader.getResource("scores.txt").getFile());
			FileReader fr = new FileReader(fichier);
			br = new BufferedReader(fr);
			
//			InputStream is = ClassLoader.getSystemResourceAsStream("scores.txt");			// Lecture fichier dans src
//			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			while ((ligneCourante = br.readLine()) != null) {
				
				String pseudo = "";
				String score = "";
				
				int compteur = 0;
				
				for (String mot: ligneCourante.split("\t")) {
					if(compteur == 0) pseudo = mot;
					if(compteur == 1) score = mot;
					
					compteur++;
					
				}
				Score s = new Score(pseudo, Integer.parseInt(score));
				scores.add(s);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		// On trie (decroissant)
		Collections.sort(scores);
		
	}
	
	/*
	 * Ajout d'un nouveau score (Ecriture dans le fichier score.txt)
	 */
	public void ajoutScore(Score score) {
		
		try {

			String texte = "\n"+								// On se met à la ligne avant d'écrire
							score.getPseudo() +"\t"+
							score.getScore();
			
			ClassLoader classLoader = getClass().getClassLoader();
			File fichier = new File(classLoader.getResource("scores.txt").getFile());
			
			// Si le fichier n'existe pas, on le crée
			if (!fichier.exists()) {
				fichier.createNewFile();
			}

			FileWriter fw = new FileWriter(fichier, true);		// Option 'true' permet d'ecrire à la fin du fichier sans écraser son contenu
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texte);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scores.add(score);
		
		
	}

	// GET/SET
	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	/*
	 * 
	 */
	public void testScore() {
		if(scores.size() < 5) {
			
		}
		else {
			
		}
	}
	
}
