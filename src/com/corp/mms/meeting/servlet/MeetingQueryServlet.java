package com.corp.mms.meeting.servlet;

import com.corp.mms.meeting.service.MeetingService;
import com.corp.mms.page.vo.Page;
import com.corp.mms.user.vo.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MeetingQueryServlet extends HttpServlet
{
  private static Page page = null;
  private static String topic = null;

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
    User user = (User)request.getSession().getAttribute("user");
    switch (pn) {
    case 0:
      page = new Page(15);
      topic = request.getParameter("topic");
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
    }

    if (user.getRoleID() == 0) {
      if ((topic == null) || (topic == "")) {
        page.setLineCount(ms.GetMyMeetingPage(user.getWorkersID()));
        if (page.getPageNo() > page.getPageCount())
          page.setPageNo(page.getPageCount());
        meetingList = ms.GetMyMeetingList(user.getWorkersID());
      } else {
        page.setLineCount(ms.GetMyMeetingPageByTopic(topic, user.getWorkersID()));
        if (page.getPageNo() > page.getPageCount())
          page.setPageNo(page.getPageCount());
        meetingList = ms.GetMyMeetingListByTopic(topic, user.getWorkersID());
      }
    }
    else if (user.getRoleID() == 1) {
      if ((topic == null) || (topic == "")) {
        page.setLineCount(ms.GetAllMeetingPage());
        if (page.getPageNo() > page.getPageCount())
          page.setPageNo(page.getPageCount());
        meetingList = ms.GetAllMeetingList();
      } else {
        page.setLineCount(ms.GetAllMeetingPageByTopic(topic));
        if (page.getPageNo() > page.getPageCount())
          page.setPageNo(page.getPageCount());
        meetingList = ms.GetAllMeetingListByTopic(topic);
      }
    }
    request.setAttribute("meetingList", meetingList);
    request.setAttribute("page", page);
    request.getRequestDispatcher(
      "../Main/MeetingManage/meetingquery.jsp").forward(request, 
      response);
  }

  public void init()
    throws ServletException
  {
  }
}