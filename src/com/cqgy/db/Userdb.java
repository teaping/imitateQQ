package com.cqgy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqgy.bean.Friend;
import com.cqgy.bean.User;
import com.cqgy.bean.UserInfo;
import com.cqgy.common.WriteErrorLog;

/*
 * �û����ݿ������:�ܶദ�����ظ����Ǹ��ü���
 */
public class Userdb {
	//ʵ��ע�Ṧ��
		public  static boolean Regisration(User user,String sql){
		 	int i=0;
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	//��ʼ������
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
			        pstmt.setString(1,user.getUser_id() );
			        pstmt.setString(2, user.getUser_pwd());
			        pstmt.setString(3, user.getUser_name());
			        pstmt.setString(4, user.getUser_icon());
			        pstmt.setString(5, user.getState());
			        i=pstmt.executeUpdate();
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean Regisration(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			 
			   if (i==0) {
				  return false;
			   }
			return true;
		}
		
		//��Ӻ���
		public  static boolean addFirend(Friend friend,String sql){
		 	int i=0;
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	System.out.println(friend.getUser_id1()+"------friend.getUser_id1()");
			    	System.out.println(friend.getUser_id2()+"-------friend.getUser_id2()");
			    	//��ʼ������
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
			        pstmt.setString(1,friend.getUser_id1());
			        pstmt.setString(2, friend.getUser_id2());
			        i=pstmt.executeUpdate();
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean Regisration(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			 
			   if (i==0) {
				  return false;
			   }
			return true;
		}
		
		
		
		
		// ��ѯ�����û���Ϣ
		public static List<Map<String, String>> findAll(String sql) {

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

			try (// 2.�������ݿ�����
					Connection conn = DBHelper.getConnection();
					// 3. ����������
					PreparedStatement pstmt = conn.prepareStatement(sql);
					// 5. ִ�в�ѯ
					ResultSet rs = pstmt.executeQuery();) {

				// 6. ���������
				while (rs.next()) {

					Map<String, String> row = new HashMap<String, String>();
					row.put("user_id", rs.getString("user_id"));
					row.put("user_name", rs.getString("user_name"));
					row.put("user_pwd", rs.getString("user_pwd"));
					row.put("user_icon", rs.getString("user_icon"));

					list.add(row);
				}

			} catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static List<Map<String, String>> findAll(String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}

			return list;
			


		}
		
		
		
		// ����������ѯ
		public static Map<String, String> findById(String id,String sql) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, id);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				if (rs.next()) {
					
					Map<String, String> row = new HashMap<String, String>();
					row.put("user_id", rs.getString("user_id"));
					row.put("user_name", rs.getString("user_name"));
					row.put("user_pwd", rs.getString("user_pwd"));
					row.put("user_icon", rs.getString("user_icon"));

					return row;
				}

			} catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static Map<String, String> findById(String id,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			} finally { // �ͷ���Դ
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

			return null;
			

			
		}
		
		
		// ��ѯ���� �б�
		public static  List<Map<String, String>> findFriends(String id,String sql) throws SQLException {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Map<String, String>> friends = new ArrayList<Map<String, String>>();
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, id);
				pstmt.setString(2, id);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				while (rs.next()) {

					Map<String, String> row = new HashMap<String, String>();
					row.put("user_id", rs.getString("user_id"));
					row.put("user_name", rs.getString("user_name"));
					row.put("user_pwd", rs.getString("user_pwd"));
					row.put("user_icon", rs.getString("user_icon"));

					friends.add(row);
				}

			} catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static  List<Map<String, String>> findFriends(String id,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			} finally { // �ͷ���Դ
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

			return friends;
			
		}
		
		
		
		//��ѯ�û��������
		public static boolean Loginstatus(String userid,String sql) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, userid);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				if (rs.next()) {

					  String  result=rs.getString("state");
					  if (result.trim().equals("1")) {
						   return true;
					  }else{
						  return false;
					  }
					}
			} catch (Exception e) {
				// TODO: handle exception
				WriteErrorLog.SaveError("��class Userdb�µ�public boolean Loginstatus(String userid)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}
			
			return false;
		}
		
		
		//�޸��û�����״̬
		public  static boolean Changestatu(User user,String sql){
		 	int i=0;
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	//��ʼ������
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
			        pstmt.setString(1,user.getState() );
			        pstmt.setString(2, user.getUser_id());
			        i=pstmt.executeUpdate();
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean Regisration(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			 
			   if (i==0) {
				  return false;
			   }
			return true;
		}
		
		
		//�޸��û�����
		public  static boolean updateUserPwd(User user,String sql){
		 	int i=0;
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	//��ʼ������
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
			        pstmt.setString(1,user.getUser_pwd() );
			        pstmt.setString(2, user.getUser_id());
			        i=pstmt.executeUpdate();
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean updateUserPwd(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			 
			   if (i==0) {
				  return false;
			   }
			return true;
		}
		
		
		       //�޸��û��˺ź��û���
				public  static boolean updateUserInfo(User user,String sql,String user_id){
				 	int i=0;
					//����SQL���
					    PreparedStatement pstmt;
					    try {
					    	//��������
					    	Connection conn = DBHelper.getConnection();
					    	//��ʼ������
					        pstmt = (PreparedStatement) conn.prepareStatement(sql);
					        pstmt.setString(1,user.getUser_name() );
					        pstmt.setString(2, user.getUser_id());
					        if (!user_id.trim().isEmpty()) {
					        	pstmt.setString(3,user_id);
							}else
							{
						        pstmt.setString(3, user.getUser_id());	
							}
					        i=pstmt.executeUpdate();
					        pstmt.close();
					        conn.close();
					    } catch (SQLException e) {
					        e.printStackTrace();
					        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean updateUserInfo(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
					        e.printStackTrace();
					        return false;
					    }
					 
					   if (i==0) {
						  return false;
					   }
					return true;
				}
		
		//�޸��û�����
		public  static boolean deleteFirend(Friend objfriend,String sql){
		 	int i=0;
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	//��ʼ������
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
			        pstmt.setString(1,objfriend.getUser_id1());
			        pstmt.setString(2,objfriend.getUser_id2());
			        i=pstmt.executeUpdate();
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean updateUserPwd(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			 
			   if (i==0) {
				  return false;
			   }
			return true;
		}
		
		//�û�����Ⱥ
		public static boolean createPro(List ctreateList,String ctreatename,String ctreaId,String userName,List friendUserNames, String sql){
			//����SQL���
			    PreparedStatement pstmt;
			    try {
			    	//��������
			    	Connection conn = DBHelper.getConnection();
			    	//��ʼ������1
			        pstmt = (PreparedStatement) conn.prepareStatement(sql);
					pstmt.setString(1,ctreatename);
					pstmt.setInt(2,ctreateList.size()+1);
					pstmt.setString(3,userName);
					pstmt.setString(4, ctreaId);
					pstmt.setString(5, ctreaId);
					pstmt.addBatch();
			        
			        for (int j = 0; j < ctreateList.size(); j++) {
			        	String ctreateid=(String) ctreateList.get(j);
						pstmt.setString(1,ctreatename);
						pstmt.setInt(2,ctreateList.size()+1);
						String UserNames=(String) friendUserNames.get(j);
						pstmt.setString(3,UserNames);
						pstmt.setString(4, ctreateid);
						pstmt.setString(5, ctreaId);
						pstmt.addBatch();
					}
			        pstmt.executeBatch();
			        pstmt.clearBatch(); // �ǵ����Batch��
			        pstmt.close();
			        conn.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        WriteErrorLog.SaveError("��class Userdb�µ�public  static boolean Regisration(User user,String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
			        e.printStackTrace();
			        return false;
			    }
			return true;
		}

		public static List<Map<String, String>> SelectCrate(String useryouid, String sql) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Map<String, String>> Cteas = new ArrayList<Map<String, String>>();
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, useryouid);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				while (rs.next()) {
					System.out.println(rs.getString("ctreaName"));
					System.out.println(rs.getString("zqid"));
					Map<String, String> row = new HashMap<String, String>();
					row.put("ctreaName",rs.getString("ctreaName"));
					row.put("zqid", rs.getString("zqid"));
					Cteas.add(row);
				}
			}catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static List<Map<String, String>> findAll(String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return Cteas;
					
		}

		public static List<Map<String, String>> SelectCrateName(String createName, String sql) {			
			// TODO Auto-generated method stub
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Map<String, String>> Cteas = new ArrayList<Map<String, String>>();
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, createName);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				while (rs.next()) {
					Map<String, String> row = new HashMap<String, String>();
					row.put("ctreaName",rs.getString("A.ProName"));
					row.put("user_icon", rs.getString("B.user_icon"));
					row.put("ctreaId", rs.getString("A.NameId"));
					Cteas.add(row);
				}
			}catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static List<Map<String, String>> findAll(String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return Cteas;
		}

		
		public static String SelectIpone(String friendUserId, String sql) {
			// TODO Auto-generated method stub
			String ipones=null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, friendUserId);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				while (rs.next()) {
					ipones=rs.getString("ipone");
				}
			}catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static List<Map<String, String>> findAll(String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return ipones;
		}

		//��ѯǩ��
		public static String SelectAuto(String useryouid, String sql) {
			// TODO Auto-generated method stub
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String Auto=null;
			try {
				// 2.�������ݿ�����
				conn = DBHelper.getConnection();
				// 3. ����������
				pstmt = conn.prepareStatement(sql);
				// 4. �󶨲���
				pstmt.setString(1, useryouid);
				// 5. ִ�в�ѯ��R��
				rs = pstmt.executeQuery();
				// 6. ���������
				while (rs.next()) {
					Auto=rs.getString("Autograph");
				}
			}catch (SQLException e) {
				WriteErrorLog.SaveError("��class Userdb�µ�public static List<Map<String, String>> findAll(String sql)�г����쳣,�쳣��Ϣ:"+e.toString());
				e.printStackTrace();
			}finally {
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return Auto;
		}

		
		
}
