package com.asindo.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {

	public static final int PORT = 4444;
	
	public static List<ThreadServeur> clients = new ArrayList<ThreadServeur>();
	
	public static String malus = "0";
	
	public static void main(String[] args) throws IOException {
		new Serveur().runServeur();

	}

	public void runServeur() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);			// 2 joueurs maximum
		System.out.println("Serveur démarré ... \n");

		while (true) {
			Socket socket = serverSocket.accept();
			ThreadServeur t = new ThreadServeur(socket);
			t.start();
			ajoutClient(t);
			
			
		}
	}
	
	/*
	 * Ajout d'un client
	 */
	public synchronized void ajoutClient(ThreadServeur t) {
		System.out.println("L''utilisateur " +t.getId() +" est maintenant connecté ...");
		Serveur.clients.add(t);
	}
	
	/*
	 * Suppression d'un client
	 */
	public synchronized void supprimeClient(ThreadServeur t) {
		System.out.println("L''utilisateur " +t.getId() +" a quitté le jeu ...");
		Serveur.clients.remove(t);
	}
}

