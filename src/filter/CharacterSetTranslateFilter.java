package filter;


import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import sun.rmi.transport.proxy.HttpReceiveSocket;
import sun.tools.jar.Main;

public class CharacterSetTranslateFilter implements Filter {
	String characterSet="UTF-8";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding(characterSet);
		rep.setContentType("text/html");
		rep.setCharacterEncoding(characterSet);
		chain.doFilter(req, rep);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String str=config.getInitParameter("AppCharacterSet");
		if(str!=null)
			characterSet=str;
	}

}
