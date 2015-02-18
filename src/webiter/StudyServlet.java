package webiter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.faces.application.Application;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.util.XMLErrorHandler;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;

import tool.XmlValidate;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

import DAO.CoCourseAssignmentSubmitDAO;
import DAO.CoCourseChapterDAO;
import DAO.CoFileInfoDAO;
import DAO.CoForumTopicDAO;
import DAO.CoProblemDAO;
import DAO.VCoForumReplyListDAO;
import DAO.VCoForumTopicListDAO;

import domain.CoCourseAssignmentSubmit;
import domain.CoCourseChapter;
import domain.CoFileInfo;
import domain.CoForumTopic;
import domain.CoProblem;
import domain.CoUserInfo;
import domain.VCoForumReplyList;
import domain.VCoForumTopicList;

public class StudyServlet extends HttpServlet {
	String webroot="";
	/**
	 * Constructor of the object.
	 */
	public StudyServlet() {
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
		if (request.getParameter("action").equals("tocoursepage"))
			tocoursepage(request, response);
		else if (request.getParameter("action").equals("tohomeworkpage"))
			tohomeworkpage(request, response);
		else if (request.getParameter("action").equals("totestpage"))
			totestpage(request, response);
		else if (request.getParameter("action").equals("getproblem"))
			getproblem(request, response);
		else if (request.getParameter("action").equals("submithomework"))
			submithomework(request, response);
		else
			return;
	}

	private void getproblem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		CoProblemDAO cpdao=new CoProblemDAO();
		String sid = request.getParameter("id");
		int topicId=Integer.parseInt(sid);
		try {
			String lmt = String.format("id='%d'",topicId);
			List<CoProblem> lcp = cpdao.query(lmt);
			if (lcp == null || lcp.size() != 1) {

			} else 
			{
				String path=lcp.get(0).getConfigFile();
				File file=new File(path);
				if (file.exists())
				{
					FileReader reader=new FileReader(file);
					response.setContentType("texl/xml");
					request.setAttribute("reader", reader);
				}
				else{
					out.print("文件不存在!");
				}
				//System.out.println(cft.getCreateTime());
				//System.out.println(lvcfrl.get(1).getCreateTime());
				request.setAttribute("lcp", lcp);
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/forum/postpage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void submithomework(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		CoUserInfo cui = (CoUserInfo) request.getSession().getAttribute("user");
		int userId = cui.getId();
		String schapterId = request.getParameter("chapterId");
		int chapterId = Integer.parseInt(schapterId);
		String spid = request.getParameter("pid");
		int pid = Integer.parseInt(spid);
		String content = request.getParameter("content");
		// content.getBytes("8859_1");
		String xsdFileName = webroot+"src/xml/submit.xsd";
		CoCourseAssignmentSubmit ccas = new CoCourseAssignmentSubmit();
		CoCourseAssignmentSubmitDAO ccasdao = new CoCourseAssignmentSubmitDAO();
		try {
			ccas.setUserId(userId);
			ccas.setChapterId(chapterId);
			ccas.setPid(pid);
			int result = ccasdao.insert(ccas);
			if (result == 0) {
				out.println("<script language = javascript>alert('作业提交失败')");
				out.println("window.history.go(-1)</script>");
			} else {
				String lmt = String.format("id='%d'", result);
				List<CoCourseAssignmentSubmit> lccas = ccasdao.query(lmt);
				ccas = lccas.get(0);
				String path = this.getServletContext().getRealPath(
						"/data/assign-submit/");
				File f = new File(path);
				if (!f.exists())
					f.mkdirs();
				String submitFile = path +"/" +result + ".xml";
				FileOutputStream fo = new FileOutputStream(submitFile);
				fo.write(content.getBytes("UTF-8"));
				fo.close();
				ccas.setSubmitFile("data/assign-submit/"+ccas.getId()+".xml");
				boolean uresult = ccasdao.update(ccas);
				if (uresult == true) 
				{
					System.out.println("作业上传成功!");
					XmlValidate xv=new XmlValidate();
					boolean validate=xv.XmlValidate(response, submitFile, xsdFileName);
					if(validate==true)
					{
						ccas.setJudgeStatus("pending");
						ccasdao.update(ccas);
						out.println("上传成功!");
					}
					else
					{
						System.out.println("XML文件通过XSD文件校验失败！");
						out.println("上传失败!");
						//writer.write(errorHandler.getErrors());
					}
				}

					// out.print("<script>alert('作业上传成功!');window.location.href=''</script>");
					// 创建默认的XML错误处理器
					
					/*try {
						XMLErrorHandler errorHandler = new XMLErrorHandler();
						// 获取基于 SAX 的解析器的实例
						SAXParserFactory factory = SAXParserFactory
								.newInstance();
						// 解析器在解析时验证 XML 内容。
						factory.setValidating(true);
						// 指定由此代码生成的解析器将提供对 XML 名称空间的支持。
						factory.setNamespaceAware(true);
						// 使用当前配置的工厂参数创建 SAXParser 的一个新实例。
						SAXParser parser = factory.newSAXParser();
						// 创建一个读取工具
						SAXReader xmlReader = new SAXReader();
						// 获取要校验xml文档实例
						Document xmlDocument = (Document) xmlReader
								.read(new File(submitFile));
						// 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
						// [url]http://sax.sourceforge.net/?selected=get-set[/url]
						// 中找到。
						parser.setProperty(
								"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
								"http://www.w3.org/2001/XMLSchema");
						parser.setProperty(
								"http://java.sun.com/xml/jaxp/properties/schemaSource",
								"file:" + xsdFileName);
						// 创建一个SAXValidator校验工具，并设置校验工具的属性
						SAXValidator validator = new SAXValidator(
								parser.getXMLReader());
						// 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
						validator.setErrorHandler(errorHandler);
						// 校验
						validator.validate(xmlDocument);
						OutputFormat of = new OutputFormat();
						of.setEncoding("UTF-8");
						// XMLWriter writer = new XMLWriter(of);
						XMLWriter writer = new XMLWriter(
								OutputFormat.createPrettyPrint());
						if (errorHandler.getErrors().hasContent()) {
							// System.out.println(errorHandler.getErrors().hasContent())
							out.println("<script language = javascript>alert('XML文件通过XSD文件校验失败！')");
							out.println("window.history.go(-1)</script>");
							System.out.println("XML文件通过XSD文件校验失败！");
							writer.write(errorHandler.getErrors());
							/*
							 * out.println(
							 * "<script language = javascript>alert('XML文件通过XSD文件校验失败！')"
							 * ); out.println("window.history.go(-1)</script>");
							 */
							// writer.write(errorHandler.getErrors());
				/*else 
				{
					out.println("<script language = javascript>alert('XML文件通过XSD文件校验成功！')");
					out.println("window.history.go(-1)</script>");
				}
				catch (Exception ex) {
						System.out
								.println("XML文件: " + submitFile + " 通过XSD文件:"
										+ xsdFileName + "检验失败。/n原因： "
										+ ex.getMessage());
						ex.printStackTrace();
					}
				} */else {
					//System.out.println("XML文件通过XSD文件校验失败！");
					out.println("上传失败!");
				}

				// cp.setConfigFile("data/problem/"+cp.getId()+"/"+cp.getId()+".xml");
				// 如果错误信息不为空，说明校验失败，打印错误信息
			}
		} catch (Exception ex) {
			System.out.println("XML文件: " + content + " 通过XSD文件:" + xsdFileName
					+ "检验失败。/n原因： " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void totestpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/study/testpage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tohomeworkpage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ServletContext sct = this.getServletContext();
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/study/homeworkpage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tocoursepage(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CoFileInfo cfi=new CoFileInfo();
			CoFileInfoDAO cfidao=new CoFileInfoDAO();
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
			// String lmt = "keyword ='discussion'";
			List<CoFileInfo> lcfi = cfidao.query("");
			ServletContext sct = this.getServletContext();
			request.setAttribute("lcfi", lcfi);
			request.setAttribute("page", page);
			RequestDispatcher rd = sct
					.getRequestDispatcher("/jsp/study/coursepage.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		// Put your code here
		webroot=this.getServletContext().getInitParameter("webroot");
	}

}
