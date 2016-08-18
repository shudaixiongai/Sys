package com.corp.mms.user.servlet;

import com.corp.mms.menu.vo.Menu;
import com.corp.mms.user.service.UserService;
import com.corp.mms.user.vo.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkCode = request.getParameter("checkcode");
		String code = (String) request.getSession().getAttribute("checkCode");
		if (checkCode.equalsIgnoreCase(code)) {
			User user = new User();
			request.setAttribute("info", 2);

			String workerID = request.getParameter("workerID");
			String password = request.getParameter("password");
			if ((workerID != null) && (workerID != "") && (password != null) && (password != "")) {
				user.setWorkersID(workerID);
				user.setPassword(password);
				user.setRoleID(Integer.parseInt(request.getParameter("roleID")));
				UserService us = new UserService();
				int count = us.CheckUser(user);
				if (count > 0) {
					Menu[] menuList = Menu.GetMenu(user.getRoleID());
					request.getSession().setAttribute("menuList", menuList);

					request.getSession().setAttribute("user", user);
					response.sendRedirect("../Main/main.jsp");
				} else {
					response.sendRedirect("../index.jsp");
				}
			} else {
				response.sendRedirect("../index.jsp");
			}
		} else {
			response.sendRedirect("../index.jsp");
		}
	}
}