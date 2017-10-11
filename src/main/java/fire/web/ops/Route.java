package fire.web.ops;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fire.web.service.ManagerService;

public class Route extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		String method=req.getParameter("method");
		String module="",action="";
		Pattern p=Pattern.compile("^(?<platform>[^\\.]+)\\.(?<module>[^\\.]+)\\.(?<action>.*)");
		Matcher m =p.matcher(method);
		if (m.find()) { 
			module=m.group("platform")+"."+m.group("module");
			action=m.group("action");
		}
		SysParameter sp=new SysParameter();
		sp.Action=action;
		sp.Module=module;
		Distribute distribute=null;
		if(module.equals("company.manager")){

			distribute=new ManagerDistribute(sp,this.getServletContext());
		}else if(module.equals("company.device")){
			distribute=new DeviceDistribute(sp,this.getServletContext());
		}else if(module.equals("company.deviceType")){
			distribute=new DeviceTypeDistribute(sp,this.getServletContext());
		}
		distribute.doPost(req, resp);


	}
//	@Autowired
//	private ManagerService managerService; 
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		super.init(config);
//		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());   
//	}



}