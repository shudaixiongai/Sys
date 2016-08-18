package com.corp.mms.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.corp.mms.equip.vo.Equipment;
import com.corp.mms.home.vo.Home;
import com.corp.mms.meeting.vo.Meeting;
import com.corp.mms.meeting.vo.Schedule;
import com.corp.mms.page.vo.Page;
import com.corp.mms.util.JDBC;
import com.corp.mms.worker.vo.Worker;

public class MeetingDao {
	Connection con = JDBC.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	//��ѯ���з�����
	public int GetPage(String beginTime,String endTime,int space){
		int count=0;
		String sql="select count(*) "
			+"from tb_homes left join tb_schedule "
			+"on tb_homes.id=tb_schedule.homeID "
			+"where tb_homes.id not IN( select homeID from tb_schedule " 
								+"where beginTime<=? and endTime>=? "
								+"or beginTime<=? and endTime>=? ) "
			+"and space>=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, beginTime);
			ps.setString(2, beginTime);
			ps.setString(3, endTime);
			ps.setString(4, endTime);
			ps.setInt(5, space);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
		return count;
	}
	//��ȡ���з����б���Ϣ
	public ArrayList<Home> GetHomesInfo(Page page,String beginTime,String endTime,int space){
		ArrayList<Home> homeList=new ArrayList<Home>();
		String sql="select distinct tb_homes.id,homeNo,address,space,hosterID " 
			+"from tb_homes left join tb_schedule "
			+"on tb_homes.id=tb_schedule.homeID "
			+"where tb_homes.id not IN( select homeID from tb_schedule " 
								+"where beginTime<=? and endTime>=? "
								+"or beginTime<=? and endTime>=? ) "
			+"and space>=? "
			+"order by tb_homes.id limit ?,? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, beginTime);
			ps.setString(2, beginTime);
			ps.setString(3, endTime);
			ps.setString(4, endTime);
			ps.setInt(5, space);
			ps.setInt(6, (page.getPageNo() - 1) * page.getPageSize());
			ps.setInt(7, page.getPageSize());
			rs = ps.executeQuery();
			while (rs.next()) {
				Home home=new Home();
				home.setId(rs.getInt("id"));
				home.setHomeNo(rs.getString("homeNo"));
				home.setAddress(rs.getString("address"));
				home.setSpace(rs.getInt("space"));
				home.setHosterID(rs.getString("hosterID"));
				homeList.add(home);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return homeList;
	}
	
	//��ȡ�����豸�б�
	public ArrayList<Equipment> GetEnableEquipsInfo(){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where belongTo=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, "�ֿ�");
			rs = ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
				equip.setId(rs.getInt("id"));
				equip.setEquipmentID(rs.getString("equipmentID"));
				equip.setEquipmentName(rs.getString("equipmentName"));
				equip.setEquipType(rs.getInt("equipType"));
				equip.setStoreTime(rs.getString("storeTime"));
				equip.setTypeName(rs.getString("typeName"));
				equipList.add(equip);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return equipList;
	}
	
	//�����������ϲ�ѯ�豸����
	public ArrayList<Equipment> GetEquipsInfo(int[] IDs){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where tb_equipments.id=? ";
		for(int i=0;i<IDs.length;i++){
			Equipment equip=new Equipment();
			try{
				ps=con.prepareStatement(sql);
				ps.setInt(1, IDs[i]);
				rs=ps.executeQuery();
				while(rs.next()){
					equip.setId(rs.getInt("id"));
					equip.setEquipmentID(rs.getString("equipmentID"));
					equip.setEquipmentName(rs.getString("equipmentName"));
					equip.setBelongTo(rs.getString("belongTo"));
					equip.setEquipType(rs.getInt("equipType"));
					equip.setStoreTime(rs.getString("storeTime"));
					equip.setTypeName(rs.getString("typeName"));
					equipList.add(equip);
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				return null;
			}
		}
		return equipList;
	}
	//��ȡ����ְ����Ϣ
	public ArrayList<Worker> GetWorkersInfo(){
		ArrayList<Worker> workerList = new ArrayList<Worker>();
		String sql = "select tb_workers.id,workerID,name,departmentName,positionName "
			+ "from tb_workers left join tb_department "
			+ "on tb_workers.departmentID=tb_department.id "
			+ "left join tb_position "
			+ "on tb_workers.positionID=tb_position.id "
			+ "order by departmentName ";
		try{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Worker worker = new Worker();
				worker.setId(rs.getInt("id"));
				worker.setWorkerID(rs.getString("workerID"));
				worker.setName(rs.getString("name"));
				worker.setDepartmentName(rs.getString("departmentName"));
				worker.setPositionName(rs.getString("positionName"));
				workerList.add(worker);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return workerList;
	}
	//�½���������
	public int AddMeeting(Meeting meeting){
		int meetingID=0;		
		String sql="insert into tb_meetingapply ( workerID,topic,documentLink,homeID,attendance,beginTime,endTime,applyTime,statusID ) "
			+"values (?,?,?,?,?,?,?,?,?) ";
		try{
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, meeting.getWorkerID());
			ps.setString(2, meeting.getTopic());
			ps.setString(3, meeting.getDocumentLink());
			ps.setInt(4, meeting.getHomeID());
			ps.setInt(5, meeting.getAttendance());
			ps.setString(6, meeting.getBeginTime());
			ps.setString(7, meeting.getEndTime());
			ps.setString(8, meeting.getApplyTime());
			ps.setInt(9, meeting.getStatusID());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next())
				meetingID=rs.getInt(1);
			return meetingID;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//����ճ�
	public boolean AddSchedule(Schedule schedule){
		String sql="insert into tb_schedule( homeID,meetingID,beginTime,endTime ) "
			+"values(?,?,?,?) ";
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, schedule.getHomeID());
			ps.setInt(2, schedule.getMeetingID());
			ps.setString(3, schedule.getBeginTime());
			ps.setString(4, schedule.getEndTime());
			int count=ps.executeUpdate();
			if(count>0)
				return true;
			else 
				return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//����豸����
	public boolean AddEquipApply(int meetingID,int[] equipIDs){
		String sql="insert into tb_equipmentapply(meetingID,equipmentID) "
			+"values( ?,? ) ";
		int count=0;
		try{
			ps = con.prepareStatement(sql);
			for(int i=0;i<equipIDs.length;i++){
				ps.setInt(1, meetingID);
				ps.setInt(2, equipIDs[i]);
				count+=ps.executeUpdate();
			}
			if(count==equipIDs.length)
				return true;
			else
				return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//��������Ա����
	public boolean AddAttendenceList(int meetingID,int[] workerIDs){
		String sql="insert into tb_attendworkers(meetingID,workerID) "
			+"values( ?,? ) ";
		int count=0;
		try{
			ps = con.prepareStatement(sql);
			for(int i=0;i<workerIDs.length;i++){
				ps.setInt(1, meetingID);
				ps.setInt(2, workerIDs[i]);
				count+=ps.executeUpdate();
			}
			if(count==workerIDs.length)
				return true;
			else
				return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//��ȡ����ԤԼ��
	public int GetMeetingApplyPage(String workerID){
		int count=0;
		String sql="select count(*) from tb_meetingapply "
			+"where workerID=? and statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ѯԤԼ/����
	public ArrayList<Meeting> GetMeetingList(String workerID) {
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where tb_meetingapply.workerID=? and statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//���������ȡ����/ԤԼ��Ŀ
	public int GetMeetingPageByTopic(String topic,String workerID){
		int count=0;
		String sql="select count(*) from tb_meetingapply "
			+"where topic=? and workerID=? and statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, topic);
			ps.setString(2, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//���������ȡԤԼ�б�
	public ArrayList<Meeting> GetMeetingListByTopic(String topic,String workerID) {
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where topic=? and tb_meetingapply.workerID=? and statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, topic);
			ps.setString(2, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//ȡ��ԤԼ
	public boolean CancleMeeting(int meetingID){
		int count=0;
		String sql="delete from tb_schedule where meetingID=? "; //ɾ���ճ�
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, meetingID);
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		sql="delete from tb_equipmentapply where meetingID=? "; //ɾ���豸����
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, meetingID);
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		sql="delete from tb_attendworkers where meetingID=? "; //ɾ�������Ա
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, meetingID);
			ps.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
		//-------------------------------------------------------
		sql="delete from tb_meetingapply where id=? "; //ɾ��������Ϣ
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, meetingID);
			count=ps.executeUpdate();
			if(count>0)
				return true;
			else
				return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//��ȡ�����ĵ�����
	public String GetDcomentLink(int meetingID){
		String documentLink=null;
		String sql="select documentLink from tb_meetingapply where id=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, meetingID);
			rs=ps.executeQuery();
			if(rs.next())
				documentLink=rs.getString("documentLink");
			return documentLink;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	//��ȡĳְ��������Ļ�����
	public int GetMyMeetingPage(String workerID){
		int count=0;
		String sql="select count(*) from tb_meetingapply "
			+"where id IN( select meetingID from tb_attendworkers "
							+"left join tb_workers on tb_workers.id=tb_attendworkers.workerID "
							+"where tb_workers.workerID=? ) and statusID=1 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ȡĳְ���������ĳ����Ļ�����
	public int GetMyMeetingPageByTopic(String workerID,String topic){
		int count=0;
		String sql="select count(*) from tb_meetingapply "
			+"where id IN( select meetingID from tb_attendworkers "
							+"left join tb_workers on tb_workers.id=tb_attendworkers.workerID "
							+"where tb_workers.workerID=? ) and topic=? and statusID=1 ";//
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			ps.setString(2, topic);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ȡĳְ��������Ļ���
	public ArrayList<Meeting> GetMyMeetingList(String workerID){
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where tb_meetingapply.id IN( select meetingID from tb_attendworkers "
											+"left join tb_workers on tb_workers.id=tb_attendworkers.workerID "
											+"where tb_workers.workerID=? ) and statusID=1 "; //
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//��ȡĳְ���������ĳ����Ļ���
	public ArrayList<Meeting> GetMyMeetingListByTopic(String workerID,String topic){
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where tb_meetingapply.id IN( select meetingID from tb_attendworkers "
											+"left join tb_workers on tb_workers.id=tb_attendworkers.workerID "
											+"where tb_workers.workerID=? ) and topic=? and statusID=1 ";//
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, workerID);
			ps.setString(2, topic);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	
	//----------------��Ҫ����ԱȨ��----------------------------//
	//��ȡ����ԤԼ��
	public int GetMeetingApplyPage(){
		int count=0;
		String sql="select count(*) from tb_meetingapply "
			+"where statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ѯԤԼ
	public ArrayList<Meeting> GetMeetingList() {
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where statusID=0 ";
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//��ȡ���л�����
	public int GetAllMeetingPage(){
		int count=0;
		String sql="select count(*) from tb_meetingapply where statusID=1 ";
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ȡ���л�����Ϣ
	public ArrayList<Meeting> GetAllMeetingList(){
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where statusID=1 "; 
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//��ȡĳ��������л�����
	public int GetAllMeetingPageByTopic(String topic){
		int count=0;
		String sql="select count(*) from tb_meetingapply where topic=? and statusID=1 ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, topic);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
			return count;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	//��ȡĳ��������л�����Ϣ
	public ArrayList<Meeting> GetAllMeetingListByTopic(String topic){
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
		String sql="select tb_meetingapply.id,tb_meetingapply.workerID,name,topic,documentLink,"
			+"homeID,homeNo,address,attendance,beginTime,endTime,applyTime "
			+"from tb_meetingapply left join tb_workers "
			+"on tb_meetingapply.workerID=tb_workers.workerID "
			+"left join tb_homes on tb_meetingapply.homeID=tb_homes.id "
			+"where topic=? and statusID=1 "; 
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, topic);
			rs=ps.executeQuery();
			while(rs.next()){
				Meeting meeting=new Meeting();
				meeting.setId(rs.getInt("id"));
				meeting.setWorkerID(rs.getString("workerID"));
				meeting.setWorkerName(rs.getString("name"));
				meeting.setTopic(rs.getString("topic"));
				meeting.setDocumentLink(rs.getString("documentLink"));
				meeting.setHomeID(rs.getInt("homeID"));
				meeting.setHomeNo(rs.getString("homeNo"));
				meeting.setAddress(rs.getString("address"));
				meeting.setAttendance(rs.getInt("attendance"));
				meeting.setBeginTime(rs.getString("beginTime"));
				meeting.setEndTime(rs.getString("endTime"));
				meeting.setApplyTime(rs.getString("applyTime"));
				meetingList.add(meeting);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return meetingList;
	}
	//��ĳԤԼͨ�����
	public boolean PassedCheck(int id){
		String sql="update tb_meetingapply set statusID=1 where id=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int count=ps.executeUpdate();
			if(count>0)
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
	//��ȡĳԤԼ���豸�����
	public ArrayList<Equipment> GetEquipListOfMeeting(int id){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,equipType,typeName "
				+"from tb_equipments left join tb_equiptypes "
				+"on tb_equipments.equipType=tb_equiptypes.id "
				+"where tb_equipments.id=( select equipmentID from tb_equipmentapply "
									+"where meetingID=? ) ";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
				equip.setId(rs.getInt("id"));
				equip.setEquipmentID(rs.getString("equipmentID"));
				equip.setEquipmentName(rs.getString("equipmentName"));
				equip.setEquipType(rs.getInt("equipType"));
				equip.setTypeName(rs.getString("typeName"));
				equipList.add(equip);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		return equipList;
	}
}
