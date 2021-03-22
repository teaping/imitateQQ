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
    //int errtage=0;//定义异常标识
   // 实现登入功能
	public Map login(User objUser) {
			// 准备一个缓冲区
			byte[] buffer = new byte[1024];
			InetAddress address;
			try {
				address = InetAddress.getByName(Client.SERVER_IP);
				//System.out.println(address);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("command", Client.COMMAND_LOGIN);
				jsonObj.put("user_id", objUser.getUser_id().trim());
				jsonObj.put("user_pwd", objUser.getUser_pwd());
				// 字节数组
				byte[] b = jsonObj.toString().getBytes();
				// 创建DatagramPacket对象
				DatagramPacket packet = new DatagramPacket(b, b.length, address, Client.SERVER_PORT);
				// 发送
				Client.socket.send(packet);

				/* 接收数据报 */
				packet = new DatagramPacket(buffer, buffer.length, address, Client.SERVER_PORT);
				Client.socket.receive(packet);
				// 接收数据长度
				int len = packet.getLength();
				String str = new String(buffer, 0, len);
				System.out.println("receivedjsonObj = " + str);
				JSONObject receivedjsonObj = new JSONObject(str);

				if ((Integer) receivedjsonObj.get("result") == 0) {
					Map userMap = receivedjsonObj.toMap();
					if (userMap==null) {
						JOptionPane.showMessageDialog(null, "您QQ号码或密码不正确");
					}
					 return userMap;
				}
			} catch (Exception e) {
				WriteErrorLog.SaveError("在class UserDao下的public void login(User objUser)中出现异常,异常信息:"+e.toString());
				JOptionPane.showMessageDialog(null, "连接服务器超时!,异常信息:\n"+e.toString());
				return null;
			}
			return null;
	}
	
	
	//实现注册功能
	public boolean Regisration(User user){
		//创建SQL语句
		String sql="INSERT INTO user VALUES(?,?,?,?,?);";
		return Userdb.Regisration(user, sql);
	}
	
	// 查询所有用户信息
	public List<Map<String, String>> findAll() {
		// SQL语句
		String sql = "select user_id,user_pwd,user_name, user_icon from user";
	    return Userdb.findAll(sql);
	}

	// 按照主键查询
	public Map<String, String> findById(String id) {
		// SQL语句
		String sql = "select user_id,user_pwd,user_name, user_icon from user where user_id = ?";
        return Userdb.findById(id,sql);
	}
	
	//查询是否为好友
	public boolean selectFriends(String userid,String friendid) throws SQLException {
		 List<Map<String, String>> objfindFriends=findFriends(userid);
		// 初始化好友列表
			for (int i = 0; i < objfindFriends.size(); i++) {
				Map<String, String> friend = objfindFriends.get(i);
				String friendUserId = friend.get("user_id");
				if (friendid.equals(friendUserId)) {
					return true;
				}
			}
		 
		return false;
	}
	
	
	 //查询用户登入情况
	public boolean Loginstatus(String userid){
		String sql="select state from user where user_id=?";
		return Userdb.Loginstatus(userid,sql);
	}
	
	//备注:更改用户名;修改用户登入状态;更改用户名这里可以考虑采用复用技术
	
	//修改用户登入状态
	public   boolean Changestatu(User user){
		String sql="update  user set state=? where user_id=?";
		return Userdb.Changestatu(user, sql);
	}
	
	
	//修改用户密码
	public boolean updateuserPwd(User user) {
		String sql="update user set user_pwd=? where user_id=? ";
		return Userdb.updateUserPwd(user, sql);
	}
	
	   //更改用户名
		public boolean updateUserInfo(User user,String user_id) {
			String sql="update user set user_name=? ,user_id=? where user_id=?";
			return Userdb.updateUserInfo(user, sql,user_id);
		}
	
	
	//删除好友
	public boolean	deleteFirend(Friend objfFriend) {
		String sql="delete  from friend  where user_id1=? and  user_id2=?";
		
		return Userdb.deleteFirend(objfFriend,sql);
	}
	
	
	
	
	// 查询好友 列表
	public  List<Map<String, String>> findFriends(String id) throws SQLException {
		// SQL语句
		String sql = "select user_id,user_pwd,user_name,user_icon FROM user " + " WHERE "
				+ "    user_id IN (select user_id2 as user_id  from friend where user_id1 = ?)"
				+ " OR user_id IN (select user_id1 as user_id  from friend where user_id2 = ?)";
			return Userdb.findFriends(id, sql);
	}
	
	//创建群聊
	public boolean CreateFinds(List ctreateList,String ctreatename,String ctreaId,String userName,List friendUserNames){
		//SQL语句
		String sql="INSERT INTO flocks(ctreaName,ProNums,ProName,NameId,zqid)VALUES(?,?,?,?,?)";
		return Userdb.createPro(ctreateList, ctreatename, ctreaId, userName, friendUserNames, sql);
	}
	
	
	
	//保存账号和密码
	public  void SaveUserInfo(String userId, String password){
	    SaveFileString.SaveFileStr(userId,password,"Userinfo.txt",false);
	}
	
	//保存登入界面上单选按钮状态
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
	
	
	
	//读取自动登入状态
	public ArrayList<String> readerAutoLoginState() {
		try {
			return ReaderFileString.readerFilestr("saveAutoLoginState.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WriteErrorLog.SaveError("在class UserDao下的public ArrayList<String> readerAutoLoginState()中出现异常,异常信息:"+e.toString());
			return null;
		}
	}
	
	
	//读取账号和密码
	public ArrayList<String> readerUserInfo() {
		try {
			return ReaderFileString.readerFilestr("Userinfo.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WriteErrorLog.SaveError("在class UserDao下的public ArrayList<String> readerUserInfo()中出现异常,异常信息:"+e.toString());
			return null;
		}
	}
	
	//添加好友
	public boolean addFirend(Friend objfriend) {
		//创建SQL语句
		String sql="INSERT INTO friend VALUES(?,?);";
		return Userdb.addFirend(objfriend, sql);
	}

	//查询群
	public List<Map<String, String>> SelectCreate(String useryouid) {
		String sql="select ctreaName,zqid from flocks where NameId=? ";
		return Userdb.SelectCrate(useryouid, sql);
	}

	//查询群的好友
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


	//查询签名
	public String SelectAutograph(String useryouid) {
		// TODO Auto-generated method stub
		String sql="select Autograph from infouser where id=?";
		return Userdb.SelectAuto(useryouid,sql);
	}
	
	
	
	
	
	
	
		
}
