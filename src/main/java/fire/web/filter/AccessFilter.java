package fire.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fire.web.entity.User;

public class AccessFilter implements Filter{

	public void destroy() {
	}
	private String login="/WebAdmin/login.jsp";
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String path = req.getRequestURI();		
		if(path.endsWith(login)){
			chain.doFilter(request, response);
			return;
		}
		User user = (User) session.getAttribute("User");
		if(user==null){
			res.sendRedirect(req.getContextPath()+login);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
}
