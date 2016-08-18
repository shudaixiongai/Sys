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
			linkList[0] = new Link("Main/MeetingManage/meetingapply_first.jsp", "ԤԼ����");
			linkList[1] = new Link("Main/MeetingManage/applicationquery.jsp", "��ѯԤԼ");
			linkList[2] = new Link("Main/MeetingManage/meetingquery.jsp", "��ѯ����");
			menu[0] = new Menu("�������", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/UserManage/adduser.jsp", "��Ϣ�޸�");
			menu[1] = new Menu("������Ϣ", linkList);
			linkList = new Link[1];
			linkList[0] = new Link("Main/HomeManage/checkmeeting.jsp", "�鿴��μӻ���");
			menu[2] = new Menu("�μӻ���", linkList);
		} else if (roleID == 1) {
			menu = new Menu[4];

			linkList = new Link[2];
			linkList[0] = new Link("Main/MeetingManage/meetingcheck.jsp", "����ԤԼ");
			linkList[1] = new Link("Main/MeetingManage/meetingquery.jsp", "��ѯ����");
			menu[0] = new Menu("�������", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/HomeManage/homemanage.jsp", "��ѯ������");
			menu[1] = new Menu("�����ҹ���", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/UserManage/usermanage.jsp", "�û�����");
			menu[2] = new Menu("�û�����", linkList);

			linkList = new Link[1];
			linkList[0] = new Link("Main/EquipManage/equipmanage.jsp", "�豸����");
			menu[3] = new Menu("�豸����", linkList);
		}
		return menu;
	}
}