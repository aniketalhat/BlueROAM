package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.DBConnection;

public class AddOffer extends JInternalFrame {

    private JLabel Offer_id,  Offer_nm,  Offer_desc;
    private JTextField txtOffer_id,  txtOffer_nm,  txtOffer_desc;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton AddNew;
    private JButton Cancel;
    private JButton Clear;
    private JButton Next;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private static JTextArea txtInfo = new JTextArea(15, 40);
    private Connection dbconn;
    private static String info;

    public AddOffer() {
        super("Add New Offer", false, true, false, true);
        setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);

        Offer_id = new JLabel("Offer No ");
        Offer_nm = new JLabel("Offer Name ");
        Offer_desc = new JLabel("Offer Desc");
        txtOffer_id = new JTextField(10);
        txtOffer_nm = new JTextField(10);
        txtOffer_desc = new JTextField(10);
        
        AddNew = new JButton("Add New", new ImageIcon("C:/img/image/new.png"));
        Cancel = new JButton("Cancel", new ImageIcon("C:/img/image/close.png"));
        Clear = new JButton("Clear", new ImageIcon("C:/img/image/clear.png"));

        jPanel1 = new JPanel(new java.awt.GridLayout(6, 2));

        jPanel1.add(Offer_id);
        jPanel1.add(txtOffer_id);
        jPanel1.add(Offer_nm);
        jPanel1.add(txtOffer_nm);
        jPanel1.add(Offer_desc);
        jPanel1.add(txtOffer_desc);
        jPanel3 = new javax.swing.JPanel(new FlowLayout());

        jPanel3.add(jPanel1);


        jPanel4 = new javax.swing.JPanel(new FlowLayout());

        jPanel4.add(AddNew);
        jPanel4.add(Cancel);
        jPanel4.add(Clear);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        setResizable(false);

       


        generator();
//        txtOffer_nm.addKeyListener(new KeyAdapter() {
//
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if (!(Character.isLetter(c) ||
//                        (c == KeyEvent.VK_BACK_SPACE) ||
//                        (c == KeyEvent.VK_SPACE) ||
//                        (c == KeyEvent.VK_DELETE))) {
//
//                    getToolkit().beep();
//                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "ERROR",
//                            JOptionPane.DEFAULT_OPTION);
//                    e.consume();
//                }
//            }
//        });
//        txtOffer_desc.addKeyListener(new KeyAdapter() {
//
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if (!(Character.isLetter(c) ||
//                        (c == KeyEvent.VK_BACK_SPACE) ||
//                        (c == KeyEvent.VK_SPACE) ||
//                        (c == KeyEvent.VK_DELETE))) {
//
//                    getToolkit().beep();
//                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "ERROR",
//                            JOptionPane.DEFAULT_OPTION);
//                    e.consume();
//                }
//            }
//        });
       

        AddNew.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (txtOffer_id.getText() == null ||
                        txtOffer_id.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Offer number", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    txtOffer_id.requestFocus();
                    return;
                }
                if (txtOffer_nm.getText() == null ||
                        txtOffer_nm.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Offer name", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    txtOffer_nm.requestFocus();
                    return;
                }
                if (txtOffer_desc.getText() == null ||
                        txtOffer_desc.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Offer description", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    txtOffer_desc.requestFocus();
                    return;
                }
                
                try {
                    Statement statement=null;
					try {
						statement = DBConnection.connectDB().createStatement();
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
                    {
                        String temp = "INSERT INTO adverties (add_id, offer_nm, offer_desc) VALUES ('" +
                                txtOffer_id.getText() + "', '" +
                                txtOffer_nm.getText() + "', '" +
                                txtOffer_desc.getText() + "')";

                        int result = statement.executeUpdate(temp);
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                                "Insert New Record", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == 0) {
                            //txtRouteNo.setText("");
                            generator();
                            txtOffer_nm.setText("");
                            txtOffer_desc.setText("");
                     

                        } else {
                            setVisible(false);
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
                    }

                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();

                }
            }
        });

        Cancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(true);
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

        Clear.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {

                txtOffer_id.setText("");
                txtOffer_nm.setText("");
                txtOffer_desc.setText("");
            }
        });

        jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);

        getContentPane().add(jPanel5);

        pack();
        setVisible(true);
    }



    private void generator() {

        try {
            ResultSet rst = DBConnection.connectDB().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT add_id FROM adverties");
            while (rst.next()) {
                String s;
                int number = rst.getInt(1);
                number = number + 1;

                s = "" + number;
                txtOffer_id.setText(s);

            }
        } catch (Exception n) {
            n.printStackTrace();
        }


    }
}
