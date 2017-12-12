package com.csy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csy.email.MailSenderInfo;
import com.csy.email.SimpleMailSender;
import com.csy.util.Constant;

public class MailSend extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����ʼ�
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String url = Constant.serverIp + getServletContext().getContextPath() + "/VeryCountEmail" + "?uuid="
				+ Constant.currentUserUuid;
		System.out.println("url:" + url);
		// ����
		boolean flag = preformSendEmail(mail, "�����ʼ�", "���ʼ�Ϊ�ٷ������ʼ�,���ѳɹ�ע��IT399�Ƽ����˻�,��������" + ":" + url + " ,�����˻���");

		// Ⱥ��
		// String[] destEmailAddresses = new
		// String[]{"xxxx@163.com","xxxxx@qq.com"};
		// boolean flag =
		// preformSendEmails(destEmailAddresses,"�����ʼ�","���ʼ�Ϊ�ٷ������ʼ�,���ѳɹ�ע��IT399�Ƽ����˻�,��������:http://www.baidu.com
		// ,�����˻���");

		if (flag) {
			// ��ת�����ͳɹ���ʾҳ��
			log("mail:���ͳɹ�");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/RegistSuccess.jsp");
			requestDispatcher.forward(req, resp);
		} else {
			// ����ʧ��,���·���
			log("mail:����ʧ��");
			// resp.setCharacterEncoding("UTF-8");
			//GBK����UTF-8������ ��Ȼjs�����������
			resp.setContentType("text/html;charset=UTF-8");

			PrintWriter out = resp.getWriter();// ��������
			//out.print("<script>" + "alert('ע��ʧ��,��������ȷ������');" + "document.location.href="+req.getServletPath()+"'/Regist.jsp';" + "</script>");

			String msg="ע��ʧ��,��������ȷ������";
			//String urlRelaod = req.getServletPath()+"/Regist.jsp";
			String urlRelaod = req.getServletContext().getContextPath()+"/Regist.jsp";
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("window.location='" + urlRelaod + "'");
			out.println("</script>");
			
			out.close();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * �����ʼ����Ĵ��� ����������
	 */
	public boolean preformSendEmail(String destEmailAddress, String title, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		// �趨������������ ���׵�163����Ϊsmtp.163.com
		mailInfo.setMailServerHost("smtp.163.com");
		// �趨�������������˿ں�
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// �ο�readme��ȡ���׿ͻ�����Ȩ����,���ﲻ��Ϊ��
		String username = null ;// ��Ҫ����׺
		String password = null ;
		if (password == null || username == null) {
			throw new RuntimeException("�뵽�������룬����ο�readme�������ʺ����벻��Ϊ�գ�");
		}
		// 163�����˻���
		mailInfo.setUserName(username);
		// ��������������߿ͻ�����Ȩ����
		mailInfo.setPassword(password);
		// ��ʾ�ڶԷ��ʼ���̧ͷ
		mailInfo.setFromAddress(username + "@163.com");
		mailInfo.setToAddress(destEmailAddress);
		// mailInfo.setSubject(title + new Date().toString());
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// �Ƿ�Ⱥ��
		mailInfo.setSnedToAll(false);
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// ���������ʽ
		// return sms.sendHtmlMail(mailInfo);// ����html��ʽ
	}

	/**
	 * �����ʼ����Ĵ��� ��Ⱥ��
	 */
	public boolean preformSendEmails(String[] destEmailAddresses, String title, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		// �趨������������ ���׵�163����Ϊsmtp.163.com
		mailInfo.setMailServerHost("smtp.163.com");
		// �趨�������������˿ں�
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// �ο�readme��ȡ���׿ͻ�����Ȩ����,���ﲻ��Ϊ��
		String username = null;// ��Ҫ����׺
		String password = null;
		if (password == null || username == null) {
			throw new RuntimeException("�뵽�������룬����ο�readme�������ʺ����벻��Ϊ�գ�");
		}
		// 163�����˻���
		mailInfo.setUserName(username);
		// ��������������߿ͻ�����Ȩ����
		mailInfo.setPassword(password);
		// ��ʾ�ڶԷ��ʼ���̧ͷ
		mailInfo.setFromAddress(username + "@163.com");
		// �Ƿ�Ⱥ��
		mailInfo.setSnedToAll(true);
		mailInfo.setToAddresses(destEmailAddresses);
		// mailInfo.setSubject(title + new Date().toString());
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		return sms.sendTextMail(mailInfo);// ���������ʽ
		// return sms.sendHtmlMail(mailInfo);// ����html��ʽ
	}
}
