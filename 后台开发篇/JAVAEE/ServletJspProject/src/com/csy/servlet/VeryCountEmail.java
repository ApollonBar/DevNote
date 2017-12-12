package com.csy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csy.util.Constant;

/**
 * Servlet implementation class VeryCountEmail
 */
public class VeryCountEmail extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uuid  = request.getParameter("uuid");
		if (Constant.currentUserUuid.equals(uuid)) {
			//������֤�ɹ�
			request.setAttribute("result", "������֤�ɹ�,��ת��¼ҳ��...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistMailVeryResult.jsp");
			dispatcher.forward(request, response);

		}else {
			//������֤ʧ��
			request.setAttribute("result", "������֤ʧ��,��תע��ҳ��...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistMailVeryResult.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
