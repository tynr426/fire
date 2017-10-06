package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fire.web.service.ManagerService;
import fire.web.utils.JsonResult;

public class ManagerDistribute extends Distribute {

	public ManagerDistribute(ServletContext context) {
		super(context);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String userName=req.getParameter("UserName");
		String password=req.getParameter("Password");
		String code=req.getParameter("Code");
		resp.setContentType("text/javascript; charset=utf-8"); 
		resp.getWriter().write(new JsonResult(getManagerService().login(userName, password, code)).toString());

	}


	private ManagerService managerService; 


	public ManagerService getManagerService() {
		if( managerService==null){
			managerService=getServiceIml("managerService");
		}
		return managerService;
	}


}
