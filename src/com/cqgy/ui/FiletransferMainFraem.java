package com.cqgy.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

public class FiletransferMainFraem extends JFrame {

	private JPanel contentPane;
	//接收界面
	public static clientFrame client;
    //发送界面
	public static SendFileFrame sendFileFrame;
	
	
	/**
	 * Create the frame.
	 */
	public FiletransferMainFraem() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//当前窗体可不显示
				close();
			}
		});
		// 窗体大小不能改变
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u63A5\u6536");
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client==null) {
					 client=new clientFrame();
				}
				JOptionPane.showOptionDialog(null, "因为没有采取线程的原因,程序启动一次文件传输只能传输一次","提示", JOptionPane.PLAIN_MESSAGE,
						 JOptionPane.QUESTION_MESSAGE,null, null, null);
				close();
			    client.setVisible(true);
			}
		});
		btnNewButton.setBounds(49, 126, 118, 115);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D1\u9001");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sendFileFrame==null) {
					 sendFileFrame=new SendFileFrame();
				}
				JOptionPane.showOptionDialog(null, "因为没有采取线程的原因,程序启动一次文件传输只能传输一次","提示", JOptionPane.PLAIN_MESSAGE,
						 JOptionPane.QUESTION_MESSAGE,null, null, null);
				close();
				sendFileFrame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(259, 123, 118, 115);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u4F7F\u7528\u89C4\u5219:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(22, 22, 63, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E00\u7AEF\u9009\u62E9\u63A5\u6536\uFF0C\u4E00\u7AEF\u9009\u62E9\u53D1\u9001\uFF0C\u63A5\u6536\u7AEF\u9009\u62E9\u4FDD\u5B58\u7684\u8DEF\u5F84");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(95, 22, 318, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u53D1\u9001\u7AEF\u9009\u62E9\u8981\u53D1\u9001\u7684\u6587\u4EF6;\u6CE8\u610F\u63A5\u6536\u7AEF\u8981\u7B49\u5F85\u53D1\u9001\u7AEF");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(95, 47, 292, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8FDB\u884C\u53D1\u9001\u540E\u518D\u8FDB\u884C\u63A5\u6536\uFF0C\u4E0D\u7136\u4F1A\u51FA\u73B0\u5F02\u5E38\u3002");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(93, 72, 284, 15);
		contentPane.add(lblNewLabel_3);
	}

	public void close(){
		this.setVisible(false);
	}
}
