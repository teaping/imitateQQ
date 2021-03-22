package com.cqgy.method;

import com.cqgy.bean.UserInfo;
import com.cqgy.db.Infodb;

public class InfoDao {

	//添加信息
	public boolean InsertInfo(UserInfo info) {
		// TODO Auto-generated method stub
		String sql="insert into InfoUser(Id,NickName,Autograph,Gender,Birthday,Location,School,company,job) VALUES (?,?,?,?,?,?,?,?,?) ";
		return Infodb.InsertInfoUser(info,sql);
	}

	//查询信息
	public UserInfo SelectInfo(String id) {
		// TODO Auto-generated method stub
		String sql="Select Id,NickName,Autograph,Gender,Birthday,Location,School,company,job from InfoUser where Id=? ";
		return Infodb.SelectInfoUser(id,sql);
	}

	//查询信息
	public int SelectUser(String id) {
		// TODO Auto-generated method stub
		String sql="select * from InfoUser where Id=?";
		return Infodb.SelectUserone(id,sql);
	}

	public boolean UpdatetInfo(UserInfo info) {
		// TODO Auto-generated method stub
		String sql="update infouser set NickName=?, Autograph=?,Gender=?,Birthday=?,Location=?,School=?,company=?,job=? where Id=?";
		return Infodb.UpdatayUserone(info,sql);
	}

	//修改User表中的name
	public boolean UpdateUser(String id, String name) {
		// TODO Auto-generated method stub
		String sql="update user set user_name=? where user_id=?";
		return Infodb.UpdateUser(id,name,sql);
	}

	//查询user的name
	public String SelectUserone(String id) {
		String sql="select user_name from user where user_id=?";
		return Infodb.SelectUserId(id,sql);
	}

	//头像修改
	public boolean UpdateAvatarch(String toux, String id) {
		// TODO Auto-generated method stub
		String sql="update user set user_icon=? where user_id=?";
		return Infodb.UpdataUsericon(toux,id,sql);
	}


	

}
