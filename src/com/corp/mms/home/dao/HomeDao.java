package com.corp.mms.home.dao;

import com.corp.mms.home.vo.Home;
import com.corp.mms.page.vo.Page;
import com.corp.mms.util.JDBC;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HomeDao
{
  Connection con = JDBC.getConnection();
  PreparedStatement ps;
  ResultSet rs;

  public int GetPage(String homeNo)
  {
    String sql = "select count(*) from tb_homes ";
    int count = 0;
    try {
      if ((homeNo == null) || (homeNo == "")) {
        this.ps = this.con.prepareStatement(sql);
      } else {
        sql = sql + "where homeNo=? ";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, homeNo);
      }
      this.rs = this.ps.executeQuery();
      while (this.rs.next())
        count = this.rs.getInt(1);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return 0;
    }
    return count;
  }

  public Home GetHomeInfo(int id) {
    Home home = new Home();
    String sql = "select tb_homes.id,homeNo,address,space,hosterID,name from tb_homes left join tb_workers on tb_workers.workerID=tb_homes.hosterID where tb_homes.id=? ";
    try
    {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setInt(1, id);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        home.setId(this.rs.getInt("id"));
        home.setHomeNo(this.rs.getString("homeNo"));
        home.setAddress(this.rs.getString("address"));
        home.setSpace(this.rs.getInt("space"));
        home.setHosterID(this.rs.getString("hosterID"));
        home.setHosterName(this.rs.getString("name"));
      }
      return home;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }return null;
  }

  public ArrayList<Home> GetHomesInfo(Page page, String homeNo)
  {
    ArrayList homeList = new ArrayList();
    String sql = "select tb_homes.id,homeNo,address,space,hosterID,name from tb_homes left join tb_workers on tb_workers.workerID=tb_homes.hosterID ";
    try
    {
      if ((homeNo == null) || (homeNo == "")) {
        sql = sql + "order by tb_homes.id limit ?,? ";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setInt(1, (page.getPageNo() - 1) * page.getPageSize());
        this.ps.setInt(2, page.getPageSize());
      } else {
        sql = sql + "where homeNo=? order by tb_homes.id limit ?,? ";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, homeNo);
        this.ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
        this.ps.setInt(3, page.getPageSize());
      }
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        Home home = new Home();
        home.setId(this.rs.getInt("id"));
        home.setHomeNo(this.rs.getString("homeNo"));
        home.setAddress(this.rs.getString("address"));
        home.setSpace(this.rs.getInt("space"));
        home.setHosterID(this.rs.getString("hosterID"));
        home.setHosterName(this.rs.getString("name"));
        homeList.add(home);
      }
      return homeList;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }return null;
  }

  public boolean DeleteHome(int id)
  {
    String sql = "delete from tb_homes where id=? ";
    int result = 0;
    try {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setInt(1, id);
      result = this.ps.executeUpdate();

      return result > 0;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }return false;
  }

  public boolean SaveWorkerInfo(Home home, int info)
  {
    String sql = null;
    if (info == 1)
      sql = "insert into tb_homes ( homeNo,address,space,hosterID ) values( ?,?,?,? ) ";
    else if (info == 2)
      sql = "update tb_homes set homeNo=? , address=? , space=? , hosterID=? where id=? ";
    try
    {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setString(1, home.getHomeNo());
      this.ps.setString(2, home.getAddress());
      this.ps.setInt(3, home.getSpace());
      this.ps.setString(4, home.getHosterID());
      if (info == 2)
        this.ps.setInt(5, home.getId());
      int result = this.ps.executeUpdate();

      return result > 0;
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }return false;
  }
}