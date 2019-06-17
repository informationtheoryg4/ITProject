package swing;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import connection.Server;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class Pier extends JPanel implements ActionListener{
	
	/**
	 * Create the panel.
	 */
	
	private CodingType codingType;
	private ChannelType channelType;
	boolean selected;
	File selectedFile;
	JComboBox comboBox_1;
	JTextArea textArea = new JTextArea();
	
	
	public Pier() {
		channelType = ChannelType.UMTS;
		codingType = CodingType.HAMMING_7_4;
		setForeground(SystemColor.desktop);
		setBackground(SystemColor.menu);
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		//textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		selected=false;
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Hamming_7_4","Hamming_12_8","Huffmann", "LZ77" }));
		comboBox_1.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Coding method:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JSeparator separator = new JSeparator();
		
		
		/*try {
			serverSocket = new Server();
			textArea.setText(serverSocket.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		JLabel lblStatus = new JLabel("Stato:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 541, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblStatus)
					.addContainerGap(464, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
					.addGap(122))
		);
		setLayout(groupLayout);

	}
	
	public void setTextArea(String s) {
		textArea.setText(s);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g); // paint the background image and scale it to fill the entire space
	    Image img= null;
	    try {
            img = ImageIO.read(new File("background-polos-png-5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

	    g.drawImage(img,5,10,null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
	public void setSelectedFile(File f) {
		selectedFile= new File(f.getPath());
	}
	
	public CodingType getCodingType() {
		return codingType;
	}
	
	public ChannelType getChannelType() {
		return channelType;
	}
}
