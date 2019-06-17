package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import connection.Client;
import connection.Server;
import hamming.HammingCoder;
import hamming.HammingDecoder;
import utils.Util;

public class FinestraConMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ABOUT_TEXT = "Project realized for Information Theory course at UNICAL (A.A.2018/19).\n"
			+ "\nStudents:\nPierfrancesco D'Amico, 189243\n" + "Cosimo Loiero, 195328\n" + "Giovanni Aloia, 195325\n"
			+ "\nProfessor:\nIng. De Rango Floriano\n";
	private JMenu file, send, receive;
	private JMenuItem esci, imageS, textS, imageR, textR, about;
	private JPanel panel1, panel2, panel3;
	private JTextArea area2;
	private JTextPane tp1;
	private JFileChooser chooser;
	private JButton start;
	private File selectedFile;
	private JScrollPane spa;
	private Pier panel4;
	private ServerInformationWindow siw;
	private String ipAddr;
	private int port;
	private HammingCoder hc;
	Server serverSocket;
	private JButton btnNewButton;
	private Client client;
//	private ServerSocket server;
//	private Socket client;

	public FinestraConMenu() {
		setTitle("Coder Simulator");
		setLocation(150, 20);
		setSize(1100, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		// creazione barra menù
		JMenuBar menuBar = new JMenuBar();
		// creazione file menu
		JMenu fileMenu = new JMenu("File");
		// fileMenu.addSeparator();
		send = new JMenu("Send");
		receive = new JMenu("Receive");
		receive.addActionListener(this);
		textS = new JMenuItem("Message");
		textS.addActionListener(this);
		imageS = new JMenuItem("Image");
		imageS.addActionListener(this);
		textR = new JMenuItem("Message");
		textR.addActionListener(this);
		imageR = new JMenuItem("Image");
		imageR.addActionListener(this);
		send.add(imageS);
		send.add(textS);
		receive.add(imageR);
		receive.add(textR);
		fileMenu.add(send);
		fileMenu.add(receive);

		esci = new JMenuItem("Esci");
		esci.addActionListener(this);
		fileMenu.addSeparator();
		fileMenu.add(esci);
		menuBar.add(fileMenu);
		// JMenu command= new JMenu("Comandi");
		// menuBar.add(command);
		JMenu hlp = new JMenu("Help");
		about = new JMenuItem("About");
		about.addActionListener(this);

		hlp.add(about);
		menuBar.add(hlp);
		this.setJMenuBar(menuBar);

		JPanel space = new JPanel(new BorderLayout());
		getContentPane().add(space, BorderLayout.CENTER);

		panel1 = new JPanel();
		panel1.setBorder(new TitledBorder("Message Sended"));

		tp1 = new JTextPane();
		tp1.setEditable(true);
		tp1.setPreferredSize(new Dimension(450, 450));
		spa = new JScrollPane(tp1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spa.setAutoscrolls(true);
		panel1.add(spa, "2, 2, left, top");
		spa.setVisible(true);

		btnNewButton = new JButton("START");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(this);
		panel1.add(btnNewButton);
		panel1.setPreferredSize(new Dimension(570, 400));

		space.add(panel1, BorderLayout.WEST);
		start = new JButton("START");

		panel4 = new Pier();
		panel4.setBorder(new TitledBorder("Preferences"));
		panel4.setPreferredSize(new Dimension(200, 50));
		// southSpace.add(panel4,BorderLayout.WEST);
		JLabel codingLabel = new JLabel();
		codingLabel.setText("Coding method:");
		panel4.add(codingLabel, BorderLayout.WEST);
		space.add(panel4, BorderLayout.CENTER);

		/*
		 * panel3 = new JPanel(); panel3.setBorder(new TitledBorder("Message Arrived"));
		 * tp3= new JTextPane(); tp3.setEditable(false); tp3.setPreferredSize(new
		 * Dimension(450,450)); JScrollPane spb= new JScrollPane(tp3,
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); spb.setVisible(true);
		 * panel3.add(spb); panel3.setPreferredSize(new Dimension(570,300));
		 * spb.setVisible(true); space.add(panel3, BorderLayout.EAST);
		 */

		/*
		 * JPanel southSpace = new JPanel(); southSpace.setPreferredSize(new
		 * Dimension(480,480));
		 * 
		 * southSpace.setSize(300, 300); southSpace.setLayout(new BorderLayout());
		 * space.add(southSpace, BorderLayout.SOUTH);
		 */

		/*
		 * panel2 = new JPanel(); panel2.setBorder(new TitledBorder("Statistics"));
		 * area2 = new JTextArea(25, 50); area2.setEditable(false); JScrollPane sp2 =
		 * new JScrollPane(area2); panel2.add(sp2); panel2.setPreferredSize(new
		 * Dimension(600,500)); southSpace.add(panel2,BorderLayout.EAST);
		 */

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == imageS) {
			FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()); // filtro
																													// solo
																													// immagini
			chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			chooser.addChoosableFileFilter(imageFilter);
			chooser.setAcceptAllFileFilterUsed(false);
			int returnValue = chooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = chooser.getSelectedFile();
				panel4.setSelectedFile(selectedFile);
				tp1.insertIcon(new ImageIcon(selectedFile.getAbsolutePath()));
				tp1.setEditable(false);
				spa.setAutoscrolls(true);
				siw = new ServerInformationWindow();
				siw.setVisible(true);
				siw.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						ipAddr = siw.getIP();
						port = siw.getPort();
						System.out.println("Indirizzo:" + ipAddr + " Porta:" + port);
					}

				});
				String filePath = selectedFile.getAbsolutePath();
				String binaryImage = Util.imageToBinary(filePath);

			}

		} else if (arg0.getSource() == textS) {
			tp1.setText("");
			tp1.setEditable(true);
			btnNewButton.setEnabled(true);
			siw = new ServerInformationWindow();
			siw.setVisible(true);
			siw.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					ipAddr = siw.getIP();
					port = siw.getPort();
					System.out.println("Indirizzo:" + ipAddr + " Porta:" + port);
				}

			});

		}
		/*
		 * else if(arg0.getSource()==send) { FileNameExtensionFilter filter = new
		 * FileNameExtensionFilter("Text Files", "txt", "text"); chooser= new
		 * JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		 * chooser.setFileFilter(filter); int returnValue =
		 * chooser.showOpenDialog(null); if (returnValue == JFileChooser.APPROVE_OPTION)
		 * { selectedFile= chooser.getSelectedFile();
		 * panel4.setSelectedFile(selectedFile); String output =
		 * Util.textFileToString(selectedFile); tp1.setText(output); } }
		 */

		else if (arg0.getSource() == textR) {
			int port = 8000;
			tp1.setEditable(false);
			boolean choosed = false;

			try {
				serverSocket = new Server(port);
				panel4.setTextArea(
						"Il server è in attesa sulla porta " + port + " con indirizzo IP " + Server.getIpAddress());
				System.out.println("YAAAAHU");
				System.out.println(
						"Il server è in attesa sulla porta " + port + " con indirizzo IP " + Server.getIpAddress());
				panel4.setTextArea(
						"Il server è in attesa sulla porta " + port + " con indirizzo IP " + Server.getIpAddress());
				choosed = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panel4.setTextArea(serverSocket.toString());
			tp1.setText(serverSocket.received);

//				System.out.println("YAAAAHU");
//			this.port = port;
//			BufferedReader in;
//			PrintStream out;
//			try {
//				System.out.println("YAAAAHU");
//				client = new Client(serverSocket.getIpAddress(), port);
//						
//				client.socket = serverSocket.accept();
//				System.out.println("YAAAAHU");
//				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//				out = new PrintStream(client.getOutputStream(), true);
//			} catch (Exception e1) {
//				try {
//					client.close();
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				return;
//			}
//			System.out.println("YAAAAHU");
////			panel4.setTextArea("Connessione accettata da client con IP " + client.getInetAddress());
//			tp1.setText(Util.binaryToText(in.toString()));

		} else if (arg0.getSource() == btnNewButton) {
			String message = HammingDecoder.decode(tp1.getText());
//			try {
			System.out.println("tahu0");
				client = new Client(ipAddr, port);
				System.out.println("yahu1");
//				BufferedReader in;
//				PrintStream out;
//				in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
				client.send(message);
				System.out.println("yahu2");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		else if (arg0.getSource() == about) {
			JOptionPane.showMessageDialog(null, ABOUT_TEXT, "Guide", WIDTH);
		} else if (arg0.getSource() == esci)
			System.exit(0);

	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // inizializzazione del JFrame affidata a EventDispatch Thread di Swing
			public void run() {
				JFrame f = new FinestraConMenu();
				f.setVisible(true);
			}
		});

	}

}
