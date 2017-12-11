package com.csy.email;


import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * ���ʼ��������������ʼ���������
 * �����ʼ���Ҫʹ�õĻ�����Ϣ
 * ʵ�ֲο���
	1.�ͻ���ͨ��Email������֤����߷�����Ϣ�Ľ������ https://github.com/forjunjian/AndroidEmail#android%E5%AE%A2%E6%88%B7%E7%AB%AF%E9%80%9A%E8%BF%87email%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E6%88%96%E8%80%85%E5%8F%8D%E9%A6%88%E4%BF%A1%E6%81%AF%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88
	2.Javaʵ��ע��ʱ���ͼ����ʼ�+���� https://www.cnblogs.com/ganchuanpu/archive/2016/11/29/6115691.html
 	3.Servlet���˶��˷��͵����ʼ�http://www.runoob.com/servlet/servlet-sending-email.html
 	4.163����ͻ�����Ȩ������ô���? 
 */

public class SimpleMailSender {
    /**
     * ���ı���ʽ�����ʼ�
     *
     * @param mailInfo �����͵��ʼ�����Ϣ
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        // �ж��Ƿ���Ҫ�����֤
        SimpleAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // �����Ҫ�����֤���򴴽�һ��������֤��
            authenticator = new SimpleAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ
            Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������
            mailMessage.setSubject(mailInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ��
            mailMessage.setSentDate(new Date());
            // �����ʼ���Ϣ����Ҫ����
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // �����ʼ�
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * ��HTML��ʽ�����ʼ�
     *
     * @param mailInfo �����͵��ʼ���Ϣ
     */
    public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // �ж��Ƿ���Ҫ�����֤
        SimpleAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //�����Ҫ�����֤���򴴽�һ��������֤��
        if (mailInfo.isValidate()) {
            authenticator = new SimpleAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ
            Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������
            mailMessage.setSubject(mailInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ��
            mailMessage.setSentDate(new Date());
            // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
            Multipart mainPart = new MimeMultipart();
            // ����һ������HTML���ݵ�MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // ����HTML����
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // ��MiniMultipart��������Ϊ�ʼ�����
            mailMessage.setContent(mainPart);
            // �����ʼ�
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}   