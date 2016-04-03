package com.asindo.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServeur extends Thread {

	private Socket socket;
	PrintWriter printWriter;
	BufferedReader bufferedReader;

	ThreadServeur(Socket socket) {
		this.socket = socket;
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		try {

			String messageDuClient = null;

			// A l'ecoute des messages des clients
			while((messageDuClient = bufferedReader.readLine()) != null) {

//				System.out.println(this.getId() +messageDuClient);
				
				String idClient = "@idClient:"+String.valueOf(this.getId());
				String nb_connectes = "nbrc:"+Serveur.clients.size();
				String malus = "";
				
				/*
				 * Malus en cours
				 */
				if(!messageDuClient.equals("0"))
					Serveur.malus = new String("id_envoyeur:" +this.getId() +"@malus:"+ messageDuClient);		//"id_envoyeur:11@malus:1"
				malus = "@"+Serveur.malus;
//				System.out.println("malus = " +malus);
				
				/*
				 * Message codee pour les clients
				 */
				String messageDuServeur = nb_connectes+malus+idClient;			//nbrc:2@id_envoyeur:11@malus:1@idClient:10
				
				/*
				 * Envoi vers client
				 */
				printWriter.println(messageDuServeur);
				
				// Réinitialise les codes
				Serveur.malus = new String("id_envoyeur:0@malus:0");
				
			}
		} catch (IOException e) {
//			e.printStackTrace();
		}
		finally {
			try {
				bufferedReader.close();
				printWriter.close();
				socket.close();
				Serveur.clients.remove(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	
}
