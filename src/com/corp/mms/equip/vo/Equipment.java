package com.corp.mms.equip.vo;

public class Equipment {
	private int id;
	private String equipmentID; //�豸���
	private String equipmentName; //�豸����
	private String belongTo; //����
	private String storeTime; //����¼�
	private int equipType; //�豸����
	private String typeName; //�豸��������
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}
	public int getEquipType() {
		return equipType;
	}
	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}
}
