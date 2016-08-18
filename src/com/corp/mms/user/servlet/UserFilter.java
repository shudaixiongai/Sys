package com.corp.mms.user.servlet;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter
  implements Filter
{
  private FilterConfig config = null;
  private String webRoot = null;

  public void destroy()
  {
  }

  public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)arg0;
    HttpServletResponse response = (HttpServletResponse)arg1;
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");

    HttpSession session = request.getSession(false);
    String uri = request.getRequestURI();
    System.out.println("uri =========" + uri);
    if ((uri != null) && (
      (uri.equals(this.webRoot + "/")) || 
      (uri.equals(this.webRoot + "/servlet/UserServlet")) || 
      (uri.contains("/images")) || (uri.contains("/js")) || 
      (uri.contains("upload/meetingdoc")) || 
      (uri.equals(this.webRoot + "/index.jsp")) || 
      (uri
      .equals(this.webRoot + "/CheckCodeServlet")))) {
      arg2.doFilter(arg0, arg1);
    }
    else if ((session == null) || (session.getAttribute("user") == null))
      response.sendRedirect(this.webRoot + "/index.jsp");
    else
      arg2.doFilter(arg0, arg1);
  }

  public void init(FilterConfig arg0)
    throws ServletException
  {
    this.config = arg0;
    ServletContext ctx = this.config.getServletContext();
    this.webRoot = ctx.getContextPath();
    System.out.println("webRoot =========" + this.webRoot);
  }
}