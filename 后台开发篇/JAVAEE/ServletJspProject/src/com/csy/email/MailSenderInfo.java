package com.csy.email;

import java.util.Properties;

/**
 * �����ʼ���Ҫʹ�õĻ�����Ϣ
 * ʵ�ֲο���
	1.�ͻ���ͨ��Email������֤����߷�����Ϣ�Ľ������ https://github.com/forjunjian/AndroidEmail#android%E5%AE%A2%E6%88%B7%E7%AB%AF%E9%80%9A%E8%BF%87email%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E6%88%96%E8%80%85%E5%8F%8D%E9%A6%88%E4%BF%A1%E6%81%AF%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88
	2.Javaʵ��ע��ʱ���ͼ����ʼ�+���� https://www.cnblogs.com/ganchuanpu/archive/2016/11/29/6115691.html
 	3.Servlet���˶��˷��͵����ʼ�http://www.runoob.com/servlet/servlet-sending-email.html
 	4.163����ͻ�����Ȩ������ô���? 
 */
public class MailSenderInfo {
    // �����ʼ��ķ�������IP�Ͷ˿�   
    private String mailServerHost;
    private String mailServerPort = "25";
    // �ʼ������ߵĵ�ַ   
    private String fromAddress;
    // �ʼ������ߵĵ�ַ   
    private String toAddress;
    // Ⱥ���ʼ������ߵĵ�ַ   
    private String[] toAddresses;
  
	//�Ƿ�Ⱥ��
    private boolean isSnedToAll = false;
    public boolean isSnedToAll() {
		return isSnedToAll;
	}

	public void setSnedToAll(boolean isSnedToAll) {
		this.isSnedToAll = isSnedToAll;
	}

	// ��½�ʼ����ͷ��������û���������   
    private String userName;
    private String password;
    // �Ƿ���Ҫ�����֤   
    private boolean validate = false;
    // �ʼ�����   
    private String subject;
    // �ʼ����ı�����   
    private String content;
    // �ʼ��������ļ���   
    private String[] attachFileNames;

    /**
     * ����ʼ��Ự����
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }
    
    public String[] getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(String[] toAddresses) {
		this.toAddresses = toAddresses;
	}

	
}   