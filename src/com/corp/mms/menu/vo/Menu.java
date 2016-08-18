package com.corp.mms.menu.vo;

import java.io.Serializable;
import java.util.LinkedList;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	private Link[] links;
	private String topic;

	public Menu() {
	}

	public Menu(String topic, Link[] links) {
		this.topic = topic;
		this.links = links;
	}

	public Link[] getLinks() {
		return this.links;
	}

	public void setLinks(Link[] links) {
		this.links = links;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public static Menu[] GetMenu(int roleID) {
		Menu[] menu = (Menu[]) null;
		Link[] linkList = (Link[]) null;
		if (roleID == 0) {
			menu = new Menu[3];

			linkList = new Link[3];
			linkList[0] = new Link("Main/MeetingManage/meetingapply_first.jsp", "预约会议");
			linkList[1] = new Link("Main/MeetingManage/applicationquery.jsp", "查询预约");
			linkList[2] = new Link("Main/MeetingManage/meetingquery.jsp", "查询会议");
			menu[0] = new Menu("会议管理", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/UserManage/adduser.jsp", "信息修改");
			menu[1] = new Menu("个人信息", linkList);
			linkList = new Link[1];
			linkList[0] = new Link("Main/HomeManage/checkmeeting.jsp", "查看需参加会议");
			menu[2] = new Menu("参加会议", linkList);
		} else if (roleID == 1) {
			menu = new Menu[4];

			linkList = new Link[2];
			linkList[0] = new Link("Main/MeetingManage/meetingcheck.jsp", "审批预约");
			linkList[1] = new Link("Main/MeetingManage/meetingquery.jsp", "查询会议");
			menu[0] = new Menu("会议管理", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/HomeManage/homemanage.jsp", "查询会议室");
			menu[1] = new Menu("会议室管理", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/UserManage/usermanage.jsp", "用户管理");
			menu[2] = new Menu("用户管理", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/EquipManage/equipmanage.jsp", "设备管理");
			menu[3] = new Menu("设备管理", linkList);
		}
		return menu;
	}
}