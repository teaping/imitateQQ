package com.cqgy.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;

public class AvatarFrame extends JFrame {
	private JPanel contentPane;

	public static String friendUserId;

	public AvatarFrame(int x, int y, String id) {

		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				close();
			}
		}, 1500);

		// 获取好友ID
		this.friendUserId = id;
		// 窗体大小不能改变
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);// 采用指定的窗口装饰风格
		setBounds(x, y, 154, 23);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_2 = new JButton("修改头像");
		btnNewButton_2.addActionListener(new ActionListener() {
			// 查看资料
			public void actionPerformed(ActionEvent e) {
				new Avatarchange(friendUserId);
			}
		});
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(0, 0, 154, 23);
		contentPane.add(btnNewButton_2);
	}

	public void close() {
		MainFrame.avatarfrme = null;
		this.setVisible(false);
	}

}
