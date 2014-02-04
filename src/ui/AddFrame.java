package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class AddFrame extends JFrame {

	public static JDesktopPane desktop;
	public static Container container ;
	
	public AddFrame() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		setTitle("Database For Addverties");
		setSize(770, 500);
		setLocation(600, 100);
		container = this.getContentPane();
		desktop = new JDesktopPane();
		desktop.setBorder(BorderFactory.createEmptyBorder());
		this.setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void showWindow() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		Adverties frm = new Adverties();
		this.getContentPane().add(frm);
		frm.setVisible(true);

	}
	

}
