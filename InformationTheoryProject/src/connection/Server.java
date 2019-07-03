package connection;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import hamming.HammingDecoder;
import swing.CodingType;
import utils.Util;

public class Server extends Thread {

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
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			StringBuilder sb = new StringBuilder();
			int txtOrImg = Integer.parseInt(in.readLine());
			CodingType codingType = CodingType.valueOf(in.readLine());// CIAO COSIMO
			msg = in.readLine();
			boolean b = false;
			while (msg != null) {
				if(b) {
					sb.append("\n");
					b=true;
				}
				sb.append(msg);
				msg = in.readLine();
			}
			if (txtOrImg == 0) { // TESTO
				switch (codingType) {
				case HAMMING_7_4:
					tp1.setText(Util.binaryToText(HammingDecoder.decode(sb.toString())));
					break;
				case HAMMING_12_8:
					tp1.setText(Util.binaryToText(HammingDecoder.decode(sb.toString())));
					break;
				case HUFFMANN:
					// TODO
					break;
				case LZ:
					// TODO
					break;
				}
			} else { // IMMAGINE
				File newFile = null;
				switch (codingType) {
				case HAMMING_7_4:
//					bi = Util.byteArrayToImage(Util.binaryToByteArray(sb.toString()));
//					try {
//						ImageIO.write(bi, "jpg", newFile);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					System.out.println(HammingDecoder.decode(sb.toString()).length());
					BufferedImage bi= Util.byteArrayToImage(Util.binaryToByteArray(HammingDecoder.decode(sb.toString())));
					try {
						ImageIO.write(bi, "jpg", new File("received.jpg") );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tp1.insertIcon(new ImageIcon("received.jpg"));
					break;
				case HAMMING_12_8:
					bi = Util.byteArrayToImage(Util.binaryToByteArray(sb.toString()));
					newFile = new File("imgReceived.jpg");
					try {
						ImageIO.write(bi, "jpg", newFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tp1.insertIcon(new ImageIcon(newFile.getAbsolutePath()));
					break;
				case HUFFMANN:
					// TODO
					break;
				case LZ:
					// TODO
					break;
				}
			}

			in.close();
			this.server.close();
		} catch (Exception e) {
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

	public static void main(String[] args) {
		CodingType ct = CodingType.HAMMING_7_4;
		System.out.println(ct.toString() + CodingType.valueOf(ct.toString()));

	}
}
