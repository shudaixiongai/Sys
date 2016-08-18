package com.corp.mms.home.vo;

public class Home {
	//properties
	private int id;
	private String homeNo; //房间号(名称)
	private String address; //所在地点
	private int space; //可容纳人数
	private String hosterID; //负责人ID
	private String hosterName;
	
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHomeNo() {
		return homeNo;
	}
	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	public String getHosterID() {
		return hosterID;
	}
	public void setHosterID(String hosterID) {
		this.hosterID = hosterID;
	}
	public String getHosterName() {
		return hosterName;
	}
	public void setHosterName(String hosterName) {
		this.hosterName = hosterName;
	}
	
}
