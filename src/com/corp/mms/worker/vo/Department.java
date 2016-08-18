package com.corp.mms.worker.vo;

public class Department {
	private int id;
	private String departmentName;
	//构造方法
	public Department(){
		
	}
	public Department(int id,String departmentName){
		this.id=id;
		this.departmentName=departmentName;
	}
	//setter and getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
