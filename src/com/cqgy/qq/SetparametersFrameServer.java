package com.cqgy.qq;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.net.DatagramSocket;
import java.sql.SQLException;

import com.cqgy.bean.ClientInfo;
import com.cqgy.common.SaveFileString;
import com.cqgy.qq.Client;
import com.cqgy.qq.Server;
import com.cqgy.ui.LoginFrame;

import javax.sound.sampled.Port;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetparametersFrameServer extends JFrame {

	private JPanel contentPane;
	private JTextField inputPort;
	public static  ClientInfo  clientinfo;

	public static void main(String[] args) {
		SetparametersFrameServer setparametersFrame = new SetparametersFrameServer();
		setparametersFrame.setLocationRelativeTo(null);
		setparametersFrame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SetparametersFrameServer() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		createView();
	}

	private void createView() {
		setType(Type.UTILITY);
		setBounds(100, 100, 440, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\u7AEF\u53E3\u53F7:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(75, 77, 54, 15);
		contentPane.add(lblNewLabel_1);

		inputPort = new JTextField();
		inputPort.setForeground(Color.WHITE);
		inputPort.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		inputPort.setOpaque(false);
		inputPort.setBounds(139, 73, 188, 21);
		contentPane.add(inputPort);
		inputPort.setColumns(10);

		JButton btn_setingParamete = new JButton("\u8BBE\u7F6E");
		btn_setingParamete.setOpaque(false);
		btn_setingParamete.setForeground(Color.WHITE);
		// #05BAFB
		btn_setingParamete.setBackground(new Color(05, 186, 251));// 设置登录按钮字体颜色
		btn_setingParamete.addActionListener(new ActionListener() {
			// 设置点击事件
			public void actionPerformed(ActionEvent e) {
				if (inputPort.getText().trim().isEmpty()) {
					return;
				}
			
				clientinfo=new ClientInfo();
				clientinfo.setPort(new Integer(inputPort.getText().trim()));
				//将端口写入到指定文件中
				SaveFileString.SaveFileStr("0", Integer.toString(clientinfo.getPort()), "clientinfo.txt", false);
				// 关闭当前窗体
				close();
				try {
					Server.Start();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_setingParamete.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btn_setingParamete.setBounds(146, 127, 93, 23);
		contentPane.add(btn_setingParamete);
		// 设置背景
		ImageIcon img = new ImageIcon("Image/20200621000229.gif");// 这是背景图片
		JLabel imgLabel = new JLabel(img);// 将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景标签的位置
		Container cp = this.getContentPane();
		((JPanel) cp).setOpaque(false); // 注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
	}

	private void close() {
		this.setVisible(false);
	}
}
