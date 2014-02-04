package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingAnimation extends JFrame implements ActionListener {
	
		Thread th;
		ImageIcon images;
		JFrame frame;
		JLabel lbl;
		JButton click;
		Container container;
		
		
		int i = 0;
		int j;
		JPanel p;
		public static void main(String[] args){
			
			
			
			
		SwingAnimation sa = new SwingAnimation();	
		}

		public SwingAnimation(){
			frame = new JFrame("Animation Frame");
		
			th = new Thread();
			lbl = new JLabel();
			Panel panel = new Panel();
			panel.add(lbl);
			frame.add(panel, BorderLayout.CENTER);
			frame.setSize(900, 900);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			for(j = 1; j <= 2; j++){
				SwingAnimator();
				if (j == 2)
					j = 0;
			}
		}

		public void SwingAnimator(){
			try{
				for(i = 26; i >= 1; i--){
					images = new ImageIcon("C:/img/products/slide"+i+".jpg");
					lbl.setIcon(images);
					//p.add(images, BorderLayout.NORTH);
					th.sleep(1000);
				}
			}
			catch(InterruptedException e){}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}


	}



