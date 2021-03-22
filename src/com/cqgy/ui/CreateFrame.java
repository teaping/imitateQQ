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

	// �����б�
	private List<Map<String, String>> friends;
	// ���ѱ�ǩ�ؼ��б�
	private List<JLabel> lblFriendList;
	// Ⱥ�����б�
	private List<String> ctreateList;
	//�Լ�id
	private String useryouid;
	//��������
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
		// ��ʼ�������б�
		for (int i = 0; i < friends.size(); i++) {
			Map<String, String> friend = this.friends.get(i);
			String friendUserId = friend.get("user_id");
			String friendUserName = friend.get("user_name");
			String friendUserIcon = friend.get("user_icon");
			// ��ú�������״̬
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

				//������ı���ʽ ������������
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
			// �������ÿ��ã��������ò�����
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}

			// ��ӵ��б���
			lblFriendList.add(lblFriend);

			// ��ӵ����
			panleone.add(lblFriend);
		}
		//Ⱥ��
		JPanel panlectname=new JPanel();
		panlectname.setLayout(new GridLayout(1,3));
		//��ʾ
		JLabel labctname=new JLabel("         Ⱥ��");
		
		//�ı�
		JTextField txtctname=new JTextField();
		
		//�հ׿ؼ�
		JLabel labkbe=new JLabel(  );
		
		panlectname.add(labctname);
		panlectname.add(txtctname);
		panlectname.add(labkbe);
		
		//������ť
		JButton butcjan=new JButton("����");
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
