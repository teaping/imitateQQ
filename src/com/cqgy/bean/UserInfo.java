package com.cqgy.bean;

public class UserInfo {
	//�û��ǳ�
	private String nickname;
	//�û�ID
	private String Id;
	//�û�����ǩ��
	private String Autograph;
	//�û��Ա�
	private String Gender;
	//�û�����
	private String Birthday;
	//�û����ڵ�
	private String Location;
	//�û�ѧУ
	private String School;
	//�û���˾
	private String company;
	//�û�ְҵ
	private String job;
	//״̬
	private String statu;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAutograph() {
		return Autograph;
	}
	public void setAutograph(String autograph) {
		Autograph = autograph;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public UserInfo(String nickname, String id, String autograph, String gender, String birthday, String location,
			String school, String company, String job, String statu) {
		super();
		this.nickname = nickname;
		Id = id;
		Autograph = autograph;
		Gender = gender;
		Birthday = birthday;
		Location = location;
		School = school;
		this.company = company;
		this.job = job;
		this.statu = statu;
	}
	public UserInfo() {
		super();
	}
	public UserInfo( String id,String nickname, String autograph, String gender, String birthday, String location,
			String school, String company, String job) {
		super();
		this.nickname = nickname;
		Id = id;
		Autograph = autograph;
		Gender = gender;
		Birthday = birthday;
		Location = location;
		School = school;
		this.company = company;
		this.job = job;
	}
	
	
	

}
