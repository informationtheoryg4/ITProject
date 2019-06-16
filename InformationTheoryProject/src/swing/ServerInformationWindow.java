package swing;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class ServerInformationWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
private JTextField ipaddr,port;
private JButton okButton;
private String ip;
private int p;

public ServerInformationWindow(){
	setTitle("Server Informations");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel p= new JPanel();
	p.add(new JLabel("Indirizzo IP", JLabel.RIGHT));
	p.add(ipaddr= new JTextField("",12));
	p.add(new JLabel("Porta",JLabel.RIGHT));
	p.add(port= new JTextField("",12));
	add(p,BorderLayout.NORTH);
	JPanel q= new JPanel();
	okButton = new JButton("OK");
	add(q, BorderLayout.SOUTH);
	q.add(okButton);
	ipaddr.addActionListener(this);
	port.addActionListener(this);
	okButton.addActionListener(this);
	setSize(450,130);
	setLocation(600,300);
}
@Override
public void actionPerformed(ActionEvent evt) {
	
	if(evt.getSource()==okButton) {
		try {
		ip= ipaddr.getText();
		p=Integer.parseInt(port.getText());
		this.dispose();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Dati non validi!");
		}
		
	}
}

public String getIP() {
	return ip;
}

public int getPort() {
	return p;
}






	public static void main(String[]args){//esempio
		ServerInformationWindow fc= new ServerInformationWindow();
		fc.setVisible(true);
	}
}//Cambio