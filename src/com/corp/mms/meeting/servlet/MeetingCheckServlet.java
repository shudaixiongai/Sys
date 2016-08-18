package com.corp.mms.meeting.servlet;

import com.corp.mms.meeting.service.MeetingService;
import com.corp.mms.page.vo.Page;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeetingCheckServlet extends HttpServlet
{
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
    ArrayList meetingList = new ArrayList();
    MeetingService ms = new MeetingService();
    int pn = Integer.parseInt(request.getParameter("pn"));
    switch (pn) {
    case 0:
      page = new Page(15);
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
      ms.PassedCheck(id);
      break;
    case 7:
      int meetingID = Integer.parseInt(request.getParameter("id"));
      ArrayList equipList = ms.GetEquipListOfMeeting(meetingID);
      request.setAttribute("equipList", equipList);
    }

    page.setLineCount(ms.GetMeetingApplyPage());
    if (page.getPageNo() > page.getPageCount())
      page.setPageNo(page.getPageCount());
    meetingList = ms.GetMeetingList();
    request.setAttribute("meetingList", meetingList);
    request.setAttribute("page", page);
    request.getRequestDispatcher("../Main/MeetingManage/meetingcheck.jsp")
      .forward(request, response);
  }

  public void init()
    throws ServletException
  {
  }
}