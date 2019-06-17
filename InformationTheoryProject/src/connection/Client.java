package connection;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

		BufferedReader in;
		PrintStream out;
public 	Socket socket;

	public Client(String ipAddress, int port) {

		String message;

		try {
			System.out.println("yahuclient");
			socket = new Socket("localhost", port);
			System.out.println("yahuclient1");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void send(String message) {
		try {
			out = new PrintStream(socket.getOutputStream(), true);
			out.println(message);
			out.flush();
			out.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String receive() {
		String message = "";
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = in.readLine();
			in.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return message;
	}
	public BufferedReader getInputStream() {
		return in;
	}
	public PrintStream getOutputStream() {
		return out;
	}
}
