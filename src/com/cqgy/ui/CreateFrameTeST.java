package com.cqgy.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cqgy.bean.Friend;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.CteataDao;
import com.cqgy.method.UserDao;
import com.cqgy.qq.Client;
import com.cqgy.qq.Server;
import com.cqgy.util.CharFrameUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class CreateFrameTeST extends JFrame implements Runnable {

	private ArrayList<SocketAddress> clients = new ArrayList<SocketAddress>(); // 保存客户端IP地址

	// mulu:用户拖拽的路径
	String mulu = "";
	public static Thread s =null;
	// 文件使用的次数
	public static int count = 0;
	private JPanel contentPane;
	private boolean isRunning = true;
	// 下滑
	private JScrollPane scrollPane = null;
	// 文件传输界面
	FiletransferMainFraem filetransferMainFraem;
	// 好友联通
	CreatsFame creatsfame = null;
	// 当前用户Id
	private String userId;
	// 聊天好友用户Id
	public String friendUserId;
	// 聊天好友用户名
	private String friendUserName;
	// 群友列表
	private List<Map<String, String>> creates;
	// 群标签控件列表
	private List<JLabel> CreatesList;

	// 发送消息文本区
	private JTextPane txtfs;
	// 消息日志
	private StringBuffer infoLog;

	// 接收消息子线程
	private Thread receiveMessageThread;
	// 日期格式化
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 好友列表Frame
	private MainFrame friendsFrame;
	JTextPane txtMainInfo;
	// 朋友对象
	private Friend objfriend;

	// 群名
	private String cteraNames;

	private String userNames;
	
	

	// private StyledDocument doc = null;
	public CreateFrameTeST(MainFrame friendsFrame, String userName, String createName, Map user) {
		
		addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e)
	          {
	        	  MainFrame.number++;
	            s.stop();
	            
	          }
	      });
		

		// TODO Auto-generated constructor stub
		// 初始化成员变量
		this.friendsFrame = friendsFrame;
		setSize(800, 600);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setTitle(createName);

		creates = new UserDao().SelectCreates(createName);
		this.infoLog = new StringBuffer();
		this.userId = (String) user.get("user_id");
		this.cteraNames = createName;
		this.userNames = userName;
		this.setResizable(false);

		receiveMessageThread = new Thread(this);
		receiveMessageThread.start();
		// 注册窗口事件
		addWindowListener(new WindowAdapter() {
			// 单击窗口关闭按钮时调用
			public void windowClosing(WindowEvent e) {
				isRunning = false;
				setVisible(false);
				// 重启好友列表线程
				friendsFrame.resetThread();
			}
		});

		// 消息显示框
		txtMainInfo = new JTextPane();
		txtMainInfo.setEditable(false);
		txtMainInfo.setLayout(null);
		txtMainInfo.setBounds(0, 0, 600, 400);
		// txtMainInfo.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
		// Color.blue));
		txtMainInfo.setCaretPosition(txtMainInfo.getStyledDocument().getLength());
		add(txtMainInfo);
		// 读取以前的数据

		// MongoCursor<Document> mongoCursor = new
		// CteataDao().SelectCtreata(cteraNames);
		// String dateseeion = null;
		// String nameseeion = null;
		// String meesageseeion = null;
		// StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
		// Style style = doc.addStyle("StyleName", null);
		// String zonghe = null;
		// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		// while (mongoCursor.hasNext()) {
		// Document studentDocument = mongoCursor.next();
		// dateseeion = format1.format(studentDocument.getDate("date"));
		// nameseeion = studentDocument.getString("username");
		// meesageseeion = studentDocument.getString("meesage");
		// zonghe = dateseeion + nameseeion + "说：" + meesageseeion + "\n";
		//
		// try {
		// doc.insertString(doc.getLength(), zonghe, style);
		// } catch (BadLocationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		s= new Thread(new SeeionXsd(txtMainInfo, cteraNames));
		s.start();
		
		scrollPane = new JScrollPane(txtMainInfo);
		scrollPane.setBounds(0, 0, 600, 400);
		scrollPane.setPreferredSize(new Dimension(325, 230));
		getContentPane().add(scrollPane);

		// 发送框
		txtfs = new JTextPane();
		txtfs.setBounds(0, 400, 600, 180);
		// txtfs.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
		// Color.blue));
		// 屏蔽回车换行
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		txtfs.getInputMap().put(enter, "none");
		// 将军用户是否按下回车按钮
		txtfs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!mulu.trim().isEmpty()) {
						sendMessage(mulu);
						mulu = "";
					} else {
						sendMessage(txtfs.getText().trim());
					}
					txtfs.setText("");
				}
			}
		});
		add(txtfs);

		JPanel panelxfinds = new JPanel();
		panelxfinds.setBounds(600, 0, 200, 800);
		// panelxfinds.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
		// Color.blue));
		panelxfinds.setLayout(new BoxLayout(panelxfinds, BoxLayout.Y_AXIS));
		add(panelxfinds);

		CreatesList = new ArrayList<JLabel>();
		// 初始化好友列表
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> friend = this.creates.get(i);
			this.friendUserId = friend.get("ctreaId");
			String friendUserName = friend.get("ctreaName");
			// 获得好友在线状态
			String friendUserIcon = friend.get("user_icon");
			String friendUserOnline = friend.get("online");
			JLabel lblFriend = new JLabel(friendUserName);
			lblFriend.setToolTipText(friendUserId);
			String friendIconFile = "/resource/img/" + friendUserIcon + ".jpg";
			URL location = MainFrame.class.getResource(friendIconFile);
			lblFriend.setIcon(new ImageIcon(location));

			lblFriend.setEnabled(true);

			lblFriend.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 用户图标双击鼠标时显示对话框
					if (e.getClickCount() == 2) {
						try {
							// 该对象是否创建过,防止多次打开相同窗体
							if (creatsfame == null) {
								creatsfame = new CreatsFame(CreateFrameTeST.this, user, friend);
							}
							// 获取单击图片的ID和我已经打开的ID是否一致，一致表示该窗体已被打开无需再打开
							if (creatsfame.friendUserId != lblFriend.getToolTipText()) {
								creatsfame = new CreatsFame(CreateFrameTeST.this, user, friend);
							}
							creatsfame.setVisible(true);
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
				}
			});
			// 添加到列表集合
			CreatesList.add(lblFriend);
			// 添加到面板
			panelxfinds.add(lblFriend);
		}
		// Timer timer = new Timer();1
		////
		//// // 开启一个计时调度，延迟 0毫秒（也就是立即开始执行），调度评率： 1秒
		// timer.schedule(new TimerTask() {
		// @Override
		// public void run() {
		// txtMainInfo.setText(null);
		//
		// MongoCursor<Document> mongoCursor = new
		// CteataDao().SelectCtreata(cteraNames);
		// String dateseeion = null;
		// String nameseeion = null;
		// String meesageseeion = null;
		// StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
		// Style style = doc.addStyle("StyleName", null);
		// String zonghe = null;
		// DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		// while (mongoCursor.hasNext()) {
		// Document studentDocument = mongoCursor.next();
		// dateseeion = format1.format(studentDocument.getDate("date"));
		// nameseeion = studentDocument.getString("username");
		// meesageseeion = studentDocument.getString("meesage");
		// zonghe = dateseeion + nameseeion + "说：" + meesageseeion + "\n";
		//
		// try {
		// doc.insertString(doc.getLength(), zonghe, style);
		// } catch (BadLocationException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		//
		// }
		// }, 0L, 1000L);

		//// timer.cancel(); // 关闭计时器
		//
		//
		//

		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private DataOutputStream dos;

	// 发送消息
	private void sendMessage(String sendstr) {
		// 发送内容不为空
		if (!sendstr.isEmpty()) {

			// 获得当前时间，并格式化
			String date = dateFormat.format(new Date());
			String info = String.format("#%s#" + "说：\n%s", date, sendstr);
			infoLog.append(info).append('\n');
			boolean seein = new CteataDao().InsertSeeion(userNames, cteraNames, userId, sendstr, new Date());

			// 判断是否为表情包里的图片
			if (CharFrameUtil.IspictureinMeme(sendstr)) {
				// 发送表情包,将表情包加载的txtMainInfo中去
				String resultpostion = sendstr.substring("图片".length());
				String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
				// System.out.println(path);
				// 发送图片
				String infodata = String.format("#%s#" + "s说：\n", date);
				insert(infodata);
				insertImage(path, txtMainInfo);
				// 拖拽图片发送
			} else if (CharFrameUtil.isFilelink(sendstr)) {
				String infodata = String.format("#%s#" + "说：\n", date);
				insert(infodata);
				insertImage(sendstr, txtMainInfo);
			} else {
				// 发送文本
				insert(info + "\n");
			}

			Map<String, String> message = new HashMap<String, String>();
			message.put("receive_user_id", friendUserId);
			message.put("user_id", userId);
			message.put("message", sendstr);

			JSONObject jsonObj = new JSONObject(message);
			jsonObj.put("command", Client.COMMAND_SENDMSG);

			try {
				InetAddress address = InetAddress.getByName(Client.SERVER_IP);
				/* 发送数据报 */
				byte[] b = jsonObj.toString().getBytes();
				DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);

				SocketAddress clientip = packet.getSocketAddress();
				if (!clients.contains(clientip)) {
					clients.add(clientip);
				}

				// Client.socket.send(packet);
				// System.out.println(Client.socket);
				// Client.clientHandlerMap.put(Client.socket, packet);
				messageThread.SendSeeion(packet);

				// messageThread.SendSeeionty(packet,b);

				// try {
				// sendAll(Client.socket, packet);
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			} catch (IOException e) {

			}
		}

	}

	private void sendAll(DatagramSocket socket, DatagramPacket dp) throws Exception {
		for (SocketAddress sa : clients) {
			DatagramPacket dd = new DatagramPacket(dp.getData(), dp.getLength(), sa);
			System.out.println(sa.toString());
			socket.send(dd);
		}
	}

	// 用户拖拽的文件路径
	protected void drop(DropTargetDropEvent arg0) {
		arg0.acceptDrop(3);// 接受滴 没这句就会报错 3表示移动or复制
		List<?> list = null;
		try {
			list = (List<?>) arg0.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			// get可转让的 get数据传输 数据的味道。java文件列表的味道
			File fi = (File) list.get(0);
			mulu = fi.getPath();
			// 显示图片
			insertImage(mulu, txtfs);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}

	private void insert(String sendStr) {
		try { // 插入文本
			StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
			Style style = doc.addStyle("StyleName", null);
			doc.insertString(doc.getLength(), sendStr, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void insertImage(String path, JTextPane textPane) {
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		textPane.setCaretPosition(doc.getLength()); // 设置插入位置
		textPane.insertIcon(new ImageIcon(path));
		insert("\n");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 准备一个缓冲区
		byte[] buffer = new byte[1024];

		while (isRunning) {
			try {

				InetAddress address = InetAddress.getByName(Client.SERVER_IP);
				// /* 接收数据报 */
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				//
				// // 开始接收
				Client.socket.receive(packet);
				// // 接收数据长度
				int len = packet.getLength();
				String str = new String(buffer, 0, len);

				// 打印接收的数据
				System.out.printf("从服务器接收的数据：【%s】\n", str);
				JSONObject jsonObj = new JSONObject(str);

				// 获得当前时间，并格式化
				String date = dateFormat.format(new Date());
				String message = (String) jsonObj.get("message");
				System.out.println(message + "-------接受的消息");
				String infoStr = String.format("#%s#" + "%s对您说：\n%s", date, friendUserName, message);
				// 判断是否为表情包里的图片
				if (CharFrameUtil.IspictureinMeme(message)) {
					// 发送表情包,将表情包加载的txtMainInfo中去
					String resultpostion = message.substring("图片".length());
					String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
					// 发送图片
					String infodata = String.format("#%s#" + "您对%s说：\n", date, friendUserName);
					insert(infodata);
					insertImage(path, txtMainInfo);

				} else if (CharFrameUtil.isFilelink(message)) {
					String infodata = String.format("#%s#" + "您对%s说：\n", date, friendUserName);
					insert(infodata);
					// 判断是否为链接
					insertImage(message, txtMainInfo);
				} else {
					insert(infoStr + "\n");
				}
				Thread.sleep(100);
				// 刷新好友列表
				JSONArray userList = (JSONArray) jsonObj.get("OnlineUserList");

				for (Object item : userList) {

					JSONObject onlineUser = (JSONObject) item;
					String userId = (String) onlineUser.get("user_id");
					String online = (String) onlineUser.get("online");
					friendsFrame.refreshFriendList(userId, online);
				}

			} catch (Exception e) {
			}
		}
	}

	public void resetThread() {
		isRunning = true;
		// 接收消息子线程
		Thread receiveMessageThread = new Thread(this);
		// 启动接收消息线程
		receiveMessageThread.start();
	}

	// public static void main(String[] args) {
	// new CreateFrameTeST("123");
	// }
	//
}
