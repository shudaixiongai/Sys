package com.corp.mms.meeting.vo;

public class MeetingTime
{
  private String meetingDate;
  private String startHour;
  private String startMinute;
  private String endHour;
  private String endMinute;
  private int attendance;

  public String getMeetingDate()
  {
    return this.meetingDate;
  }
  public void setMeetingDate(String meetingDate) {
    this.meetingDate = meetingDate;
  }
  public String getStartHour() {
    return this.startHour;
  }
  public void setStartHour(String startHour) {
    this.startHour = startHour;
  }
  public String getStartMinute() {
    return this.startMinute;
  }
  public void setStartMinute(String startMinute) {
    this.startMinute = startMinute;
  }
  public String getEndHour() {
    return this.endHour;
  }
  public void setEndHour(String endHour) {
    this.endHour = endHour;
  }
  public String getEndMinute() {
    return this.endMinute;
  }
  public void setEndMinute(String endMinute) {
    this.endMinute = endMinute;
  }
  public int getAttendance() {
    return this.attendance;
  }
  public void setAttendance(int attendance) {
    this.attendance = attendance;
  }
}