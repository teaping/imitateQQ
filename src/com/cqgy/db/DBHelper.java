

package com.cqgy.db;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;


public class DBHelper {

	// �������ݿ�url
	static String url;
	// ����Properties����
	static Properties info = new Properties();

	// 1.�����������
	static {
		// ��������ļ�������
		InputStream input = DBHelper.class.getClassLoader()
				.getResourceAsStream("com/cqgy/db/config.properties");

		try {
			// ���������ļ����ݵ�Properties����
			info.load(input);
			// �������ļ���ȡ��url
			url = info.getProperty("url");
			// Class.forName("com.mysql.jdbc.Driver");
			// �������ļ���ȡ��driver
			String driverClassName = info.getProperty("driver");
			Class.forName(driverClassName);
			System.out.println("����������سɹ�...");
		} catch (ClassNotFoundException e) {
			System.out.println("�����������ʧ��...");
		} catch (IOException e) {
			System.out.println("���������ļ�ʧ��...");
		}
	}

	public static Connection getConnection() throws SQLException {
		// �������ݿ�����
		Connection conn = DriverManager.getConnection(url, info);
		return conn;
	}
	
	
	private  Connection connect;
	private  Statement statement;
	private  ResultSet resultSet;

	/** ��ѯ */
	public  ResultSet select(String sql) {
		try {
			System.out.println(sql);
			//connect = DataConnect.getConnect();
			connect = DBHelper.getConnection();
			statement = connect.createStatement();
			resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnect(connect, statement, resultSet);
		}
		return null;
	}

	
//	/** ��ѯ */
//	public static ResultSet select(String sql,String[] Parameters) {
//			java.sql.PreparedStatement pstmt = null;
//			try {
//				// 2.�������ݿ�����
//				connect = DBHelper.getConnection();
//				// 3. ����������
//				pstmt = connect.prepareStatement(sql);
//				// 4. �󶨲���
//				if (Parameters.length>0&&Parameters!=null) {
//					for(int i=0;i<Parameters.length;i++)
//					{
//						pstmt.setString(i+1, Parameters[i]);	
//					}
//				}
//				// 5. ִ�в�ѯ��R��
//				resultSet = pstmt.executeQuery();
//			return resultSet;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (Parameters!=null) {
//				closeConnect(connect, null,pstmt,null);
//			}
//		}
//		return null;
//		
//	}
	
//	/** �������������޸ġ�ɾ���� */
//	public int operate(String sql) {
//		int number = 0;
//		try {
//			System.out.println(sql);
//			//connect = DataConnect.getConnect();
//			connect = DBHelper.getConnection();
//			statement = connect.createStatement();
//			number = statement.executeUpdate(sql);
//			// ��������Ϊ�ֶ�������ع�
//			connect.setAutoCommit(false);
//			connect.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				connect.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		} finally {
//			closeConnect(connect, statement, resultSet);
//		}
//		return number;
//	}

	private void closeConnect(Connection connect2, Statement statement2, ResultSet resultSet2) {
		// TODO Auto-generated method stub
		
	}


	/** �ر����� */
	public static void closeConnect(Connection connect, ResultSet resultSet,
			Statement statement) {
		try {
			if (null != resultSet) {
				resultSet.close();
			}
			if (null != statement) {
				statement.close();
			}
			if (null != connect) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/** �ر����� */
//	public static void closeConnect(Connection connect, ResultSet resultSet,
//			java.sql.PreparedStatement pstmt,Statement statement) {
//		try {
//			if (null != resultSet) {
//				resultSet.close();
//			}
//			if (null != pstmt) {
//				pstmt.close();
//			}
//			if (null != statement) {
//				statement.close();
//			}
//			if (null != connect) {
//				connect.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}



}
