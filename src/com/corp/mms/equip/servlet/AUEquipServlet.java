package com.corp.mms.equip.servlet;

import com.corp.mms.equip.service.EquipService;
import com.corp.mms.equip.vo.Equipment;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AUEquipServlet extends HttpServlet
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
    EquipService es = new EquipService();
    if (ac == 3) {
      int info = Integer.parseInt(request.getParameter("info"));
      Equipment equip = new Equipment();

      equip.setEquipmentID(request.getParameter("equipmentID"));
      equip.setEquipmentName(request.getParameter("equipmentName"));
      equip.setEquipType(Integer.parseInt(request.getParameter("equiptype")));
      equip.setStoreTime(request.getParameter("storeTime"));
      equip.setBelongTo(request.getParameter("belongTo"));
      if (info == 2) {
        equip.setId(Integer.parseInt(request.getParameter("id")));
      }
      boolean result = es.SaveEquipInfo(equip, info);
      if (result) {
        request.getRequestDispatcher("../success.jsp").forward(request, response);
      } else {
        ArrayList typeList = new ArrayList();
        typeList = es.GetEquipTypeList();
        request.setAttribute("typeList", typeList);
        request.setAttribute("equip", equip);
        request.setAttribute("info", Integer.valueOf(info));
        request.getRequestDispatcher("../Main/EquipManage/addequip.jsp").forward(request, response);
      }
    } else {
      ArrayList typeList = new ArrayList();
      typeList = es.GetEquipTypeList();
      request.setAttribute("typeList", typeList);
      if (ac == 1) {
        request.setAttribute("info", Integer.valueOf(1));
        request.getRequestDispatcher("../Main/EquipManage/addequip.jsp")
          .forward(request, response);
      }
      else if (ac == 2) {
        int id = Integer.parseInt(request.getParameter("id"));
        Equipment equip = new Equipment();
        equip = es.GetEquipInfo(id);
        request.setAttribute("equip", equip);
        request.setAttribute("info", Integer.valueOf(2));
        request.getRequestDispatcher("../Main/EquipManage/addequip.jsp")
          .forward(request, response);
      }
    }
  }

  public void init()
    throws ServletException
  {
  }
}