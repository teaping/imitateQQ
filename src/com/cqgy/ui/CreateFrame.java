package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.UserDao;

public class CreateFrame extends JFrame {

	// 好友列表
	private List<Map<String, String>> friends;
	// 好友标签控件列表
	private List<JLabel> lblFriendList;
	// 群好友列表
	private List<String> ctreateList;
	//自己id
	private String useryouid;
	//好友名字
	private	List<String> friendUserNames ; 

	public CreateFrame(List<Map<String, String>> friends, List<JLabel> lblFriendList, String useryouid,String userName) throws HeadlessException {
		super();
		this.friends = friends;
		this.lblFriendList = lblFriendList;
		this.useryouid = useryouid;
		ctreateList = new ArrayList<String>();
		friendUserNames = new ArrayList<String>();

		JPanel panleone = new JPanel();
		panleone.setLayout(new BoxLayout(panleone, BoxLayout.Y_AXIS));

		lblFriendList = new ArrayList<JLabel>();
		// 初始化好友列表
		for (int i = 0; i < friends.size(); i++) {
			Map<String, String> friend = this.friends.get(i);
			String friendUserId = friend.get("user_id");
			String friendUserName = friend.get("user_name");
			String friendUserIcon = friend.get("user_icon");
			// 获得好友在线状态
			String friendUserOnline = friend.get("online");

			JLabel lblFriend = new JLabel(friendUserName);
			lblFriend.setToolTipText(friendUserId);
			String friendIconFile = "/resource/img/" + friendUserIcon + ".jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblFriend.setIcon(new ImageIcon(location));
			lblFriend.addMouseListener(new MouseListener() {
				int nums = 0;

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				//鼠标点击改变样式 并将存入数组
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					nums++;
					if (nums % 2 != 0) {
						lblFriend.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						ctreateList.add(lblFriend.getToolTipText());
						friendUserNames.add(friendUserName);
					} else {
						lblFriend.setBorder(null);
						for (int i=0;i<ctreateList.size();i++) {
							if (lblFriend.getToolTipText().equals(ctreateList.get(i))) {
								ctreateList.remove(i);
								friendUserNames.remove(i);
							}
						}
					}
				}
			});
			// 在线设置可用，离线设置不可用
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}

			// 添加到列表集合
			lblFriendList.add(lblFriend);

			// 添加到面板
			panleone.add(lblFriend);
		}
		//群名
		JPanel panlectname=new JPanel();
		panlectname.setLayout(new GridLayout(1,3));
		//提示
		JLabel labctname=new JLabel("         群名");
		
		//文本
		JTextField txtctname=new JTextField();
		
		//空白控件
		JLabel labkbe=new JLabel(  );
		
		panlectname.add(labctname);
		panlectname.add(txtctname);
		panlectname.add(labkbe);
		
		//创建按钮
		JButton butcjan=new JButton("创建");
		butcjan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean zj= new UserDao().CreateFinds(ctreateList,txtctname.getText(),useryouid,userName,friendUserNames);
				if(zj){
					dispose();
				}
				
			}
		});
		
	
		
		add(panlectname,BorderLayout.NORTH);
		add(panleone,BorderLayout.CENTER);
		add(butcjan,BorderLayout.SOUTH);
		

	}

	public CreateFrame() throws HeadlessException {
		super();
	}

}
