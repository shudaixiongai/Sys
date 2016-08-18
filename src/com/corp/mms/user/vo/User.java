package com.corp.mms.user.vo;

public class User {
	private String workersID; //ÕÊºÅ(Ö°¹¤ºÅ)
	private String password;  //µÇÂ¼ÃÜÂë
	private int roleID;		  //½ÇÉ«
	
	public String getWorkersID() {
		return workersID;
	}
	public void setWorkersID(String workersID) {
		this.workersID = workersID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
}
