package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.sdk.utils.ConvertUtils;
import fire.web.service.AuthBindService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class AuthBindDistribute extends Distribute{
	
	private AuthBindService authBindService;
	
	public AuthBindDistribute(SysParameter sp, ServletContext context) {
		super(sp, context);
		if( authBindService==null){
			authBindService=getServiceIml("authBindService");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("getBindList")){
			getBindList(req,resp);
		}if(sp.Action.equals("deleteById")){
			deleteById(req,resp);
		}
	}
	private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = ConvertUtils.toInt(req.getParameter("Id"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(authBindService.deleteById(id)));
		resp.getWriter().write(str);
	}
	private void getBindList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int companyId = ConvertUtils.toInt(req.getParameter("CompanyId"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(authBindService.getBindList(companyId)));
		resp.getWriter().write(str);
	}
}
