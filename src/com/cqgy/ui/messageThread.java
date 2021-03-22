package com.cqgy.ui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.cqgy.bean.ClientInfo;
import com.cqgy.qq.Server;

public class messageThread {
	public static List<DatagramSocket> clienList = new ArrayList<>();
	// public static HashMap<DatagramSocket, DatagramPacket > clientHandlerMap
	// =new HashMap<>();

	public static void SendSeeion(DatagramPacket packet) {
		for (DatagramSocket datagramSocket : clienList) {
			try {
				datagramSocket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void SendSeeionone(DatagramPacket packet) {
		JSONObject	jsonObj=null;
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(Server.SERVER_PORT);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (ClientInfo info : Server.clientList) {
			try {
				// 创建DatagramPacket对象，用于向客户端发送数据
				byte[] b = jsonObj.toString().getBytes();
				packet = new DatagramPacket(b, b.length, info.getAddress(), info.getPort());
				// 转发给好友
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public static void SendSeeionty(DatagramPacket packet1, byte [] b) {
		InetAddress address = packet1.getAddress();
		int port = packet1.getPort();
		DatagramPacket packet = new DatagramPacket(b, b.length, address, port);
		byte [] b2=Arrays.copyOf(b,b.length);
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(Server.SERVER_PORT);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (ClientInfo info : Server.clientList) {
			try {
				packet = new DatagramPacket(b2, b2.length, info.getAddress(), info.getPort());
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	

}





