package com.corp.mms.meeting.vo;

public class Schedule
{
  private int id;
  private int homeID;
  private int meetingID;
  private String beginTime;
  private String endTime;

  public int getId()
  {
    return this.id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getHomeID() {
    return this.homeID;
  }
  public void setHomeID(int homeID) {
    this.homeID = homeID;
  }

  public int getMeetingID() {
    return this.meetingID;
  }
  public void setMeetingID(int meetingID) {
    this.meetingID = meetingID;
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
}