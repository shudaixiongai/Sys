package com.corp.mms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/meetingmanagesys","root","root");
		}catch(Exception e){
			System.out.println("���ݿ�����ʧ��:"+e.getMessage());
		}
		return con;
	}
}
