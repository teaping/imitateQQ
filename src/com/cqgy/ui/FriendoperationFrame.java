package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cqgy.method.UserDao;

import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.util.Map;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendoperationFrame extends JFrame {

	  
		// 聊天好友用户Id
		public static  String friendUserId;
	   private JPanel contentPane;
        //好友信息
	   public static FriendsInfoFream  friendsInfoFream;
	   


	/**
	 * Create the frame.
	 */
	public FriendoperationFrame(int x,int y, Map<String, String> friend) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}

		});
		
		//获取好友ID
		this.friendUserId = friend.get("user_id");
		// 窗体大小不能改变
	    this.setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(x, y, 170, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(0, 0, 154, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6587\u4EF6\u4F20\u8F93");
		btnNewButton_1.addActionListener(new ActionListener() {
			//修改当前用户信息//找回密码
			public void actionPerformed(ActionEvent e) {
				//new  MainFrame(friend).close();
				close();
				FiletransferMainFraem  filetransferMainFraem=new FiletransferMainFraem();
				filetransferMainFraem.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(0, 22, 154, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u67E5\u770B\u8D44\u6599");
		btnNewButton_2.addActionListener(new ActionListener() {
			//查看资料
			public void actionPerformed(ActionEvent e) {
					try {
						if (friendsInfoFream==null) {
							friendsInfoFream=new FriendsInfoFream(friend);	
						}
						friendsInfoFream.setVisible(true);
						close();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(0, 43, 154, 23);
		contentPane.add(btnNewButton_2);
	}
	
	private void close() {
		MainFrame.friendoperationFrame=null;
		this.setVisible(false);
	}
}
