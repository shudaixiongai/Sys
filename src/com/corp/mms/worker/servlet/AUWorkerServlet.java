package com.corp.mms.worker.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corp.mms.worker.service.WorkerService;
import com.corp.mms.worker.vo.*;

public class AUWorkerServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ����,ְλ�б� �����浽request
		// 1:����û�;2:�޸��û�;3:����(���/�޸�)
		int ac = Integer.parseInt(request.getParameter("ac"));
		WorkerService ws = new WorkerService();
		
		if (ac == 3) { //3:����(���/�޸�)
			int info=Integer.parseInt(request.getParameter("info"));
			Worker worker=new Worker();
			
			worker.setWorkerID(request.getParameter("workerID"));
			worker.setUserGroup(request.getParameter("userGroup"));
			worker.setName(request.getParameter("name"));
			worker.setTelephone(request.getParameter("telephone"));
			worker.setBirthday(request.getParameter("birthday"));
			worker.setSex(request.getParameter("sex"));
			worker.seteMail(request.getParameter("eMail"));
			worker.setDepartmentID(Integer.parseInt(request.getParameter("department")));
			worker.setPositionID(Integer.parseInt(request.getParameter("position")));
			if(info==2){ //�����޸�
				worker.setId(Integer.parseInt(request.getParameter("id")));
			}
			boolean result=ws.SaveWorkerInfo(worker, info); 
			//��ȡ������,�ɹ�����ת���ɹ�ҳ,ʧ������ԭҳ��
			if(result){
				request.getRequestDispatcher("../success.jsp").forward(request, response);
			}else{ //ʧ�������¼�������
				ArrayList<Department> departList = new ArrayList<Department>();
				ArrayList<Position> positionList = new ArrayList<Position>();
				departList = ws.GetDepartmentList();
				positionList = ws.GetPositionList();
				request.setAttribute("departList", departList);
				request.setAttribute("positionList", positionList);
				request.setAttribute("worker", worker);
				request.setAttribute("info", info);
				request.getRequestDispatcher("../Main/UserManage/adduser.jsp").forward(request, response);
			}
		} else {
			ArrayList<Department> departList = new ArrayList<Department>();
			ArrayList<Position> positionList = new ArrayList<Position>();
			departList = ws.GetDepartmentList();
			positionList = ws.GetPositionList();
			request.setAttribute("departList", departList);
			request.setAttribute("positionList", positionList);
			if(ac==1){ //1:����û�
				request.setAttribute("info", 1);
				request.getRequestDispatcher("../Main/UserManage/adduser.jsp")
				.forward(request, response);  //info:1-->����;2-->�޸�
			}
			else if (ac == 2) { //2:�޸��û�
				int id = Integer.parseInt(request.getParameter("id"));
				Worker worker = new Worker();
				worker = ws.GetWorkerInfo(id);
				request.setAttribute("worker", worker);
				request.setAttribute("info", 2);
				request.getRequestDispatcher("../Main/UserManage/adduser.jsp")
				.forward(request, response);
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
