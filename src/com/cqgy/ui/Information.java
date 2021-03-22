package com.cqgy.ui;

import java.awt.Button;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.cqgy.bean.UserInfo;
import com.cqgy.method.InfoDao;
import com.sunking.swing.JDatePicker;

public class Information extends JFrame {
	private String Id;
	public static String Name;
	private String Autograph;
	private String Gender;
	private String Birthday;
	private String Location;
	private String School;
	private String company;
	private String job;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private List<JTextField> listtext;
	private int num = 0;
	private UserInfo info = null;
	private UserInfo infochaun = null;
	public static int numone=0;
	Date dateone=null;
	private static int numstart=0; 

	public Information(String id, String userName) throws HeadlessException {
		super();
		
		addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e)
	          {
	        	  MainFrame.SeeionTh.stop();
//	        	  dispose();
//	        	  try {
//					MainFrame.SeeionTh.wait();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
	            
	          }
	      });
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Id = id;
		infochaun = new InfoDao().SelectInfo(Id); 
		Name = infochaun.getNickname();
		Autograph = infochaun.getAutograph();
		Gender = infochaun.getGender();          
		Birthday = infochaun.getBirthday();
		Location = infochaun.getLocation();
		School = infochaun.getSchool();        
		company = infochaun.getCompany();
		job = infochaun.getJob();  
		System.out.println(Birthday);
		if(!"None".equals(Birthday)&&Birthday!=null){
			try {
			dateone=sdf.parse(Birthday.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dateone=null;
		}
		}
		

		InformationFrom(Id, userName);
//		System.out.println(infochaun.getNickname()+"--------------12asasf");
	}

	// 基础信息
	public void InformationFrom(String id, String userName) {
		listtext = new ArrayList();
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u6027\u522B");
		lblNewLabel.setBounds(29, 60, 67, 26);

		getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("\u7528\u6237\u6635\u79F0");
		label.setBounds(195, 24, 67, 26);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("\u751F\u65E5");
		label_1.setBounds(195, 60, 67, 26);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u8D26\u53F7");
		label_2.setBounds(29, 24, 67, 26);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("\u804C\u4E1A");
		label_3.setBounds(29, 96, 67, 26);
		getContentPane().add(label_3);

		JLabel label_4 = new JLabel("\u516C\u53F8");
		label_4.setBounds(195, 96, 67, 26);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("\u6240\u5728\u5730");
		label_5.setBounds(29, 132, 67, 26);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("\u72B6\u6001");
		label_6.setBounds(195, 127, 67, 26);
		getContentPane().add(label_6);

		JLabel label_7 = new JLabel("\u4E2A\u6027\u7B7E\u540D");
		label_7.setBounds(29, 168, 67, 26);
		getContentPane().add(label_7);

		textField = new JTextField();
		textField.setBounds(72, 27, 78, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		if (!Id.isEmpty()) {
			textField.setText(Id);
		}
		textField.setEditable(false);

		textField_1 = new JTextField();
		listtext.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setBounds(249, 27, 78, 21);
		getContentPane().add(textField_1);
		if (userName!=null&&!userName.isEmpty()) {
			textField_1.setText(userName);
		}else{
			textField_1.setText("None");
		}

		textField_2 = new JTextField();
		listtext.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setBounds(72, 63, 78, 21);
		getContentPane().add(textField_2);
		if (infochaun.getGender()!=null&&!infochaun.getGender().isEmpty()) {
			textField_2.setText(infochaun.getGender());
		}else{
			textField_2.setText("None");
		}
		
		
		
		
//		textField_3 = new JTextField();
//		listtext.add(textField_3);
//		textField_3.setColumns(10);
//		textField_3.setBounds(249, 63, 66, 21);
//		getContentPane().add(textField_3);
//		if (infochaun.getBirthday()!=null&&!infochaun.getBirthday().isEmpty()) {
//			textField_3.setText(infochaun.getBirthday());
//		}else{
//			textField_3.setText("None");
//		}
		JDatePicker ds=new JDatePicker();
		ds.setBounds(249, 63, 78, 21);
		if (dateone!=null) {
			try {
				ds.setSelectedDate(dateone);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				ds.setSelectedDate(new Date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getContentPane().add(ds);
		
		
		
		textField_4 = new JTextField();
		listtext.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setBounds(72, 96, 78, 21);
		getContentPane().add(textField_4);
		if (infochaun.getJob()!=null&&!infochaun.getJob().isEmpty()) {
			textField_4.setText(infochaun.getJob());
		}else{
			textField_4.setText("None");
		}
		
		
		textField_5 = new JTextField();
		listtext.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setBounds(249, 96, 78, 21);
		getContentPane().add(textField_5);
		if (infochaun.getCompany()!=null&&!infochaun.getCompany().isEmpty()) {
			textField_5.setText(infochaun.getCompany());
		}else{
			textField_5.setText("None");
		}

		textField_6 = new JTextField();
		listtext.add(textField_6);
		textField_6.setColumns(10);
		textField_6.setBounds(72, 132, 78, 21);
		getContentPane().add(textField_6);
		if (infochaun.getLocation()!=null&&!infochaun.getLocation().isEmpty()) {
			textField_6.setText(infochaun.getLocation());
		}else{
			textField_6.setText("None");
		}
		
		textField_7 = new JTextField();
		listtext.add(textField_7);
		textField_7.setColumns(10);
		textField_7.setBounds(249, 132, 78, 21);
		getContentPane().add(textField_7);
		if (infochaun.getStatu()!=null&&!infochaun.getStatu().isEmpty()) {
			textField_7.setText(infochaun.getStatu());
		}else{
			textField_7.setText("None");
		}

		JTextPane textPane = new JTextPane();
		// listtext.add(textPane);
		textPane.setBounds(39, 191, 276, 21);
		getContentPane().add(textPane);
		if (infochaun.getAutograph()!=null&&!infochaun.getAutograph().isEmpty()) {
			textPane.setText(infochaun.getAutograph());
		}else{
			textPane.setText("None");
		}

		for (JTextField jTextField : listtext) {
			 jTextField.setEditable(false);
		}
		textPane.setEditable(false);
		
		num++;
		
		Button button = new Button("\u7F16\u8F91");
		button.setBounds(29, 224, 286, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date bb = null;
				java.sql.Date birthday=null;
				int nums=new InfoDao().SelectUser(Id);
				if (num % 2 != 0) {
					button.setLabel("保存");
					
					for (JTextField jTextField : listtext) {
						jTextField.setEditable(true);
					}
					textPane.setEditable(true);
				
				} else {
					try {
						bb = ds.getSelectedDate();
						birthday=new java.sql.Date(bb.getTime());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Name = textField_1.getText();
					Gender = textField_2.getText();
					Birthday = birthday.toString();
					job  = textField_4.getText();
					School = textField_7.getText();
					company = textField_5.getText();
					Location= textField_6.getText();
					Autograph = textPane.getText();
					MainFrame.user_Signature.setText(textPane.getText());
					
					info = new UserInfo(id,Name, Autograph,Gender ,Birthday, Location, School, company, job);
					if(nums!=0){
						boolean userad = new InfoDao().UpdateUser(id,Name);
						boolean onr = new InfoDao().UpdatetInfo(info);
					}else{
						boolean onr = new InfoDao().InsertInfo(info);
					}
					SeeionThear.SeeionNums++;
//					if(numstart!=0){
//						MainFrame.SeeionTh.notify();
//					}else{
						MainFrame.SeeionTh.start();
//					}
						//g关闭当前线程
					dispose();
					numone++;
					button.setLabel("编辑");
				
					for (JTextField jTextField : listtext) {
						 jTextField.setEditable(false);
					}
					textPane.setEditable(false);

				}
				
			
				num++;

			}
		});
		getContentPane().add(button);
	}

//	public static void main(String[] args) {
//		Information infomation = new Information("wr","dsfdsf");
//		infomation.setSize(369, 300);
//		infomation.setVisible(true);
//	}
}
