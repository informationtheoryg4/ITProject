package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;




public class FinestraConMenu extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem esci,file,image,text,about;
	private JPanel panel1,panel2,panel3;
	private JTextArea area2;
	private JTextPane tp1,tp3;
	private JFileChooser chooser;
	private File selectedFile;
	
	public FinestraConMenu(){
		setTitle("Coder Simulator");
		setLocation(150,20);
		setSize(1100,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//creazione barra menù
		JMenuBar menuBar= new JMenuBar();
		//creazione file menu
		JMenu fileMenu= new JMenu("Send");
		fileMenu.addSeparator();
		file= new JMenuItem("File");
		image= new JMenuItem("Image");
		text= new JMenuItem("Message");
		text.addActionListener(this);
		image.addActionListener(this);
		fileMenu.add(file);
		fileMenu.add(image);
		fileMenu.add(text);
		esci= new JMenuItem("Esci");
		esci.addActionListener(this);
		fileMenu.addSeparator();
		fileMenu.add(esci);
		menuBar.add(fileMenu);
		//JMenu command= new JMenu("Comandi");
		//menuBar.add(command);
		JMenu hlp= new JMenu("Help");
		about= new JMenuItem("About");
		hlp.add(about);
		menuBar.add(hlp);
		this.setJMenuBar(menuBar);
		
		
		JPanel space= new JPanel(new BorderLayout());
		add(space, BorderLayout.CENTER);
		
		panel1 = new JPanel();
		panel1.setBorder(new TitledBorder("Message Sended")); 
		
		
		tp1= new JTextPane();
		tp1.setEditable(false);
		tp1.setPreferredSize(new Dimension(460,400));
		//tp1.insertIcon ( new ImageIcon ( "C:\\Users\\Giova\\Desktop\\luna.jpg" ) );
		JScrollPane spa= new JScrollPane(tp1);
		panel1.add(spa);
		panel1.setPreferredSize(new Dimension(530,400));
		spa.setVisible(true);
		space.add(panel1,BorderLayout.WEST);
		
		
		panel3 = new JPanel();
		panel3.setBorder(new TitledBorder("Message Arrived")); 
		
		
		
		tp3= new JTextPane();
		tp3.setEditable(false);
		tp3.setPreferredSize(new Dimension(460,400));
		JScrollPane spb= new JScrollPane(tp3);
		panel3.add(spb);
		panel3.setPreferredSize(new Dimension(460,300));
		spb.setVisible(true);
		space.add(panel3, BorderLayout.CENTER);
		
		
		JPanel southSpace = new JPanel();
		southSpace.setPreferredSize(new Dimension(480,480));
		
		southSpace.setSize(300, 300);
		southSpace.setLayout(new BorderLayout());
		space.add(southSpace, BorderLayout.SOUTH);
		
		
		panel2 = new JPanel();
		panel2.setBorder(new TitledBorder("Statistics")); 
		area2 = new JTextArea(25, 50);
		area2.setEditable(false);
		JScrollPane sp2 = new JScrollPane(area2);
		panel2.add(sp2);
		panel2.setPreferredSize(new Dimension(600,500));
		southSpace.add(panel2,BorderLayout.EAST);
		
		JPanel panel4 = new Pier();
		panel4.setBorder(new TitledBorder("Preferences")); 

		panel4.setPreferredSize(new Dimension(500,50));
		southSpace.add(panel4,BorderLayout.WEST);
		
		JLabel codingLabel= new JLabel();
		codingLabel.setText("Coding method:");
		panel4.add(codingLabel,BorderLayout.WEST);	
		
		JComboBox codingCombo= new JComboBox();
		codingCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Hamming", "LZ77", "Huffmann" })); 
		panel4.add(codingCombo, BorderLayout.WEST);	
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==image) {
			chooser= new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = chooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				tp1.insertIcon ( new ImageIcon (selectedFile.getAbsolutePath()));
			}
			
		}
		else if(arg0.getSource()==text) {
			tp1.setText("");
			tp1.setEditable(true);
		}
			
		else if(arg0.getSource()==esci)
			System.exit(0);
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){ //inizializzazione del JFrame affidata a EventDispatch Thread di Swing
			public void run(){
				JFrame f= new FinestraConMenu();
				f.setVisible(true);
			}
		});

	}

}
