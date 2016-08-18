package com.corp.mms.meeting.servlet;

import com.corp.mms.home.service.HomeService;
import com.corp.mms.home.vo.Home;
import com.corp.mms.meeting.service.MeetingService;
import com.corp.mms.meeting.vo.Meeting;
import com.corp.mms.meeting.vo.MeetingTime;
import com.corp.mms.meeting.vo.Schedule;
import com.corp.mms.page.vo.Page;
import com.corp.mms.user.vo.User;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MeetingServlet extends HttpServlet
{
  private static Meeting meeting = null;

  private static Page page = null;
  private static MeetingTime meetingTime = null;
  private static String beginTime = null;
  private static String endTime = null;
  private static int attendance = 0;
  private static Schedule schedule = null;

  private static Home home = null;
  private static String[] IDs = null;
  private static int[] equipIDs = null;

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
    int step = Integer.parseInt(request.getParameter("step"));
    switch (step) {
    case 1:
      SetpOne(request, response);
      break;
    case 2:
      SetpTwo(request, response);
      break;
    case 3:
      SetpThird(request, response);
      break;
    case 4:
      SetpFour(request, response);
      break;
    case 5:
      SetpFive(request, response);
      break;
    case 6:
      SetpSix(request, response);
      break;
    case 7:
      SetpSeven(request, response);
    }
  }

  public void init()
    throws ServletException
  {
  }

  public void SetpOne(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ArrayList homeList = new ArrayList();
    MeetingService ms = new MeetingService();
    int pn = Integer.parseInt(request.getParameter("pn"));
    switch (pn) {
    case 0:
      page = new Page(15);
      meetingTime = new MeetingTime();
      meetingTime.setMeetingDate(request.getParameter("beginTime"));
      meetingTime.setStartHour(request.getParameter("beginHour"));
      meetingTime.setStartMinute(request.getParameter("beginMinute"));
      meetingTime.setEndHour(request.getParameter("endHour"));
      meetingTime.setEndMinute(request.getParameter("endMinute"));
      meetingTime.setAttendance(Integer.parseInt(request.getParameter("attendance")));

      beginTime = request.getParameter("beginTime") + " " + 
        request.getParameter("beginHour") + ":" + request.getParameter("beginMinute");
      endTime = request.getParameter("beginTime") + " " + 
        request.getParameter("endHour") + ":" + request.getParameter("endMinute");
      attendance = Integer.parseInt(request.getParameter("attendance"));
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

    page.setLineCount(ms.GetPage(beginTime, endTime, attendance));
    homeList = ms.GetHomesInfo(page, beginTime, endTime, attendance);

    request.setAttribute("meetingTime", meetingTime);
    request.setAttribute("attendance", Integer.valueOf(attendance));
    request.setAttribute("homeList", homeList);
    request.setAttribute("page", page);
    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_first.jsp")
      .forward(request, response);
  }

  public void SetpTwo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    int id = Integer.parseInt(request.getParameter("id"));

    home = new Home();
    home = new HomeService().GetHomeInfo(id);

    schedule = new Schedule();
    schedule.setHomeID(id);
    schedule.setBeginTime(beginTime);
    schedule.setEndTime(endTime);

    meeting = new Meeting();
    User user = (User)request.getSession().getAttribute("user");
    meeting.setWorkerID(user.getWorkersID());
    meeting.setBeginTime(beginTime);
    meeting.setEndTime(endTime);
    meeting.setHomeID(id);
    meeting.setAttendance(attendance);

    request.setAttribute("meeting", meeting);
    request.setAttribute("schedule", schedule);
    request.setAttribute("home", home);
    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_second.jsp")
      .forward(request, response);
  }

  public void SetpThird(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    ArrayList equipList = new ArrayList();
    MeetingService ms = new MeetingService();

    equipList = ms.GetEnableEquipsInfo();
    request.setAttribute("equipList", equipList);
    request.setAttribute("home", home);

    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_second.jsp")
      .forward(request, response);
  }

  public void SetpFour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    MeetingService ms = new MeetingService();
    ArrayList equipList = new ArrayList();
    ArrayList workerList = new ArrayList();
    workerList = ms.GetWorkersInfo();
    request.setAttribute("workerList", workerList);
    if ((request.getParameter("IDs") != "") && (request.getParameter("IDs") != null))
      IDs = request.getParameter("IDs").split(",");
    else {
      IDs = null;
    }
    if ((IDs != null) && (IDs.length > 0)) {
      equipIDs = new int[IDs.length];
      for (int i = 0; i < equipIDs.length; i++) {
        equipIDs[i] = Integer.parseInt(IDs[i]);
      }
      equipList = ms.GetEquipsInfo(equipIDs);
      request.setAttribute("equipList", equipList);
    }
    request.setAttribute("home", home);
    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_third.jsp")
      .forward(request, response);
  }

  public void SetpFive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    ArrayList homeList = new ArrayList();
    MeetingService ms = new MeetingService();

    page.setLineCount(ms.GetPage(beginTime, endTime, attendance));
    homeList = ms.GetHomesInfo(page, beginTime, endTime, attendance);

    request.setAttribute("meetingTime", meetingTime);
    request.setAttribute("homeList", homeList);
    request.setAttribute("page", page);
    request.setAttribute("attendance", Integer.valueOf(attendance));
    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_first.jsp")
      .forward(request, response);
  }

  public void SetpSix(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    ArrayList equipList = new ArrayList();
    MeetingService ms = new MeetingService();

    equipList = ms.GetEnableEquipsInfo();
    request.setAttribute("home", home);
    request.getRequestDispatcher("../Main/MeetingManage/meetingapply_second.jsp")
      .forward(request, response);
  }

  public void SetpSeven(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    MeetingService ms = new MeetingService();
    String[] wIDs = (String[])null;
    int[] workerIDs = (int[])null;
    if ((request.getParameter("wIDs") != "") && (request.getParameter("wIDs") != null))
      wIDs = request.getParameter("wIDs").split(",");
    else {
      wIDs = (String[])null;
    }
    if ((wIDs != null) && (wIDs.length > 0)) {
      workerIDs = new int[wIDs.length];
      for (int i = 0; i < wIDs.length; i++) {
        workerIDs[i] = Integer.parseInt(wIDs[i]);
      }
    }

    meeting.setStatusID(0);
    Date now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat();
    meeting.setApplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now));

    String cachePath = getServletContext().getRealPath("/upload/cache/");
    String filePath = getServletContext().getRealPath("/upload/meetingdoc/");
    String[] allowedExt = { "doc", "docx", "ppt", "txt", 
      "xls", "xlsx", "rar" };
    boolean legal = false;

    if (ServletFileUpload.isMultipartContent(request))
    {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setRepository(new File(cachePath));

      ServletFileUpload upload = new ServletFileUpload(factory);

      upload.setSizeMax(104857600L);

      List fileList = null;
      try {
        fileList = upload.parseRequest(request);
      }
      catch (FileUploadException localFileUploadException)
      {
      }
      Iterator it = fileList.iterator();
      while (it.hasNext()) {
        FileItem fileItem = (FileItem)it.next();
        if (fileItem.isFormField()) {
          if (fileItem.getFieldName().equals("topic"))
            meeting.setTopic(fileItem.getString("UTF-8"));
        }
        else {
          String userPath = fileItem.getName();
          int start = userPath.lastIndexOf(".");
          String fileExt = userPath.substring(start + 1);
          for (int i = 0; i < allowedExt.length; i++) {
            if (allowedExt[i].equals(fileExt)) {
              legal = true;
              break;
            }
          }
          if (legal) {
            String dateTime = String.valueOf(System.currentTimeMillis()) + "." + fileExt;
            String fileName = filePath + "\\" + dateTime;
            try {
              meeting.setDocumentLink(dateTime);
              int returnedID = ms.AddMeeting(meeting);
              meeting.setId(returnedID);

              if (meeting.getId() > 0) {
                schedule.setMeetingID(meeting.getId());
                ms.AddSchedule(schedule);
                if ((equipIDs != null) && 
                  (!ms.AddEquipApply(meeting.getId(), equipIDs))) {
                  SetpFour(request, response);
                }
                if ((workerIDs != null) && 
                  (ms.AddAttendenceList(meeting.getId(), workerIDs)))
                  SetpFour(request, response);
              }
              else {
                SetpFour(request, response);
              }

              request.getRequestDispatcher("../success.jsp").forward(request, response);
              File diskFile = new File(fileName);
              fileItem.write(diskFile);
            }
            catch (Exception localException)
            {
            }
          }
        }
      }
    }
  }
}