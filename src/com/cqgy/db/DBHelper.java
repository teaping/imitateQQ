

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

	// 连接数据库url
	static String url;
	// 创建Properties对象
	static Properties info = new Properties();

	// 1.驱动程序加载
	static {
		// 获得属性文件输入流
		InputStream input = DBHelper.class.getClassLoader()
				.getResourceAsStream("com/cqgy/db/config.properties");

		try {
			// 加载属性文件内容到Properties对象
			info.load(input);
			// 从属性文件中取出url
			url = info.getProperty("url");
			// Class.forName("com.mysql.jdbc.Driver");
			// 从属性文件中取出driver
			String driverClassName = info.getProperty("driver");
			Class.forName(driverClassName);
			System.out.println("驱动程序加载成功...");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动程序加载失败...");
		} catch (IOException e) {
			System.out.println("加载属性文件失败...");
		}
	}

	public static Connection getConnection() throws SQLException {
		// 创建数据库连接
		Connection conn = DriverManager.getConnection(url, info);
		return conn;
	}
	
	
	private  Connection connect;
	private  Statement statement;
	private  ResultSet resultSet;

	/** 查询 */
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

	
//	/** 查询 */
//	public static ResultSet select(String sql,String[] Parameters) {
//			java.sql.PreparedStatement pstmt = null;
//			try {
//				// 2.创建数据库连接
//				connect = DBHelper.getConnection();
//				// 3. 创建语句对象
//				pstmt = connect.prepareStatement(sql);
//				// 4. 绑定参数
//				if (Parameters.length>0&&Parameters!=null) {
//					for(int i=0;i<Parameters.length;i++)
//					{
//						pstmt.setString(i+1, Parameters[i]);	
//					}
//				}
//				// 5. 执行查询（R）
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
	
//	/** 操作（新增、修改、删除） */
//	public int operate(String sql) {
//		int number = 0;
//		try {
//			System.out.println(sql);
//			//connect = DataConnect.getConnect();
//			connect = DBHelper.getConnection();
//			statement = connect.createStatement();
//			number = statement.executeUpdate(sql);
//			// 设置事务为手动，方便回滚
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


	/** 关闭连接 */
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
	
//	/** 关闭连接 */
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
