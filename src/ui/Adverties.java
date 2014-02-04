package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import dao.DBConnection;

@SuppressWarnings("serial")
public class Adverties extends JPanel {

	private static javax.swing.JTable jTable;
	private JScrollPane jScrollPane;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton0;
	private JButton jButton1;
	private JMenuItem mnuNewuser;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton AddNew, Update, Remove, Search, Clear, Exit;
	private static int rowCnt = 0;
	private static int selectedRow;
	private static JTextArea txtInfo = new JTextArea(15, 40);
	private Connection dbconn;
	private static String info;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	

	public Adverties() throws InstantiationException, IllegalAccessException, ClassNotFoundException  {

		//super("Adverties", false, true, false, true);
		//this.setSize(350, 270);
		//this.setLocation(0,0);
		//this.setLayout(null);


        jTable = new javax.swing.JTable(new AbstractTable());
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 3; i++) {
            column = jTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(85);
            } else {
                column.setPreferredWidth(50);
            }
        }

        jScrollPane = new JScrollPane(jTable);
        jPanel1 = new JPanel(new java.awt.BorderLayout());
        jPanel1.add(jScrollPane, java.awt.BorderLayout.CENTER);

		//mnuNewuser.setIcon(new ImageIcon("images/users.png"));
        jButton0 = new JButton("Send",new ImageIcon("C:/img/image/send.png"));
        jButton1 = new JButton("Add Entry", new ImageIcon("C:/img/image/new.png"));
        jButton2 = new JButton("Update", new ImageIcon("C:/img/image/update.png"));
        jButton3 = new JButton("Refresh", new ImageIcon("C:/img/image/refresh.png"));
        jButton4 = new JButton("Close", new ImageIcon("C:/img/image/close.png"));

        jPanel2 = new JPanel(new FlowLayout());
        try {

            Statement s = DBConnection.connectDB().createStatement();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
        jPanel2.add(jButton0);
        jPanel2.add(jButton1);
        jPanel2.add(jButton2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        setSize(800, 400);
        load_jTable();

        jButton0.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	String str =" Offers on "+jTable.getValueAt(getSelectedRow(), 1).toString()+"   ::  "+
                        jTable.getValueAt(getSelectedRow(), 2).toString();
            	
            	MyDialog.jTextArea.setText(str);
            
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
        });


        
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //new AddNew().setVisible(true);
                AddOffer frm = new AddOffer();
                setVisible(false);
                AddFrame.container.add(frm);
        		frm.setVisible(true);
                try {
                    frm.setSelected(true);
                } catch (Exception ex) {
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	UpdateOffer frm = new UpdateOffer(jTable.getValueAt(getSelectedRow(), 0).toString(),
                        jTable.getValueAt(getSelectedRow(), 1).toString(),
                        jTable.getValueAt(getSelectedRow(), 2).toString());
                setVisible(false);
                AddFrame.container.add(frm);
        		frm.setVisible(true);
                try {
                    frm.setSelected(true);
                } catch (Exception ex) {
                }
            }
        });

        jButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
					load_jTable();
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
                setVisible(true);
            }
        });

        jButton4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
        
            System.exit(0);
            }
        });

        jPanel1.add(jPanel2, BorderLayout.SOUTH);
        jPanel1.setPreferredSize(new Dimension(750, 300));

        add(jPanel1);
    }

	public static int getSelectedRow() {
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.ListSelectionModel rowSel = jTable.getSelectionModel();
        //added by vishal
        if(rowSel.getAnchorSelectionIndex()!= 0)
        	return rowSel.getAnchorSelectionIndex();
        //
        rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel sel = (ListSelectionModel) e.getSource();
                if (!sel.isSelectionEmpty()) {
                    selectedRow = sel.getMinSelectionIndex();
                }
            }
        });

        return selectedRow;
    }
	
	
	 class AbstractTable extends javax.swing.table.AbstractTableModel {

	        private String[] columnNames = {"Offer_id", "Offer Name", "Offer Discription"
	        };
	        private Object[][] data = new Object[100][3];

	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public int getRowCount() {
	            return data.length;
	        }

	        public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }

	        public void setValueAt(Object value, int row, int col) {
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	        }
	    }
	
	
	 public void load_jTable() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

	        try {
	            Statement statement = DBConnection.connectDB().createStatement();
	            {
	                String temp = ("SELECT * FROM adverties ORDER BY add_id");
	                int Numrow = 0;
	                ResultSet result = statement.executeQuery(temp);
	                while (result.next()) {
	                    jTable.setValueAt(result.getInt(1), Numrow, 0);
	                    jTable.setValueAt(result.getString(2), Numrow, 1);
	                    jTable.setValueAt(result.getString(3), Numrow, 2);
	                    Numrow++;
	                }

	            }
	            System.out.println("load table");
	        } catch (SQLException sqlex) {
	            sqlex.printStackTrace();
	        }
	    }
	
}
