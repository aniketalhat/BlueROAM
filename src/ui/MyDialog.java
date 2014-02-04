package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.BTRefresh;
import main.LoadImage;

public class MyDialog extends JFrame implements ActionListener {

	public static JTextArea jTextArea;
	private JList jList, offersList;
	private DialogListener listener;
	private Object[] items;
	private JLabel statusLabel, deviceFound, WorkPad, Logger;
	String str = "Loaded";
	private JTextArea jTextArea2;
	private JLabel State, offers;
	public JButton jButton, jButton2;
	public JLabel logo;
	public ImageIcon images;
	JProgressBar current;
	JTextArea out;
	JButton find;
	Thread runner;
	Thread th;
	int num = 0, i = 0, j = 0;

	// MyDialog frame;

	@SuppressWarnings("deprecation")
	public MyDialog(String title, Object[] items, DialogListener listener) {
		super.setTitle(title);

		this.listener = listener;
		this.setSize(557, 610);
		this.setLocation(200, 80);

		jList = new JList();

		if (items == null) {
			items = new Object[0];
		}
		jList.setListData(items);

		this.items = items;
		this.getContentPane().setLayout(null);

		Logger = new JLabel("Logger Of the Application");
		Logger.setBounds(0, 350, 200, 20);
		this.getContentPane().add(Logger);

		jTextArea2 = new JTextArea();
		jTextArea2.setBounds(0, 370, 360, 100);
		this.getContentPane().add(jTextArea2);

		JScrollPane jScrollPane2 = new JScrollPane(jTextArea2);
		jScrollPane2.setBounds(0, 370, 360, 100);
		this.getContentPane().add(jScrollPane2);

		State = new JLabel("Curret State of Application...");
		// this.getContentPane().add(statusLabel,BorderLayout.NORTH);
		State.setBounds(0, 470, 600, 30);
		this.getContentPane().add(State);

		deviceFound = new JLabel("Devices Found");
		deviceFound.setBounds(0, 0, 180, 20);
		this.getContentPane().add(deviceFound);

		JScrollPane jScrollPane = new JScrollPane(jList);
		// jScrollPane.setSize(400,100);
		jScrollPane.setBounds(0, 20, 180, 300);
		this.getContentPane().add(jScrollPane);
		// this.getContentPane().setLayout(new BorderLayout());
		// this.getContentPane().add(jScrollPane,BorderLayout.WEST);

		WorkPad = new JLabel("Text Area");
		WorkPad.setBounds(180, 0, 400, 20);
		this.getContentPane().add(WorkPad);

		jTextArea = new JTextArea("Enter message here");
		// this.getContentPane().add(jTextArea,BorderLayout.CENTER);
		jTextArea.setBounds(180, 20, 180, 300);
		this.getContentPane().add(jTextArea);

		JScrollPane jScrollPane21 = new JScrollPane(jTextArea);
		// jScrollPane.setSize(400,100);
		jScrollPane21.setBounds(180, 20, 180, 300);
		this.getContentPane().add(jScrollPane21);

		offers = new JLabel("Copyright 2011");
		offers.setBounds(360, 0, 300, 20);
		this.getContentPane().add(offers);
		offersList = new JList();
		offersList.setBounds(300, 20, 500, 300);

		// th = new Thread();
		logo = new JLabel();
		// this.getContentPane().add(jButton,BorderLayout.SOUTH);
		logo.setBounds(360, 0, 268, 339);
		this.getContentPane().add(logo);
		//SwingAnimator(1);
		th = new Thread(new LoadImage(this));
		th.start();
		
		JButton jButton = new JButton("Send Message");
		jButton.addActionListener(this);
		// this.getContentPane().add(jButton,BorderLayout.SOUTH);
		jButton.setBounds(180, 320, 180, 30);
		this.getContentPane().add(jButton);

		JButton jButton2 = new JButton("Refresh");
		jButton2.addActionListener(this);
		// this.getContentPane().add(jButton,BorderLayout.SOUTH);
		jButton2.setBounds(0, 320, 180, 30);
		this.getContentPane().add(jButton2);

		JButton add = new JButton("Add Adverties");
		add.addActionListener(this);
		// this.getContentPane().add(jButton,BorderLayout.SOUTH);
		add.setBounds(360, 320, 200, 30);
		this.getContentPane().add(add);

		statusLabel = new JLabel("Started");
		// this.getContentPane().add(statusLabel,BorderLayout.NORTH);
		statusLabel.setBounds(0, 500, 600, 30);
		this.getContentPane().add(statusLabel);
		// setLocationRelativeTo(null);

		// JPanel pane = new JPanel();
		// pane.setLayout(new FlowLayout());
		current = new JProgressBar(0, 2000);
		current.setValue(0);
		current.setStringPainted(true);
		current.setBounds(0, 540, 200, 30);
		this.getContentPane().add(current);
		/*
		 * this.pack(); //this.setVisible(false); this.iterate();
		 * //setContentPane(pane);
		 */

		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// this.show();
	}

	public void SwingAnimator() {
		try {
			
			for(i = 26; i >= 1; i--){
					images = new ImageIcon("C:/img/products/slide"+i+".jpg");
					logo.setIcon(images);
					Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
		
	}


	public void addItem(Object item) {

		Object[] newitems = new Object[items.length + 1];

		for (int i = 0; i < items.length; i++) {
			newitems[i] = items[i];
		}
		newitems[newitems.length - 1] = item;

		items = newitems;
		jList.setListData(newitems);
		// jList2.setListData(newitems);
	}

	public void setStatus(String text) {
		statusLabel.setText(text);
	}

	public void setLog(String text) {
		jTextArea2.setEditable(false);
		jTextArea2.setText(text);
	}

	public void Pro() {
		while (num < 2000) {
			current.setValue(num);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
			}
			num += 95;
		}

		current.setString(str);
		current.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Send Message") {
			Object[] selectedItems = jList.getSelectedValues();
			String message = jTextArea.getText();

			listener.sendPressed(selectedItems, message, this);
		}

		if (e.getActionCommand() == "Refresh") {
			try {
				setStatus("Refreshing...plz wait!!!");
				/*jList.removeAll();
				items = new Object[0];
				jList.setListData(items);*/

				BTRefresh btr = new BTRefresh();
				btr.methodRefresh(this);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getActionCommand().equals("Add Adverties")) {
			try {
				AddFrame frm = new AddFrame();
				frm.showWindow();
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

			System.out.println("in add");

		}

	}

	public interface DialogListener {
		public void sendPressed(Object[] selectedItems, String message,
				MyDialog dialog);
	}

		/*
	 * public MyDialog() {
	 * 
	 * //Progress frame = new Progress(null); //this.pack();
	 * //this.setVisible(true); iterate(); // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
