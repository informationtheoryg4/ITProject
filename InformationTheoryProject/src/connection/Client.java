package connection;

import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {

	private Socket client;
	private String msg;

	public Client(String address, int port, String msg) {
		try {
			this.msg=msg;
			client = new Socket(address, port);
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			PrintStream out = new PrintStream(client.getOutputStream(), true);
			out.println(msg);
			out.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[]args) {
		Client c = new Client("localhost", 5001, "ajeje");
	}

}
