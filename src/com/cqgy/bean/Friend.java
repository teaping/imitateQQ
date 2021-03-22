package com.cqgy.bean;

public class Friend {
	private String friend_id,friend_name,friend_icon,ipAddress;
    private String user_id1,user_id2;
    public String getUser_id1() {
		return user_id1;
	}
    public  Friend() {
		
	}

	public void setUser_id1(String user_id1) {
		this.user_id1 = user_id1;
	}

	public String getUser_id2() {
		return user_id2;
	}

	public void setUser_id2(String user_id2) {
		this.user_id2 = user_id2;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public  Friend(String friendId,String friendName,String frendIco,String ipAddress) {
	        this.friend_id=friendId;
	        this.friend_name=friendName;
	        this.friend_icon=frendIco;
	        this.ipAddress=ipAddress;
    }
	
	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}

	public String getFriend_icon() {
		return friend_icon;
	}

	public void setFriend_icon(String friend_icon) {
		this.friend_icon = friend_icon;
	}
}
