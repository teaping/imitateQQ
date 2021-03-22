package com.cqgy.qq;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.text.html.HTML.Tag;

import org.json.JSONObject;

import com.cqgy.bean.ClientInfo;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.method.UserDao;


public class Server {

	// �������
	public static final int COMMAND_LOGIN = 1; // ��¼����
	public static final int COMMAND_LOGOUT = 2; // ע������
	public static final int COMMAND_SENDMSG = 3; // ����Ϣ����
	public static int SERVER_PORT = 7788;
	// �����Ѿ���¼�Ŀͻ�����Ϣ
	public static List<ClientInfo> clientList = new CopyOnWriteArrayList<ClientInfo>();
	// �������ݷ��ʶ���
	static UserDao dao=new UserDao();

	
	public static void Start() throws SQLException {

		
		/*
		if (args.length == 1) {
			SERVER_PORT = Integer.parseInt(args[0]);
		}
		
		*/
		
		//��ȡ�˿�
		SERVER_PORT=SetparametersFrameServer.clientinfo.getPort();
		System.out.println(SERVER_PORT);
		
		System.out.printf("����������, �����Լ��Ķ˿�%d...\n", SERVER_PORT);

		byte[] buffer = new byte[2048];
		
		
			
		try ( // ����DatagramSocket���󣬼����Լ��Ķ˿�7788
				DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {

			while (true) {
                System.out.println(SERVER_PORT);
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				// �������ݳ���
				int len = packet.getLength();
				String str = new String(buffer, 0, len);
				// �ӿͻ��˴��������ݰ��еõ��ͻ��˵�ַ
				InetAddress address = packet.getAddress();
				System.out.println(address);
				// �ӿͻ��˴��������ݰ��еõ��ͻ��˶˿ں�
				int port = packet.getPort();
				System.out.println(port);

				JSONObject jsonObj = new JSONObject(str);
				System.out.println(jsonObj);

				int cmd = (int) jsonObj.get("command");

				if (cmd == COMMAND_LOGIN) {// �û���¼����
					// ͨ���û�Id��ѯ�û���Ϣ
					String userId = (String) jsonObj.get("user_id");
					Map<String, String> user = dao.findById(userId);

					// �жϿͻ��˷��͹��������������ݿ�������Ƿ�һ��
					if (user != null && jsonObj.get("user_pwd").equals(user.get("user_pwd"))) {
						JSONObject sendJsonObj = new JSONObject(user);
						// ���result:0��ֵ�ԣ�0��ʾ�ɹ���-1��ʾʧ��
						sendJsonObj.put("result", 0);

						ClientInfo cInfo = new ClientInfo();
						cInfo.setUserId(userId);
						cInfo.setAddress(address);
						cInfo.setPort(port);
						clientList.add(cInfo);

						// ȡ�������û��б�
						List<Map<String, String>> friends = dao.findFriends(userId);

						// ���ú���״̬������friends���ϣ����online�ֶ�
						for (Map<String, String> friend : friends) {
							// ��Ӻ���״̬ 1���� 0����
							friend.put("online", "0");
							String fid = friend.get("user_id");
							// ������clientList�����д��ڣ�������
							for (ClientInfo c : clientList) {
								String uid = c.getUserId();
								// ��������
								if (uid.equals(fid)) {
									// ���º���״̬ 1���� 0����
									friend.put("online", "1");
									break;
								}
							}
						}
						sendJsonObj.put("friends", friends);

						// ����DatagramPacket����������ͻ��˷�������
						byte[] b = sendJsonObj.toString().getBytes();
						packet = new DatagramPacket(b, b.length, address, port);

						socket.send(packet);
						
						// �㲥��ǰ�û�������
						for (ClientInfo info : clientList) {
							// ���������ѷ��ͣ���ǰ�û�������Ϣ
							if (!info.getUserId().equals(userId)) {
								jsonObj = new JSONObject();
								jsonObj.put("user_id", userId);
								jsonObj.put("online", "1");

								byte[] b2 = jsonObj.toString().getBytes();
								packet = new DatagramPacket(b2, b2.length, info.getAddress(), info.getPort());
								// ת��������
								socket.send(packet);
							}
						}

					} else {
						// ��ʧ����Ϣ
						JSONObject sendJsonObj = new JSONObject();
						sendJsonObj.put("result", -1);
						byte[] b = sendJsonObj.toString().getBytes();
						packet = new DatagramPacket(b, b.length, address, port);
						// �������¼�Ŀͻ��˷�������
						socket.send(packet);
					}
				} else if (cmd == COMMAND_SENDMSG) {// �û�������Ϣ
					// ��ú���Id
					String friendUserId = (String) jsonObj.get("receive_user_id");

					// ��ͻ��˷�������
					for (ClientInfo info : clientList) {
						// �ҵ����ѵ�IP��ַ�Ͷ˿ں�
						if (info.getUserId().equals(friendUserId)) {
							jsonObj.put("OnlineUserList", getUserOnlineStateList());

							// ����DatagramPacket����������ͻ��˷�������
							byte[] b = jsonObj.toString().getBytes();
							packet = new DatagramPacket(b, b.length, info.getAddress(), info.getPort());
							// ת��������
							socket.send(packet);
							break;
						}
					}
				} else if (cmd == COMMAND_LOGOUT) {// �û�����ע������

					// ����û�Id
					String userId = (String) jsonObj.get("user_id");

					// ��clientList������ɾ���û�
					for (ClientInfo info : clientList) {
						if (info.getUserId().equals(userId)) {
							clientList.remove(info);
							break;
						}
					}

					// �������ͻ��˹㲥���û�����
					for (ClientInfo info : clientList) {

						jsonObj = new JSONObject();
						jsonObj.put("user_id", userId);
						jsonObj.put("online", "0");

						byte[] b2 = jsonObj.toString().getBytes();
						packet = new DatagramPacket(b2, b2.length, info.getAddress(), info.getPort());
						socket.send(packet);
					}
				}
			}
		} catch (IOException e) {
			WriteErrorLog.SaveError("��class Server�µ�public static void main(String[] args)�г����쳣,�쳣��Ϣ:"+e.toString());
			e.printStackTrace();
		}
	}

	// ����û�����״̬
	private static List<Map<String, String>> getUserOnlineStateList() {
		//�����ݿ��ѯ�����û���Ϣ
		List<Map<String, String>> userList = dao.findAll();
		//�����û�����״̬����
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (Map<String, String> user : userList) {
			
			String userId = user.get("user_id");
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", userId);
			// Ĭ������
			map.put("online", "0");

			for (ClientInfo info : clientList) {
				//���clientList���Ѿ���¼�Ŀͻ�����Ϣ�����и��û�������û�����
				if (info.getUserId().equals(userId)) {
					// ����Ϊ����
					map.put("online", "1");
					break;
				}
			}
			list.add(map);
		}
		return list;
	}

	
}
