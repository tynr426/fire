package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.AuthBind;
import fire.common.entity.CompanyResult;
import fire.sdk.utils.ConvertUtils;
import fire.web.service.AuthBindService;
import fire.web.service.ManagerService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class ManagerDistribute extends Distribute {

	private ManagerService managerService; 
	private AuthBindService authBindService; 
	public ManagerDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( managerService==null){
			managerService=getServiceIml("managerService");
		}
		if( authBindService==null){
			authBindService=getServiceIml("authBindService");
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
		}else if(sp.Action.equals("getManagerList")){
			getManagerList(req,resp);
		}else if(sp.Action.equals("updatePwd")){
			updatePwd(req,resp);
		}
		else if(sp.Action.equals("wxLogin")){
			wxLogin(req,resp);
		}else if(sp.Action.equals("isWxAuth")){
			isWxAuth(req,resp);
		}
	}
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		AuthBind entity=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			entity= (AuthBind) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/javascript; charset=utf-8"); 
		
		CompanyResult result= managerService.login(entity.getUserName(), entity.getPassword(), entity.getCode());
		entity.setCompanyId(result.getId());
		entity.setManagerId(result.getManagerId());
		
		String str=Utils.objectToJson(new JsonResult(result));
		
		if(entity.getOpenId()!=null&&!entity.getOpenId().equals("")){
			authBindService.addAuthBind(entity);
		}
		resp.getWriter().write(str);
	}
	private void wxLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		AuthBind entity=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			entity= (AuthBind) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str="";
		if(entity.getOpenId()!=null&&!entity.getOpenId().equals("")){
		  AuthBind auth=authBindService.getAuthBind(entity.getOpenId());
		  if(auth!=null&&auth.getId()>0){
			  CompanyResult result= managerService.wxLogin(auth.getManagerId(),auth.getCompanyId());
			  str=Utils.objectToJson(new JsonResult(result));
			 
		  }
		}
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
	private void getManagerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		String str=Utils.objectToJson(new JsonResult(managerService.getManagerList(companyId)));
		resp.setContentType("text/javascript; charset=utf-8"); 
		resp.getWriter().write(str);
	}
	private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String oldPwd=req.getParameter("OldPwd");
		String pwd=req.getParameter("Pwd");
		int managerId=ConvertUtils.toInt(req.getParameter("ManagerId"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(managerService.updatePwd(oldPwd, pwd, managerId)));
		resp.getWriter().write(str);
	}
	private void isWxAuth(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String openId=req.getParameter("OpenId");
	
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(authBindService.getAuthBind(openId)));
		resp.getWriter().write(str);
	}
}
