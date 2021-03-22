package com.cqgy.method;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import com.cqgy.bean.Friend;
import com.cqgy.bean.User;
import com.cqgy.qq.Client;
import com.cqgy.common.ReaderFileString;
import com.cqgy.common.SaveFileString;
import com.cqgy.common.WriteErrorLog;
import com.cqgy.db.DBHelper;
import com.cqgy.db.Userdb;

public class UserDao {
    //int errtage=0;//�����쳣��ʶ
   // ʵ�ֵ��빦��
	public Map login(User objUser) {
			// ׼��һ��������
			byte[] buffer = new byte[1024];
			InetAddress address;
			try {
				address = InetAddress.getByName(Client.SERVER_IP);
				//System.out.println(address);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGIN);
				jsonObj.put("user_id", objUser.getUser_id().trim());
				jsonObj.put("user_pwd", objUser.getUser_pwd());
				// �ֽ�����
				byte[] b = jsonObj.toString().getBytes();
				// ����DatagramPacket����
				DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
				// ����
				Client.socket.send(packet);

				/* �������ݱ� */
				packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				Client.socket.receive(packet);
				// �������ݳ���
				int len = packet.getLength();
				String str = new String(buffer, 0, len);
				System.out.println("receivedjsonObj = " + str);
				JSONObject receivedjsonObj = new JSONObject(str);

				if ((Integer) receivedjsonObj.get("result") == 0) {
					Map userMap = receivedjsonObj.toMap();
					if (userMap==null) {
						JOptionPane.showMessageDialog(null, "��QQ��������벻��ȷ");
					}
					 return userMap;
				}
			} catch (Exception e) {
				WriteErrorLog.SaveError("��class UserDao�µ�public void login(User objUser)�г����쳣,�쳣��Ϣ:"+e.toString());
				JOptionPane.showMessageDialog(null, "���ӷ�������ʱ!,�쳣��Ϣ:\n"+e.toString());
				return null;
			}
			return null;
	}
	
	
	//ʵ��ע�Ṧ��
	public boolean Regisration(User user){
		//����SQL���
		String sql="INSERT INTO user VALUES(?,?,?,?,?);";
		return Userdb.Regisration(user, sql);
	}
	
	// ��ѯ�����û���Ϣ
	public List<Map<String, String>> findAll() {
		// SQL���
		String sql = "select user_id,user_pwd,user_name, user_icon from user";
	    return Userdb.findAll(sql);
	}

	// ����������ѯ
	public Map<String, String> findById(String id) {
		// SQL���
		String sql = "select user_id,user_pwd,user_name, user_icon from user where user_id = ?";
        return Userdb.findById(id,sql);
	}
	
	//��ѯ�Ƿ�Ϊ����
	public boolean selectFriends(String userid,String friendid) throws SQLException {
		 List<Map<String, String>> objfindFriends=findFriends(userid);
		// ��ʼ�������б�
			for (int i = 0; i < objfindFriends.size(); i++) {
				Map<String, String> friend = objfindFriends.get(i);
				String friendUserId = friend.get("user_id");
				if (friendid.equals(friendUserId)) {
					return true;
				}
			}
		 
		return false;
	}
	
	
	 //��ѯ�û��������
	public boolean Loginstatus(String userid){
		String sql="select state from user where user_id=?";
		return Userdb.Loginstatus(userid,sql);
	}
	
	//��ע:�����û���;�޸��û�����״̬;�����û���������Կ��ǲ��ø��ü���
	
	//�޸��û�����״̬
	public   boolean Changestatu(User user){
		String sql="update  user set state=? where user_id=?";
		return Userdb.Changestatu(user, sql);
	}
	
	
	//�޸��û�����
	public boolean updateuserPwd(User user) {
		String sql="update user set user_pwd=? where user_id=? ";
		return Userdb.updateUserPwd(user, sql);
	}
	
	   //�����û���
		public boolean updateUserInfo(User user,String user_id) {
			String sql="update user set user_name=? ,user_id=? where user_id=?";
			return Userdb.updateUserInfo(user, sql,user_id);
		}
	
	
	//ɾ������
	public boolean	deleteFirend(Friend objfFriend) {
		String sql="delete  from friend  where user_id1=? and  user_id2=?";
		
		return Userdb.deleteFirend(objfFriend,sql);
	}
	
	
	
	
	// ��ѯ���� �б�
	public  List<Map<String, String>> findFriends(String id) throws SQLException {
		// SQL���
		String sql = "select user_id,user_pwd,user_name,user_icon FROM user " + " WHERE "
				+ "    user_id IN (select user_id2 as user_id  from friend where user_id1 = ?)"
				+ " OR user_id IN (select user_id1 as user_id  from friend where user_id2 = ?)";
			return Userdb.findFriends(id, sql);
	}
	
	//����Ⱥ��
	public boolean CreateFinds(List ctreateList,String ctreatename,String ctreaId,String userName,List friendUserNames){
		//SQL���
		String sql="INSERT INTO flocks(ctreaName,ProNums,ProName,NameId,zqid)VALUES(?,?,?,?,?)";
		return Userdb.createPro(ctreateList, ctreatename, ctreaId, userName, friendUserNames, sql);
	}
	
	
	
	//�����˺ź�����
	public  void SaveUserInfo(String userId, String password){
	    SaveFileString.SaveFileStr(userId,password,"Userinfo.txt",false);
	}
	
	//�����������ϵ�ѡ��ť״̬
	public void Savestate(boolean saveuserInfoState,boolean autoLogin) {
		String state1="0",state2="0";
		if (saveuserInfoState) {
			state1="1";
		}
		if (autoLogin) {
			state2="1";
		}
		SaveFileString.SaveFileStr(state1,state2,"saveAutoLoginState.txt",false);
	}
	
	
	
	//��ȡ�Զ�����״̬
	public ArrayList<String> readerAutoLoginState() {
		try {
			return ReaderFileString.readerFilestr("saveAutoLoginState.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WriteErrorLog.SaveError("��class UserDao�µ�public ArrayList<String> readerAutoLoginState()�г����쳣,�쳣��Ϣ:"+e.toString());
			return null;
		}
	}
	
	
	//��ȡ�˺ź�����
	public ArrayList<String> readerUserInfo() {
		try {
			return ReaderFileString.readerFilestr("Userinfo.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WriteErrorLog.SaveError("��class UserDao�µ�public ArrayList<String> readerUserInfo()�г����쳣,�쳣��Ϣ:"+e.toString());
			return null;
		}
	}
	
	//��Ӻ���
	public boolean addFirend(Friend objfriend) {
		//����SQL���
		String sql="INSERT INTO friend VALUES(?,?);";
		return Userdb.addFirend(objfriend, sql);
	}

	//��ѯȺ
	public List<Map<String, String>> SelectCreate(String useryouid) {
		String sql="select ctreaName,zqid from flocks where NameId=? ";
		return Userdb.SelectCrate(useryouid, sql);
	}

	//��ѯȺ�ĺ���
	public List<Map<String, String>> SelectCreates(String createName) {
		// TODO Auto-generated method stub
		String sql="SELECT A.NameId,A.ProName,B.user_icon FROM flocks A LEFT JOIN `user` B ON A.NameId = B.user_id WHERE ctreaName=?";
		return Userdb.SelectCrateName(createName, sql);
	}


	public String SelectIpone(String friendUserId) {
		// TODO Auto-generated method stub
		String sql="select ipone from user where user_id=?";
		return Userdb.SelectIpone(friendUserId,sql);
	}


	//��ѯǩ��
	public String SelectAutograph(String useryouid) {
		// TODO Auto-generated method stub
		String sql="select Autograph from infouser where id=?";
		return Userdb.SelectAuto(useryouid,sql);
	}
	
	
	
	
	
	
	
		
}
