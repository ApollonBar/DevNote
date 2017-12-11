package com.csy.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csy.email.MailSenderInfo;
import com.csy.email.SimpleMailSender;

public class MailSend extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		//�����ʼ�
		req.getAttribute("");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		doGet(req, resp);
		
	}
	
	
	  /**
     * �����ʼ����Ĵ��� �����ֲ�����ο�readme
     */
    public void preformSendEmail(String destEmailAddress, String title, String content) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        // �趨������������ ���׵�163����Ϊsmtp.163.com
        mailInfo.setMailServerHost("smtp.163.com");
        // �趨�������������˿ں�
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // �ο�readme��ȡ���׿ͻ�����Ȩ����,���ﲻ��Ϊ��
        String username = null;
        String password = null;
        if (password == null || username == null) {
            throw new RuntimeException("�뵽�������룬����ο�readme�������ʺ����벻��Ϊ�գ�");
        }
        //163�����˻���
        mailInfo.setUserName(username);
        // ��������������߿ͻ�����Ȩ����
        mailInfo.setPassword(password);
        // Ĭ�Ͽ���û�У���ʾ�ڶԷ��ʼ���̧ͷ
        mailInfo.setFromAddress(username + "@163.com");
        mailInfo.setToAddress(destEmailAddress);
        mailInfo.setSubject(title + new Date().toString());
        mailInfo.setContent(content);
        // �������Ҫ�������ʼ�
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);// ���������ʽ
//		sms.sendHtmlMail(mailInfo);// ����html��ʽ
        System.out.println("sending success");
    }
}
