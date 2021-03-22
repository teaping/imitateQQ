package com.cqgy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqgy.bean.UserInfo;
import com.cqgy.common.WriteErrorLog;

public class Infodb {

	public static boolean InsertInfoUser(UserInfo info, String sql) {
		// TODO Auto-generated method stub
		  PreparedStatement pstmt;
		    try {
		    	
//		    	String sql="insert into InfoUser(Id,NickName,Autograph,Gender,Birthday,Location,School,company,job) VALUES (?,?,?,?,?,?,?,?,?) ";
		    	
		    	//创建连接
		    	Connection conn = DBHelper.getConnection();
		    	//初始化参数
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setString(1,info.getId());
		        pstmt.setString(2,info.getNickname());
		        pstmt.setString(3,info.getAutograph());
		        pstmt.setString(4,info.getGender());
		        pstmt.setString(5,info.getBirthday());
		        pstmt.setString(6,info.getLocation());
		        pstmt.setString(7,info.getSchool());
		        pstmt.setString(8,info.getCompany());
		        pstmt.setString(9,info.getJob());
				pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        WriteErrorLog.SaveError("在class Userdb下的public  static boolean Regisration(User user,String sql)中出现异常,异常信息:"+e.toString());
		        e.printStackTrace();
		        return false;
		    }
		    
		    return true;
	}

	public static UserInfo SelectInfoUser(String id, String sql) {
		// TODO Auto-generated method stub
		UserInfo user=new UserInfo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, id);
			// 5. 执行查询（R）
			rs = pstmt.executeQuery();
			// 6. 遍历结果集
			while (rs.next()) {
				user.setId(rs.getString("Id"));
				user.setNickname(rs.getString("NickName"));
				user.setAutograph(rs.getString("Autograph"));
				user.setBirthday(rs.getString("Birthday"));
				user.setCompany(rs.getString("company"));
				user.setGender(rs.getString("Gender"));
				user.setSchool(rs.getString("School"));
				user.setJob(rs.getString("job"));
				user.setLocation(rs.getString("Location"));
			}

		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return user;
	}

	//查询用户信息
	public static int SelectUserone(String id, String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 int count=0;
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, id);
			// 5. 执行查询（R）
			 rs = pstmt.executeQuery(); 
			 if ( rs.next()) {
			   //当存在时，执行这里的代码
				count = rs.getInt(1); 
			 } else {
			   //当不存在时，执行这里的代码
			 }
		 
			
			 return count;
		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return 0;
	}

	public static boolean UpdatayUserone(UserInfo info, String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
//		update infouser set NickName=?, Autograph=?,Gender=?,Birthday=?,Location=?,School=?,company=?,job=? where Id=?";
		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, info.getNickname());
			pstmt.setString(2, info.getAutograph());
			pstmt.setString(3, info.getGender());
			pstmt.setString(4, info.getBirthday());
			pstmt.setString(5, info.getLocation());
			pstmt.setString(6, info.getSchool());
			pstmt.setString(7, info.getCompany());
			pstmt.setString(8, info.getJob());
			pstmt.setString(9, info.getId());
			// 5. 执行查询（R）
			int i= pstmt.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}

	public static boolean UpdateUser(String id,String name, String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
//		update infouser set NickName=?, Autograph=?,Gender=?,Birthday=?,Location=?,School=?,company=?,job=? where Id=?";
		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			// 5. 执行查询（R）
			int i= pstmt.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}

	//查询name
	public static String SelectUserId(String id, String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String User_id=null;
		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, id);
			// 5. 执行查询（R）
			 rs = pstmt.executeQuery(); 
			 if(rs.next()){
				 User_id=rs.getString("user_name");
			 }
		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return User_id;
	}

	public static boolean UpdataUsericon(String toux, String id, String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
//		update infouser set NickName=?, Autograph=?,Gender=?,Birthday=?,Location=?,School=?,company=?,job=? where Id=?";
		try {
			// 2.创建数据库连接
			conn = DBHelper.getConnection();
			// 3. 创建语句对象
			pstmt = conn.prepareStatement(sql);
			// 4. 绑定参数
			pstmt.setString(1, toux);
			pstmt.setString(2, id);
			// 5. 执行查询（R）
			int i= pstmt.executeUpdate();
			if(i!=0){
				return true;
			}
		} catch (SQLException e) {
			WriteErrorLog.SaveError("在class Userdb下的public static  List<Map<String, String>> findFriends(String id,String sql)中出现异常,异常信息:"+e.toString());
			e.printStackTrace();
		} finally { // 释放资源
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}

}
