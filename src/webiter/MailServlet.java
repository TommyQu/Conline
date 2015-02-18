package webiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.net.*;

import org.apache.commons.lang.StringEscapeUtils;

import DAO.CoUserInfoDAO;


import domain.CoUserInfo;

public class MailServlet extends HttpServlet {
	private static String host;

	private static String from;

	/**
	 * Constructor of the object.
	 */
	public MailServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("username");
			String email = request.getParameter("email");
			PrintWriter out=response.getWriter();
			CoUserInfoDAO cuidao = new CoUserInfoDAO();
			String lmt = String.format("name='%s' and email='%s'",
					StringEscapeUtils.escapeSql(name),
					StringEscapeUtils.escapeSql(email));
			List<CoUserInfo> lcui = cuidao.query(lmt);
			if (lcui == null || lcui.size() != 1) {
				out.println("<script language = javascript>alert('用户名邮箱错误')");
				out.println("window.history.go(-1)</script>");
			}
			else
			{
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.transport.protocol", "smtp");
				props.put("mail.smtp.host", host);
				Session session = Session.getInstance(props);
				//session.setDebug(true);

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setSubject("C语言在线学习平台密码找回");
				message.setText(lcui.get(0).getPwd());
				Transport transport = session.getTransport();
				transport.connect("smtp.qq.com", 25, from, "fzu!20040132");
				transport.sendMessage(message, new Address[]{new InternetAddress(email)});
				transport.close();
			/*Properties props = System.getProperties();
			// Setup mail server
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");
			props.put("mail.transport.protocol", "smtp");// 发送邮件的协议
			// Get session
			Session session = Session.getDefaultInstance(props, null);
			// Define message
			MimeMessage message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress(from));
			// Set the to address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			// Set the subject
			message.setSubject("C语言在线学习平台密码找回");
			// Set the content
			message.setText(lcui.get(0).getPwd());
			// Send message
			Transport tran = session.getTransport();
			tran.connect("smtp.qq.com", "qht1992", "fzu!20040132");
			tran.send(message);*/
				out.print("<script>alert('邮件发送成功!');window.location.href='index.jsp'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
		host = config.getInitParameter("host");
		from = config.getInitParameter("from");
		//System.out.println(host + from);
	}

}
