package com.corp.mms.user.dao;

import com.corp.mms.user.vo.User;
import com.corp.mms.util.JDBC;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao
{
  Connection con = JDBC.getConnection();
  PreparedStatement ps;
  ResultSet rs;

  public int CheckUser(User user)
  {
    try
    {
      String sql = "select count(*) from tb_login where workerID=? and password=? and roleID=?";
      this.ps = this.con.prepareStatement(sql);
      this.ps.setString(1, user.getWorkersID());
      this.ps.setString(2, user.getPassword());
      this.ps.setInt(3, user.getRoleID());
      this.rs = this.ps.executeQuery();
      if (this.rs.next()) {
        int count = this.rs.getInt(1);
        int i = count;
        return i;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return 0;
    } finally {
      try {
        this.rs.close(); } catch (SQLException e) { e.printStackTrace(); } try {
        this.ps.close(); } catch (SQLException e) { e.printStackTrace(); } try {
        this.con.close(); } catch (SQLException e) { e.printStackTrace();
      }
    }
    try
    {
      this.rs.close(); } catch (SQLException e) { e.printStackTrace(); } try {
      this.ps.close(); } catch (SQLException e) { e.printStackTrace(); } try {
      this.con.close(); } catch (SQLException e) { e.printStackTrace();
    }
    return 0;
  }
}