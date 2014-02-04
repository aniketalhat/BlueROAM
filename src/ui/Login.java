package ui;

import java.awt.Container;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.BTServicesSearcher;

import dao.User;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	JTextField username;
	JPasswordField password;
	JLabel user_name, pass_word;
	JButton Login, Cancel;
	Container container;
	Panel p;
	Login Lo;
	
	public Login() {
		setTitle("Login Page");
		setSize(400, 200);
		setLocation(300, 300);
		
		container = getContentPane();
		p = new Panel();
		p.setLayout(null);

		user_name = new JLabel("User Name :");
		user_name.setBounds(10, 20, 100, 20);
		username = new JTextField();
		username.setBounds(120, 20, 100, 20);

		pass_word = new JLabel("Password :");
		pass_word.setBounds(10, 50, 100, 20);
		password = new JPasswordField();
		password.setBounds(120, 50, 100, 20);

		Login = new JButton("Login");
		Login.setBounds(20, 100, 100, 20);
		Cancel = new JButton("Cancel");
		Cancel.setBounds(140, 100, 100, 20);

		p.add(user_name);
		p.add(username);
		p.add(pass_word);
		p.add(password);
		p.add(Login);
		p.add(Cancel);
		Login.addActionListener(this);
		Cancel.addActionListener(this);
		container.add(p);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
//public Login(boolean flag) {
//		
//		if(flag)
//		{
//			//new Progress();
//			Lo.show();
//		}
//		
//	}


	@Override
	public void actionPerformed(ActionEvent e) {

		// JButton action = (JButton) e.getSource();
		if (e.getSource() == Login) {
			
				setVisible(false);
				// JRadioButton r = (JRadioButton) e.getSource();
				String name = username.getText();
				
				char[] pass = password.getPassword();
				String password = new String(pass);
				System.out.println("in Login");

				User dao = new User();
				Boolean value = dao.validUser(name, password);
				if (value == true) {
				
					new BTServicesSearcher(true);
								
				}
				else
				{
				JOptionPane.showMessageDialog(container,"Please enter valid UserName and Password!!!","ERROR", JOptionPane.ERROR_MESSAGE);
				new Login();
				}
			
		} else if (e.getSource() == Cancel) {
			//this.setVisible(false);
			this.dispose();
		}

	}
}
