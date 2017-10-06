package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fire.web.service.ManagerService;

public class ManagerDistribute extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String userName=req.getParameter("UserName");
		String password=req.getParameter("Password");
		String code=req.getParameter("Code");
		managerService.login(userName, password, "111111", code);
	}


	private ManagerService managerService; 

    public  ManagerDistribute(ServletContext context) 
    {  
	    WebApplicationContext wac = null;   
	    wac = WebApplicationContextUtils.getWebApplicationContext(context);  
	    if(wac!=null){
	    managerService=(ManagerService)wac.getBean("managerService");
	    }
	    
    } 

}
