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
	// 头像框
	public static JLabel HeaderImg;
	JScrollPane scrollPane;
	// 自己id
	String useryouid = null;
	// 好友聊天窗体
	CharFrameTest charFrameTest = null;
	// 群聊天
	CreateFrameTeST createFrameTest = null;
	// 线程运行状态
	private boolean isRunning = true;
	// 用户信息
	private Map user;
	// 好友列表
	public static List<Map<String, String>> friends;
	// 群列表
	private List<Map<String, String>> creates;
	// 群标签控件列表
	private List<JLabel> CreatesList;
	// 好友标签控件列表
	private List<JLabel> lblFriendList;
	User objuser;
	// 好友操作
	public static FriendoperationFrame friendoperationFrame = null;
	private JLabel bottom;
	public static int number = 0;
	String userName;
	public static Thread SeeionTh = null;
	Information infomation = null;
	public static AvatarFrame avatarfrme = null;
	
	public static boolean boolfinds=false;
	public static List<JLabel> finsuserDele=new ArrayList<>();
	//签名
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
		// 初始化成员变量
		this.user = user;
		/// 初始化用户列表
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

		// 创建视图
		createView(objuser);

		JPanel panel1 = new JPanel();
		scrollPane.setViewportView(panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		// 好友列表面板
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
			// 点击头像弹出自己的信息
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userId);

				if (infomation == null) {
					SeeionThear seion = new SeeionThear(user_name, useryouid);
					SeeionTh = new Thread(seion);
					infomation = new Information(userId, user_name.getText());
					infomation.setSize(369, 300);
					infomation.setVisible(true);
					infomation.setLocationRelativeTo(null);// 窗口在屏幕中间显示
					infomation.setResizable(false);
					infomation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				infomation = null;

			}

			// 移入
			public void mouseEntered(MouseEvent e) {
				Point point1 = java.awt.MouseInfo.getPointerInfo().getLocation();
				// lblFriend.getLocation();)
				if (avatarfrme == null) {
					avatarfrme = new AvatarFrame((int) point1.getX(), (int) point1.getY(), useryouid);
					avatarfrme.setVisible(true);
				}
			}

			// // 移出
			// public void mouseExited(MouseEvent e) {
			// avatarfrme.close();
			// }
		});
		HeaderImg.setBounds(10, 49, 36, 36);
		panel.add(HeaderImg);

		user_name = new JLabel("None");
		user_name.setForeground(Color.WHITE);
		user_name.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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
		//查询个性签名
		String qxname=new UserDao().SelectAutograph(useryouid);
		user_Signature = new JLabel("None");
		user_Signature.setForeground(Color.WHITE);
		user_Signature.setBackground(Color.WHITE);
		user_Signature.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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

			// 查询用户信息
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 数据验证
					if (txtqq.getText().trim().isEmpty()) {
						JOptionPane.showOptionDialog(null, "输入的QQ号不能为空", "提示", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}
					// 采用正则表达式进行验证
					if (!ValidateUtil.isNumber(txtqq.getText().trim())) {
						JOptionPane.showOptionDialog(null, "输入的QQ号不符合规则", "提示", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}

					try {
						// 通过用户id获取用户信息
					
						Map<String, String> objfriend = new UserDao().findById(txtqq.getText().trim());
						if (objfriend == null) {
							JOptionPane.showOptionDialog(null, "用户不存在", "提示", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							return;
						}
					// 亲空
//						String user_idaone=txtqq.getText().trim();
						txtqq.setText("");
						AddFriendsFrame addFriendsFrame = new AddFriendsFrame(objfriend, userId);
						addFriendsFrame.setVisible(true);
						

					} catch (UnknownHostException | SQLException e1) {
						// TODO Auto-generated catch block
						// 异常处理写入错误日志，便于项目维护
						WriteErrorLog.SaveError(
								"在public class MainFrame 下的private void business(Map user) 下public void keyPressed(KeyEvent e) 中出现异常,异常信息:"
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

		txtqq.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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

		// 开启一个计时调度，延迟 0毫秒（也就是立即开始执行），调度评率： 1秒

		// friendListPanel.removeAll();
		// for ( int i=lblFriendList.size()-1; i>=0;i--) {
		// lblFriendList.remove(i);
		// }
		// 初始化好友列表
		for (int i = 0; i < friends.size(); i++) {
//			System.out.println(i+"次数");
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
			// 获得好友在线状态
			String friendUserOnline = friend.get("online");

			JLabel lblFriend = new JLabel(friendUserName);
			//添加控件上去
			finsuserDele.add(lblFriend);
			lblFriend.setToolTipText(friendUserId);
			String friendIconFile = "/resource/img/" + friendUserIcon + ".jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblFriend.setIcon(new ImageIcon(location));

			// 在线设置可用，离线设置不可用
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}
			lblFriend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 用户图标双击鼠标时显示对话框
					if (e.getClickCount() == 2) {
						try {
							// 该对象是否创建过,防止多次打开相同窗体
							if (charFrameTest == null) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							// 获取单击图片的ID和我已经打开的ID是否一致，一致表示该窗体已被打开无需再打开
							if (charFrameTest.friendUserId != lblFriend.getToolTipText()) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							charFrameTest.setVisible(true);
							isRunning = false;
						} catch (UnknownHostException e1) {
							// 异常处理写入错误日志，便于项目维护
							WriteErrorLog.SaveError(
									"在public class MainFrame 下的private void business(Map user) 下lblFriend.addMouseListener(new MouseAdapter() 中出现异常,异常信息:"
											+ e.toString());
							e1.printStackTrace();
						}

					}

					// 鼠标右键
					if (e.isMetaDown()) {

						// 获取x,y
						// jPopupMenu1.show(this, e.getX(), e.getX());
						Point point = java.awt.MouseInfo.getPointerInfo().getLocation(); // 如果是计算当前Fream的坐标，可以用getLocation()(Point
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

			// 添加到列表集合
			lblFriendList.add(lblFriend);
			// 添加到面板
			friendListPanel.add(lblFriend);
			// friendListPanel.repaint();
		}
		// }
		// }, 0L, 5000L);

		// timer.cancel(); // 关闭计时器

		creates = new UserDao().SelectCreate(useryouid);
		CreatesList = new ArrayList<JLabel>();
		System.out.println(creates.size() + "---duos");
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> create = creates.get(i);
			String createName = create.get("ctreaName");
			String creatQzId = create.get("zqid");
			JLabel lblcreate = new JLabel(createName + "(群)");

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
					// 用户图标双击鼠标时显示对话框
					if (e.getClickCount() == 2) {

						if (createFrameTest != null) {
							createFrameTest = null;
						}

						// 该对象是否创建过,防止多次打开相同窗体
						if (createFrameTest == null) {
							createFrameTest = new CreateFrameTeST(MainFrame.this, userName, createName, user);
						}
						// 获取单击图片的ID和我已经打开的ID是否一致，一致表示该窗体已被打开无需再打开
						// if
						// (createFrameTest.getTitle()+"群"==lblcreate.getToolTipText())
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
			// 添加到面板
			friendListPanel.add(lblcreate);
		}

		// 注册窗口事件
		addWindowListener(new WindowAdapter() {
			// 单击窗口关闭按钮时调用
			public void windowClosing(WindowEvent e) {

				// 当前用户下线
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGOUT);
				jsonObj.put("user_id", userId);
				byte[] b = jsonObj.toString().getBytes();

				InetAddress address;
				try {
					address = InetAddress.getByName(Client.SERVER_IP);
					// 创建DatagramPacket对象
					DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
					// 发送
					Client.socket.send(packet);

				} catch (IOException e1) {
					WriteErrorLog
							.SaveError("在class FriendsFrame下的public FriendsFrame(Map user) 中出现异常,异常信息:" + e.toString());
					e1.printStackTrace();
				}

				// 退出系统
				int n = JOptionPane.showConfirmDialog(null, "你确定要关闭当前程序?", "警告", JOptionPane.YES_NO_OPTION); // 返回值为0或1
				if (n == JOptionPane.YES_OPTION) {
					// 将用户信息改为离线
					User objuser = new User();
					objuser.setUser_id((String) user.get("user_id"));
					objuser.setState("0");
					System.out.println(objuser.getUser_id() + ";" + objuser.getState());
					new UserDao().Changestatu(objuser);
					System.exit(0);
				}
			}
		});

		// 启动接收消息子线程
		resetThread();
	}

	@SuppressWarnings("unchecked")
	public void busdsad(Map user) {
		// 初始化成员变量
		this.user = user;
		/// 初始化用户列表
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

		// 创建视图
		createView(objuser);

		JPanel panel1 = new JPanel();
		scrollPane.setViewportView(panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		// 好友列表面板
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
			// 点击头像弹出自己的信息
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userId);

				if (infomation == null) {
					SeeionThear seion = new SeeionThear(user_name, useryouid);
					SeeionTh = new Thread(seion);
					infomation = new Information(userId, user_name.getText());
					infomation.setSize(369, 300);
					infomation.setVisible(true);
					infomation.setLocationRelativeTo(null);// 窗口在屏幕中间显示
					infomation.setResizable(false);
					infomation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				infomation = null;

			}

			// 移入
			public void mouseEntered(MouseEvent e) {
				Point point1 = java.awt.MouseInfo.getPointerInfo().getLocation();
				// lblFriend.getLocation();)
				if (avatarfrme == null) {
					avatarfrme = new AvatarFrame((int) point1.getX(), (int) point1.getY(), useryouid);
					avatarfrme.setVisible(true);
				}
			}

			// // 移出
			// public void mouseExited(MouseEvent e) {
			// avatarfrme.close();
			// }
		});
		HeaderImg.setBounds(10, 49, 36, 36);
		panel.add(HeaderImg);

		user_name = new JLabel("None");
		user_name.setForeground(Color.WHITE);
		user_name.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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
		user_Signature.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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

			// 查询用户信息
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// 数据验证
					if (txtqq.getText().trim().isEmpty()) {
						JOptionPane.showOptionDialog(null, "输入的QQ号不能为空", "提示", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}
					// 采用正则表达式进行验证
					if (!ValidateUtil.isNumber(txtqq.getText().trim())) {
						JOptionPane.showOptionDialog(null, "输入的QQ号不符合规则", "提示", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						return;
					}

					try {
						// 通过用户id获取用户信息

						Map<String, String> objfriend = new UserDao().findById(txtqq.getText().trim());
						if (objfriend == null) {
							JOptionPane.showOptionDialog(null, "用户不存在", "提示", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.QUESTION_MESSAGE, null, null, null);
							return;
						}
						// 亲空
						txtqq.setText("");
						AddFriendsFrame addFriendsFrame = new AddFriendsFrame(objfriend, userId);
						addFriendsFrame.setVisible(true);

					} catch (UnknownHostException | SQLException e1) {
						// TODO Auto-generated catch block
						// 异常处理写入错误日志，便于项目维护
						WriteErrorLog.SaveError(
								"在public class MainFrame 下的private void business(Map user) 下public void keyPressed(KeyEvent e) 中出现异常,异常信息:"
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

		txtqq.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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

		// 开启一个计时调度，延迟 0毫秒（也就是立即开始执行），调度评率： 1秒

		// friendListPanel.removeAll();
		// for ( int i=lblFriendList.size()-1; i>=0;i--) {
		// lblFriendList.remove(i);
		// }
		// 初始化好友列表
		for (int i = 0; i < friends.size(); i++) {
			Map<String, String> friend = friends.get(i);
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

			// 在线设置可用，离线设置不可用
			if (friendUserOnline.equals("0")) {
				lblFriend.setEnabled(false);
			} else {
				lblFriend.setEnabled(true);
			}
			lblFriend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 用户图标双击鼠标时显示对话框
					if (e.getClickCount() == 2) {
						try {
							// 该对象是否创建过,防止多次打开相同窗体
							if (charFrameTest == null) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							// 获取单击图片的ID和我已经打开的ID是否一致，一致表示该窗体已被打开无需再打开
							if (charFrameTest.friendUserId != lblFriend.getToolTipText()) {
								charFrameTest = new CharFrameTest(MainFrame.this, user, friend);
							}
							charFrameTest.setVisible(true);
							isRunning = false;
						} catch (UnknownHostException e1) {
							// 异常处理写入错误日志，便于项目维护
							WriteErrorLog.SaveError(
									"在public class MainFrame 下的private void business(Map user) 下lblFriend.addMouseListener(new MouseAdapter() 中出现异常,异常信息:"
											+ e.toString());
							e1.printStackTrace();
						}

					}

					// 鼠标右键
					if (e.isMetaDown()) {

						// 获取x,y
						// jPopupMenu1.show(this, e.getX(), e.getX());
						Point point = java.awt.MouseInfo.getPointerInfo().getLocation(); // 如果是计算当前Fream的坐标，可以用getLocation()(Point
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

			// 添加到列表集合
			lblFriendList.add(lblFriend);
			// 添加到面板
			friendListPanel.add(lblFriend);
			// friendListPanel.repaint();
		}
		// }
		// }, 0L, 5000L);

		// timer.cancel(); // 关闭计时器

		creates = new UserDao().SelectCreate(useryouid);
		CreatesList = new ArrayList<JLabel>();
		System.out.println(creates.size() + "---duos");
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> create = creates.get(i);
			String createName = create.get("ctreaName");
			String creatQzId = create.get("zqid");
			JLabel lblcreate = new JLabel(createName + "(群)");

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
					// 用户图标双击鼠标时显示对话框
					if (e.getClickCount() == 2) {

						if (createFrameTest != null) {
							createFrameTest = null;
						}

						// 该对象是否创建过,防止多次打开相同窗体
						if (createFrameTest == null) {
							createFrameTest = new CreateFrameTeST(MainFrame.this, userName, createName, user);
						}
						// 获取单击图片的ID和我已经打开的ID是否一致，一致表示该窗体已被打开无需再打开
						// if
						// (createFrameTest.getTitle()+"群"==lblcreate.getToolTipText())
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
			// 添加到面板
			friendListPanel.add(lblcreate);
		}

		// 注册窗口事件
		addWindowListener(new WindowAdapter() {
			// 单击窗口关闭按钮时调用
			public void windowClosing(WindowEvent e) {

				// 当前用户下线
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGOUT);
				jsonObj.put("user_id", userId);
				byte[] b = jsonObj.toString().getBytes();

				InetAddress address;
				try {
					address = InetAddress.getByName(Client.SERVER_IP);
					// 创建DatagramPacket对象
					DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
					// 发送
					Client.socket.send(packet);

				} catch (IOException e1) {
					WriteErrorLog
							.SaveError("在class FriendsFrame下的public FriendsFrame(Map user) 中出现异常,异常信息:" + e.toString());
					e1.printStackTrace();
				}

				// 退出系统
				int n = JOptionPane.showConfirmDialog(null, "你确定要关闭当前程序?", "警告", JOptionPane.YES_NO_OPTION); // 返回值为0或1
				if (n == JOptionPane.YES_OPTION) {
					// 将用户信息改为离线
					User objuser = new User();
					objuser.setUser_id((String) user.get("user_id"));
					objuser.setState("0");
					System.out.println(objuser.getUser_id() + ";" + objuser.getState());
					new UserDao().Changestatu(objuser);
					System.exit(0);
				}
			}
		});

		// 启动接收消息子线程
		resetThread();
	}

	

	private void createView(User objuser) {
		// 窗体大小不能改变
		this.setResizable(false);
		setTitle("QQ主界面");
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

		JButton btnjq = new JButton("建群");
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
		// 准备一个缓冲区
		byte[] buffer = new byte[1024];
		while (isRunning) {

			try {
				InetAddress address = InetAddress.getByName(Client.SERVER_IP);
				/* 接收数据报 */
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				// 开始接收
				Client.socket.receive(packet);
				// 接收数据长度
				int len = packet.getLength();
				String str = new String(buffer, 0, len);

				System.out.println("客户端：  " + str);

				JSONObject jsonObj = new JSONObject(str);
				String userId = (String) jsonObj.get("user_id");
				String online = (String) jsonObj.get("online");
				// 刷新好友列表
				refreshFriendList(userId, online);

			} catch (Exception e) {
			}
		}
	}

	// 刷新好友列表
	public void refreshFriendList(String userId, String online) {
		// 初始化好友列表

		for (JLabel lblFriend : lblFriendList) {
			// 判断用户Id是否一致
			if (userId.equals(lblFriend.getToolTipText())) {
				if (online.equals("1")) {

					lblFriend.setEnabled(true);
				} else {
					lblFriend.setEnabled(false);
				}
			}
		}
	}

	// 重新启动接收消息子线程
	public void resetThread() {
		isRunning = true;
		// 接收消息子线程
		Thread receiveMessageThread = new Thread(this);
		// 启动接收消息线程
		receiveMessageThread.start();
	}
}
