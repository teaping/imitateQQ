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
    //创建用户业务类
	private UserDao objUserDao;
	// 当前用户Id
	private String userId;
	// 聊天好友用户Id
	public String friendUserId;
	// 聊天好友用户名
	private String friendUserName;
	//添加好友
	private boolean addFriend=false;
	//删除好友
	private boolean deleteFriend=false;
	//修改用户姓名【备注】
	private boolean updateUserName=false;
	//修改用户账号【qq号】
	private boolean updateUserNum=false;
	//修改用户信息
	private boolean updateInfo=false;
//	public static JPanel MainFrameoneq=null;
	//朋友对象
		private Friend objfriend;
		private JTextField user_name;
		private JTextField user_section;
	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 * @throws SQLException 
	 */
		
		
		
	public AddFriendsFrame( Map<String, String> friend,String userid,String findsipone) throws UnknownHostException, SQLException {
		//获取好友信息
		String friendIco=friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		/// 初始化当前Frame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		//封装朋友对象
		if (objfriend==null) {
		 objfriend=new Friend(friendUserId, friendUserName, iconFile,InetAddress.getByName(Client.SERVER_IP).toString());	
		}
		
		//获取当前用户ID
		this.userId=userid;
	    	//查询该用户是否为自己的好友和是否是自己
				if (objUserDao==null) {
					objUserDao=new UserDao();
				}
				if (userId.trim().equals(friendUserId)) {
					titleStr="个人信息";
					addFriend=false;
					deleteFriend=false;
					updateUserName=true;
					updateUserNum=true;
					updateInfo=true;
				}else
				{
					titleStr="陌生人";
					addFriend=true;
					deleteFriend=false;
					updateUserName=false;
					updateUserNum=false;
					updateInfo=false;
				}
				if (objUserDao.selectFriends(userId,friendUserId)) {
					//为好友
					titleStr="好友信息";
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
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label.setBounds(96, 0, 98, 33);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 67, 54, 15);
		contentPane.add(lblNewLabel_1);
	
		
		JLabel lblNewLabel_3 = new JLabel("QQ\u53F7:");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 104, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_5 = new JLabel("\u7535\u8BDD:");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 148, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("None");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_6.setBounds(141, 149, 133, 15);
		if (findsipone!=null) {
			lblNewLabel_6.setText(findsipone);
		}
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_7.setBounds(80, 195, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_8.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_8.setBounds(80, 237, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel user_postbox = new JLabel("None");
		user_postbox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		user_postbox.setBounds(141, 196, 133, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id()+"@qq.com");
		}
		contentPane.add(user_postbox);
		
		JLabel user_IpAddress = new JLabel("None");
		user_IpAddress.setFont(new Font("微软雅黑", Font.BOLD, 15));
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
			//添加好友
			public void actionPerformed(ActionEvent e) {
				//封装对象
				Friend  field=new Friend();
				field.setUser_id1(userId);
//				System.out.println(userId+"-----sadfsgajdsa");
				field.setUser_id2(friendUserId);
				if (objUserDao.addFirend(field)) {
					 JOptionPane.showOptionDialog(null, "添加成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
//					 MainFrame.boolfinds=true;
					 
					 close();
					 
				}else
				{
					 JOptionPane.showOptionDialog(null, "添加失败!","提示", JOptionPane.PLAIN_MESSAGE,
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
			//删除好友
			public void actionPerformed(ActionEvent e) {
				//封装对象
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.deleteFirend(field)) {
					 JOptionPane.showOptionDialog(null, "删除成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
//					 MainFrame.boolfinds=true;
					 close();
				}else
				{
					 JOptionPane.showOptionDialog(null, "删除失败!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
			}
		});
		btn_remove.setBounds(49, 301, 163, 23);
		contentPane.add(btn_remove);
		
		JButton btnSaveInfo = new JButton("\u4FDD\u5B58\u7528\u6237\u4FE1\u606F");
		btnSaveInfo.addActionListener(new ActionListener() {
			//保存用户信息
			public void actionPerformed(ActionEvent e) {
				//封装对象
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
				//实现修改业务
				if (result) {
					JOptionPane.showOptionDialog(null, "修改成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					//用户下线
					objUser.setState("0");
					objUser.setUser_id(userId);
					new UserDao().Changestatu(objUser);
					JOptionPane.showOptionDialog(null, "数据上传服务器成功请重新启动程序!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					//保存用户状态
					
					//强制用户下线
					System.exit(0);
				}else
				{
					JOptionPane.showOptionDialog(null, "修改失败!","提示", JOptionPane.PLAIN_MESSAGE,
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
		//用户试考可修改
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
		//获取好友信息
//		this.MainFrameoneq=friendListPanel;
		String ipone=new UserDao().SelectIpone(userid);
		String friendIco=friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		/// 初始化当前Frame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		//封装朋友对象
		if (objfriend==null) {
		 objfriend=new Friend(friendUserId, friendUserName, iconFile,InetAddress.getByName(Client.SERVER_IP).toString());	
		}
		
		//获取当前用户ID
		this.userId=userid;
	    	//查询该用户是否为自己的好友和是否是自己
				if (objUserDao==null) {
					objUserDao=new UserDao();
				}
				if (userId.trim().equals(friendUserId)) {
					titleStr="个人信息";
					addFriend=false;
					deleteFriend=false;
					updateUserName=true;
					updateUserNum=true;
					updateInfo=true;
				}else
				{
					titleStr="陌生人";
					addFriend=true;
					deleteFriend=false;
					updateUserName=false;
					updateUserNum=false;
					updateInfo=false;
				}
				if (objUserDao.selectFriends(userId,friendUserId)) {
					//为好友
					titleStr="好友信息";
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
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label.setBounds(96, 0, 98, 33);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 67, 54, 15);
		contentPane.add(lblNewLabel_1);
	
		
		JLabel lblNewLabel_3 = new JLabel("QQ\u53F7:");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 104, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_5 = new JLabel("\u7535\u8BDD:");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 148, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("电话");
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_6.setBounds(141, 149, 133, 15);
		if (ipone!="None") {
			lblNewLabel_6.setText(ipone);
		}
	
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_7.setBounds(80, 195, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_8.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_8.setBounds(80, 237, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel user_postbox = new JLabel("None");
		user_postbox.setFont(new Font("微软雅黑", Font.BOLD, 15));
		user_postbox.setBounds(141, 196, 133, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id()+"@qq.com");
		}
		contentPane.add(user_postbox);
		
		JLabel user_IpAddress = new JLabel("None");
		user_IpAddress.setFont(new Font("微软雅黑", Font.BOLD, 15));
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
			//添加好友
			public void actionPerformed(ActionEvent e) {
				//封装对象
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.addFirend(field)) {
					System.out.println("建立了");
					 JOptionPane.showOptionDialog(null, "添加成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					 MainFrame.boolfinds=true;
					 close();
//					 MainFrameoneq.updateUI();
				}else
				{
					 JOptionPane.showOptionDialog(null, "添加失败!","提示", JOptionPane.PLAIN_MESSAGE,
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
			//删除好友
			public void actionPerformed(ActionEvent e) {
				//封装对象
				Friend  field=new Friend();
				field.setUser_id1(userId);
				field.setUser_id2(friendUserId);
				if (objUserDao.deleteFirend(field)) {
					 JOptionPane.showOptionDialog(null, "删除成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					 MainFrame.boolfinds=true;
					 close();
//					 for (int i = 0; i < MainFrame.friends.size(); i++) {
//				        for (String key : MainFrame.friends.get(i).keySet()) {
//				            System.out.println("key= " + key + " and value= " + MainFrame.friends.get(i).get(key));
//				            if(MainFrame.friends.get(i).get(key).equals(friendUserId)){
//				            	MainFrame.friends.remove(i);
//				            	System.out.println("删除建立了"+MainFrame.friends.size());
////				            	MainFrame.friendListPanel.updateUI();
////				            	MainFrame.friendListPanel.rem
////				            	MainFrame.friendListPanel.validate();
////				            	//重绘组件
////				            	MainFrame.friendListPanel.repaint();
//				            	break;
//				            }
//				        }
//					 }
					 
//					 System.out.println();1
				        
				        
				        
//					 for (int i = 0; i <  MainFrame.finsuserDele.size(); i++) {
//						 if(MainFrame.finsuserDele.get(i).getToolTipText().equals(friendUserId)){
//							 System.out.println("进来了---");
//							  MainFrame.finsuserDele.remove(i);
//							  MainFrame.friendListPanel.updateUI();
//							  System.out.println("删除建立了");
//							  break;
//						 }
//							
//					}
					
					 
				}else
				{
					 JOptionPane.showOptionDialog(null, "删除失败!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
				}
			}
		});
		btn_remove.setBounds(49, 301, 163, 23);
		contentPane.add(btn_remove);
		
		JButton btnSaveInfo = new JButton("\u4FDD\u5B58\u7528\u6237\u4FE1\u606F");
		btnSaveInfo.addActionListener(new ActionListener() {
			//保存用户信息
			public void actionPerformed(ActionEvent e) {
				//封装对象
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
				//实现修改业务
				if (result) {
					JOptionPane.showOptionDialog(null, "修改成功!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);	
					//用户下线
					objUser.setState("0");
					objUser.setUser_id(userId);
					new UserDao().Changestatu(objUser);
					JOptionPane.showOptionDialog(null, "数据上传服务器成功请重新启动程序!","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					//保存用户状态
					
					//强制用户下线
					System.exit(0);
				}else
				{
					JOptionPane.showOptionDialog(null, "修改失败!","提示", JOptionPane.PLAIN_MESSAGE,
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
		//用户试考可修改
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
		//释放对象
		objUserDao=null;
		this.setVisible(false);
	}
}
