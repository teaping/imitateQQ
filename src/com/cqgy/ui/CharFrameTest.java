package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cqgy.bean.Friend;
import com.cqgy.method.CteataDao;
import com.cqgy.qq.Client;
import com.cqgy.util.CharFrameUtil;
import com.cqgy.util.FontAttrib;
import com.cqgy.util.regularSeeion;
import com.mongodb.client.MongoCursor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CharFrameTest extends JFrame implements Runnable {

	// mulu:�û���ק��·��
	String mulu = "";
	// �ļ�ʹ�õĴ���
	public static int count = 0;
	private JPanel contentPane;
	private boolean isRunning = true;
	// �»�
	private JScrollPane scrollPane = null;
	// �ļ��������
	FiletransferMainFraem filetransferMainFraem;
	// ��ǰ�û�Id
	private String userId;
	// ��������û�Id
	public String friendUserId;
	// ��������û���
	private String friendUserName;
	// ������Ϣ�ı���
	private JTextPane txtInfo;
	// ��Ϣ��־
	private StringBuffer infoLog;

	// ������Ϣ���߳�
	private Thread receiveMessageThread;
	// ���ڸ�ʽ��
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// �����б�Frame
	private MainFrame friendsFrame;
	JTextPane txtMainInfo;
	// ���Ѷ���
	private Friend objfriend;
	// private StyledDocument doc = null;
	private DataOutputStream dos;

	private String userNames;

	public CharFrameTest(MainFrame friendsFrame, Map<String, String> user, Map<String, String> friend)
			throws UnknownHostException {

		// ��ʼ����Ա����
		this.friendsFrame = friendsFrame;

		this.userId = user.get("user_id");
		String userIcon = user.get("user_icon");
		String friendIco = friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		this.infoLog = new StringBuffer();
		this.userNames = (String) user.get("user_name");

		/// ��ʼ����ǰFrame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		System.out.println(friendIco);
		// ��װ���Ѷ���
		if (objfriend == null) {
			objfriend = new Friend(friendUserId, friendUserName, iconFile,
					InetAddress.getByName(Client.SERVER_IP).toString());
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(Client.class.getResource(iconFile)));
		String title = String.format("��%s������...", friendUserName);
		setTitle(title);
		setResizable(false);
		initView(objfriend);
		// getContentPane().setLayout(null);

		receiveMessageThread = new Thread(this);
		receiveMessageThread.start();
		// ע�ᴰ���¼�
		addWindowListener(new WindowAdapter() {
			// �������ڹرհ�ťʱ����
			public void windowClosing(WindowEvent e) {
				isRunning = false;
				setVisible(false);
				// ���������б��߳�
				friendsFrame.resetThread();
			}
		});
	}

	public CharFrameTest() {
		initView(null);
	}

	private void initView(Friend objfriend) {
		setBackground(new Color(220, 238, 247));
		this.setSize(520, 470);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 187, 226));
		panel.setBounds(0, 0, 520, 90);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel headimage = new JLabel();
		headimage.setBounds(10, 10, 46, 46);
		if (!objfriend.getFriend_icon().isEmpty()) {
			// headimage.setIcon(new ImageIcon(pathImage));
			headimage.setIcon(new ImageIcon(Client.class.getResource(objfriend.getFriend_icon())));
		} else {
			headimage.setIcon(new ImageIcon("image/2.jpg"));
		}
		panel.add(headimage);

		JLabel lblNewLabel_3 = new JLabel("None");
		if (!objfriend.getFriend_name().isEmpty()) {
			lblNewLabel_3.setText(objfriend.getFriend_name());
		}
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_3.setBounds(66, 20, 54, 15);
		panel.add(lblNewLabel_3);

		JButton filetranfer = new JButton();
		filetranfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count == 0) {
					if (filetransferMainFraem == null) {
						filetransferMainFraem = new FiletransferMainFraem();
					}
					filetransferMainFraem.setVisible(true);
				} else {
					JOptionPane.showOptionDialog(null, "�ļ�����ÿ����һ��ֻ��ʹ��һ��,���Ѿ�ʹ�ù���!", "��ʾ", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
				}
			}
		});
		filetranfer.setBounds(10, 61, 22, 22);
		filetranfer.setIcon(new ImageIcon("image/filetranferyellow22.png"));
		filetranfer.setOpaque(false);
		panel.add(filetranfer);

		JButton microphone = new JButton();
		microphone.setBounds(40, 61, 22, 22);
		microphone.setIcon(new ImageIcon("image/microphone.png"));
		microphone.setOpaque(false);
		panel.add(microphone);

		JButton remoteconnection = new JButton();
		remoteconnection.setBounds(70, 61, 22, 22);
		remoteconnection.setIcon(new ImageIcon("image/Remoteconnection2.png"));
		remoteconnection.setOpaque(false);
		panel.add(remoteconnection);

		JButton print = new JButton("");
		print.setBounds(100, 61, 22, 22);
		print.setIcon(new ImageIcon("image/print.png"));
		panel.add(print);

		JLabel lblNewLabel = new JLabel("\u7535\u8111\u548CPad,\u624B\u673A\u4E92\u4F20\u6587\u4EF6\u5417?");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 449, 160, 15);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("\u5173\u95ED");
		btnNewButton.setBackground(new Color(235, 246, 250));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(172, 445, 62, 23);
		btnNewButton.setBorderPainted(false);
		getContentPane().add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(232, 244, 249));
		panel_2.setBounds(0, 319, 325, 28);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton fontbtn = new JButton();
		fontbtn.setBackground(Color.WHITE);
		fontbtn.setBounds(10, 3, 22, 22);
		fontbtn.setIcon(new ImageIcon("image/fontImage.png"));
		fontbtn.setOpaque(false);
		panel_2.add(fontbtn);

		JButton Expression = new JButton();
		Expression.setBounds(40, 3, 22, 22);
		Expression.setIcon(new ImageIcon("image/expression.png"));
		Expression.setOpaque(false);
		panel_2.add(Expression);

		JButton Pictures = new JButton();
		Pictures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser(); // �����ļ�
				f.showOpenDialog(null);
				// insertImage(f.getSelectedFile().getPath());
				// ��������
				sendMessage(f.getSelectedFile().getPath());

			}
		});
		Pictures.setBounds(70, 3, 22, 22);
		Pictures.setIcon(new ImageIcon("image/picture.png"));
		Pictures.setOpaque(false);
		panel_2.add(Pictures);

		JButton infoimage = new JButton();
		infoimage.setBounds(100, 3, 22, 22);
		infoimage.setIcon(new ImageIcon("image/infoImage.png"));
		infoimage.setOpaque(false);
		panel_2.add(infoimage);

		JButton Vibration = new JButton();
		Vibration.setBounds(130, 3, 22, 22);
		Vibration.setIcon(new ImageIcon("image/vibrate.png"));
		Vibration.setOpaque(false);
		panel_2.add(Vibration);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(194, 226, 239));
		panel_4.setBounds(325, 89, 195, 25);
		getContentPane().add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 12));
		panel_4.add(lblNewLabel_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(239, 246, 249));
		panel_5.setBounds(325, 114, 175, 325);
		getContentPane().add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D:");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_2.setBounds(26, 57, 43, 15);
		panel_5.add(lblNewLabel_2);

		JLabel user_name = new JLabel("None");
		if (!objfriend.getFriend_name().isEmpty()) {
			user_name.setText(objfriend.getFriend_name());
		}
		user_name.setFont(new Font("����", Font.BOLD, 14));
		user_name.setBounds(69, 57, 54, 15);
		panel_5.add(user_name);

		JLabel lblNewLabel_4 = new JLabel("QQ\u53F7:\r\n");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_4.setBounds(26, 87, 46, 15);
		panel_5.add(lblNewLabel_4);

		JLabel user_section = new JLabel("None");
		if (!objfriend.getFriend_id().isEmpty()) {
			user_section.setText(objfriend.getFriend_id());
		}
		user_section.setFont(new Font("����", Font.BOLD, 14));
		user_section.setBounds(69, 87, 54, 15);
		panel_5.add(user_section);

		JLabel lblNewLabel_6 = new JLabel("\u7535\u8BDD:\r\n\r\n");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_6.setBounds(26, 117, 54, 15);
		panel_5.add(lblNewLabel_6);

		JLabel user_phone = new JLabel("None");
		user_phone.setFont(new Font("����", Font.BOLD, 14));
		user_phone.setBounds(69, 117, 54, 15);
		panel_5.add(user_phone);

		JLabel lblNewLabel_8 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_8.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_8.setBounds(26, 147, 43, 15);
		panel_5.add(lblNewLabel_8);

		JLabel user_postbox = new JLabel("None\r\n");
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id() + "@qq.com");
		}
		user_postbox.setFont(new Font("����", Font.BOLD, 14));
		user_postbox.setBounds(69, 147, 96, 15);
		panel_5.add(user_postbox);

		JLabel lblNewLabel_10 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_10.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_10.setBounds(26, 177, 54, 15);
		panel_5.add(lblNewLabel_10);

		JLabel user_IpAddress = new JLabel("None");
		if (!objfriend.getIpAddress().isEmpty()) {
			user_IpAddress.setText(objfriend.getIpAddress());
		}
		user_IpAddress.setFont(new Font("����", Font.BOLD, 12));
		user_IpAddress.setBounds(79, 177, 86, 15);
		panel_5.add(user_IpAddress);

		txtInfo = new JTextPane();
		// ���λس�����
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		txtInfo.getInputMap().put(enter, "none");
		// �����û��Ƿ��»س���ť
		txtInfo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!mulu.trim().isEmpty()) {
						sendMessage(mulu);
						mulu = "";
					} else {
						sendMessage(txtInfo.getText().trim());
					}
					txtInfo.setText("");
				}
			}
		});
		new DropTarget(txtInfo, new DropTargetAdapter() {

			public void drop(DropTargetDropEvent arg0) {
				CharFrameTest.this.drop(arg0);
			}
		});
		txtInfo.setBounds(0, 348, 325, 91);
		getContentPane().add(txtInfo);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(235, 246, 250));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u53D1\u9001", "\u5173\u95ED" }));
		comboBox.setBounds(244, 446, 62, 23);
		getContentPane().add(comboBox);

		txtMainInfo = new JTextPane();
		txtMainInfo.setEditable(false);
		txtMainInfo.setBounds(0, 89, 325, 230);
		txtMainInfo.setCaretPosition(txtMainInfo.getStyledDocument().getLength());
		// doc = txtMainInfo.getStyledDocument();
		// scrollPane = new JScrollPane(txtMainInfo);
		// scrollPane.setPreferredSize(new Dimension(400, 400));
		getContentPane().add(txtMainInfo);
		int zh = regularSeeion.RegularFinds(userId + "," + friendUserId, userId, friendUserId);
		// ��ȡ��ǰ������
		if (zh != -1) {
			MongoCursor<Document> mongoCursor = new CteataDao().SelectCtreata(String.valueOf(zh));
			String dateseeion = null;
			String nameseeion = null;
			String meesageseeion = null;
			StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
			Style style = doc.addStyle("StyleName", null);
			String zonghe = null;
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			while (mongoCursor.hasNext()) {
				Document studentDocument = mongoCursor.next();
				dateseeion = format1.format(studentDocument.getDate("date"));
				nameseeion = studentDocument.getString("username");
				meesageseeion = studentDocument.getString("meesage");
				zonghe = dateseeion + nameseeion + "˵��" + meesageseeion + "\n";

				try {
					doc.insertString(doc.getLength(), zonghe, style);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		scrollPane = new JScrollPane(txtMainInfo);
		scrollPane.setBounds(0, 89, 325, 230);
		scrollPane.setPreferredSize(new Dimension(325, 230));
		getContentPane().add(scrollPane);
	}

	// ������Ϣ
	private void sendMessage(String sendstr) {
		// �������ݲ�Ϊ��
		if (!sendstr.isEmpty()) {
			boolean Seeonfind = new CteataDao().InsertFinds(userNames, friendUserId, userId, sendstr, new Date());

			// ��õ�ǰʱ�䣬����ʽ��
			String date = dateFormat.format(new Date());
			String info = String.format("#%s#" + "����%s˵��\n%s", date, friendUserName, sendstr);
			infoLog.append(info).append('\n');
			// �ж��Ƿ�Ϊ��������ͼƬ
			if (CharFrameUtil.IspictureinMeme(sendstr)) {
				// ���ͱ����,����������ص�txtMainInfo��ȥ
				String resultpostion = sendstr.substring("ͼƬ".length());
				String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
				// System.out.println(path);
				// ����ͼƬ
				String infodata = String.format("#%s#" + "����%s˵��\n", date, friendUserName);
				insert(infodata);
				insertImage(path, txtMainInfo);
				// ��קͼƬ����
			} else if (CharFrameUtil.isFilelink(sendstr)) {
				String infodata = String.format("#%s#" + "����%s˵��\n", date, friendUserName);
				insert(infodata);
				insertImage(sendstr, txtMainInfo);
			} else {
				// �����ı�
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
				/* �������ݱ� */
				byte[] b = jsonObj.toString().getBytes();
				DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
				Client.socket.send(packet);

			} catch (IOException e) {

			}
		}
	}

	// �û���ק���ļ�·��
	protected void drop(DropTargetDropEvent arg0) {
		arg0.acceptDrop(3);// ���ܵ� û���ͻᱨ�� 3��ʾ�ƶ�or����
		List<?> list = null;
		try {
			list = (List<?>) arg0.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			// get��ת�õ� get���ݴ��� ���ݵ�ζ����java�ļ��б��ζ��
			File fi = (File) list.get(0);
			mulu = fi.getPath();
			// ��ʾͼƬ
			insertImage(mulu, txtInfo);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
	}

	// ������Ϣ
	@Override
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

				// ��ӡ���յ�����
				System.out.printf("�ӷ��������յ����ݣ���%s��\n", str);
				JSONObject jsonObj = new JSONObject(str);
				// �ж�
				String usetidObj = (String) jsonObj.get("user_id");
				System.out.println(usetidObj+"-------------------user_id");
				System.out.println(friendUserId+"----------------------friendUserId");
				if (usetidObj.equals(friendUserId)) {
					// ��õ�ǰʱ�䣬����ʽ��
					String date = dateFormat.format(new Date());
					String message = (String) jsonObj.get("message");
					String infoStr = String.format("#%s#" + "%s����˵��\n%s", date, friendUserName, message);
					// �ж��Ƿ�Ϊ��������ͼƬ
					if (CharFrameUtil.IspictureinMeme(message)) {
						// ���ͱ����,����������ص�txtMainInfo��ȥ
						String resultpostion = message.substring("ͼƬ".length());
						String path = "EmoticonImage/" + resultpostion.trim() + ".gif";
						// ����ͼƬ
						String infodata = String.format("#%s#" + "����%s˵��\n", date, friendUserName);
						insert(infodata);
						insertImage(path, txtMainInfo);

					} else if (CharFrameUtil.isFilelink(message)) {
						String infodata = String.format("#%s#" + "����%s˵��\n", date, friendUserName);
						insert(infodata);
						// �ж��Ƿ�Ϊ����
						insertImage(message, txtMainInfo);
					} else {
						insert(infoStr + "\n");
					}
					Thread.sleep(100);
					// ˢ�º����б�
					JSONArray userList = (JSONArray) jsonObj.get("OnlineUserList");
					for (Object item : userList) {

						JSONObject onlineUser = (JSONObject) item;
						String userId = (String) onlineUser.get("user_id");
						String online = (String) onlineUser.get("online");
						friendsFrame.refreshFriendList(userId, online);
					}
				}else{
					InetAddress address1 = InetAddress.getByName(Client.SERVER_IP);
					/* �������ݱ� */
					DatagramPacket packet1 = new DatagramPacket(buffer, buffer.length, address1, Client.SERVER_PORT);
					
					Client.socket.send(packet1);
					Thread.sleep(1000);
				}

			} catch (Exception e) {
			}
		}
	}

	private void insert(String sendStr) {
		try { // �����ı�
			StyledDocument doc = (StyledDocument) txtMainInfo.getDocument();
			Style style = doc.addStyle("StyleName", null);
			doc.insertString(doc.getLength(), sendStr, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void insertImage(String path, JTextPane textPane) {
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		textPane.setCaretPosition(doc.getLength()); // ���ò���λ��
		textPane.insertIcon(new ImageIcon(path));
		insert("\n");
	}

	public void resetThread() {
		isRunning = true;
		// ������Ϣ���߳�
		Thread receiveMessageThread = new Thread(this);
		// ����������Ϣ�߳�
		receiveMessageThread.start();

	}
}
