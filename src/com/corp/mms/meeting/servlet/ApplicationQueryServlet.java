package com.corp.mms.meeting.servlet;

import com.corp.mms.meeting.service.MeetingService;
import com.corp.mms.page.vo.Page;
import com.corp.mms.user.vo.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationQueryServlet extends HttpServlet
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

      break;
    case 6:
      int id = Integer.parseInt(request.getParameter("id"));
      String docLink = ms.GetDcomentLink(id);
      if ((docLink != null) && (docLink != "")) {
        String filePath = getServletContext().getRealPath("upload/meetingdoc/" + docLink);
        File file = new File(filePath);
        if (file.exists())
          file.delete();
      }
      boolean result = ms.CancleMeeting(id);
    }

    if ((topic == null) || (topic == "")) {
      page.setLineCount(ms.GetMeetingApplyPage(user.getWorkersID()));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      meetingList = ms.GetMeetingList(user.getWorkersID());
    } else {
      page.setLineCount(ms.GetMeetingPageByTopic(topic, user.getWorkersID()));
      if (page.getPageNo() > page.getPageCount())
        page.setPageNo(page.getPageCount());
      meetingList = ms.GetMeetingListByTopic(topic, user.getWorkersID());
    }
    request.setAttribute("meetingList", meetingList);
    request.setAttribute("page", page);
    request.getRequestDispatcher("../Main/MeetingManage/applicationquery.jsp")
      .forward(request, response);
  }

  public void init()
    throws ServletException
  {
  }
}