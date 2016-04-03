package com.asindo;

import javax.swing.JOptionPane;

import com.guru.util.Donnees;
import com.guru.util.Score;

public class Piece
{

	private int numero;
	private int position = 0;
	private int x = 0;
	private int y = 0;
	private int x1 = 0;
	private int y1 = 0;

	private boolean b = false;

	private int[][] Piece1 = { {0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0},
			{0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0} };
	private int[][] Piece2 = { {0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0},
			{0,2,2,0,0,2,2,0,0,0,0,0,0,0,0,0} };
	private int[][] Piece3 = { {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
			{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,0},
			{3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0} };
	private int[][] Piece4 = { {0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
			{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0},
			{0,0,4,0,0,4,4,0,0,4,0,0,0,0,0,0},
			{0,0,0,0,0,4,4,0,0,0,4,4,0,0,0,0} };
	private int[][] Piece5 = { {0,5,0,0,0,5,5,0,0,5,0,0,0,0,0,0},
			{0,0,0,0,0,0,5,0,0,5,5,5,0,0,0,0},
			{0,0,0,5,0,0,5,5,0,0,0,5,0,0,0,0},
			{0,5,5,5,0,0,5,0,0,0,0,0,0,0,0,0} };                          
	private int[][] Piece6 = { {0,0,6,0,0,0,6,0,0,6,6,0,0,0,0,0},
			{0,0,0,0,0,6,6,6,0,0,0,6,0,0,0,0},
			{0,6,6,0,0,6,0,0,0,6,0,0,0,0,0,0},
			{0,0,0,0,0,6,0,0,0,6,6,6,0,0,0,0} };       
	private int[][] Piece7 = { {0,7,0,0,0,7,0,0,0,7,7,0,0,0,0,0},
			{0,0,0,0,0,0,0,7,0,7,7,7,0,0,0,0},
			{0,7,7,0,0,0,7,0,0,0,7,0,0,0,0,0},
			{0,0,0,0,0,7,7,7,0,7,0,0,0,0,0,0} };       

	private int [][] piecec =  new int [4][16];
	
	private int score = 0;
	private int malus_score = 0;
	private int typeMalus = 0;
	
	private boolean isMalus1 = false;		// Impossibilité de tourner les pièces
	private boolean isMalus2 = false;		// Vitesse x 2
	
	public Piece() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 16; j++) {
				piecec [i][j] = 0;
			}
		}
	}

	/*
	 * Creation pièce
	 */
	public void cree_piece() {

		// numero est compris entre 1 et 7
		numero = 1 + ((int)(Math.random()*7));

		switch (numero) {
		case 1:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece1 [i][j]; }}; break;
		case 2:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece2 [i][j]; }}; break;
		case 3:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece3 [i][j]; }}; break;
		case 4:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece4 [i][j]; }}; break;
		case 5:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece5 [i][j]; }}; break;
		case 6:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece6 [i][j]; }}; break;
		case 7:   for (int i=0;i<4;i++) { for (int j=0;j<16;j++) { piecec [i][j]=Piece7 [i][j]; }}; break;
		}

	}
	
	/*
	 * Déplacement à gauche
	 */
	public void gauche(int[][] g) {
		efface(g);
		x--;

		if (bloque_gauche(g)) x++;
		affiche_piece(g);
	}

	/*
	 * Déplacement à droite
	 */
	public void droite(int[][] g) {
		efface(g);

		x++;

		if (bloque_droite(g)) x--;
		affiche_piece(g);
	}
	
	/*
	 * Blocage à gauche
	 */
	public boolean bloque_gauche(int[][] g)
	{
		boolean d = false;
		for (int m=0;m<16;m++) {
			// La case de la pièce est-elle remplie ?
			if (piecec[position][m] != 0) {

				x1 = m % 4;  // colonne de la pièce
				y1 = m / 4;  // Ligne de la pièce

				if ((x+x1) < 0 ) return true;
				else {
					if (g[x+x1][y+y1] != 0) return true;
				}
			}

		}

		return d;
	}
	
	/*
	 * Blocage à droite
	 */
	public boolean bloque_droite(int[][] g)
	{
		boolean d = false;
		for (int m = 0; m < 16; m++) {
			// La case de la pièce est-elle remplie ?
			if (piecec[position][m] != 0) {
				int x1 = m % 4;  // colonne de la pièce
				int y1 = m / 4;  // Ligne de la pièce
				if ((x + x1) > 9) return true;
				else {
					if (g[x+x1][y+y1] != 0) return true;
				}
			}

		}

		return d;
	}
	
	/*
	 * Tourne la pièce
	 */
	public void tourned(int[][] g) {
		
		efface(g);
		
		if(isMalus1 == false) {								// Malus : impossibilité de tourner les pièces
			
			int old = position;
			position ++;
			if (position ==4) position=0;
	
			if (bloque_tourne(g)) position = old;
	
			if (bloque(g)) position = old;
		}
		
		affiche_piece(g);
	}
	
	/*
	 * Blocage tour
	 */
	public boolean bloque_tourne(int[][] g)
	{
		boolean d = false;
		for (int m = 0; m < 16; m++) {
			// La case de la pièce est-elle remplie ?
			if (piecec[position][m] != 0) {

				x1 = m % 4;  // colonne de la pièce
				y1 = m / 4;  // Ligne de la pièce

				if ((x+x1) > 9 ) return true;

				if ((x+x1) < 0 ) return true;
				else {
					if (g[x+x1][y+y1] != 0) return true;
				}
			}

		}

		return d;
	}
	
	/*
	 * Copie de la pièce dans la grille
	 * ligne par ligne
	 */
	public void affiche_piece(int[][] g)
	{
		for (int m = 0; m < 4; m++)
			if ( ((x+m) < 10) && ((x+m) >= 0) && (g[x+m][y] == 0) && (piecec[position][m] != 0))
				g[x+m][y] = piecec[position][m];

		if ((y+1)<24)
			for (int m = 0; m < 4; m++)
				if (  ((x+m)<10)   && ((x+m) >= 0) && (g[x+m][y+1] == 0) && (piecec[position][m+4] != 0))
					g[x+m][y+1] = piecec[position][m + 4];

		if ((y+2) < 24)
			for (int m = 0; m < 4; m++)
				if ( ((x+m)<10)  && ((x+m) >= 0) && (g[x+m][y+2] == 0) && (piecec[position][m+8] != 0))
					g[x+m][y+2] = piecec[position][m + 8];

		if ((y+3) < 24)
			for (int m = 0; m < 4; m++)
				if ( ((x+m) < 10)  && ((x+m) >= 0) && (g[x+m][y+3] == 0) && (piecec[position][m+12] != 0))
					g[x+m][y+3] = piecec[position][m+12];

	}
	
	/*
	 * Teste si une pièce peut descendre 
	 * ou bloque
	 */
	public boolean bloque(int[][] g)
	{
		boolean c = false;

		for (int m = 0; m < 4; m++) {
			if (((x+m)<10) && (y < 24) && ((x+m)>=0))
				if ((g[x+m][y]!=0) && (piecec[position][m]!=0)) return true;
		}
		for (int m = 0; m < 4; m++) {
			if (((x+m)<10) && (y < 23) && ((x+m)>=0))
				if ((g[x+m][y+1]!=0) && (piecec[position][m+4]!=0)) return true;
		}
		for (int m = 0; m < 4; m++) {
			if (((x+m)<10) && (y < 22) && ((x+m)>=0))
				if ((g[x+m][y+2]!=0) && (piecec[position][m+8]!=0)) return true;
		}
		for (int m = 0; m < 4; m++) {
			if (((x+m)<10) && (y < 21) && ((x+m)>=0))
				if ((g[x+m][y+3]!=0) && (piecec[position][m+12]!=0)) return true;
		}

		return c;
	}

	/*
	 * Vérifie si la ligne saute
	 * et met a jour le score
	 */
	public void verifie_ligne(int[][] g) {
		
		for(int m = 0; m < 20; m++) {
			b = false;
			for(int n = 0; n < 10; n++) {
				if (g[n][m] == 0) b = true;					// S'il y a une case 0 dans la ligne, celle-ci n'est pas complète
			}

			if (b == false) {
				for(int i = 0; i < 10; i++) {
					g[i][0] = 0;							// fait sauter la ligne --> toutes les cases de la ligne = 0
				}
				
				// augmente le score
				score++;
				System.out.println("score : " +score);
//				System.out.println("ligne saute");
				
				malus_score ++;
//				System.out.println("malus_score : " +malus_score);
				if(testEnvoiMalus()) {
					malus_score = 0;
//					System.out.println("MALUS");
					
					typeMalus = ((int)(Math.random()*2) + 1);
					System.out.println("MALUS ---- > " +typeMalus);
					
				}
				else {
					typeMalus = 0;
				}
					
				// fait descendre les lignes du dessus
				// et combler la ligne qui a sauté
				for(int t = m; t > 0; t--) {
					for (int o = 0; o < 10; o++) {
						g[o][t] = g[o][t-1];
					}
				}
			}
		}
		
		
	}
	
	/*
	 * Efface la pièce sur la grille
	 */
	public void efface(int[][] g) {
		for (int m = 0; m < 4; m++) if (piecec[position][m] != 0) 		g[x+m][y] = 0;
		for (int m = 0; m < 4; m++) if (piecec[position][m+4] != 0) 	g[x+m][y+1] = 0;
		for (int m = 0; m < 4; m++) if (piecec[position][m+8] != 0) 	g[x+m][y+2] = 0;
		for (int m = 0; m < 4; m++) if (piecec[position][m+12] != 0)	g[x+m][y+3] = 0;
	}
	
	/*
	 * Teste si le jeu est fini
	 */
	public void teste_perdu(int[][] g) {
		
		boolean perdu = false;
		
		for(int m = 0; m < 16; m++) {
			// La case de la pièce est-elle remplie ?
			if (piecec[position][m] != 0) {

				x1 = m % 4;		// colonne de la pièce
				y1 = m / 4;		// Ligne de la pièce

				if (g[x+x1][y+y1] != 0) {				// on perd 
					perdu = true;
					// on remplit par des blocs nuls
					for(int i = 0; i < 10; i++) {
						for (int j = 0; j < 20; j++) {
							g[i][j] = 0;
						}
					}
					
				}
			}
		}
		//
		affiche_menu2(perdu);
		
	}
	
	/*
	 * Menu 2 : fin de partie
	 */
	public void affiche_menu2(boolean perdu) {
		
		if(perdu) {
			/*
			 * Enregistrement du score
			 */
			if(new Donnees().getScores().size() >= 5) {
				if(score > new Donnees().getScores().get(4).getScore()) {
					String pseudo = JOptionPane.showInputDialog("Entrer votre nom : ");
					if(pseudo.trim().equals("")) new Donnees().ajoutScore(new Score("Inconnu", score)); 
					else new Donnees().ajoutScore(new Score(pseudo, score));
				}
			}
			else if(new Donnees().getScores().size() < 5) {
				int taille = new Donnees().getScores().size();
				if(score > new Donnees().getScores().get(taille-1).getScore()) {
					String pseudo = JOptionPane.showInputDialog("Entrer votre nom : ");
					if(pseudo.trim().equals("")) new Donnees().ajoutScore(new Score("Inconnu", score)); 
					else new Donnees().ajoutScore(new Score(pseudo, score));
				}
			}
			else {
				String msg = "SCORE TOTAL = "+String.valueOf(score);
				JOptionPane.showMessageDialog(null, msg, "Score", JOptionPane.PLAIN_MESSAGE);
					
				// on affiche le score
//				System.out.println("---> SCORE TOTAL = " +score);
			}
			
			// on réinitialise le score
			score = 0;		
			
			// on enlève le malus
			typeMalus = 0;
			
		}//
	}
	
	/*
	 * Descente d'une pièce
	 */
	public void descend(int[][] g)
	{

		efface(g);
		
		y++;

		if (bloque(g)) {				// si la pièce est bloquée
			y --;						
			affiche_piece(g);			
			verifie_ligne(g);
			y = 0;						// on se place tout en haut de la grille
			x = 3;
			cree_piece();				// une nouvelle pièce
			teste_perdu(g);
		} 
		else {
			affiche_piece(g);
		}
		

	}
	
	/*
	 * Test d'envoi Malus
	 */
	public boolean testEnvoiMalus() {
		if(malus_score == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Malus 1 :
	 */
	public void malus1(int[][] g) {
		//TODOs
	}
	
	/*
	 * Malus 2 : 
	 */
	public void malus2(int[][] g) {
		//TODO
	}
	
	// GET / SET
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTypeMalus() {
		return typeMalus;
	}

	public void setTypeMalus(int typeMalus) {
		this.typeMalus = typeMalus;
	}

	public boolean isMalus1() {
		return isMalus1;
	}

	public void setMalus1(boolean isMalus1) {
		this.isMalus1 = isMalus1;
	}

	public boolean isMalus2() {
		return isMalus2;
	}

	public void setMalus2(boolean isMalus2) {
		this.isMalus2 = isMalus2;
	}
	
}
