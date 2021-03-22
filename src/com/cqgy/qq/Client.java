

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

	// �������
	public static final int COMMAND_LOGIN = 1; // ��¼����
	public static final int COMMAND_LOGOUT = 2; // ע������
	public static final int COMMAND_SENDMSG = 3; // ����Ϣ����

	public static DatagramSocket socket=null;
	public static HashMap<DatagramSocket, DatagramPacket > clientHandlerMap =new HashMap<>();
	// ��������IP
	public static String SERVER_IP = "127.0.0.2";
	// �������˶˿ں�
	public static int SERVER_PORT = 7788;
	
	public static int tag=0;

	
	public static void start() {
			if (tag==1) {
				
				try {
					System.out.println(SERVER_IP+";"+SERVER_PORT);
					// ����DatagramSocket������ϵͳ�������ʹ�õĶ˿�
					socket = new DatagramSocket();
					messageThread.clienList.add(socket);
					//System.out.println(socket.getLocalPort());
					// ���ó�ʱ0.5�룬���ٵȴ���������
					socket.setSoTimeout(5000);
					System.out.println("�ͻ�������...");
					LoginFrame frame=new LoginFrame();
					frame.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}else if (tag==2) {
					
				try {
					// ����DatagramSocket������ϵͳ�������ʹ�õĶ˿�
					socket = new DatagramSocket();
					messageThread.clienList.add(socket);
					// ���ó�ʱ0.5�룬���ٵȴ���������
					socket.setSoTimeout(5000);
					System.out.println("�ͻ�������...");
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
			
		try {// ����DatagramSocket������ϵͳ�������ʹ�õĶ˿�
			socket = new DatagramSocket();
			// ���ó�ʱ0.5�룬���ٵȴ���������
			socket.setSoTimeout(5000);
			System.out.println("�ͻ�������...");
			LoginFrame frame=new LoginFrame();
			frame.setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
		*/
		

	
}
