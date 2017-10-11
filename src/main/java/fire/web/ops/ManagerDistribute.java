package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.web.service.ManagerService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class ManagerDistribute extends Distribute {

	private ManagerService managerService; 
	public ManagerDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( managerService==null){
			managerService=getServiceIml("managerService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("login")){
			login(req,resp);
		}
		else if(sp.Action.equals("autologin")){
			autoLogin(req,resp);
		}else if(sp.Action.equals("getManager")){
			getManager(req,resp);
		}
	}
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String userName=req.getParameter("UserName");
		String password=req.getParameter("Password");
		String code=req.getParameter("Code");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(managerService.login(userName, password, code)));
		resp.getWriter().write(str);
	}
	private void autoLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String token=req.getParameter("Token");
				resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(managerService.verifyToken(token)));
		resp.getWriter().write(str);
	}
	private void getManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id=req.getParameter("Id");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(managerService.getManager(Integer.parseInt(id))));
		resp.getWriter().write(str);
	}
}
