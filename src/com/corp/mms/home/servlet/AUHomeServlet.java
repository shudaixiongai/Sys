package com.corp.mms.home.servlet;

import com.corp.mms.home.service.HomeService;
import com.corp.mms.home.vo.Home;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AUHomeServlet extends HttpServlet
{
  public void destroy()
  {
    super.destroy();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    int ac = Integer.parseInt(request.getParameter("ac"));
    HomeService hs = new HomeService();
    if (ac == 3) {
      int info = Integer.parseInt(request.getParameter("info"));
      Home home = new Home();
      home.setHomeNo(request.getParameter("homeNo"));
      home.setAddress(request.getParameter("address"));
      home.setSpace(Integer.parseInt(request.getParameter("space")));
      home.setHosterID(request.getParameter("hosterID"));
      if (info == 2) {
        home.setId(Integer.parseInt(request.getParameter("id")));
      }
      boolean result = hs.SaveWorkerInfo(home, info);

      if (result) {
        request.getRequestDispatcher("../success.jsp").forward(request, response);
      } else {
        request.setAttribute("home", home);
        request.setAttribute("info", Integer.valueOf(info));
        request.getRequestDispatcher("../Main/HomeManage/addhome.jsp").forward(request, response);
      }

    }
    else if (ac == 1) {
      request.setAttribute("info", Integer.valueOf(1));
      request.getRequestDispatcher("../Main/HomeManage/addhome.jsp")
        .forward(request, response);
    } else if (ac == 2) {
      int id = Integer.parseInt(request.getParameter("id"));
      Home home = new Home();
      home = hs.GetHomeInfo(id);
      request.setAttribute("home", home);
      request.setAttribute("info", Integer.valueOf(2));
      request.getRequestDispatcher("../Main/HomeManage/addhome.jsp")
        .forward(request, response);
    }
  }

  public void init()
    throws ServletException
  {
  }
}