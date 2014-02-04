package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DBConnection;

public class UpdateOffer extends JInternalFrame {

	private JLabel Offer_id, Offer_nm, Offer_desc;
	private JTextField Add_id, Add_nm, Add_desc;
	private JButton Update, Search, Clear;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	String add_id, offer_nm, offer_desc;
	private JButton Cancel;
	private JPanel jPanel1;
	final JFileChooser fc = new JFileChooser();
	String getPicture;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private Connection dbconn;
	private static String info;

	public UpdateOffer(String add_id, String offer_nm, String offer_desc) {
		
		super("Update Table", false, true, false, true);
		this.add_id = add_id;
		this.offer_nm = offer_nm;
		this.offer_desc = offer_desc;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Offer_id = new JLabel("Offer Id ");
		Offer_nm = new JLabel("Offer Name ");
		Offer_desc = new JLabel("Offer Desc");

		Add_id = new JTextField(10);
		Add_nm = new JTextField(10);
		Add_desc = new JTextField(10);
		
		Add_id.setText(add_id);
		Add_nm.setText(offer_nm);
		Add_desc.setText(offer_desc);
		
		Update = new JButton("Update", new ImageIcon("C:/img/image/update.png"));
		Search = new JButton("Search", new ImageIcon("C:/img/image/search.png"));
		Clear = new JButton("Clear", new ImageIcon("C:/img/image/clear.png"));
		Cancel = new JButton("Cancel", new ImageIcon("c:/img/image/cancel.png"));
		
		jPanel1 = new javax.swing.JPanel(new java.awt.GridLayout(6, 2));
		jPanel1.add(Offer_id);
		jPanel1.add(Add_id);
		jPanel1.add(Offer_nm);
		jPanel1.add(Add_nm);
		jPanel1.add(Offer_desc);
		jPanel1.add(Add_desc);

		jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel3.add(jPanel1);

		jPanel4 = new javax.swing.JPanel(new java.awt.FlowLayout());

		jPanel4.add(Update);
		jPanel4.add(Cancel);
		jPanel4.add(Search);
		jPanel4.add(Clear);
		
		setResizable(false);
		setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
		try {

			Statement s = DBConnection.connectDB().createStatement();
		} catch (Exception excp) {
			excp.printStackTrace();
		}
//		Add_nm.addKeyListener(new KeyAdapter() {
//
//			public void keyTyped(KeyEvent e) {
//				char c = e.getKeyChar();
//				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
//						|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
//
//					getToolkit().beep();
//					JOptionPane.showMessageDialog(null,
//							"This Field Only acept text", "ERROR",
//							JOptionPane.DEFAULT_OPTION);
//					e.consume();
//				}
//			}
//		});
//		Add_desc.addKeyListener(new KeyAdapter() {
//
//			public void keyTyped(KeyEvent e) {
//				char c = e.getKeyChar();
//				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
//						|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
//
//					getToolkit().beep();
//					JOptionPane.showMessageDialog(null,
//							"This Field Only acept text", "ERROR",
//							JOptionPane.DEFAULT_OPTION);
//					e.consume();
//				}
//			}
//		});
		Update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Add_id.getText() == null
						|| Add_id.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Addverties number",
							"Error", JOptionPane.DEFAULT_OPTION);
					Add_id.requestFocus();
					return;
				}
				if (Add_nm.getText() == null
						|| Add_nm.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Offer name",
							"Offer Name", JOptionPane.DEFAULT_OPTION);
					Add_nm.requestFocus();
					return;
				}
				if (Add_desc.getText() == null || Add_desc.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter From",
							"Offer Description.", JOptionPane.DEFAULT_OPTION);
					Add_desc.requestFocus();
					return;
				}
				try {
					Statement statement = DBConnection.connectDB()
							.createStatement();
					{
						String temp = "UPDATE adverties SET "
								+ "  offer_nm        ='"
								+ Add_nm.getText()
								+ "',offer_desc           ='"
								+ Add_desc.getText() + "' WHERE add_id LIKE'"
								+ Add_id.getText() + "'";
						int result = statement.executeUpdate(temp);
						
						
						dispose();
						setVisible(false);
						
						Adverties frm=new Adverties();
						AddFrame.container.add(frm);
						frm.setVisible(true);
						
					}
					
				} 
				catch (Exception sqlex) {
					sqlex.printStackTrace();
				}

			}
		});

		Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				Adverties frm = null;
				try {
					frm = new Adverties();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddFrame.container.add(frm);
				frm.setVisible(true);
				
			}
			
		});

		Clear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Add_id.setText("");
				Add_nm.setText("");
				Add_desc.setText("");

			}
		});

		Search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (!Add_id.equals("")) {

						Statement statement = DBConnection.connectDB()
								.createStatement();
						String query = ("SELECT * FROM adverties where add_id ='"
								+ Add_id.getText() + "'");
						ResultSet rs = statement.executeQuery(query);
						display(rs);
						statement.close();
					} else // {
					{
						JOptionPane.showMessageDialog(null,
								"Enter Addverties number to search", "Error",
								JOptionPane.DEFAULT_OPTION);
					}
					return;
					// }
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				} catch (Exception as) {
					as.printStackTrace();
				}

				setVisible(true);

			}
		});

		jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

		jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);
		jPanel5.add(jPanel4, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanel5);

		pack();
		setVisible(true);
	}

	public void display(ResultSet rs) {
		try {
			// rs.next();

			boolean recordNumber = rs.next();
			if (recordNumber) {

				add_id = rs.getString(1);
				offer_nm = rs.getString(2);
				offer_desc = rs.getString(3);

				Add_id.setText(add_id);
				Add_nm.setText(offer_nm);
				Add_desc.setText(offer_desc);

			} else {
				JOptionPane.showMessageDialog(null, "Record Not found",
						"ERROR", JOptionPane.DEFAULT_OPTION);
			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

	}

}
