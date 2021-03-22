package com.cqgy.ui;

import javax.swing.JLabel;

import com.cqgy.method.InfoDao;

public class SeeionThear implements Runnable{
	public static int SeeionNums=0;
	JLabel 	user_name=null; 
	private String Id=null;
	public SeeionThear() {
		super();
	}
	public SeeionThear(JLabel user_name, String id) {
		super();
		this.user_name = user_name;
		Id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if(SeeionNums!=0){
				String user_id= new InfoDao().SelectUserone(Id); 
				user_name.setText(user_id);
				SeeionNums=0;
			}
		}
	}
}
