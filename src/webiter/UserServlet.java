package webiter;

import java.beans.Statement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.DriverManager;

import javassist.bytecode.Descriptor.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

import DAO.CoFileInfoDAO;
import DAO.CoForumTopicDAO;
import DAO.CoUserInfoDAO;
import DAO.CoUserLogDAO;
import DAO.VCoForumTopicListDAO;
import tool.DbConnection;
import domain.CoFileInfo;
import domain.CoForumTopic;
import domain.CoUserInfo;
import domain.CoUserLog;
import domain.VCoForumTopicList;

public class UserServlet extends HttpServlet {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public UserServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		if (request.getParameter("action").equals("register"))
			try {
				register(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (request.getParameter("action").equals("login"))
			try {
				login(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (request.getParameter("action").equals("homepage"))
			homepage(request, response);
		else if (request.getParameter("action").equals("toregisterpage"))
			toregisterpage(request, response);
		else if (request.getParameter("action").equals("toforgetpasswordpage"))
			toforgetpasswordpage(request, response);
		else if (request.getParameter("action").equals("todiscussionarea"))
			todiscussionarea(request, response);
		else if (request.getParameter("action").equals("tofreearea"))
			tofreearea(request, response);
		else if (request.getParameter("action").equals("todownloadarea"))
			todownloadarea(request, response);
		else if (request.getParameter("action").equals("download"))
			download(request, response);
		else if (request.getParameter("action").equals("tosearchpostpage"))
			tosearchpostpage(request, response);
		else if (request.getParameter("action").equals("tonewchallenge"))
			tonewchallenge(request, response);
		else if (request.getParameter("action").equals("tomemory"))
			tomemory(request, response);
		else if (request.getParameter("action").equals("toranking"))
			toranking(request, response);
		else if (request.getParameter("action").equals("tomoreinformation"))
			tomoreinformation(request, response);
		else if (request.getParameter("action").equals("upload"))
			upload(request, response);
		else if (request.getParameter("action").equals("touploadpage"))
			touploadpage(request, response);
		else if(request.getParameter("action").equals("logout"))
			logout(request, response);
		else
			System.out.println("error");

	}

	private void download(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("fileName");

		  System.out.print("dddddddddd:" + name);
		  // web绝对路径
		  String path = request.getSession().getServletContext().getRealPath("/");
		  String savePath = path + "upload";

		  // 设置为下载application/x-download
		  response.setContentType("application/x-download");
		  // 即将下载的文件在服务器上的绝对路径
		  String filenamedownload = savePath + "/" + name;
		  // 下载文件时显示的文件保存名称
		  String filenamedisplay = name;
		  // 中文编码转换
		  filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		  response.addHeader("Content-Disposition", "attachment;filename="
		    + filenamedisplay);
		  try {
		   java.io.OutputStream os = response.getOutputStream();
		   java.io.FileInputStream fis = new java.io.FileInputStream(
		     filenamedownload);
		   byte[] b = new byte[1024];
		   int i = 0;
		   while ((i = fis.read(b)) > 0) {
		    os.write(b, 0, i);
		   }
		   fis.close();
		   os.flush();
		   os.close();
		  } catch (Exception e) {

		  }

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("user");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	private void touploadpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/uploadfile.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");// 防止中文乱码
		PrintWriter out = response.getWriter();
		String realPath = this.getServletContext().getRealPath("/file");// 需要存放的真实路径
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		out.println("<script language = javascript>alert('上传成功')");
		out.println("window.history.go(-1)</script>");
		// TODO Auto-generated method stub
		/*
		 * ServletInputStream in=request.getInputStream(); PrintWriter
		 * out=response.getWriter(); long len=request.getContentLength(); byte[]
		 * b=new byte[128]; int i=0; i=in.readLine(b, 0, 128); len-=i;
		 * i=in.readLine(b, 0, 128); len-=i; long fileSize=len; String s=new
		 * String(b,0,i); String
		 * fileName=s.substring(s.lastIndexOf("=")+2,s.lastIndexOf("\""));
		 * if(!fileName.equals("")) { String
		 * savename=fileName.substring(fileName.lastIndexOf("\\")+1);
		 * i=in.readLine(b, 0, 128); len-=i; i=in.readLine(b, 0, 128); len-=i;
		 * FileOutputStream fos=new
		 * FileOutputStream(request.getRealPath("")+"/file/"+savename);
		 * while((i=in.readLine(b, 0, 128))!=-1&&len>47) { len-=i;
		 * fos.write(b,0,i); } CoFileInfo cfi=new CoFileInfo(); CoFileInfoDAO
		 * cfidao=new CoFileInfoDAO(); CoUserInfo cui=(CoUserInfo)
		 * request.getSession().getAttribute("user");
		 * cfi.setUserId(cui.getId()); cfi.setFileName(fileName);
		 * cfi.setFileSize(fileSize); cfi.setCreateTime(new Date()); try {
		 * boolean result=cfidao.insert(cfi);
		 * 
		 * } catch(Exception e) {
		 * out.println("<script language = javascript>alert('系统错误')");
		 * out.println("window.history.go(-1)</script>"); } in.close();
		 * fos.close();
		 * out.println("<script language = javascript>alert('上传成功')");
		 * out.println("window.history.go(-1)</script>"); } else {
		 * out.println("<script language = javascript>alert('请选择文件')");
		 * out.println("window.history.go(-1)</script>"); }
		 */
	}

	private void tosearchpostpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
			String spage = request.getParameter("page");
			String keyword = request.getParameter("keyword");
			request.setAttribute("keyword", keyword);
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
			String lmt = "keyword ='" + keyword + "'";
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/forum/searchpost.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void toforgetpasswordpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/user/forgetpasswordpage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tomoreinformation(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/moreinformation.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void toranking(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/ladder/ranking.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tomemory(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/ladder/memory.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tonewchallenge(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/ladder/newchallenge.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void todownloadarea(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
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
			String lmt = "keyword ='download'";
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/forum/downloadarea.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tofreearea(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
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
			String lmt = "keyword ='free'";
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/forum/freearea.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void todiscussionarea(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
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
			String lmt = "keyword ='discussion'";
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			ServletContext sct = this.getServletContext();
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/forum/discussionarea.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void toregisterpage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/user/register.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	private void homepage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/user/homepage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * request.getSession().removeAttribute("name"); RequestDispatcher rd =
		 * request.getRequestDispatcher("index.jsp"); rd.forward(request,
		 * response);
		 */
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, NamingException,
			SQLException, ServletException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		CoUserInfo cui=new CoUserInfo();
		CoUserInfoDAO cuidao=new CoUserInfoDAO();
		try {
			String name=request.getParameter("username");
			String pwd=request.getParameter("password");
			String sex=request.getParameter("sex");
			if(sex.equals("male"))
				sex="男";
			else
				sex="女";
			String email=request.getParameter("email");
			String qq=request.getParameter("qq");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String zipCode=request.getParameter("zipCode");
			String school=request.getParameter("school");
			String sbirthday=request.getParameter("birthday");
			Date birthday=sdf.parse(sbirthday);
			String major=request.getParameter("major");
			String grade=request.getParameter("grade");
			/*if(name.equals("")||pwd.equals("")||sex.equals("")||email.equals("")||sbirthday.equals(""))
			{
				out.println("<script language = javascript>alert('请完成必填信息')");
				out.println("window.history.go(-1)</script>"); 
			}
			else
			{*/
			cui.setName(name);
			cui.setPwd(pwd);
			cui.setSex(sex);
			cui.setEmail(email);
			cui.setQq(qq);
			cui.setPhone(phone);
			cui.setAddress(address);
			cui.setZipCode(zipCode);
			cui.setSchool(school);
			cui.setBirthday(birthday);
			cui.setMajor(major);
			cui.setGrade(grade);
			boolean result = cuidao.insert(cui);
			if (result == true) 
			{
				out.print("<script>alert('注册成功!');window.location.href='index.jsp'</script>");
			} else {
				out.println("<script language = javascript>alert('注册失败')");
				out.println("window.history.go(-1)</script>"); 
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('请完成必填信息')");
			out.println("window.history.go(-1)</script>"); 
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws NamingException, SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		CoUserInfoDAO cuidao = new CoUserInfoDAO();
		CoUserLog cul=new CoUserLog();
		CoUserLogDAO culdao=new CoUserLogDAO();
		RequestDispatcher rd;
		try {
			String name = request.getParameter("username");
			String pwd = request.getParameter("password");
			String lmt = String.format("name='%s' and pwd='%s'",
					StringEscapeUtils.escapeSql(name),
					StringEscapeUtils.escapeSql(pwd));
			List<CoUserInfo> lcui = cuidao.query(lmt);
			if (lcui == null || lcui.size() != 1) {
				out.println("<script language = javascript>alert('用户名密码错误')");
				out.println("window.history.go(-1)</script>");
			} else {
				cul.setUserId(lcui.get(0).getId());
				cul.setLogType("登录日志");
				cul.setLogContent("成功登录");
				culdao.insert(cul);
				HttpSession session = request.getSession(true);
				session.setAttribute("user", lcui.get(0));
				if (name.equals("admin"))
					rd = request.getRequestDispatcher("/jsp/admin/adminpage.jsp");
				else
					rd = request.getRequestDispatcher("/jsp/user/homepage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>");
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
