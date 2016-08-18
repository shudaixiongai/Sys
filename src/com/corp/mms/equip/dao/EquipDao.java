package com.corp.mms.equip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.corp.mms.equip.vo.EquipType;
import com.corp.mms.equip.vo.Equipment;
import com.corp.mms.page.vo.Page;
import com.corp.mms.util.JDBC;

public class EquipDao {
	Connection con = JDBC.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	//ͳ�������豸����
	public int GetPage(){
		String sql="select count(*) from tb_equipments";
		int count=0;
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e){
			return 0;
		}
		return count;
	}
	// ��ȡsql�����������(���豸��)
	public int GetPageByName(String equipmentName){
		String sql = "select count(*) from tb_equipments where equipmentName=? ";
		int count = 0;
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, equipmentName);
			rs=ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e){
			return 0;
		}
		return count;
	}
	//��ȡsql�����������(�����豸����)
	public int GetPageByType(String typeName){
		int count=0;
		String sql="select count(*) from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where typeName=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, typeName);
			rs=ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e){
			return 0;
		}
		return count;
	}
	//��ȡsql�����������(��������)
	public int GetPageByBelongTo(String BelongTo){
		int count=0;
		String sql="select count(*) from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where BelongTo=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, BelongTo);
			rs=ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e){
			return 0;
		}
		return count;
	}
	//�����豸������ȡ�豸��Ϣ
	public Equipment GetEquipInfo(int id){
		Equipment equip=new Equipment();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where tb_equipments.id=? ";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				equip.setId(rs.getInt("id"));
				equip.setEquipmentID(rs.getString("equipmentID"));
				equip.setEquipmentName(rs.getString("equipmentName"));
				equip.setBelongTo(rs.getString("belongTo"));
				equip.setEquipType(rs.getInt("equipType"));
				equip.setStoreTime(rs.getString("storeTime"));
				equip.setTypeName(rs.getString("typeName"));
			}
		}catch(Exception e){
			return null;
		}
		return equip;
	}
	//��ȡ�����豸��Ϣ
	public ArrayList<Equipment> GetEquipsInfo(Page page){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"limit ?,?";
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, (page.getPageNo() - 1) * page.getPageSize());
			ps.setInt(2, page.getPageSize());
			rs = ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
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
			return null;
		}
		return equipList;
	}
	//�����豸����ȡ�豸��Ϣ
	public ArrayList<Equipment> GetEquipsInfoByName(Page page,String equipmentName){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where equipmentName=? limit ?,?";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, equipmentName);
			ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
			ps.setInt(3, page.getPageSize());
			rs = ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
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
			return null;
		}
		return equipList;
	}
	//�����豸���Ͳ�ѯ�豸��Ϣ
	public ArrayList<Equipment> GetEquipsInfoByType(Page page,String typeName){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where typeName=? limit ?,?";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, typeName);
			ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
			ps.setInt(3, page.getPageSize());
			rs = ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
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
			return null;
		}
		return equipList;
	}
	//���ݻ����һ�ȡ�豸��Ϣ
	public ArrayList<Equipment> GetEquipsInfoByBelongTo(Page page,String belongTo){
		ArrayList<Equipment> equipList=new ArrayList<Equipment>();
		String sql="select tb_equipments.id,equipmentID,equipmentName,belongTo,equipType,storeTime,typeName "
			+"from tb_equipments left join tb_equiptypes "
			+"on tb_equipments.equipType=tb_equiptypes.id "
			+"where belongTo=? limit ?,?";
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1, belongTo);
			ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
			ps.setInt(3, page.getPageSize());
			rs = ps.executeQuery();
			while(rs.next()){
				Equipment equip=new Equipment();
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
			return null;
		}
		return equipList;
	}
	//ɾ���豸��Ϣ
	public boolean DeleteEquip(int id){
		String sql="delete from tb_equipments where id=? ";
		int result=0;
		try{
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			result=ps.executeUpdate();
			if(result>0)
				return true;
			else return false;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	//��ȡ�豸�����б�
	public ArrayList<EquipType> GetEquipTypeList(){
		ArrayList<EquipType> typeList=new ArrayList<EquipType>();
		String sql="select id,typeName from tb_equiptypes ";
		try{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				EquipType type=new EquipType();
				type.setId(rs.getInt("id"));
				type.setTypeName(rs.getString("typeName"));
				typeList.add(type);
			}
			return typeList;
		}catch(Exception e){
			return null;
		}
	}
	//�����豸��Ϣ
	public boolean SaveEquipInfo(Equipment equip,int info){
		String sql = null;
		if(info==1){ // �����û�
			sql = "insert into tb_equipments ( equipmentID,equipmentName,belongTo,storeTime,equipType ) values( ?,?,?,?,? ) ";
		} else if (info == 2) { // �޸��û�
			sql="update tb_equipments set equipmentID=? , equipmentName=? , belongTo=? , storeTime=? ,equipType=? where id=? ";
		}
		try{
			ps = con.prepareStatement(sql);
			ps.setString(1, equip.getEquipmentID());
			ps.setString(2, equip.getEquipmentName());
			ps.setString(3, equip.getBelongTo());
			ps.setString(4, equip.getStoreTime());
			ps.setInt(5, equip.getEquipType());
			if(info==2){
				ps.setInt(6, equip.getId());
			}
			int result = ps.executeUpdate();
			if (result > 0) //�޸Ļ����ɹ�
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
		}
	}
}
