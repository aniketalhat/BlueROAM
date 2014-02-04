package main;

import ui.MyDialog;

public class LoadImage implements Runnable{
MyDialog dialog;
int i=1;
private int a;
	
	public LoadImage(MyDialog dialog)
	{
		this.dialog = dialog;
	}
	@Override
	public void run() {
		try{
		for(a=1;a<=28;a++){ 
		System.out.println("in LoadImage");
		dialog.SwingAnimator();
		//Thread.sleep(500);
		if(a==38)
		{
			a=1;
			
		}
	}
	}catch(Exception e)
	{
		
	}
		}

}
