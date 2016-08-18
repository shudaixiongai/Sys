package com.corp.mms.meeting.service;

import com.corp.mms.equip.vo.Equipment;
import com.corp.mms.home.vo.Home;
import com.corp.mms.meeting.dao.MeetingDao;
import com.corp.mms.meeting.vo.Meeting;
import com.corp.mms.meeting.vo.Schedule;
import com.corp.mms.page.vo.Page;
import com.corp.mms.worker.vo.Worker;
import java.util.ArrayList;

public class MeetingService
{
  MeetingDao md = new MeetingDao();

  public int GetPage(String beginTime, String endTime, int space) {
    return this.md.GetPage(beginTime, endTime, space);
  }
  public ArrayList<Home> GetHomesInfo(Page page, String beginTime, String endTime, int space) {
    return this.md.GetHomesInfo(page, beginTime, endTime, space);
  }
  public ArrayList<Equipment> GetEnableEquipsInfo() {
    return this.md.GetEnableEquipsInfo();
  }
  public ArrayList<Equipment> GetEquipsInfo(int[] IDs) {
    return this.md.GetEquipsInfo(IDs);
  }
  public ArrayList<Worker> GetWorkersInfo() {
    return this.md.GetWorkersInfo();
  }
  public int AddMeeting(Meeting meeting) {
    return this.md.AddMeeting(meeting);
  }
  public boolean AddSchedule(Schedule schedule) {
    return this.md.AddSchedule(schedule);
  }
  public boolean AddEquipApply(int meetingID, int[] equipIDs) {
    return this.md.AddEquipApply(meetingID, equipIDs);
  }
  public boolean AddAttendenceList(int meetingID, int[] workerIDs) {
    return this.md.AddAttendenceList(meetingID, workerIDs);
  }
  public int GetMeetingApplyPage(String workerID) {
    return this.md.GetMeetingApplyPage(workerID);
  }
  public int GetMeetingPageByTopic(String topic, String workerID) {
    return this.md.GetMeetingPageByTopic(topic, workerID);
  }
  public ArrayList<Meeting> GetMeetingList(String workerID) {
    return this.md.GetMeetingList(workerID);
  }
  public ArrayList<Meeting> GetMeetingListByTopic(String topic, String workerID) {
    return this.md.GetMeetingListByTopic(topic, workerID);
  }
  public boolean CancleMeeting(int meetingID) {
    return this.md.CancleMeeting(meetingID);
  }
  public String GetDcomentLink(int meetingID) {
    return this.md.GetDcomentLink(meetingID);
  }
  public int GetMyMeetingPage(String workerID) {
    return this.md.GetMyMeetingPage(workerID);
  }
  public int GetMyMeetingPageByTopic(String workerID, String topic) {
    return this.md.GetMyMeetingPageByTopic(workerID, topic);
  }
  public ArrayList<Meeting> GetMyMeetingList(String workerID) {
    return this.md.GetMyMeetingList(workerID);
  }
  public ArrayList<Meeting> GetMyMeetingListByTopic(String workerID, String topic) {
    return this.md.GetMyMeetingListByTopic(workerID, topic);
  }
  public int GetMeetingApplyPage() {
    return this.md.GetMeetingApplyPage();
  }
  public ArrayList<Meeting> GetMeetingList() {
    return this.md.GetMeetingList();
  }
  public int GetAllMeetingPage() {
    return this.md.GetAllMeetingPage();
  }
  public ArrayList<Meeting> GetAllMeetingList() {
    return this.md.GetAllMeetingList();
  }
  public int GetAllMeetingPageByTopic(String topic) {
    return this.md.GetAllMeetingPageByTopic(topic);
  }
  public ArrayList<Meeting> GetAllMeetingListByTopic(String topic) {
    return this.md.GetAllMeetingListByTopic(topic);
  }
  public boolean PassedCheck(int id) {
    return this.md.PassedCheck(id);
  }
  public ArrayList<Equipment> GetEquipListOfMeeting(int id) {
    return this.md.GetEquipListOfMeeting(id);
  }
}