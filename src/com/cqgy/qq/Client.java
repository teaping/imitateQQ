

package com.cqgy.qq;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;

import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.parser.TagElement;

import com.cqgy.ui.LoginFrame;
import com.cqgy.ui.messageThread;


public class Client {

	// 命令代码
	public static final int COMMAND_LOGIN = 1; // 登录命令
	public static final int COMMAND_LOGOUT = 2; // 注销命令
	public static final int COMMAND_SENDMSG = 3; // 发消息命令

	public static DatagramSocket socket=null;
	public static HashMap<DatagramSocket, DatagramPacket > clientHandlerMap =new HashMap<>();
	// 服务器端IP
	public static String SERVER_IP = "127.0.0.2";
	// 服务器端端口号
	public static int SERVER_PORT = 7788;
	
	public static int tag=0;

	
	public static void start() {
			if (tag==1) {
				
				try {
					System.out.println(SERVER_IP+";"+SERVER_PORT);
					// 创建DatagramSocket对象，由系统分配可以使用的端口
					socket = new DatagramSocket();
					messageThread.clienList.add(socket);
					//System.out.println(socket.getLocalPort());
					// 设置超时0.5秒，不再等待接收数据
					socket.setSoTimeout(5000);
					System.out.println("客户端运行...");
					LoginFrame frame=new LoginFrame();
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}else if (tag==2) {
					
				try {
					// 创建DatagramSocket对象，由系统分配可以使用的端口
					socket = new DatagramSocket();
					messageThread.clienList.add(socket);
					// 设置超时0.5秒，不再等待接收数据
					socket.setSoTimeout(5000);
					System.out.println("客户端运行...");
					LoginFrame frame=new LoginFrame();
					frame.setVisible(true);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			}


	
		
		

			/*
				
	public static void main(String[] args) {
		if (args.length == 2) {
			SERVER_IP = args[0];
			SERVER_PORT = Integer.parseInt(args[1]);
		}
			
		try {// 创建DatagramSocket对象，由系统分配可以使用的端口
			socket = new DatagramSocket();
			// 设置超时0.5秒，不再等待接收数据
			socket.setSoTimeout(5000);
			System.out.println("客户端运行...");
			LoginFrame frame=new LoginFrame();
			frame.setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
		*/
		

	
}
