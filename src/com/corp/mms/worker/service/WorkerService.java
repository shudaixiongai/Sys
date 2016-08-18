package com.corp.mms.worker.service;

import com.corp.mms.page.vo.Page;
import com.corp.mms.worker.dao.WorkerDao;
import com.corp.mms.worker.vo.Department;
import com.corp.mms.worker.vo.Position;
import com.corp.mms.worker.vo.Worker;
import java.util.ArrayList;

public class WorkerService
{
  WorkerDao workerDao = new WorkerDao();

  public int GetPage(String name, String department) {
    return this.workerDao.GetPage(name, department);
  }
  public Worker GetWorkerInfo(int id) {
    return this.workerDao.GetWorkerInfo(id);
  }

  public ArrayList<Worker> GetWorkersInfo(Page page, String name, String department) {
    return this.workerDao.GetWorkersInfo(page, name, department);
  }
  public boolean DeleteWorker(int id) {
    return this.workerDao.DeleteWorker(id);
  }
  public ArrayList<Department> GetDepartmentList() {
    return this.workerDao.GetDepartmentList();
  }
  public ArrayList<Position> GetPositionList() {
    return this.workerDao.GetPositionList();
  }
  public boolean SaveWorkerInfo(Worker worker, int info) {
    return this.workerDao.SaveWorkerInfo(worker, info);
  }
}