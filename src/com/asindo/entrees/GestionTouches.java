package com.asindo.entrees;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GestionTouches implements KeyListener {

	private boolean[] touches;
	public boolean haut, bas, gauche, droite;
	
	public GestionTouches() {
		touches = new boolean[256];
	}
	
	public void tick() {
		haut = touches[KeyEvent.VK_UP];
		bas = touches[KeyEvent.VK_DOWN];
		gauche = touches[KeyEvent.VK_LEFT];
		droite = touches[KeyEvent.VK_RIGHT];
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		touches[e.getKeyCode()] = true;

		tick();
//		
//		if(haut) {
//			System.out.println("haut");
//		}
//		if(bas) {
//			System.out.println("bas");
//		}
//		if(gauche) {
//			System.out.println("gauche");
//		}
//		if(droite) {
//			System.out.println("droite");
//		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		touches[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
