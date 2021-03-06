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

	private ArrayList<SocketAddress> clients = new ArrayList<SocketAddress>(); // ??????????IP????

	// mulu:??????????????
	String mulu = "";
	public static Thread s =null;
	// ??????????????
	public static int count = 0;
	private JPanel contentPane;
	private boolean isRunning = true;
	// ????
	private JScrollPane scrollPane = null;
	// ????????????
	FiletransferMainFraem filetransferMainFraem;
	// ????????
	CreatsFame creatsfame = null;
	// ????????Id
	private String userId;
	// ????????????Id
	public String friendUserId;
	// ??????????????
	private String friendUserName;
	// ????????
	private List<Map<String, String>> creates;
	// ??????????????
	private List<JLabel> CreatesList;

	// ??????????????
	private JTextPane txtfs;
	// ????????
	private StringBuffer infoLog;

	// ??????????????
	private Thread receiveMessageThread;
	// ??????????
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// ????????Frame
	private MainFrame friendsFrame;
	JTextPane txtMainInfo;
	// ????????
	private Friend objfriend;

	// ????
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
		// ??????????????
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
		// ????????????
		addWindowListener(new WindowAdapter() {
			// ??????????????????????
			public void windowClosing(WindowEvent e) {
				isRunning = false;
				setVisible(false);
				// ????????????????
				friendsFrame.resetThread();
			}
		});

		// ??????????
		txtMainInfo = new JTextPane();
		txtMainInfo.setEditable(false);
		txtMainInfo.setLayout(null);
		txtMainInfo.setBounds(0, 0, 600, 400);
		// txtMainInfo.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
		// Color.blue));
		txtMainInfo.setCaretPosition(txtMainInfo.getStyledDocument().getLength());
		add(txtMainInfo);
		// ??????????????

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
		// zonghe = dateseeion + nameseeion + "????" + meesageseeion + "\n";
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

		// ??????
		txtfs = new JTextPane();
		txtfs.setBounds(0, 400, 600, 180);
		// txtfs.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
		// Color.blue));
		// ????????????
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		txtfs.getInputMap().put(enter, "none");
		// ????????????????????????
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
		// ??????????????
		for (int i = 0; i < creates.size(); i++) {
			Map<String, String> friend = this.creates.get(i);
			this.friendUserId = friend.get("ctreaId");
			String friendUserName = friend.get("ctreaName");
			// ????????????????
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
					// ????????????????????????????
					if (e.getClickCount() == 2) {
						try {
							// ????????????????,????????????????????
							if (creatsfame == null) {
								creatsfame = new CreatsFame(CreateFrameTeST.this, user, friend);
							}
							// ??????????????ID??????????????ID??????????????????????????????????????????
							if (creatsfame.friendUserId != lblFriend.getToolTipText()) {
								creatsfame = new CreatsFame(CreateFrameTeST.this, user, friend);
							}
							creatsfame.setVisible(true);
							isRunning = false;
						} catch (UnknownHostException e1) {
							// ??????????????????????????????????
							WriteErrorLog.SaveError(
									"??public class MainFrame ????private void business(Map user) ??lblFriend.addMouseListener(new MouseAdapter() ??????????,????????:"
											+ e.toString());
							e1.printStackTrace();
						}

					}

					// ????????
				}
			});
			// ??????????????
			CreatesList.add(lblFriend);
			// ??????????
			panelxfinds.add(lblFriend);
		}
		// Timer timer = new Timer();1
		////
		//// // ?????????????????????? 0?????????????????????????????????????? 1??
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
		// zonghe = dateseeion + nameseeion + "????" + meesageseeion + "\n";
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

		//// timer.cancel(); // ??????????
		//
		//
		//

		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private DataOutputStream dos;

	// ????????
	private void sendMessage(String sendstr) {
		// ??????????????
		if (!sendstr.isEmpty()) {

			// ??????????????????????
			String date = dateFormat.format(new Date());
			String info = String.format("#%s#" + "????\n%s", date, sendstr);
			infoLog.append(info).append('\n');
			boolean seein = new CteataDao().InsertSeeion(userNames, cteraNames, userId, sendstr, new Date());

			// ????????????????????????
			if (CharFrameUtil.IspictureinMeme(sendstr)) {
				// ??????????,??????????????txtMainInfo????
				String resultpostion = sendstr.substring("????".length());
				String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
				// System.out.println(path);
				// ????????
				String infodata = String.format("#%s#" + "s????\n", date);
				insert(infodata);
				insertImage(path, txtMainInfo);
				// ????????????
			} else if (CharFrameUtil.isFilelink(sendstr)) {
				String infodata = String.format("#%s#" + "????\n", date);
				insert(infodata);
				insertImage(sendstr, txtMainInfo);
			} else {
				// ????????
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
				/* ?????????? */
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

	// ??????????????????
	protected void drop(DropTargetDropEvent arg0) {
		arg0.acceptDrop(3);// ?????? ?????????????? 3????????or????
		List<?> list = null;
		try {
			list = (List<?>) arg0.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			// get???????? get???????? ????????????java??????????????
			File fi = (File) list.get(0);
			mulu = fi.getPath();
			// ????????
			insertImage(mulu, txtfs);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}

	private void insert(String sendStr) {
		try { // ????????
			StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
			Style style = doc.addStyle("StyleName", null);
			doc.insertString(doc.getLength(), sendStr, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void insertImage(String path, JTextPane textPane) {
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		textPane.setCaretPosition(doc.getLength()); // ????????????
		textPane.insertIcon(new ImageIcon(path));
		insert("\n");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ??????????????
		byte[] buffer = new byte[1024];

		while (isRunning) {
			try {

				InetAddress address = InetAddress.getByName(Client.SERVER_IP);
				// /* ?????????? */
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				//
				// // ????????
				Client.socket.receive(packet);
				// // ????????????
				int len = packet.getLength();
				String str = new String(buffer, 0, len);

				// ??????????????
				System.out.printf("??????????????????????%s??\n", str);
				JSONObject jsonObj = new JSONObject(str);

				// ??????????????????????
				String date = dateFormat.format(new Date());
				String message = (String) jsonObj.get("message");
				System.out.println(message + "-------??????????");
				String infoStr = String.format("#%s#" + "%s????????\n%s", date, friendUserName, message);
				// ????????????????????????
				if (CharFrameUtil.IspictureinMeme(message)) {
					// ??????????,??????????????txtMainInfo????
					String resultpostion = message.substring("????".length());
					String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
					// ????????
					String infodata = String.format("#%s#" + "????%s????\n", date, friendUserName);
					insert(infodata);
					insertImage(path, txtMainInfo);

				} else if (CharFrameUtil.isFilelink(message)) {
					String infodata = String.format("#%s#" + "????%s????\n", date, friendUserName);
					insert(infodata);
					// ??????????????
					insertImage(message, txtMainInfo);
				} else {
					insert(infoStr + "\n");
				}
				Thread.sleep(100);
				// ????????????
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
		// ??????????????
		Thread receiveMessageThread = new Thread(this);
		// ????????????????
		receiveMessageThread.start();
	}

	// public static void main(String[] args) {
	// new CreateFrameTeST("123");
	// }
	//
}
