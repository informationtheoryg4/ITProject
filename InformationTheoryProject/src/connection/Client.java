package connection;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	BufferedReader in;
	PrintStream out;
	Socket socket;

	public Client(String ipAddress, int port) {

		String message;

		try {
			socket = new Socket(ipAddress, port);

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

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return message;
	}
}
