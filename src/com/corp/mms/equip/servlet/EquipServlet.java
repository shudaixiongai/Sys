package com.corp.mms.equip.servlet;

import com.corp.mms.equip.service.EquipService;
import com.corp.mms.page.vo.Page;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EquipServlet extends HttpServlet
{
  private static Page page = null;
  private static String txtQuery = null;
  private int condition = 1;

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
    ArrayList equipList = new ArrayList();
    EquipService es = new EquipService();
    int pn = Integer.parseInt(request.getParameter("pn"));
    switch (pn) {
    case 0:
      page = new Page(15);
      this.condition = Integer.parseInt(request.getParameter("condition"));
      txtQuery = request.getParameter("txtQuery");
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
      boolean bool = es.DeleteEquip(id);
    }

    if ((txtQuery == null) || (txtQuery == "")) {
      page.setLineCount(es.GetPage());
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      equipList = es.GetEquipsInfo(page);
    }
    else if (this.condition == 1) {
      page.setLineCount(es.GetPageByName(txtQuery));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      equipList = es.GetEquipsInfoByName(page, txtQuery);
    } else if (this.condition == 2) {
      page.setLineCount(es.GetPageByType(txtQuery));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      equipList = es.GetEquipsInfoByType(page, txtQuery);
    } else if (this.condition == 3) {
      page.setLineCount(es.GetPageByBelongTo(txtQuery));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      equipList = es.GetEquipsInfoByBelongTo(page, txtQuery);
    }

    request.setAttribute("equipList", equipList);
    request.setAttribute("page", page);
    request.getRequestDispatcher("../Main/EquipManage/equipmanage.jsp")
      .forward(request, response);
  }

  public void init()
    throws ServletException
  {
  }
}