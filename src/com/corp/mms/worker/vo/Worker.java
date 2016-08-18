package com.corp.mms.worker.vo;

public class Worker
{
  private int id;
  private String workerID;
  private String name;
  private String userGroup;
  private String telephone;
  private String sex;
  private String birthday;
  private int departmentID;
  private String departmentName;
  private int positionID;
  private String positionName;
  private String eMail;

  public int getId()
  {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getWorkerID() {
    return this.workerID;
  }
  public void setWorkerID(String workerID) {
    this.workerID = workerID;
  }
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getUserGroup() {
    return this.userGroup;
  }
  public void setUserGroup(String userGroup) {
    this.userGroup = userGroup;
  }
  public String getTelephone() {
    return this.telephone;
  }
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
  public String getSex() {
    return this.sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getBirthday() {
    return this.birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public int getDepartmentID() {
    return this.departmentID;
  }
  public void setDepartmentID(int departmentID) {
    this.departmentID = departmentID;
  }
  public String getDepartmentName() {
    return this.departmentName;
  }
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }
  public int getPositionID() {
    return this.positionID;
  }
  public void setPositionID(int positionID) {
    this.positionID = positionID;
  }
  public String getPositionName() {
    return this.positionName;
  }
  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }
  public String geteMail() {
    return this.eMail;
  }
  public void seteMail(String eMail) {
    this.eMail = eMail;
  }
}