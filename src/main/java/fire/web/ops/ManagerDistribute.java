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
import fire.web.utils.Utils;

public class ManagerDistribute extends Distribute {


	private ManagerService managerService; 
	public ManagerDistribute(ServletContext context) {
		super(context);
		if( managerService==null){
			managerService=getServiceIml("managerService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String userName=req.getParameter("UserName");
		String password=req.getParameter("Password");
		String code=req.getParameter("Code");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(managerService.login(userName, password, code)));
		resp.getWriter().write(str);
	}


}
