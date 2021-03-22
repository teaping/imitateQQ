package com.cqgy.bean;

public class User {
	private String user_id,user_pwd,user_name,user_icon,state,ipone;
	//无参构造函数
	public User(){
		
	}
	//有参构造函数
    public User(String user_id,String user_pwd,String user_name,String user_icon){
    	this.user_id=user_id;
    	this.user_pwd=user_pwd;
    	this.user_name=user_name;
    	this.user_icon=user_icon;
    	
	}
    
  //有参构造函数
    public User(String user_id,String user_pwd,String user_name,String user_icon,String state){
    	this.user_id=user_id;
    	this.user_pwd=user_pwd;
    	this.user_name=user_name;
    	this.user_icon=user_icon;
    	this.state=state;
	}
	
	
	public User(String user_id, String user_pwd, String user_name, String user_icon, String state, String ipone) {
	super();
	this.user_id = user_id;
	this.user_pwd = user_pwd;
	this.user_name = user_name;
	this.user_icon = user_icon;
	this.state = state;
	this.ipone = ipone;
}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIpone() {
		return ipone;
	}
	public void setIpone(String ipone) {
		this.ipone = ipone;
	}
	
}
