package com.asindo.vue;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import com.guru.util.Donnees;
import com.guru.util.Score;

public class Fenetre {
	
	private JFrame frame;
	private Canvas canvas;			// permet d'afficher quelque chose à l'écran
	
	private String titre;
	private int width, height;
	
	//
	private int nbConnectes = 1;
	
	public Fenetre(String titre, int width, int height) {
		this.titre = titre;
		this.width = width;
		this.height = height;
	
		affiche();
	}
	
	private void affiche() {
		frame = new JFrame(titre);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		
		/*
		 * Menu
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem item1 = new JMenuItem("Scores");
		JMenuItem item2 = new JMenuItem("Multijoueur");
		
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	String texte_scores = "";
				
		    	int compt = 0;
				for(Score s : new Donnees().getScores()) {
					if(compt < 5) {
						texte_scores = texte_scores.concat(s.getPseudo() +"        : "+ s.getScore() +"\n");
						compt++;
					}
				}
//				System.out.println(texte_scores);
				
		    	JOptionPane.showMessageDialog(frame, texte_scores, 
					      "Meilleurs scores", JOptionPane.INFORMATION_MESSAGE);
		    	
			}
		});
		
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				messageDuServeur = "En attente d'un adversaire ... ";
				if(nbConnectes < 2) {
					String msg = "En attente d'adversaires ... ";
					JOptionPane.showMessageDialog(frame, msg, 
						      "Multi-joueurs", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String msg = "La partie a déjà commencée ... ";
					JOptionPane.showMessageDialog(frame, msg, 
						      "Multi-joueurs", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		menu.add(item1);
		menu.add(new JSeparator()); // SEPARATOR
		menu.add(item2);
		
		menuBar.add(menu);
		
		frame.setJMenuBar(menuBar);
		
		frame.pack();
	}

	/*
	 * GET/SET
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public int getNbConnectes() {
		return nbConnectes;
	}

	public void setNbConnectes(int nbConnectes) {
		this.nbConnectes = nbConnectes;
	}

}
