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

import connection.Server;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.Action;
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
	JRadioButton umts;
	JRadioButton satellitar;
	boolean selected;
	File selectedFile;
	JComboBox comboBox_1;
	JButton btnStartSimulation;
	JTextArea textArea = new JTextArea();
	
	
	public Pier() {
		channelType = ChannelType.UMTS;
		codingType = CodingType.HUFFMANN;
		setForeground(SystemColor.desktop);
		setBackground(SystemColor.menu);
		
		selected=false;
		
		umts = new JRadioButton("UMTS");
		umts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		umts.setSelected(true);
		umts.addActionListener(this);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Huffmann", "LZ77", "Hamming_7_4", "Hamming_12_8"}));
		comboBox_1.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Coding method:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		satellitar = new JRadioButton("Satellitar");
		satellitar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		satellitar.addActionListener(this);
		
		JLabel lblChannel = new JLabel("Channel:");
		lblChannel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JSeparator separator = new JSeparator();
		
		btnStartSimulation = new JButton("START SIMULATION");
		btnStartSimulation.addActionListener(this);
		btnStartSimulation.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		/*try {
			serverSocket = new Server();
			textArea.setText(serverSocket.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 541, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addComponent(lblChannel)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(umts, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(satellitar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(198, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(143)
					.addComponent(btnStartSimulation, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(228, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(108, Short.MAX_VALUE))
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
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChannel)
						.addComponent(umts, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(satellitar))
					.addGap(18)
					.addComponent(btnStartSimulation, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
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
		if(arg0.getSource()==satellitar) {
			umts.setSelected(false);
			satellitar.setSelected(true);
		}
		else if(arg0.getSource()==umts) {
			satellitar.setSelected(false);
			umts.setSelected(true);
		}
		else if(arg0.getSource()==comboBox_1) {
			String selected= String.valueOf(comboBox_1.getSelectedItem());
			switch(selected) {
			case "Hamming_7_4":
				codingType = CodingType.HAMMING_7_4;
				System.out.println("Hamming");
				break;
			case "Hamming_12_8":
				codingType = CodingType.HAMMING_12_8;
				System.out.println("Hamming");
				break;
			case "Huffmann":
				codingType = CodingType.HUFFMANN;
				System.out.println("Huffmann");
				break;
			case "LZ77":
				codingType = CodingType.LZ;
				System.out.println("LZ77");
				break;
			default:
				System.out.println("ORANGO");
				break;
			}
		
		}
		else if(arg0.getSource()== btnStartSimulation) {
			System.out.println("Da implementare");
		}
		
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
