package com.corp.mms.worker.vo;

public class Position {
	private int id;
	private String positionName;
	//构造方法
	public Position(){
		
	}
	public Position(int id,String positionName){
		this.id=id;
		this.positionName=positionName;
	}
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
}
