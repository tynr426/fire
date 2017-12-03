package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.sdk.utils.ConvertUtils;
import fire.web.service.AssignmentService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class AssigmentDistribute extends Distribute {

	private AssignmentService assignmentService;
	public AssigmentDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( assignmentService==null){
			assignmentService=getServiceIml("assignmentService");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("showAssigment")){
			showAssigment(req,resp);
		}
	}
	private void showAssigment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int managerId=ConvertUtils.toInt(req.getParameter("ManagerId"));
		int index=ConvertUtils.toInt(req.getParameter("Index"));
		int size=ConvertUtils.toInt(req.getParameter("Size"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(assignmentService.getAssignmentPageByManager(managerId, index, size)));
		resp.getWriter().write(str);
	}
}
