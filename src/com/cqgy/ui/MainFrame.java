package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import com.cqgy.bean.User;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.UserDao;
import com.cqgy.qq.Client;
import com.cqgy.util.ValidateUtil;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.Point;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField txtqq;
	private JPanel panel_1;
	private JLabel user_name;
	// ͷ���
	public static JLabel HeaderImg;
	JScrollPane scrollPane;
	// �Լ�id
	String useryouid = null;
	// �������촰��
	CharFrameTest charFrameTest = null;
	// Ⱥ����
	CreateFrameTeST createFrameTest = null;
	// �߳�����״̬
	private boolean isRunning = true;
	// �û���Ϣ
	private Map user;
	// �����б�
	public static List<Map<String, String>> friends;
	// Ⱥ�б�
	private List<Map<String, String>> creates;
	// Ⱥ��ǩ�ؼ��б�
	private List<JLabel> CreatesList;
	// ���ѱ�ǩ�ؼ��б�
	private List<JLabel> lblFriendList;
	User objuser;
	// ���Ѳ���
	public static FriendoperationFrame friendoperationFrame = null;
	private JLabel bottom;
	public static int number = 0;
	String userName;
	public static Thread SeeionTh = null;
	Information infomation = null;
	public static AvatarFrame avatarfrme = null;
	
	public static boolean boolfinds=false;
	public static List<JLabel> finsuserDele=new ArrayList<>();
	//ǩ��
	public static JLabel user_Signature=null;
	
	public static JPanel friendListPanel=null;

	/**
	 * Create the frame.
	 */
	public MainFrame(Map user) {
//		inherit in=new inherit(user,this);
//		Thread tone=new Thread(in);
//		tone.start();
		business(user);
	}

	public MainFrame() {
	}

	@SuppressWarnings("unchecked")
	private void business(Map user) {
		// ��ʼ����Ա����
		this.user = user;
		/// ��ʼ���û��б�
		this.friends = (List<Map<String, String>>) user.get("friends");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		String userId = (String) user.get("user_id");
		useryouid = userId;
		userName = (String) user.get("user_name");
		String userIcon = (String) user.get("user_icon");
		if (objuser == null) {
			objuser = new User();
		}
		objuser.setUser_icon(userIcon);
		objuser.setUser_name(userName);

		// ������ͼ
		createView(objuser);

		JPanel panel1 = new JPanel();
		scrollPane.setViewportView(panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		// �����б����
		 friendListPanel = new JPanel();
		
		
		panel1.add(friendListPanel);
		friendListPanel.setLayout(new GridLayout(50, 0, 0, 5));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 345, 140);
		contentPane.add(panel);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		HeaderImg = new JLabel();
		HeaderImg.addMouseListener(new MouseAdapter() {
			// ���ͷ�񵯳��Լ�����Ϣ
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userId);

				if (infomation == null) {
					SeeionThear seion = new SeeionThear(user_name, useryouid);
					SeeionTh = new Thread(seion);
					infomation = new Information(userId, user_name.getText());
					infomation.setSize(369, 300);
					infomation.setVisible(true);
					infomation.setLocationRelativeTo(null);// ��������Ļ�м���ʾ
					infomation.setResizable(false);
					infomation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				infomation = null;

			}

			// ����
			public void mouseEntered(MouseEvent e) {
				Point point1 = java.awt.MouseInfo.getPointerInfo().getLocation();
				// lblFriend.getLocation();)
				if (avatarfrme == null) {
					avatarfrme = new AvatarFrame((int) point1.getX(), (int) point1.getY(), useryouid);
					avatarfrme.setVisible(true);
				}
			}

			// // �Ƴ�
			// public void mouseExited(MouseEvent e) {
			// avatarfrme.close();
			// }
		});
		HeaderImg.setBounds(10, 49, 36, 36);
		panel.add(HeaderImg);

		user_name = new JLabel("None");
		user_name.setForeground(Color.WHITE);
		user_name.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		user_name.setBounds(56, 40, 69, 23);
		if (Information.numone != 0) {
			user_name.setText(Information.Name);
		}

		if (!objuser.getUser_name().trim().isEmpty()) {
			System.out.println(objuser.getUser_name());
			user_name.setText(objuser.getUser_name());
		}
		user_name.setOpaque(false);
		panel.add(user_name);
		//��ѯ����ǩ��
		String qxname=new UserDao().SelectAutograph(useryouid);
		user_Signature = new JLabel("None");
		user_Signature.setForeground(Color.WHITE);
		user_Signature.setBackground(Color.WHITE);
		user_Signature.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		user_Signature.setBounds(56, 62, 69, 23);
		if(user_Signature!=null){
			user_Signature.setText(qxname);
		}else{
			user_Signature.setText("None");
		}
		if (!objuser.getUser_icon().trim().isEmpty()) {
			String iconFile = String.format("/resource/img/%s.jpg", objuser.getUser_icon());
			HeaderImg.setIcon(new ImageIcon(MainFrame.class.getResource(iconFile)));
		}
		user_Signature.setOpaque(false);
		panel.add(user_Signature);

		txtqq = new JTextField();
		txtqq.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// ��ѯ�û���Ϣ
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// ������֤
					if (txtqq.getText().trim().isEmpty()) {
						JOptionPane.showOptionDialog(null, "�����QQ�Ų���Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}
					// ����������ʽ������֤
					if (!ValidateUtil.isNumber(txtqq.getText().trim())) {
						JOptionPane.showOptionDialog(null, "�����QQ�Ų����Ϲ���", "��ʾ", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}

					try {
						// ͨ���û�id��ȡ�û���Ϣ
					
						Map<String, String> objfriend = new UserDao().findById(txtqq.getText().trim());
						if (objfriend == null) {
							JOptionPane.showOptionDialog(null, "�û�������", "��ʾ", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							return;
						}
					// �׿�
//						String user_idaone=txtqq.getText().trim();
						txtqq.setText("");
						AddFriendsFrame addFriendsFrame = new AddFriendsFrame(objfriend, userId);
						addFriendsFrame.setVisible(true);
						

					} catch (UnknownHostException | SQLException e1) {
						// TODO Auto-generated catch block
						// �쳣����д�������־��������Ŀά��
						WriteErrorLog.SaveError(
								"��public class MainFrame �µ�private void business(Map user) ��public void keyPressed(KeyEvent e) �г����쳣,�쳣��Ϣ:"
										+ e.toString());
						e1.printStackTrace();
					}
				}

			}
		});

		txtqq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtqq.setEnabled(true);
				txtqq.setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtqq.setEnabled(false);
				txtqq.setOpaque(false);
			}
		});

		txtqq.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		txtqq.setForeground(Color.BLACK);
		txtqq.setBounds(0, 108, 345, 32);
		txtqq.setOpaque(false);
		panel.add(txtqq);
		txtqq.setBackground(Color.WHITE);
		txtqq.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 345, 140);
		lblNewLabel.setIcon(new ImageIcon("Image/340140timg.png"));
		panel.add(lblNewLabel);

		lblFriendList = new ArrayList<JLabel>();
		// Timer timer = new Timer();

		// ����һ����ʱ���ȣ��ӳ� 0���루Ҳ����������ʼִ�У����������ʣ� 1��

		// friendListPanel.removeAll();
		// for ( int i=lblFriendList.size()-1; i>=0;i--) {
		// lblFriendList.remove(i);
		// }
		// ��ʼ�������б�
		for (int i = 0; i < friends.size(); i++) {
//			System.out.println(i+"����");
//	        for (String key : friends.get(i).keySet()) {
//	            System.out.println("key= " + key + " and value= " + friends.get(i).get(key));
//	        }
//		
//			
			Map<String, String> friend = friends.get(i);
//			System.out.println(friend.get("user_id")+"sdfgbdsfgfriend.get()");
			String friendUserId = friend.get("user_id");
			String friendUserName = friend.get("user_name");
			String friendUserIcon = friend.get("user_icon");
			// ��ú�������״̬
			String friendUserOnline = friend.get("online");

			JLabel lblFriend = new JLabel(friendUserName);
			//��ӿؼ���ȥ
			finsuserDele.add(lblFriend);
			lblFriend.setToolTipText(friendUserId);
			String friendIconFile = "/resource/img/" + friendUserIcon + ".jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblFriend.setIcon(new ImageIcon(location));

			// �������ÿ��ã��������ò�����
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}
			lblFriend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// �û�ͼ��˫�����ʱ��ʾ�Ի���
					if (e.getClickCount() == 2) {
						try {
							// �ö����Ƿ񴴽���,��ֹ��δ���ͬ����
							if (charFrameTest == null) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							// ��ȡ����ͼƬ��ID�����Ѿ��򿪵�ID�Ƿ�һ�£�һ�±�ʾ�ô����ѱ��������ٴ�
							if (charFrameTest.friendUserId != lblFriend.getToolTipText()) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							charFrameTest.setVisible(true);
							isRunning = false;
						} catch (UnknownHostException e1) {
							// �쳣����д�������־��������Ŀά��
							WriteErrorLog.SaveError(
									"��public class MainFrame �µ�private void business(Map user) ��lblFriend.addMouseListener(new MouseAdapter() �г����쳣,�쳣��Ϣ:"
											+ e.toString());
							e1.printStackTrace();
						}

					}

					// ����Ҽ�
					if (e.isMetaDown()) {

						// ��ȡx,y
						// jPopupMenu1.show(this, e.getX(), e.getX());
						Point point = java.awt.MouseInfo.getPointerInfo().getLocation(); // ����Ǽ��㵱ǰFream�����꣬������getLocation()(Point
																							// p
																							// =
																							// lblFriend.getLocation();)
						if (friendoperationFrame == null) {
							friendoperationFrame = new FriendoperationFrame((int) point.getX(), (int) point.getY(),
									friend);

						}
						if (FriendoperationFrame.friendUserId != lblFriend.getToolTipText()) {
							friendoperationFrame = new FriendoperationFrame((int) point.getX(), (int) point.getY(),
									friend);
						}
						friendoperationFrame.setVisible(true);

					}

				}
			});

			// ��ӵ��б���
			lblFriendList.add(lblFriend);
			// ��ӵ����
			friendListPanel.add(lblFriend);
			// friendListPanel.repaint();
		}
		// }
		// }, 0L, 5000L);

		// timer.cancel(); // �رռ�ʱ��

		creates = new UserDao().SelectCreate(useryouid);
		CreatesList = new ArrayList<JLabel>();
		System.out.println(creates.size() + "---duos");
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> create = creates.get(i);
			String createName = create.get("ctreaName");
			String creatQzId = create.get("zqid");
			JLabel lblcreate = new JLabel(createName + "(Ⱥ)");

			lblcreate.setToolTipText(creatQzId);
			String friendIconFile = "/resource/img/43.jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblcreate.setIcon(new ImageIcon(location));

			lblcreate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// if(number!=0){
					// CreateFrameTeST.s.notify();
					// }
					//
					// �û�ͼ��˫�����ʱ��ʾ�Ի���
					if (e.getClickCount() == 2) {

						if (createFrameTest != null) {
							createFrameTest = null;
						}

						// �ö����Ƿ񴴽���,��ֹ��δ���ͬ����
						if (createFrameTest == null) {
							createFrameTest = new CreateFrameTeST(MainFrame.this, userName, createName, user);
						}
						// ��ȡ����ͼƬ��ID�����Ѿ��򿪵�ID�Ƿ�һ�£�һ�±�ʾ�ô����ѱ��������ٴ�
						// if
						// (createFrameTest.getTitle()+"Ⱥ"==lblcreate.getToolTipText())
						// {
						// createFrameTest = new
						// CreateFrameTeST(MainFrame.this,userName,createName,user);
						// }
						createFrameTest.setVisible(true);
						// isRunning = false;
					}
				}
			});

			CreatesList.add(lblcreate);
			// ��ӵ����
			friendListPanel.add(lblcreate);
		}

		// ע�ᴰ���¼�
		addWindowListener(new WindowAdapter() {
			// �������ڹرհ�ťʱ����
			public void windowClosing(WindowEvent e) {

				// ��ǰ�û�����
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGOUT);
				jsonObj.put("user_id", userId);
				byte[] b = jsonObj.toString().getBytes();

				InetAddress address;
				try {
					address = InetAddress.getByName(Client.SERVER_IP);
					// ����DatagramPacket����
					DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
					// ����
					Client.socket.send(packet);

				} catch (IOException e1) {
					WriteErrorLog
							.SaveError("��class FriendsFrame�µ�public FriendsFrame(Map user) �г����쳣,�쳣��Ϣ:" + e.toString());
					e1.printStackTrace();
				}

				// �˳�ϵͳ
				int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�رյ�ǰ����?", "����", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
				if (n == JOptionPane.YES_OPTION) {
					// ���û���Ϣ��Ϊ����
					User objuser = new User();
					objuser.setUser_id((String) user.get("user_id"));
					objuser.setState("0");
					System.out.println(objuser.getUser_id() + ";" + objuser.getState());
					new UserDao().Changestatu(objuser);
					System.exit(0);
				}
			}
		});

		// ����������Ϣ���߳�
		resetThread();
	}

	@SuppressWarnings("unchecked")
	public void busdsad(Map user) {
		// ��ʼ����Ա����
		this.user = user;
		/// ��ʼ���û��б�
		this.friends = (List<Map<String, String>>) user.get("friends");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		String userId = (String) user.get("user_id");
		useryouid = userId;
		userName = (String) user.get("user_name");
		String userIcon = (String) user.get("user_icon");
		if (objuser == null) {
			objuser = new User();
		}
		objuser.setUser_icon(userIcon);
		objuser.setUser_name(userName);

		// ������ͼ
		createView(objuser);

		JPanel panel1 = new JPanel();
		scrollPane.setViewportView(panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		// �����б����
		JPanel friendListPanel = new JPanel();
		panel1.add(friendListPanel);
		friendListPanel.setLayout(new GridLayout(50, 0, 0, 5));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 345, 140);
		contentPane.add(panel);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		HeaderImg = new JLabel();
		HeaderImg.addMouseListener(new MouseAdapter() {
			// ���ͷ�񵯳��Լ�����Ϣ
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userId);

				if (infomation == null) {
					SeeionThear seion = new SeeionThear(user_name, useryouid);
					SeeionTh = new Thread(seion);
					infomation = new Information(userId, user_name.getText());
					infomation.setSize(369, 300);
					infomation.setVisible(true);
					infomation.setLocationRelativeTo(null);// ��������Ļ�м���ʾ
					infomation.setResizable(false);
					infomation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				infomation = null;

			}

			// ����
			public void mouseEntered(MouseEvent e) {
				Point point1 = java.awt.MouseInfo.getPointerInfo().getLocation();
				// lblFriend.getLocation();)
				if (avatarfrme == null) {
					avatarfrme = new AvatarFrame((int) point1.getX(), (int) point1.getY(), useryouid);
					avatarfrme.setVisible(true);
				}
			}

			// // �Ƴ�
			// public void mouseExited(MouseEvent e) {
			// avatarfrme.close();
			// }
		});
		HeaderImg.setBounds(10, 49, 36, 36);
		panel.add(HeaderImg);

		user_name = new JLabel("None");
		user_name.setForeground(Color.WHITE);
		user_name.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		user_name.setBounds(56, 40, 69, 23);
		if (Information.numone != 0) {
			user_name.setText(Information.Name);
		}

		if (!objuser.getUser_name().trim().isEmpty()) {
			System.out.println(objuser.getUser_name());
			user_name.setText(objuser.getUser_name());
		}
		user_name.setOpaque(false);
		panel.add(user_name);

		JLabel user_Signature = new JLabel("None");
		user_Signature.setForeground(Color.WHITE);
		user_Signature.setBackground(Color.WHITE);
		user_Signature.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		user_Signature.setBounds(56, 62, 69, 23);
		if (!objuser.getUser_icon().trim().isEmpty()) {
			String iconFile = String.format("/resource/img/%s.jpg", objuser.getUser_icon());
			HeaderImg.setIcon(new ImageIcon(MainFrame.class.getResource(iconFile)));
		}
		user_Signature.setOpaque(false);
		panel.add(user_Signature);

		txtqq = new JTextField();
		txtqq.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// ��ѯ�û���Ϣ
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// ������֤
					if (txtqq.getText().trim().isEmpty()) {
						JOptionPane.showOptionDialog(null, "�����QQ�Ų���Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}
					// ����������ʽ������֤
					if (!ValidateUtil.isNumber(txtqq.getText().trim())) {
						JOptionPane.showOptionDialog(null, "�����QQ�Ų����Ϲ���", "��ʾ", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}

					try {
						// ͨ���û�id��ȡ�û���Ϣ

						Map<String, String> objfriend = new UserDao().findById(txtqq.getText().trim());
						if (objfriend == null) {
							JOptionPane.showOptionDialog(null, "�û�������", "��ʾ", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							return;
						}
						// �׿�
						txtqq.setText("");
						AddFriendsFrame addFriendsFrame = new AddFriendsFrame(objfriend, userId);
						addFriendsFrame.setVisible(true);

					} catch (UnknownHostException | SQLException e1) {
						// TODO Auto-generated catch block
						// �쳣����д�������־��������Ŀά��
						WriteErrorLog.SaveError(
								"��public class MainFrame �µ�private void business(Map user) ��public void keyPressed(KeyEvent e) �г����쳣,�쳣��Ϣ:"
										+ e.toString());
						e1.printStackTrace();
					}
				}

			}
		});

		txtqq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtqq.setEnabled(true);
				txtqq.setOpaque(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txtqq.setEnabled(false);
				txtqq.setOpaque(false);
			}
		});

		txtqq.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		txtqq.setForeground(Color.BLACK);
		txtqq.setBounds(0, 108, 345, 32);
		txtqq.setOpaque(false);
		panel.add(txtqq);
		txtqq.setBackground(Color.WHITE);
		txtqq.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 345, 140);
		lblNewLabel.setIcon(new ImageIcon("Image/340140timg.png"));
		panel.add(lblNewLabel);

		lblFriendList = new ArrayList<JLabel>();
		// Timer timer = new Timer();

		// ����һ����ʱ���ȣ��ӳ� 0���루Ҳ����������ʼִ�У����������ʣ� 1��

		// friendListPanel.removeAll();
		// for ( int i=lblFriendList.size()-1; i>=0;i--) {
		// lblFriendList.remove(i);
		// }
		// ��ʼ�������б�
		for (int i = 0; i < friends.size(); i++) {
			Map<String, String> friend = friends.get(i);
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

			// �������ÿ��ã��������ò�����
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}
			lblFriend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// �û�ͼ��˫�����ʱ��ʾ�Ի���
					if (e.getClickCount() == 2) {
						try {
							// �ö����Ƿ񴴽���,��ֹ��δ���ͬ����
							if (charFrameTest == null) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							// ��ȡ����ͼƬ��ID�����Ѿ��򿪵�ID�Ƿ�һ�£�һ�±�ʾ�ô����ѱ��������ٴ�
							if (charFrameTest.friendUserId != lblFriend.getToolTipText()) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							charFrameTest.setVisible(true);
							isRunning = false;
						} catch (UnknownHostException e1) {
							// �쳣����д�������־��������Ŀά��
							WriteErrorLog.SaveError(
									"��public class MainFrame �µ�private void business(Map user) ��lblFriend.addMouseListener(new MouseAdapter() �г����쳣,�쳣��Ϣ:"
											+ e.toString());
							e1.printStackTrace();
						}

					}

					// ����Ҽ�
					if (e.isMetaDown()) {

						// ��ȡx,y
						// jPopupMenu1.show(this, e.getX(), e.getX());
						Point point = java.awt.MouseInfo.getPointerInfo().getLocation(); // ����Ǽ��㵱ǰFream�����꣬������getLocation()(Point
																							// p
																							// =
																							// lblFriend.getLocation();)
						if (friendoperationFrame == null) {
							friendoperationFrame = new FriendoperationFrame((int) point.getX(), (int) point.getY(),
									friend);

						}
						if (FriendoperationFrame.friendUserId != lblFriend.getToolTipText()) {
							friendoperationFrame = new FriendoperationFrame((int) point.getX(), (int) point.getY(),
									friend);
						}
						friendoperationFrame.setVisible(true);

					}

				}
			});

			// ��ӵ��б���
			lblFriendList.add(lblFriend);
			// ��ӵ����
			friendListPanel.add(lblFriend);
			// friendListPanel.repaint();
		}
		// }
		// }, 0L, 5000L);

		// timer.cancel(); // �رռ�ʱ��

		creates = new UserDao().SelectCreate(useryouid);
		CreatesList = new ArrayList<JLabel>();
		System.out.println(creates.size() + "---duos");
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> create = creates.get(i);
			String createName = create.get("ctreaName");
			String creatQzId = create.get("zqid");
			JLabel lblcreate = new JLabel(createName + "(Ⱥ)");

			lblcreate.setToolTipText(creatQzId);
			String friendIconFile = "/resource/img/43.jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblcreate.setIcon(new ImageIcon(location));

			lblcreate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// if(number!=0){
					// CreateFrameTeST.s.notify();
					// }
					//
					// �û�ͼ��˫�����ʱ��ʾ�Ի���
					if (e.getClickCount() == 2) {

						if (createFrameTest != null) {
							createFrameTest = null;
						}

						// �ö����Ƿ񴴽���,��ֹ��δ���ͬ����
						if (createFrameTest == null) {
							createFrameTest = new CreateFrameTeST(MainFrame.this, userName, createName, user);
						}
						// ��ȡ����ͼƬ��ID�����Ѿ��򿪵�ID�Ƿ�һ�£�һ�±�ʾ�ô����ѱ��������ٴ�
						// if
						// (createFrameTest.getTitle()+"Ⱥ"==lblcreate.getToolTipText())
						// {
						// createFrameTest = new
						// CreateFrameTeST(MainFrame.this,userName,createName,user);
						// }
						createFrameTest.setVisible(true);
						// isRunning = false;
					}
				}
			});

			CreatesList.add(lblcreate);
			// ��ӵ����
			friendListPanel.add(lblcreate);
		}

		// ע�ᴰ���¼�
		addWindowListener(new WindowAdapter() {
			// �������ڹرհ�ťʱ����
			public void windowClosing(WindowEvent e) {

				// ��ǰ�û�����
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGOUT);
				jsonObj.put("user_id", userId);
				byte[] b = jsonObj.toString().getBytes();

				InetAddress address;
				try {
					address = InetAddress.getByName(Client.SERVER_IP);
					// ����DatagramPacket����
					DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
					// ����
					Client.socket.send(packet);

				} catch (IOException e1) {
					WriteErrorLog
							.SaveError("��class FriendsFrame�µ�public FriendsFrame(Map user) �г����쳣,�쳣��Ϣ:" + e.toString());
					e1.printStackTrace();
				}

				// �˳�ϵͳ
				int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�رյ�ǰ����?", "����", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
				if (n == JOptionPane.YES_OPTION) {
					// ���û���Ϣ��Ϊ����
					User objuser = new User();
					objuser.setUser_id((String) user.get("user_id"));
					objuser.setState("0");
					System.out.println(objuser.getUser_id() + ";" + objuser.getState());
					new UserDao().Changestatu(objuser);
					System.exit(0);
				}
			}
		});

		// ����������Ϣ���߳�
		resetThread();
	}

	

	private void createView(User objuser) {
		// �����С���ܸı�
		this.setResizable(false);
		setTitle("QQ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 698);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 608, 345, 61);
		panel_1.setBackground(new Color(198, 211, 228));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		bottom = new JLabel();
		bottom.setBounds(0, 0, 345, 61);
		bottom.setIcon(new ImageIcon("Image/20200622114000.png"));
		panel_1.add(bottom);

		JButton btnjq = new JButton("��Ⱥ");
		btnjq.setBounds(0, 0, 60, 30);
		btnjq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CreateFrame create = new CreateFrame(friends, lblFriendList, useryouid, userName);
				create.setBounds(0, 0, 250, 500);
				create.setVisible(true);

				create.setResizable(false);
				create.setLocationRelativeTo(null);
				create.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		bottom.add(btnjq);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 140, 345, 469);
		contentPane.add(scrollPane);
		close();
	}

	public void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	public void run() {
		// ׼��һ��������
		byte[] buffer = new byte[1024];
		while (isRunning) {

			try {
				InetAddress address = InetAddress.getByName(Client.SERVER_IP);
				/* �������ݱ� */
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				// ��ʼ����
				Client.socket.receive(packet);
				// �������ݳ���
				int len = packet.getLength();
				String str = new String(buffer, 0, len);

				System.out.println("�ͻ��ˣ�  " + str);

				JSONObject jsonObj = new JSONObject(str);
				String userId = (String) jsonObj.get("user_id");
				String online = (String) jsonObj.get("online");
				// ˢ�º����б�
				refreshFriendList(userId, online);

			} catch (Exception e) {
			}
		}
	}

	// ˢ�º����б�
	public void refreshFriendList(String userId, String online) {
		// ��ʼ�������б�

		for (JLabel lblFriend : lblFriendList) {
			// �ж��û�Id�Ƿ�һ��
			if (userId.equals(lblFriend.getToolTipText())) {
				if (online.equals("1")) {

					lblFriend.setEnabled(true);
				} else {
					lblFriend.setEnabled(false);
				}
			}
		}
	}

	// ��������������Ϣ���߳�
	public void resetThread() {
		isRunning = true;
		// ������Ϣ���߳�
		Thread receiveMessageThread = new Thread(this);
		// ����������Ϣ�߳�
		receiveMessageThread.start();
	}
}
