package com.cqgy.qq;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.cqgy.bean.ClientInfo;
import com.cqgy.common.ReaderFileString;
import com.cqgy.common.SaveFileString;
import com.cqgy.qq.Client;
import com.cqgy.ui.LoginFrame;
import com.cqgy.util.ValidateUtil;

import javax.sound.sampled.Port;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetparametersFrameClient extends JFrame {

	private JPanel contentPane;
	private JTextField inputIp;
	private JTextField inputPort;
    public static ClientInfo clientInfo;

  public static void main(String[] args) throws IOException {
	  SetparametersFrameClient setparametersFrame=new SetparametersFrameClient();
	  setparametersFrame.setLocationRelativeTo(null);
	  setparametersFrame.setVisible(true);
  }
	
	
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public SetparametersFrameClient() throws IOException {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			  System.exit(0);
			}
		});
	     createView();
	}



	private void createView() throws IOException {
		JOptionPane.showOptionDialog(null, "IP地址格式为A.B.C.D(127.0.0.1)A:首位必须为127,"+ "\r\nB,C,D均不可超过255不然无法连接!","提示", JOptionPane.PLAIN_MESSAGE,
				 JOptionPane.QUESTION_MESSAGE,null, null, null); 
		//获取端口号
		ArrayList<String> readerUserInfo= ReaderFileString.readerFilestr("clientinfo.txt");
		if (readerUserInfo.size()>0) {
			  clientInfo=new ClientInfo();
			  clientInfo.setPort(new Integer(readerUserInfo.get(1)));
			  JOptionPane.showOptionDialog(null, "服务器端口为"+Integer.toString(clientInfo.getPort())+"\r\n如果为0表示未启动服务器，将无法登入!","提示", JOptionPane.PLAIN_MESSAGE,
						 JOptionPane.QUESTION_MESSAGE,null, null, null);	
		}
		setBounds(100, 100, 440, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("IP\u5730\u5740:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setBounds(86, 59, 54, 15);
		contentPane.add(lblNewLabel);
		
		inputIp = new JTextField();
		inputIp.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		inputIp.setForeground(Color.WHITE);
		inputIp.setOpaque(false);
		inputIp.setBounds(150, 57, 188, 21);
		contentPane.add(inputIp);
		inputIp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7AEF\u53E3\u53F7:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(86, 113, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		inputPort = new JTextField();
		inputPort.setEditable(false);
		inputPort.setForeground(Color.WHITE);
		inputPort.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		inputPort.setOpaque(false);
		if (clientInfo!=null) {
			inputPort.setText(Integer.toString(clientInfo.getPort()));	
		}
		inputPort.setBounds(150, 111, 188, 21);
		contentPane.add(inputPort);
		inputPort.setColumns(10);
		
		JButton btn_setingParamete = new JButton("\u8BBE\u7F6E");
		btn_setingParamete.setOpaque(false);
		btn_setingParamete.setForeground(Color.WHITE);
		  //#05BAFB
		btn_setingParamete.setBackground(new Color(05,186,251));//设置登录按钮字体颜色
		btn_setingParamete.addActionListener(new ActionListener() {
			//设置点击事件
			public void actionPerformed(ActionEvent e) {
				//数据验证
				if (inputIp.getText().trim().isEmpty()) {
					JOptionPane.showOptionDialog(null, "IP不能为空","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					return;
				}
				
				if (inputPort.getText().trim().isEmpty()) {
					JOptionPane.showOptionDialog(null, "请启动服务器","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					return;
				}
				
				//通过正在表达式认证
				if (!ValidateUtil.isIPLegal(inputIp.getText().trim())) {
					JOptionPane.showOptionDialog(null, "IP不符合规则","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					return;
				}
				System.out.println(inputIp.getText().trim().substring(0,inputIp.getText().trim().indexOf(".")));
				//判断首位是否为127
				if (!inputIp.getText().trim().substring(0,inputIp.getText().trim().indexOf(".")).equals("127")) {
					JOptionPane.showOptionDialog(null, "IP首位必须为127","提示", JOptionPane.PLAIN_MESSAGE,
							 JOptionPane.QUESTION_MESSAGE,null, null, null);
					return;
				}
				
				
				Client.SERVER_IP=inputIp.getText().trim();
				try {
					clientInfo.setAddress(InetAddress.getByName(inputIp.getText().trim()));
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//将IP写入文件中去
				SaveFileString.SaveFileStr(inputIp.getText().trim(),Integer.toString(clientInfo.getPort()), "clientinfo.txt", false);
				Client.SERVER_PORT=new Integer(inputPort.getText().trim());
				Client.tag=1;
				//关闭当前窗体
				close();
				Client.start();
				
			}
		});
		btn_setingParamete.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btn_setingParamete.setBounds(173, 183, 93, 23);
		contentPane.add(btn_setingParamete);
		 //设置背景
        ImageIcon img = new ImageIcon("Image/20200621000229.gif");//这是背景图片
        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
        Container cp=this.getContentPane();
        cp.setLayout(new BorderLayout());
        ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
	}
	
	private void close() {
		this.setVisible(false);
	}
}
