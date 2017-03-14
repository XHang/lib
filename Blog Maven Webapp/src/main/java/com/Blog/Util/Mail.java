package com.Blog.Util;


import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
	public Mail() {
	}

	public void sendMsg(String mailAddress, String title, String content) {
		boolean sessionDebug = false;
		InternetAddress[] address = null;
		try {
			// 设定所要用的Mail 服务器和所使用的传输协议
			java.util.Properties props = System.getProperties();
			props.put("mail.host", "smtp.sina.com");
			// 这里是我们是用的 smtp
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			// 产生新的Session 服务

			Authentic auth = new Authentic();
			// 看到没，这里我们就用到验证类了 注意我这里是创建的空参的构造方法 因为我在Authentic类里已经给了默认
			// 值 也就是username=“hackq” pwd=“******”这里的****替换成你邮箱的密码

			Session mailSession = Session.getDefaultInstance(props, auth);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			// 设定传送邮件的发信人
			msg.setFrom(new InternetAddress("1083594261@qq.com"));
			// 设定传送邮件至收信人的信箱
			address = InternetAddress.parse(mailAddress, false);
			msg.setRecipients(Message.RecipientType.TO, address);
			// 设定信中的主题
			msg.setSubject(title);
			// 设定送信的时间
			// msg.setSentDate(new Date());
			msg.setDataHandler(new javax.activation.DataHandler("love",
					"text/html"));
			// 这里要注意一下activation用到它了吧 也就是说我们可以发送超文本的邮件 html格式 “love”参数你可以随
			// 便指定为任意字符串
			// 设定传送信的MIME Type
			// 送信
			msg.setContent(content, "text/html;charset=gb2312");
			// content 是你要发送的内容 也就是你调用sendMsg（String mailAddress,String
			// title,String content）传
			// 进来的的值
			Transport.send(msg);
			// out.println("邮件已顺利传送");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	} 
	/*
	 * 发送邮件的方法，参数举例"1083594261", "password", "smtp.sina.com",
								"发送地址", "目的地址", "标题",
								"正文"
	 */
	public void sendMsg(String name, String password, String smtp,
			String sendAddress, String inceptAddress, String title,
			String content) throws Exception {
		boolean sessionDebug = false;
		InternetAddress[] address = null;
		try {
			// 设定所要用的Mail 服务器和所使用的传输协议
			java.util.Properties props = System.getProperties();
			props.put("mail.host", smtp);
			props.put("mail.transport.protocol", smtp);
			props.put("mail.smtp.auth", "true");
			// 产生新的Session 服务

			Authentic auth = new Authentic(name, password);
			Session mailSession = Session.getDefaultInstance(props, auth);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			// 设定传送邮件的发信地址和昵称
			msg.setFrom(new InternetAddress(javax.mail.internet.MimeUtility.encodeText("轻博客")+" <"+sendAddress+">"));  
			// 设定传送邮件至收信人的信箱
			address = InternetAddress.parse(inceptAddress, false);
			msg.setRecipients(Message.RecipientType.TO, address);
			// 设定信中的主题
			msg.setSubject(title);
			// 设定送信的时间
			// msg.setSentDate(new Date());
			msg.setDataHandler(new javax.activation.DataHandler("love",
					"text/html"));
			// 设定传送信的MIME Type
			// msg.setText(messageText);
			// 送信
			msg.setContent(content, "text/html;charset=gb2312");
			Transport.send(msg);
			// out.println("邮件已顺利传送");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}	
}
