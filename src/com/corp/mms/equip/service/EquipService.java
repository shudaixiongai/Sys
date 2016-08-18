package com.corp.mms.equip.service;

import java.util.ArrayList;

import com.corp.mms.equip.dao.EquipDao;
import com.corp.mms.equip.vo.EquipType;
import com.corp.mms.equip.vo.Equipment;
import com.corp.mms.page.vo.Page;

public class EquipService {

	EquipDao ed=new EquipDao();
	public int GetPage(){
		return ed.GetPage();
	}
	public int GetPageByName(String equipmentName){
		return ed.GetPageByName(equipmentName);
	}
	public int GetPageByType(String typeName){
		return ed.GetPageByType(typeName);
	}
	public int GetPageByBelongTo(String BelongTo){
		return ed.GetPageByBelongTo(BelongTo);
	}
	public Equipment GetEquipInfo(int id){
		return ed.GetEquipInfo(id);
	}
	public ArrayList<Equipment> GetEquipsInfo(Page page){
		return ed.GetEquipsInfo(page);
	}
	public ArrayList<Equipment> GetEquipsInfoByName(Page page,String equipmentName){
		return ed.GetEquipsInfoByName(page, equipmentName);
	}
	public ArrayList<Equipment> GetEquipsInfoByType(Page page,String typeName){
		return ed.GetEquipsInfoByType(page, typeName);
	}
	public ArrayList<Equipment> GetEquipsInfoByBelongTo(Page page,String belongTo){
		return ed.GetEquipsInfoByBelongTo(page, belongTo);
	}
	public boolean DeleteEquip(int id){
		return ed.DeleteEquip(id);
	}
	public boolean SaveEquipInfo(Equipment equip,int info){
		return ed.SaveEquipInfo(equip, info);
	}
	public ArrayList<EquipType> GetEquipTypeList(){
		return ed.GetEquipTypeList();
	}
}
