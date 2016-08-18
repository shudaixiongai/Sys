package com.corp.mms.worker.dao;

import com.corp.mms.page.vo.Page;
import com.corp.mms.util.JDBC;
import com.corp.mms.worker.vo.Department;
import com.corp.mms.worker.vo.Position;
import com.corp.mms.worker.vo.Worker;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WorkerDao
{
  Connection con = JDBC.getConnection();
  PreparedStatement ps;
  ResultSet rs;

  public int GetPage(String name, String departmentName)
  {
    String sql = "select count(*) from tb_workers ";
    int count = 0;
    try {
      if ((name == null) && (departmentName == null)) {
        this.ps = this.con.prepareStatement(sql);
      }
      else if (name != null) {
        sql = sql + "where name=?";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, name);
      } else if (departmentName != null) {
        sql = sql + "left join tb_department on tb_workers.departmentID=tb_department.id where departmentName=?";

        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, departmentName);
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

  public Worker GetWorkerInfo(int id) {
    Worker worker = new Worker();
    String sql = "select id,workerID,name,userGroup,telephone,sex,birthday,eMail,departmentID,positionID from tb_workers where id=?";
    try
    {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setInt(1, id);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        worker.setId(this.rs.getInt("id"));
        worker.setWorkerID(this.rs.getString("workerID"));
        worker.setName(this.rs.getString("name"));
        worker.setUserGroup(this.rs.getString("userGroup"));
        worker.setTelephone(this.rs.getString("telephone"));
        worker.setSex(this.rs.getString("sex"));
        worker.setBirthday(this.rs.getString("birthday"));
        worker.seteMail(this.rs.getString("eMail"));
        worker.setDepartmentID(this.rs.getInt("departmentID"));
        worker.setPositionID(this.rs.getInt("positionID"));
      }
      return worker; } catch (Exception e) {
    }
    return null;
  }

  public ArrayList<Worker> GetWorkersInfo(Page page, String name, String department)
  {
    ArrayList workers = new ArrayList();

    String sql = "select tb_workers.id,workerID,name,userGroup,telephone,sex,birthday,eMail,departmentName,positionName from tb_workers left join tb_department on tb_workers.departmentID=tb_department.id left join tb_position on tb_workers.positionID=tb_position.id ";
    try
    {
      if ((name == null) && (department == null)) {
        sql = sql + "order by tb_workers.id limit ?,?";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setInt(1, (page.getPageNo() - 1) * page.getPageSize());
        this.ps.setInt(2, page.getPageSize());
      } else if (name != null) {
        sql = sql + "where name=? limit ?,?";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, name);
        this.ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
        this.ps.setInt(3, page.getPageSize());
      } else if (department != null) {
        sql = sql + "where departmentName=? limit ?,?";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, department);
        this.ps.setInt(2, (page.getPageNo() - 1) * page.getPageSize());
        this.ps.setInt(3, page.getPageSize());
      }
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        Worker worker = new Worker();
        worker.setId(this.rs.getInt("id"));
        worker.setWorkerID(this.rs.getString("workerID"));
        worker.setName(this.rs.getString("name"));
        worker.setUserGroup(this.rs.getString("userGroup"));
        worker.setTelephone(this.rs.getString("telephone"));
        worker.setSex(this.rs.getString("sex"));
        worker.setBirthday(this.rs.getString("birthday"));
        worker.seteMail(this.rs.getString("eMail"));
        worker.setDepartmentName(this.rs.getString("departmentName"));
        worker.setPositionName(this.rs.getString("positionName"));
        workers.add(worker);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
    return workers;
  }

  public boolean DeleteWorker(int id)
  {
    String sql = "delete from tb_login where workerID=( select workerID from tb_workers where id=? )";
    int result = 0;
    try {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setInt(1, id);
      result = this.ps.executeUpdate();
      if (result > 0) {
        sql = "delete from tb_workers where id=?";
        this.ps = this.con.prepareStatement(sql);
        this.ps.setInt(1, id);
        int count = this.ps.executeUpdate();

        return count > 0;
      }

      return false;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }return false;
  }

  public ArrayList<Department> GetDepartmentList()
  {
    ArrayList departList = new ArrayList();
    String sql = "select id,departmentName from tb_department ";
    try {
      this.ps = this.con.prepareStatement(sql);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        Department depart = new Department();
        depart.setId(this.rs.getInt("id"));
        depart.setDepartmentName(this.rs.getString("departmentName"));
        departList.add(depart);
      }
      return departList; } catch (Exception e) {
    }
    return null;
  }

  public ArrayList<Position> GetPositionList()
  {
    ArrayList positionList = new ArrayList();
    String sql = "select id,positionName from tb_position ";
    try {
      this.ps = this.con.prepareStatement(sql);
      this.rs = this.ps.executeQuery();
      while (this.rs.next()) {
        Position posi = new Position();
        posi.setId(this.rs.getInt("id"));
        posi.setPositionName(this.rs.getString("positionName"));
        positionList.add(posi);
      }
      return positionList; } catch (Exception e) {
    }
    return null;
  }

  public boolean SaveWorkerInfo(Worker worker, int info)
  {
    String sql = null;
    if (info == 1) {
      sql = "insert into tb_login( workerID,password,roleID ) values(?,?,?)";
      try {
        this.ps = this.con.prepareStatement(sql);
        this.ps.setString(1, worker.getWorkerID());
        this.ps.setString(2, worker.getWorkerID());
        if (worker.getUserGroup().equals("普通用户"))
          this.ps.setInt(3, 0);
        else if (worker.getUserGroup().equals("管理员")) {
          this.ps.setInt(3, 1);
        }
        int result = this.ps.executeUpdate();
        if (result < 1)
          return false;
      } catch (Exception e) {
        return false;
      }
      sql = "insert into tb_workers ( workerID,name,userGroup,telephone,sex,birthday,eMail,departmentID,positionID ) values(?,?,?,?,?,?,?,?,?)";
    }
    else if (info == 2) {
      sql = "update tb_workers set workerID=? , name=? , userGroup=? , telephone=? , sex=? , birthday=? , eMail=? , departmentID=? , positionID=? where id=? ";
    }

    try
    {
      this.ps = this.con.prepareStatement(sql);
      this.ps.setString(1, worker.getWorkerID());
      this.ps.setString(2, worker.getName());
      this.ps.setString(3, worker.getUserGroup());
      this.ps.setString(4, worker.getTelephone());
      this.ps.setString(5, worker.getSex());
      this.ps.setString(6, worker.getBirthday());
      this.ps.setString(7, worker.geteMail());
      this.ps.setInt(8, worker.getDepartmentID());
      this.ps.setInt(9, worker.getPositionID());
      if (info == 2)
        this.ps.setInt(10, worker.getId());
      int result = this.ps.executeUpdate();

      return result > 0;
    }
    catch (Exception e)
    {
    }
    return false;
  }
}