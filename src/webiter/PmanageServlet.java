package webiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import DAO.CoCourseAssignmentSubmitDAO;
import DAO.CoUserInfoDAO;
import DAO.VCoUserLogDAO;

import domain.CoCourseAssignmentSubmit;
import domain.CoUserInfo;
import domain.VCoUserLog;

public class PmanageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PmanageServlet() {
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
		if (request.getParameter("action").equals("tomodifypage"))
			tomodifypage(request, response);
		else if (request.getParameter("action").equals("modify"))
			modify(request, response);
		else if (request.getParameter("action").equals("tologsearch"))
			tologsearch(request, response);
		else if (request.getParameter("action").equals("tohomeworksearchpage"))
			tohomeworksearchpage(request, response);
		else
			return;
	}

	private void tohomeworksearchpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoUserInfo cui=(CoUserInfo) request.getSession().getAttribute("user");
			int userId=cui.getId();
			String userName=cui.getName();
			CoCourseAssignmentSubmitDAO ccasdao=new CoCourseAssignmentSubmitDAO();
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				ccasdao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					ccasdao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					ccasdao.queryStartPos = (page - 1) * 10;
				}
			}
			ccasdao.queryStartCount=10;
			String lmt=String.format("userId='%d'", userId);
			List<CoCourseAssignmentSubmit> lccas=ccasdao.query(lmt);
			request.setAttribute("lccas", lccas);
			request.setAttribute("page", page);
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/pmanage/homeworksearchpage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tologsearch(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoUserInfo cui=(CoUserInfo) request.getSession().getAttribute("user");
			int userId=cui.getId();
			String userName=cui.getName();
			VCoUserLogDAO vculdao=new VCoUserLogDAO();
			String spage = request.getParameter("page");
			int page = Integer.parseInt(spage);
			if (page == 0) {
				page = 1;
				vculdao.queryStartPos = 0;
			} else {
				String flag = request.getParameter("flag");
				if (flag.equals("up")) {
					page--;
					vculdao.queryStartPos = (page - 1) * 10;
					if (page == 0)
						page = 1;
				} else {
					page++;
					vculdao.queryStartPos = (page - 1) * 10;
				}
			}
			vculdao.queryStartCount=10;
			String lmt=String.format("userId='%d'", userId);
			List<VCoUserLog> lvcul=vculdao.query(lmt);
			request.setAttribute("lvcul", lvcul);
			request.setAttribute("page", page);
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/pmanage/logsearch.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoUserInfoDAO cuidao = new CoUserInfoDAO();
			CoUserInfo cui = (CoUserInfo) request.getSession().getAttribute(
					"user");
			PrintWriter out=response.getWriter();
			String pwd = request.getParameter("password");
			String email = request.getParameter("email");
			String qq = request.getParameter("qq");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String zipCode = request.getParameter("zipCode");
			cui.setPwd(pwd);
			cui.setEmail(email);
			cui.setQq(qq);
			cui.setPhone(phone);
			cui.setAddress(address);
			cui.setZipCode(zipCode);
			boolean result = cuidao.updateUser(cui);
			if (result == true) {
				out.print("<script>alert('修改成功!');window.location.href='user?action=homepage'</script>");
				/*request.setAttribute("message", "修改成功");
				request.setAttribute("url", "user?action=homepage");
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/alert.jsp");
				rd.forward(request, response);*/
			}
			else
			{
				out.println("<script language = javascript>alert('修改失败')");
				out.println("window.history.go(-1)</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tomodifypage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/pmanage/modify.jsp");
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
	public void init() throws ServletException {
		// Put your code here
	}

}
