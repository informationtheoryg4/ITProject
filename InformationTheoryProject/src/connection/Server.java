package connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.swing.JTextPane;

import utils.Util;

public class Server extends Thread{
	
	private ServerSocket server;
	private String msg;
	private JTextPane tp1;

	public Server(int port, JTextPane tp1) {
		try {
			this.tp1 = tp1;
			server = new ServerSocket(port);
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Socket client = server.accept();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			StringBuilder sb = new StringBuilder();
			msg=in.readLine();
			while(msg!=null) {
				sb.append(msg+"\n");
				msg = in.readLine();
			}
//			System.out.println(msg);
			
			//qui aggiornare GUI
			tp1.setText(sb.toString());
			
			in.close();
			this.server.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIpAddress() {
		URL myIP;
		try {
			myIP = new URL("http://api.externalip.net/ip/");

			BufferedReader in = new BufferedReader(new InputStreamReader(myIP.openStream()));
			return in.readLine();
		} catch (Exception e) {
			try {
				myIP = new URL("http://myip.dnsomatic.com/");

				BufferedReader in = new BufferedReader(new InputStreamReader(myIP.openStream()));
				return in.readLine();
			} catch (Exception e1) {
				try {
					myIP = new URL("http://icanhazip.com/");

					BufferedReader in = new BufferedReader(new InputStreamReader(myIP.openStream()));
					return in.readLine();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return null;
	}

	
	public static void main(String[]args) {
//		ProvaServer s = new ProvaServer(5001);
		
	}
}
