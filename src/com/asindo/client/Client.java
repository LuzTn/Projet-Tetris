package com.asindo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.asindo.Jeu;

public class Client {
//
	public static final String hostname = "localhost";
	public static final int PORT = 4444;
	private int id = 0;

	public static void main(String[] args) throws IOException {

		Jeu jeu = new Jeu("Tetris", 230, 300);			// 15*10 colonnes = 150 | 15*20 lignes = 300
		jeu.start();

		Socket socket = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try {
			String messageDuServeur = "";

			socket = new Socket(hostname, PORT);

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	// pour lire dans le flux
			printWriter = new PrintWriter(socket.getOutputStream(), true);									// Pour écrire dans le flux

			while(true) {
				/*
				 * Envoi infos au serveur
				 */
				printWriter.println(jeu.getMessagePourServeur());
//				System.out.println("MESSAGE POUR LE SERVEUR : " +jeu.getMessagePourServeur());

				/*
				 * Reception du message du serveur
				 * Décodage du message
				 */
				messageDuServeur = bufferedReader.readLine();
//				System.out.println(messageDuServeur);			//nbrc:2@id_envoyeur:11@malus:1@idClient:10
				String nb_connectes = "";
				String id_envoyeur = "";
				String malus = "";
				String id_client = "";

				int i = 0;
				for (String s: messageDuServeur.split("@")) {
					if(i == 0) nb_connectes = s.substring("nbrc:".length(), s.length());		//System.out.println("nb_connectes : " +nb_connectes);
					if(i == 1) id_envoyeur = s.substring("id_envoyeur:".length(), s.length());	//System.out.println("id_envoyeur : " +id_envoyeur);
					if(i == 2) malus = s.substring("malus:".length(), s.length());				//System.out.println("malus : " +malus);
					if(i == 3) id_client = s.substring("idClient:".length(), s.length());		//System.out.println("id_client : " +id_client);
					i++;
				}
				//System.out.println();
				
				jeu.setNbConnectes(Integer.parseInt(nb_connectes));
				
				/*
				 * Malus
				 */
				if(malus.equals("1") || malus.equals("2")) {
					if(!id_envoyeur.equals(id_client)) jeu.setMalus(Integer.parseInt(malus));
//					System.out.println("MALUS CLIENT = " +malus);
				}
			}
		}
		catch(IOException e) {
//			System.out.println(e.getMessage());
		}
		finally {
			bufferedReader.close();
			printWriter.close();
			socket.close();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
