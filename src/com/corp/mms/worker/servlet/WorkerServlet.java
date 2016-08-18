package com.corp.mms.worker.servlet;

import com.corp.mms.page.vo.Page;
import com.corp.mms.worker.service.WorkerService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkerServlet extends HttpServlet
{
  private static String txtQuery = null;
  private static String condition = null;
  private static Page page = null;

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
    ArrayList workerList = new ArrayList();
    WorkerService ws = new WorkerService();
    int pn = Integer.parseInt(request.getParameter("pn"));

    switch (pn) {
    case 0:
      page = new Page(15);
      txtQuery = request.getParameter("txtQeury");
      condition = request.getParameter("condition");
      break;
    case 1:
      page.setPageNo(1);
      break;
    case 2:
      if (page.getPageNo() <= 1) break;
      page.setPageNo(page.getPageNo() - 1);

      break;
    case 3:
      if (page.getPageNo() >= page.getPageCount()) break;
      page.setPageNo(page.getPageNo() + 1);

      break;
    case 4:
      page.setPageNo(page.getPageCount());
      break;
    case 5:
      int changPageNo = Integer.parseInt(request.getParameter("changPageNo"));
      if ((changPageNo >= page.getPageCount()) || (changPageNo <= 0)) break;
      page.setPageNo(changPageNo);

      break;
    case 6:
      int id = Integer.parseInt(request.getParameter("id"));
      boolean bool = ws.DeleteWorker(id);
    }

    if ((txtQuery == null) || (txtQuery.equals(""))) {
      page.setLineCount(ws.GetPage(null, null));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      workerList = ws.GetWorkersInfo(page, null, null);
    } else if (condition.equals("1")) {
      page.setLineCount(ws.GetPage(txtQuery, null));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      workerList = ws.GetWorkersInfo(page, txtQuery, null);
    } else if (condition.equals("2")) {
      page.setLineCount(ws.GetPage(null, txtQuery));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      workerList = ws.GetWorkersInfo(page, null, txtQuery);
    }
    request.setAttribute("workerList", workerList);
    request.setAttribute("page", page);
    request.getRequestDispatcher("../Main/UserManage/usermanage.jsp")
      .forward(request, response);
  }

  public void init()
    throws ServletException
  {
  }
}