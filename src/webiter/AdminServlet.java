package webiter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

import DAO.CoFileInfoDAO;
import DAO.CoForumLogDAO;
import DAO.CoForumReplyDAO;
import DAO.CoForumTopicDAO;
import DAO.CoJudgeClientDAO;
import DAO.CoProblemDAO;
import DAO.CoUserInfoDAO;
import DAO.VCoForumTopicListDAO;
import domain.CoFileInfo;
import domain.CoForumLog;
import domain.CoForumTopic;
import domain.CoJudgeClient;
import domain.CoProblem;
import domain.CoUserInfo;
import domain.VCoForumTopicList;

public class AdminServlet extends HttpServlet {
	private static String host;

	private static String from;
	/**
	 * Constructor of the object.
	 */
	public AdminServlet() {
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
		if (request.getParameter("action").equals("tousermanagepage"))
			tousermanagepage(request, response);
		else if (request.getParameter("action").equals("usermanage"))
			usermanage(request, response);
		else if (request.getParameter("action").equals("topostmanagepage"))
			topostmanagepage(request, response);
		else if (request.getParameter("action").equals("deletepost"))
			deletepost(request, response);
		else if (request.getParameter("action").equals("touploadproblempage"))
			touploadproblempage(request, response);
		else if (request.getParameter("action").equals("uploadproblem"))
			uploadproblem(request, response);
		else if (request.getParameter("action").equals("tonotifypage"))
			tonotifypage(request, response);
		else if (request.getParameter("action").equals("notify"))
			notify(request, response);
		else if (request.getParameter("action").equals("touploadfilepage"))
			touploafilepage(request, response);
		else if (request.getParameter("action").equals("uploadfile"))
			uploadfile(request, response);
		else if (request.getParameter("action").equals("todeletefilepage"))
			todeletefilepage(request, response);
		else if (request.getParameter("action").equals("deletefile"))
			deletefile(request, response);
		else if (request.getParameter("action").equals("todeleteproblempage"))
			todeleteproblempage(request, response);
		else if (request.getParameter("action").equals("deleteproblem"))
			deleteproblem(request, response);
		else if (request.getParameter("action").equals("toforumlogpage"))
			toforumlogpage(request, response);
		else if (request.getParameter("action").equals("deletelog"))
			deletelog(request, response);
		else if (request.getParameter("action").equals("judgeclient"))
			judgeclient(request, response);
		else if (request.getParameter("action").equals("upmaillogpage"))
			tomaillogpage(request, response);
		else if (request.getParameter("action").equals("logout"))
			logout(request, response);
	}

	private void judgeclient(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoJudgeClientDAO cjcdao=new CoJudgeClientDAO();
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				cjcdao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					cjcdao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					cjcdao.queryStartPos = (page - 1) * 10;
				}
			}
			cjcdao.queryStartCount = 10;
			String lmt = "";
			List<CoJudgeClient> lcjc = cjcdao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcjc", lcjc);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/JudgeClient.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deletelog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			CoForumLogDAO cfldao=new CoForumLogDAO();
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			String lmt;
			lmt = String.format("id='%d'", id);
			boolean result = cfldao.delete(lmt);
			if (result == true) {
				out.print("<script>alert('删除成功!');window.location.href='admin?action=toforumlogpage&page=0'</script>");
			} else {
				out.println("<script language = javascript>alert('删除失败')");
				out.println("window.history.go(-1)</script>");
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>");
			e.printStackTrace();
		}
	}

	private void deleteproblem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			CoProblemDAO cpdao=new CoProblemDAO();
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			String lmt;
			lmt = String.format("id='%d'", id);
			boolean result = cpdao.delete(lmt);
			if (result == true) {
				out.print("<script>alert('删除成功!');window.location.href='admin?action=todeleteproblempage&page=0'</script>");
			} else {
				out.println("<script language = javascript>alert('删除失败')");
				out.println("window.history.go(-1)</script>");
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>");
			e.printStackTrace();
		}
	}

	private void todeleteproblempage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoProblemDAO cpdao=new CoProblemDAO();
			String lmt = String.format("");
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				cpdao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					cpdao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					cpdao.queryStartPos = (page - 1) * 10;
				}
			}
			cpdao.queryStartCount = 10;
			List<CoProblem> lcp = cpdao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcp", lcp);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/DeleteProblem.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deletefile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			CoFileInfoDAO cfidao=new CoFileInfoDAO();
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			String lmt;
			lmt = String.format("id='%d'", id);
			boolean result = cfidao.delete(lmt);
			if (result == true) {
				out.print("<script>alert('删除成功!');window.location.href='admin?action=todeletefilepage&page=0'</script>");
			} else {
				out.println("<script language = javascript>alert('删除失败')");
				out.println("window.history.go(-1)</script>");
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>");
			e.printStackTrace();
		}
	}

	private void todeletefilepage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoFileInfoDAO cfidao=new CoFileInfoDAO();
			String lmt = String.format("");
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				cfidao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					cfidao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					cfidao.queryStartPos = (page - 1) * 10;
				}
			}
			cfidao.queryStartCount = 10;
			List<CoFileInfo> lcfi = cfidao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcfi", lcfi);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/DeleteFile.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tomaillogpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void toforumlogpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoForumLog cfl=new CoForumLog();
			CoForumLogDAO cfldao=new CoForumLogDAO();
			String lmt = String.format("");
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				cfldao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					cfldao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					cfldao.queryStartPos = (page - 1) * 10;
				}
			}
			cfldao.queryStartCount = 10;
			List<CoForumLog> lcfl = cfldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcfl", lcfl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/ForumLog.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uploadfile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");// 防止中文乱码
		CoForumTopic cft=new CoForumTopic();
		CoForumTopicDAO cftdao=new CoForumTopicDAO();
		CoFileInfo cfi=new CoFileInfo();
		CoFileInfoDAO cfidao=new CoFileInfoDAO();
		CoUserInfo cui=(CoUserInfo) request.getSession().getAttribute("user");
		PrintWriter out = response.getWriter();
		String realPath = this.getServletContext().getRealPath("/data/coursematerial");// 需要存放的真实路径
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart)
			return;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(1024 * 1024 * 100);// the max size of the upload
												// file
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		for (FileItem item : items) {
			if (!item.isFormField()) {
				File fullFile = new File(item.getName());
				File uploadFile = new File(realPath, fullFile.getName());
				try {
					item.write(uploadFile);
					cfi.setUserId(cui.getId());
					cfi.setFileName(uploadFile.getName());
					cfi.setFileSize(uploadFile.length());
					String filePath=uploadFile.getPath().replace("\\", "\\\\");
					cfi.setFilePath(filePath);
					System.out.println(filePath);
					cft.setUserId(cui.getId());
					cft.setTopic(uploadFile.getName());
					cft.setKeyword("download");
					cft.setAttachFileName(filePath);
					cftdao.insert(cft);
					boolean result=cfidao.insert(cfi);
					if(result==true)
					{
						out.println("<script language = javascript>alert('上传成功')");
						out.println("window.history.go(-1)</script>");
					}
					else
					{
						out.println("<script language = javascript>alert('上传失败')");
						out.println("window.history.go(-1)</script>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		/*out.println("<script language = javascript>alert('上传成功')");
		out.println("window.history.go(-1)</script>");*/
	}

	private void touploafilepage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/UploadFile.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void notify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoUserInfo cui=(CoUserInfo)request.getSession().getAttribute("user");
			String content=request.getParameter("content");
			PrintWriter out=response.getWriter();
			CoUserInfoDAO cuidao=new CoUserInfoDAO();
			List<CoUserInfo> lcui=cuidao.query("");
			String email = null;
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			Session session = Session.getInstance(props);
			//session.setDebug(true);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setSubject("C语言在线学习平台邮件通知");
			message.setText(content);
			Transport transport = session.getTransport();
			transport.connect("smtp.qq.com", 25, from, "fzu!20040132");
			for(int i=0;i<lcui.size();i++)
			{
				email=lcui.get(0).getEmail();
				transport.sendMessage(message, new Address[]{new InternetAddress(email)});
			}
			transport.close();
			out.print("<script>alert('邮件发送成功!');window.location.href='admin?action=tonotifypage'</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tonotifypage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/Notify.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getSession().removeAttribute("user");
			RequestDispatcher rd = request
					.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deletepost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			String sid = request.getParameter("id");
			int id = Integer.parseInt(sid);
			CoUserInfo cui=(CoUserInfo) request.getSession().getAttribute("user");
			CoForumLog cfl=new CoForumLog();
			cfl.setUserId(cui.getId());
			CoForumTopicDAO cftdao = new CoForumTopicDAO();
			CoForumLogDAO cfldao=new CoForumLogDAO();
			String lmt;
			lmt = String.format("id='%d'", id);
			List<CoForumTopic> lcft=cftdao.query(lmt);
			boolean result = cftdao.delete(lmt);
			if (result == true) {
				cfl.setContent("删除帖子"+sid);
				cfl.setContent("删除日志:"+lcft.get(0).getTopic());
				cfldao.insert(cfl);
				out.print("<script>alert('删除成功!');window.location.href='admin?action=topostmanagepage&page=0'</script>");
			} else {
				out.println("<script language = javascript>alert('删除失败')");
				out.println("window.history.go(-1)</script>");
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>");
			e.printStackTrace();
		}
	}

	private void uploadproblem(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			CoProblem cp = new CoProblem();
			CoProblemDAO cpdao = new CoProblemDAO();
			CoUserInfo cui = (CoUserInfo) request.getSession().getAttribute(
					"user");
			String keyword = request.getParameter("keyword");
			String content = request.getParameter("content");
			cp.setKeyword(keyword);
			cp.setProviderName(cui.getName());
			int result = cpdao.insert(cp);
			if (result == 0) {
				out.println("<script language = javascript>alert('添加失败')");
				out.println("window.history.go(-1)</script>");
			} else {
				String lmt = String.format("id='%d'", result);
				List<CoProblem> lcp = cpdao.query(lmt);
				cp = lcp.get(0);
				String path = this.getServletContext().getRealPath(
						"/data/problem/" + cp.getId());
				File f = new File(path);
				if (!f.exists())
					f.mkdirs();
				String configFile = path + "/" + cp.getId() + ".xml";
				FileOutputStream fo = new FileOutputStream(configFile);
				fo.write(content.getBytes("UTF-8"));
				fo.close();
				cp.setConfigFile("data/problem/"+cp.getId()+"/"+cp.getId()+".xml");
				boolean uresult = cpdao.update(cp);
				if (uresult == true)
				{
					out.print("<script>alert('题目上传成功!');window.location.href='admin?action=touploadproblempage'</script>");
				}
				else {
					out.println("<script language = javascript>alert('上传失败')");
					out.println("window.history.go(-1)</script>");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void touploadproblempage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/UploadProblem.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void topostmanagepage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			VCoForumTopicList vcftl = new VCoForumTopicList();
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
			String lmt = String.format("");
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				vcftldao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					vcftldao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					vcftldao.queryStartPos = (page - 1) * 10;
				}
			}
			vcftldao.queryStartCount = 10;
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/PostManage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void usermanage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		CoUserInfoDAO cuidao = new CoUserInfoDAO();
		String lmt;
		lmt = String.format("id='%d'", id);
		boolean result = cuidao.delete(lmt);
		if (result == true)
			out.print("<script>alert('删除成功!');window.location.href='admin?action=tousermanagepage&page=0'</script>");
		else {
			out.println("<script language = javascript>alert('删除失败')");
			out.println("window.history.go(-1)</script>");
		}
	}

	private void tousermanagepage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoUserInfoDAO cuidao = new CoUserInfoDAO();
			String lmt = String.format("name!='admin'");
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				cuidao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					cuidao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					cuidao.queryStartPos = (page - 1) * 10;
				}
			}
			cuidao.queryStartCount = 10;
			List<CoUserInfo> lcui = cuidao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcui", lcui);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/admin/UserManage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
		host = config.getInitParameter("host");
		from = config.getInitParameter("from");
		//System.out.println(host + from);
	}
}
