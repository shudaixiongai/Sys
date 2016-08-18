package com.corp.mms.home.service;

import com.corp.mms.home.dao.HomeDao;
import com.corp.mms.home.vo.Home;
import com.corp.mms.page.vo.Page;
import java.util.ArrayList;

public class HomeService
{
  HomeDao hd = new HomeDao();

  public int GetPage(String homeNo) {
    return this.hd.GetPage(homeNo);
  }
  public Home GetHomeInfo(int id) {
    return this.hd.GetHomeInfo(id);
  }
  public ArrayList<Home> GetHomesInfo(Page page, String homeNo) {
    return this.hd.GetHomesInfo(page, homeNo);
  }
  public boolean DeleteHome(int id) {
    return this.hd.DeleteHome(id);
  }
  public boolean SaveWorkerInfo(Home home, int info) {
    return this.hd.SaveWorkerInfo(home, info);
  }
}