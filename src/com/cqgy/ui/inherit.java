package com.cqgy.ui;

import java.util.Map;

import javax.swing.JFrame;

//Ë¢ÐÂ
public class inherit implements Runnable {

	Map user=null;
	JFrame MainFrame1=null;
	
	
	public inherit() {
		super();
	}

	
	public inherit(Map user,JFrame MainFrame) {
		super();
		this.user = user;
		this.MainFrame1=MainFrame;
	}

	public void run(){
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(MainFrame.boolfinds){
				MainFrame1.revalidate();
				((MainFrame) MainFrame1).busdsad(user);
				MainFrame1.setVisible(true);
				MainFrame1.validate();
				MainFrame.boolfinds=false;
			}
		}
	}
	
}
