package com.cqgy.ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.cqgy.bean.Friend;
import com.cqgy.method.UserDao;
import com.cqgy.qq.Client;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FriendsInfoFream extends JFrame {

	
	private JPanel contentPane;

	// µ±«∞”√ªßId
	private String userId;
	// ¡ƒÃÏ∫√”—”√ªßId
	public String friendUserId;
	// ¡ƒÃÏ∫√”—”√ªß√˚
	private String friendUserName;
	//≈Û”—∂‘œÛ
	private Friend objfriend;
	//∫√”—µÁª∞
	private String ipone=null;
	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public FriendsInfoFream( Map<String, String> friend) throws UnknownHostException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		setType(Type.UTILITY);
		//ªÒ»°∫√”—–≈œ¢
		String friendIco=friend.get("user_icon");
		this.friendUserId = friend.get("user_id");
		this.friendUserName = friend.get("user_name");
		
		ipone=new UserDao().SelectIpone(friendUserId);
		/// ≥ı ºªØµ±«∞Frame
		String iconFile = String.format("/resource/img/%s.jpg", friendIco);
		//∑‚◊∞≈Û”—∂‘œÛ
		if (objfriend==null) {
		 objfriend=new Friend(friendUserId, friendUserName, iconFile,InetAddress.getByName(Client.SERVER_IP).toString());	
		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel headimage = new JLabel("");
		headimage.setBounds(24, 67, 36, 36);
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
		label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 20));
		label.setBounds(84, 0, 98, 33);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		lblNewLabel_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 67, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel user_name = new JLabel("None");
		user_name.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		user_name.setBounds(141, 67, 54, 15);
		if (!objfriend.getFriend_name().isEmpty()) {
			user_name.setText(objfriend.getFriend_name());
		}
		contentPane.add(user_name);
		
		JLabel lblNewLabel_3 = new JLabel("QQ\u53F7:");
		lblNewLabel_3.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 104, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel user_section = new JLabel("None");
		user_section.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		user_section.setBounds(141, 104, 54, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_section.setText(objfriend.getFriend_id());
		}
		contentPane.add(user_section);
		
		JLabel lblNewLabel_5 = new JLabel("\u7535\u8BDD:");
		lblNewLabel_5.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 148, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("None");
		lblNewLabel_6.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_6.setBounds(141, 149, 133, 15);
		if (ipone!=null&&ipone!="") {
			lblNewLabel_6.setText(ipone);
			ipone="";
		}
		contentPane.add(lblNewLabel_6);
		
		
		JLabel lblNewLabel_7 = new JLabel("\u90AE\u7BB1:");
		lblNewLabel_7.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_7.setBounds(80, 195, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IP\u5730\u5740:");
		lblNewLabel_8.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		lblNewLabel_8.setBounds(80, 237, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel user_postbox = new JLabel("None");
		user_postbox.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		user_postbox.setBounds(141, 196, 133, 15);
		if (!objfriend.getFriend_id().isEmpty()) {
			user_postbox.setText(objfriend.getFriend_id()+"@qq.com");
		}
		contentPane.add(user_postbox);
		
		JLabel user_IpAddress = new JLabel("None");
		user_IpAddress.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 15));
		user_IpAddress.setBounds(141, 238, 133, 15);
		if (!objfriend.getIpAddress().isEmpty()) {
			user_IpAddress.setText(objfriend.getIpAddress());
		}
		contentPane.add(user_IpAddress);
	}
	
	private void close() {
		 FriendoperationFrame.friendsInfoFream=null;
		this.setVisible(false);
	}
}
