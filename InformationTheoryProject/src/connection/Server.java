package connection;

import java.io.*;
import java.net.*;
import java.util.*;
 
public class Server extends Thread{
	
	ServerSocket Server;
	StringBuilder output;
	public Socket client;
	
	public static void main(String argv[]) throws Exception{
	
//		new Server();
	}
	
	public Server(int port) throws Exception{
		
		output= new StringBuilder();
		Server = new ServerSocket(port);
		output.append("Il Server è in attesa sulla porta "+port+" con indirizzo IP "+getIpAddress()+"\n");
		this.start();
	}
	
	public void run(){
		
		while(true)
		{
			try {
				output.append("In attesa di Connessione. \n");
				System.out.println("In attesa di Connessione.");
				client = Server.accept();
				System.out.println("Connessione accettata da: "+
						client.getInetAddress());
				output.append("Connessione accettata da: "+
						client.getInetAddress()+"\n");
//				Connect c = new Connect(client);
			}
			catch(Exception e) {}
		}
		
	}
	
	public static String getIpAddress() 
	{ 
	        URL myIP;
	        try {
	            myIP = new URL("http://api.externalip.net/ip/");

	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(myIP.openStream())
	                    );
	            return in.readLine();
	        } catch (Exception e) 
	        {
	            try 
	            {
	                myIP = new URL("http://myip.dnsomatic.com/");

	                BufferedReader in = new BufferedReader(
	                        new InputStreamReader(myIP.openStream())
	                        );
	                return in.readLine();
	            } catch (Exception e1) 
	            {
	                try {
	                    myIP = new URL("http://icanhazip.com/");

	                    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(myIP.openStream())
	                            );
	                    return in.readLine();
	                } catch (Exception e2) {
	                    e2.printStackTrace(); 
	                }
	            }
	        }

	    return null;
	}
	
	public String toString() {
		return output.toString();
	}
}
 
	class Connect extends Thread{
		
		private Client client = null;
		BufferedReader in = null;
		PrintStream out = null;
		public Connect() {}
		public Connect(Socket clientSocket){
			
			client.socket = clientSocket;
			try
			{
				in = new BufferedReader(
						new InputStreamReader(client.socket.getInputStream()));
				out = new PrintStream(client.socket.getOutputStream(), true);
			}
			catch(Exception e1)
			{
				try { client.socket.close(); }
				catch(Exception e) { System.out.println(e.getMessage());}
				return;
			}
			this.start();
		}
		public void run(){
			try
			{
				out.println("Generico messaggio per il Client");
				out.flush();
				// chiude gli stream e le connessioni
				out.close();
				in.close();
				client.socket.close();
				
			}
			catch(Exception e) {}
		}
//		public BufferedReader getIn() {
//			return in;
//		}
//		public PrintStream getOut() {
//			return out;
//		}
		
}
