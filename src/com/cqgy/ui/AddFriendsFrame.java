package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cqgy.bean.Friend;
import com.cqgy.bean.User;
import com.cqgy.method.UserDao;
import com.cqgy.qq.Client;
import com.cqgy.qq.SetparametersFrameClient;
import com.mysql.jdbc.Field;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Map;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AddFriendsFrame extends JFrame {

	
	private String titleStr="";
	private JPanel contentPane;
    //�����û�ҵ����
	private UserDao objUserDao;
	// ��ǰ�û�Id
	private String userId;
	// ��������û�Id
	public String friendUserId;
	// ��������û���
	private String friendUserName;
	//��Ӻ���
	private boolean addFriend=false;
	//ɾ������
	private boolean deleteFriend=false;
	//�޸��û���������ע��
	private boolean updateUserName=false;
	//�޸��û��˺š�qq�š�
	private boolean updateUserNum=false;
	//�޸��û���Ϣ
	private boolean updateInfo=false;
//	public static JPanel MainFrameoneq=null;
	//���Ѷ���
		private Friend objfriend;
		private JTextField user_name;
		private JTextField user_section;
	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 * @throws SQLException 
	 */
		
		
		
	public AddFriendsFrame( Map<String, String> friend,String userid,String findsipone) throws UnknownHostException, SQLException {
		//��ȡ������Ϣ
		String friendIco=friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		/// ��ʼ����ǰFrame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		//��װ���Ѷ���
		if (objfriend==null) {
		 objfriend=new Friend(friendUserId, friendUserName, iconFile,InetAddress.getByName(Client.SERVER_IP).toString());	
		}
		
		//��ȡ��ǰ�û�ID
		this.userId=userid;
	    	//��ѯ���û��Ƿ�Ϊ�Լ��ĺ��Ѻ��Ƿ����Լ�
				if (objUserDao==null) {
					objUserDao=new UserDao();
				}
				if (userId.trim().equals(friendUserId)) {
					titleStr="������Ϣ";
					addFriend=false;
					deleteFriend=false;
					updateUserName=true;
					updateUserNum=true;
					updateInfo=true;
				}else
				{
					titleStr="İ����";
					addFriend=true;
					deleteFriend=false;
					updateUserName=false;
					updateUserNum=false;
					updateInfo=false;
				}
				if (objUserDao.selectFriends(userId,friendUserId)) {
					//Ϊ����
					titleStr="������Ϣ";
					addFriend=false;
					deleteFriend=true;
					updateUserName=true;
					updateUserNum=false;
					updateInfo=true;
				}
		
		
	    addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						close();
					}
		});
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 406);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headimage = new JLabel("");
		headimage.setBounds(10, 69, 60, 60);
		if (!objfriend.getFriend_icon().isEmpty()) {
			//headimage.setIcon(new ImageIcon(pathImage));
			headimage.setIcon(new ImageIcon(Client.class.getResource(objfriend.getFriend_icon())));
		}else
		{
			headimage.setIcon(new ImageIcon("image/2.jpg"));
		}
		contentPane.add(headimage);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 284, 33);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u597D\u53CB\u4FE1\u606F");
		if (!titleStr.trim().isEmpty()) {
			label.setText(titleStr);
		}
		label.setFont(new Font("΢���ź�", Font.BOLD, 20));
		label.setBounds(96, 0, 98, 33);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 67, 54, 15);
		contentPane.add(lblNewLabel_1);
	
		
		JLabel lblNewLabel_3 = new JLabel("QQ\u53F7:");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 104, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_5 = new JLabel("\u7535\u8BDD:");
		lblNewLabel_5.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 148, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("None");
		lblNewLabel_6.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_6.setBounds(141, 149, 133, 15);
		if (findsipone!=null) {
			lblNewLabel_6.setText(findsipone);
		}
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_7.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_7.setBounds(80, 195, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_8.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_8.setBounds(80, 237, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel user_postbox = new JLabel("None");
		user_postbox.setFont(new Font("΢���ź�", Font.BOLD, 15));
		user_postbox.setBounds(141, 196, 133, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id()+"@qq.com");
		}
		contentPane.add(user_postbox);
		
		JLabel user_IpAddress = new JLabel("None");
		user_IpAddress.setFont(new Font("΢���ź�", Font.BOLD, 15));
		user_IpAddress.setBounds(141, 238, 133, 15);
		if (!objfriend.getIpAddress().isEmpty()) {
			user_IpAddress.setText(objfriend.getIpAddress());
		}
		contentPane.add(user_IpAddress);
		
		JButton btn_add = new JButton("\u6DFB\u52A0\u597D\u53CB");
		if (addFriend) {
			btn_add.setVisible(true);
		}else
		{
			btn_add.setVisible(false);
		}
		btn_add.addActionListener(new ActionListener() {
			//��Ӻ���
			public void actionPerformed(ActionEvent e) {
				//��װ����
				Friend  field=new Friend();
				field.setUser_id1(userId);
//				System.out.println(userId+"-----sadfsgajdsa");
				field.setUser_id2(friendUserId);
				if (objUserDao.addFirend(field)) {
					 JOptionPane.showOptionDialog(null, "��ӳɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
//					 MainFrame.boolfinds=true;
					 
					 close();
					 
				}else
				{
					 JOptionPane.showOptionDialog(null, "���ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
				
			}
		});
		btn_add.setBounds(49, 268, 163, 23);
		contentPane.add(btn_add);
		
		JButton btn_remove = new JButton("\u5220\u9664\u597D\u53CB");
		if (deleteFriend) {
			btn_remove.setVisible(true);
		}else
		{
			btn_remove.setVisible(false);
		}
		btn_remove.addActionListener(new ActionListener() {
			//ɾ������
			public void actionPerformed(ActionEvent e) {
				//��װ����
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.deleteFirend(field)) {
					 JOptionPane.showOptionDialog(null, "ɾ���ɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
//					 MainFrame.boolfinds=true;
					 close();
				}else
				{
					 JOptionPane.showOptionDialog(null, "ɾ��ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
			}
		});
		btn_remove.setBounds(49, 301, 163, 23);
		contentPane.add(btn_remove);
		
		JButton btnSaveInfo = new JButton("\u4FDD\u5B58\u7528\u6237\u4FE1\u606F");
		btnSaveInfo.addActionListener(new ActionListener() {
			//�����û���Ϣ
			public void actionPerformed(ActionEvent e) {
				//��װ����
				User objUser=new User();
				objUser.setUser_id(user_section.getText().trim());
				objUser.setUser_name(user_name.getText().trim());
				boolean result=false;
				if (updateUserNum) {
					result=objUserDao.updateUserInfo(objUser,userId);
				}else
				{
					result=objUserDao.updateUserInfo(objUser,"");
				}
				//ʵ���޸�ҵ��
				if (result) {
					JOptionPane.showOptionDialog(null, "�޸ĳɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					//�û�����
					objUser.setState("0");
					objUser.setUser_id(userId);
					new UserDao().Changestatu(objUser);
					JOptionPane.showOptionDialog(null, "�����ϴ��������ɹ���������������!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					//�����û�״̬
					
					//ǿ���û�����
					System.exit(0);
				}else
				{
					JOptionPane.showOptionDialog(null, "�޸�ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
				}
			}
		});
		btnSaveInfo.setBounds(49, 334, 163, 23);
		contentPane.add(btnSaveInfo);
		
		user_name = new JTextField();
		if (!objfriend.getFriend_name().isEmpty()) {
			user_name.setText(objfriend.getFriend_name());
		}
		user_name.setBounds(124, 65, 150, 21);
		contentPane.add(user_name);
		user_name.setColumns(10);
		
		user_section = new JTextField();
		user_section.setBounds(124, 102, 150, 21);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_section.setText(objfriend.getFriend_id());
		}
		contentPane.add(user_section);
		user_section.setColumns(10);
		//�û��Կ����޸�
		if (updateInfo) {
			user_name.setEditable(updateUserName);	
			user_section.setEditable(updateUserNum);	
			btnSaveInfo.setVisible(true);
		}else {
			user_name.setEditable(false);	
			user_section.setEditable(false);	
			btnSaveInfo.setVisible(false);
		}
		
		
	}
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public AddFriendsFrame( Map<String, String> friend,String userid) throws UnknownHostException, SQLException {
		//��ȡ������Ϣ
//		this.MainFrameoneq=friendListPanel;
		String ipone=new UserDao().SelectIpone(userid);
		String friendIco=friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		/// ��ʼ����ǰFrame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		//��װ���Ѷ���
		if (objfriend==null) {
		 objfriend=new Friend(friendUserId, friendUserName, iconFile,InetAddress.getByName(Client.SERVER_IP).toString());	
		}
		
		//��ȡ��ǰ�û�ID
		this.userId=userid;
	    	//��ѯ���û��Ƿ�Ϊ�Լ��ĺ��Ѻ��Ƿ����Լ�
				if (objUserDao==null) {
					objUserDao=new UserDao();
				}
				if (userId.trim().equals(friendUserId)) {
					titleStr="������Ϣ";
					addFriend=false;
					deleteFriend=false;
					updateUserName=true;
					updateUserNum=true;
					updateInfo=true;
				}else
				{
					titleStr="İ����";
					addFriend=true;
					deleteFriend=false;
					updateUserName=false;
					updateUserNum=false;
					updateInfo=false;
				}
				if (objUserDao.selectFriends(userId,friendUserId)) {
					//Ϊ����
					titleStr="������Ϣ";
					addFriend=false;
					deleteFriend=true;
					updateUserName=true;
					updateUserNum=false;
					updateInfo=true;
				}
		
		
	    addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						close();
					}
		});
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 406);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headimage = new JLabel("");
		headimage.setBounds(10, 69, 60, 60);
		if (!objfriend.getFriend_icon().isEmpty()) {
			//headimage.setIcon(new ImageIcon(pathImage));
			headimage.setIcon(new ImageIcon(Client.class.getResource(objfriend.getFriend_icon())));
		}else
		{
			headimage.setIcon(new ImageIcon("image/2.jpg"));
		}
		contentPane.add(headimage);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 284, 33);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u597D\u53CB\u4FE1\u606F");
		if (!titleStr.trim().isEmpty()) {
			label.setText(titleStr);
		}
		label.setFont(new Font("΢���ź�", Font.BOLD, 20));
		label.setBounds(96, 0, 98, 33);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 67, 54, 15);
		contentPane.add(lblNewLabel_1);
	
		
		JLabel lblNewLabel_3 = new JLabel("QQ\u53F7:");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 104, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_5 = new JLabel("\u7535\u8BDD:");
		lblNewLabel_5.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 148, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("�绰");
		lblNewLabel_6.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_6.setBounds(141, 149, 133, 15);
		if (ipone!="None") {
			lblNewLabel_6.setText(ipone);
		}
	
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_7.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_7.setBounds(80, 195, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_8.setFont(new Font("΢���ź�", Font.BOLD, 15));
		lblNewLabel_8.setBounds(80, 237, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel user_postbox = new JLabel("None");
		user_postbox.setFont(new Font("΢���ź�", Font.BOLD, 15));
		user_postbox.setBounds(141, 196, 133, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id()+"@qq.com");
		}
		contentPane.add(user_postbox);
		
		JLabel user_IpAddress = new JLabel("None");
		user_IpAddress.setFont(new Font("΢���ź�", Font.BOLD, 15));
		user_IpAddress.setBounds(141, 238, 133, 15);
		if (!objfriend.getIpAddress().isEmpty()) {
			user_IpAddress.setText(objfriend.getIpAddress());
		}
		contentPane.add(user_IpAddress);
		
		JButton btn_add = new JButton("\u6DFB\u52A0\u597D\u53CB");
		if (addFriend) {
			btn_add.setVisible(true);
		}else
		{
			btn_add.setVisible(false);
		}
		btn_add.addActionListener(new ActionListener() {
			//��Ӻ���
			public void actionPerformed(ActionEvent e) {
				//��װ����
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.addFirend(field)) {
					System.out.println("������");
					 JOptionPane.showOptionDialog(null, "��ӳɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					 MainFrame.boolfinds=true;
					 close();
//					 MainFrameoneq.updateUI();
				}else
				{
					 JOptionPane.showOptionDialog(null, "���ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
				
			}
		});
		btn_add.setBounds(49, 268, 163, 23);
		contentPane.add(btn_add);
		
		JButton btn_remove = new JButton("\u5220\u9664\u597D\u53CB");
		if (deleteFriend) {
			btn_remove.setVisible(true);
		}else
		{
			btn_remove.setVisible(false);
		}
		btn_remove.addActionListener(new ActionListener() {
			//ɾ������
			public void actionPerformed(ActionEvent e) {
				//��װ����
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.deleteFirend(field)) {
					 JOptionPane.showOptionDialog(null, "ɾ���ɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					 MainFrame.boolfinds=true;
					 close();
//					 for (int i = 0; i < MainFrame.friends.size(); i++) {
//				        for (String key : MainFrame.friends.get(i).keySet()) {
//				            System.out.println("key= " + key + " and value= " + MainFrame.friends.get(i).get(key));
//				            if(MainFrame.friends.get(i).get(key).equals(friendUserId)){
//				            	MainFrame.friends.remove(i);
//				            	System.out.println("ɾ��������"+MainFrame.friends.size());
////				            	MainFrame.friendListPanel.updateUI();
////				            	MainFrame.friendListPanel.rem
////				            	MainFrame.friendListPanel.validate();
////				            	//�ػ����
////				            	MainFrame.friendListPanel.repaint();
//				            	break;
//				            }
//				        }
//					 }
					 
//					 System.out.println();1
				        
				        
				        
//					 for (int i = 0; i <  MainFrame.finsuserDele.size(); i++) {
//						 if(MainFrame.finsuserDele.get(i).getToolTipText().equals(friendUserId)){
//							 System.out.println("������---");
//							  MainFrame.finsuserDele.remove(i);
//							  MainFrame.friendListPanel.updateUI();
//							  System.out.println("ɾ��������");
//							  break;
//						 }
//							
//					}
					
					 
				}else
				{
					 JOptionPane.showOptionDialog(null, "ɾ��ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
			}
		});
		btn_remove.setBounds(49, 301, 163, 23);
		contentPane.add(btn_remove);
		
		JButton btnSaveInfo = new JButton("\u4FDD\u5B58\u7528\u6237\u4FE1\u606F");
		btnSaveInfo.addActionListener(new ActionListener() {
			//�����û���Ϣ
			public void actionPerformed(ActionEvent e) {
				//��װ����
				User objUser=new User();
				objUser.setUser_id(user_section.getText().trim());
				objUser.setUser_name(user_name.getText().trim());
				boolean result=false;
				if (updateUserNum) {
					result=objUserDao.updateUserInfo(objUser,userId);
				}else
				{
					result=objUserDao.updateUserInfo(objUser,"");
				}
				//ʵ���޸�ҵ��
				if (result) {
					JOptionPane.showOptionDialog(null, "�޸ĳɹ�!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					//�û�����
					objUser.setState("0");
					objUser.setUser_id(userId);
					new UserDao().Changestatu(objUser);
					JOptionPane.showOptionDialog(null, "�����ϴ��������ɹ���������������!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					//�����û�״̬
					
					//ǿ���û�����
					System.exit(0);
				}else
				{
					JOptionPane.showOptionDialog(null, "�޸�ʧ��!","��ʾ", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
				}
			}
		});
		btnSaveInfo.setBounds(49, 334, 163, 23);
		contentPane.add(btnSaveInfo);
		
		user_name = new JTextField();
		if (!objfriend.getFriend_name().isEmpty()) {
			user_name.setText(objfriend.getFriend_name());
		}
		user_name.setBounds(124, 65, 150, 21);
		contentPane.add(user_name);
		user_name.setColumns(10);
		
		user_section = new JTextField();
		user_section.setBounds(124, 102, 150, 21);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_section.setText(objfriend.getFriend_id());
		}
		contentPane.add(user_section);
		user_section.setColumns(10);
		//�û��Կ����޸�
		if (updateInfo) {
			user_name.setEditable(updateUserName);	
			user_section.setEditable(updateUserNum);	
			btnSaveInfo.setVisible(true);
		}else {
			user_name.setEditable(false);	
			user_section.setEditable(false);	
			btnSaveInfo.setVisible(false);
		}
		
		
	}
	
	private void close() {
		//�ͷŶ���
		objUserDao=null;
		this.setVisible(false);
	}
}
