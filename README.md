# Projet-Tetris-SBOUI-KECHCHANY
SBOUI Nour 4A - TCSE3
KECHCHANY Hajar 4A - CFA


Comment jouer?
  utiliser les flèches pour tourner les pièces et les faire descendre dans l'endroit que vous choisissez . Dès qu'une ligne est cassée, le score s'incrémente.  
  
  Les features faites:
 - Votre tetris pourra être joué seul mais aussi en mode multijoueur 
- Le mode multijoueur devra être effectué à travers le réseau (une instance de l'application démarré par joueur).
- En mode multijoueur, chaque fois qu'un joueur casse 3 lignes, il envoie un malus à son adversaire. Un minimum de deux types de malus différents est requis: impossibilité de tourner les pièces pendant un certain temps et augmenter la vitesse des tétriminos.
Un minimum de trois types de tétriminos (les pièces du tetris) différents est requis. Le joueur doit au minimum pouvoir tourner la pièce et la déplacer de droite à gauche.

-Les 5 meilleurs scores (nombre de lignes détruites) doivent être enregistrés dans un fichier pour pouvoir être persistés et ainsi pouvoir être affichés a posteriori.
- GUI est utilisé pour l'interface
- Utilisation du pattern MVC 

Exercice Design Pattern / SOLID
On a appliqué le principe SRP : SINGLE RESPONSABILITY PRINCIPLE
Un composant = une responsabilité: 
public class Lanceur {
	
	public static void main(String[] args) {
		
		Jeu jeu = new Jeu("Tetris", 230, 300);			// 15*10 colonnes = 150 | 15*20 lignes = 300
		
		jeu.start();
		
	}
	
	
}
cette classe n'a pour role que lancer le jeu.



  
  
