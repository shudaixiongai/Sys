package com.corp.mms.meeting.vo;

public class Meeting
{
  private int id = 0;
  private String workerID;
  private String workerName;
  private int homeID;
  private String homeNo;
  private String address;
  private int attendance;
  private String beginTime;
  private String endTime;
  private String topic;
  private String documentLink;
  private int statusID;
  private String applyTime;

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
  public String getWorkerName() {
    return this.workerName;
  }
  public void setWorkerName(String workerName) {
    this.workerName = workerName;
  }
  public int getHomeID() {
    return this.homeID;
  }
  public void setHomeID(int homeID) {
    this.homeID = homeID;
  }

  public String getHomeNo() {
    return this.homeNo;
  }
  public void setHomeNo(String homeNo) {
    this.homeNo = homeNo;
  }

  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public int getAttendance() {
    return this.attendance;
  }
  public void setAttendance(int attendance) {
    this.attendance = attendance;
  }
  public String getBeginTime() {
    return this.beginTime;
  }
  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }
  public String getEndTime() {
    return this.endTime;
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
  public String getTopic() {
    return this.topic;
  }
  public void setTopic(String topic) {
    this.topic = topic;
  }
  public String getDocumentLink() {
    return this.documentLink;
  }
  public void setDocumentLink(String documentLink) {
    this.documentLink = documentLink;
  }
  public int getStatusID() {
    return this.statusID;
  }
  public void setStatusID(int statusID) {
    this.statusID = statusID;
  }
  public String getApplyTime() {
    return this.applyTime;
  }
  public void setApplyTime(String applyTime) {
    this.applyTime = applyTime;
  }
}