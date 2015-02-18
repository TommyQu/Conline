package webiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringEscapeUtils;

import DAO.CoForumReplyDAO;
import DAO.CoForumTopicDAO;
import DAO.CoUserLogDAO;
import DAO.VCoForumReplyListDAO;
import DAO.VCoForumTopicListDAO;

import domain.CoForumReply;
import domain.CoForumTopic;
import domain.CoUserInfo;
import domain.CoUserLog;
import domain.VCoForumReplyList;
import domain.VCoForumTopicList;

public class PostServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PostServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equals("tonewpostpage"))
			tonewpostpage(request, response);
		else if (request.getParameter("action").equals("newpost"))
			newpost(request, response);
		else if (request.getParameter("action").equals("getonepost"))
			getonepost(request, response);
		else if (request.getParameter("action").equals("reply"))
			reply(request, response);
		else if (request.getParameter("action").equals("searchpost"))
			searchpost(request, response);
		else
			return;
	}

	private void searchpost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String keyword=request.getParameter("keyword");
		String select=request.getParameter("select");
		String searchcontent=request.getParameter("searchcontent");
		if(select.equals("namesearch"))
			select="userName";
		else if(select.equals("topicsearch"))
			select="topic";
		else if(select.equals("contentsearch"))
			select="content";
		else
		{
			
		}
		try {
			VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page=1;
				vcftldao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up"))
				{
					page--;
					vcftldao.queryStartPos=(page-1)*10;
					if(page==0)
						page=1;
				}
				else if(flag.equals("down"))
				{
					page++;
					vcftldao.queryStartPos=(page-1)*10;
				}
				else
				{
					
				}
			}
			vcftldao.queryStartCount = 10;
			String lmt = "keyword ='"+keyword+"' and "+select+" like '%"+searchcontent+"%'";
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			request.setAttribute("lvcftl", lvcftl);
			request.setAttribute("page", page);
			request.setAttribute("keyword", keyword);
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/forum/searchpost.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(keyword);
		
	}

	private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		CoForumReply cfr=new CoForumReply();
		CoForumReplyDAO cfrdao=new CoForumReplyDAO();
		CoUserLog cul=new CoUserLog();
		CoUserLogDAO culdao=new CoUserLogDAO();
		CoUserInfo cui=(CoUserInfo) request.getSession().getAttribute("user");
		//System.out.println(cui.getId());
		String stopicId=request.getParameter("topicId");
		String content=request.getParameter("content");
		int topicId=Integer.parseInt(stopicId);
		RequestDispatcher rd;
		try
		{
			cfr.setUserId(cui.getId());
			cfr.setTopicId(topicId);
			cfr.setContent(content);
			cfr.setCreateTime(new Date());
			boolean result=cfrdao.insert(cfr);
			if(result==true)
			{
				cul.setUserId(cui.getId());
				cul.setLogType("论坛日志");
				cul.setLogContent("回复内容"+content);
				culdao.insert(cul);
				out.print("<script>alert('回复成功!');window.location.href='post?action=getonepost&topicId="+topicId+"'</script>");
				/*
				request.setAttribute("message", "回复成功");
				request.setAttribute("url", "post?action=getonepost&topicId="+topicId+"");
				rd = request.getRequestDispatcher("/jsp/alert.jsp");
				rd.forward(request, response);*/
			}
			else
			{
				out.println("<script language = javascript>alert('回复失败')");
				out.println("window.history.go(-1)</script>"); 
			}
				
		}
		catch(Exception e)
		{
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>"); 
			e.printStackTrace();
		}
	}

	private void getonepost(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		VCoForumTopicListDAO vcftldao = new VCoForumTopicListDAO();
		String stopicId = request.getParameter("topicId");
		int topicId=Integer.parseInt(stopicId);
		try {
			String lmt = String.format("id='%d'",topicId);
			List<VCoForumTopicList> lvcftl = vcftldao.query(lmt);
			if (lvcftl == null || lvcftl.size() != 1) {

			} else {
				VCoForumReplyListDAO vcfrldao = new VCoForumReplyListDAO();
				CoForumTopicDAO cftdao=new CoForumTopicDAO();
				VCoForumTopicList vcftl = lvcftl.get(0);
				lmt = String.format("topicId='%d'", topicId);
				List<VCoForumReplyList> lvcfrl = vcfrldao.query(lmt);
				lmt = String.format("id='%d'", topicId);
				CoForumTopic cft=cftdao.query(lmt).get(0);
				int accessCount=cft.getAccessCount();
				accessCount++;
				cft.setAccessCount(accessCount);
				cftdao.update(cft);
				//System.out.println(cft.getCreateTime());
				//System.out.println(lvcfrl.get(1).getCreateTime());
				request.setAttribute("lvcfrl", lvcfrl);
				request.setAttribute("vcftl", vcftl);
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/forum/postpage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void newpost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		CoForumTopic cft = new CoForumTopic();
		CoUserInfo cui = new CoUserInfo();
		CoForumTopicDAO cftdao = new CoForumTopicDAO();
		CoUserLog cul=new CoUserLog();
		CoUserLogDAO culdao=new CoUserLogDAO();
		cui = (CoUserInfo) request.getSession().getAttribute("user");
		try {
			int userId = cui.getId();
			String topic = request.getParameter("topic");
			String content = request.getParameter("content");
			String keyword = request.getParameter("keyword");
			int level = 10;
			int status = 0;
			int accessCount = 0;
			int replyCount = 0;
			int attachFileId = -1;
			String attachFileName = "";
			cft.setUserId(userId);
			cft.setTopic(topic);
			cft.setContent(content);
			cft.setKeyword(keyword);
			cft.setLevel(level);
			cft.setStatus(status);
			cft.setAccessCount(accessCount);
			cft.setLastReplyTime(new Date());
			cft.setCreateTime(new Date());
			cft.setReplyCount(replyCount);
			cft.setAttachFileID(attachFileId);
			cft.setAttachFileName(attachFileName);
			boolean result = cftdao.insert(cft);
			if (result == true) 
			{
				//request.setAttribute("message", "发布成功");
				if(keyword.equals("discussion"))
				{
					cul.setUserId(userId);
					cul.setLogType("论坛日志");
					cul.setLogContent("讨论区发布主题"+topic);
					culdao.insert(cul);
					out.print("<script>alert('发布成功!');window.location.href='user?action=todiscussionarea&page=0'</script>");
					/*request.setAttribute("url", "user?action=todiscussionarea&page=0");
					rd = request.getRequestDispatcher("/jsp/alert.jsp");
					rd.forward(request, response);*/
				}
				else
				{
					cul.setUserId(userId);
					cul.setLogType("论坛日志");
					cul.setLogContent("灌水区发布主题"+topic);
					culdao.insert(cul);
					out.print("<script>alert('发布成功!');window.location.href='user?action=tofreearea&page=0'</script>");
					/*request.setAttribute("url", "user?action=tofreearea&page=0");
					rd = request.getRequestDispatcher("/jsp/alert.jsp");
					rd.forward(request, response);*/
				}
			} else {
				out.println("<script language = javascript>alert('发布失败')");
				out.println("window.history.go(-1)</script>"); 
			}
		} catch (Exception e) {
			out.println("<script language = javascript>alert('系统错误')");
			out.println("window.history.go(-1)</script>"); 
		}

	}

	private void tonewpostpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/forum/newpost.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void init() throws ServletException {
		// Put your code here
	}

}
