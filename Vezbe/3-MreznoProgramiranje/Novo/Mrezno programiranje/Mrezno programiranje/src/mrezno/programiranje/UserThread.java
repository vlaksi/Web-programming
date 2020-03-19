package mrezno.programiranje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UserThread extends Thread {
	
	private Socket sock; // objekat soket za razmenu podataka
	private BufferedReader in;
	private PrintWriter out;
	private int clientCounter;
	
	public UserThread(Socket sock, int clientCounter) {
		this.sock = sock;
		this.clientCounter = clientCounter;
		;
		try {
			// inicijalizuj ulazni stream
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			// inicijalizuj izlazni stream
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		start(); //startujemo novu nit
	}
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));

			// inicijalizuj izlazni stream
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					sock.getOutputStream())), true);
			// procitaj zahtev
			String request = in.readLine();
			System.out.println(request);

			// odgovori na zahtev
			out.println("(" + clientCounter + ")");

			// zatvori konekciju
			in.close();
			out.close();
			sock.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
