package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import ui.BlueMall;
import ui.Login;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {

	JButton click;
	Container container;
	JLabel version;
	JPanel p;

	public Main() {

		setTitle("Welcome");
		setSize(1116, 946);
		setLocation(0, 0);

		container = getContentPane();
		p = new JPanel(new BorderLayout());

		click = new JButton(new ImageIcon("src/image/welcomeImg.jpg"));
		p.add(click, BorderLayout.NORTH);

		String name = "Version 1.1.0 ";
		version = new JLabel(name);

		p.add(version, BorderLayout.SOUTH);
		click.addActionListener(this);
		container.add(p);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == click) {
			this.setVisible(false);
			//new BlueMall(true);
//			new Login(true);
		}
			

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//new Main();
		new Login();
		new BTServicesSearcher(null);
		//new BlueMall();
		
	}

}
