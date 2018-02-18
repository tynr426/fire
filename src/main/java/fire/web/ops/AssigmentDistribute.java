package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.Assignment;
import fire.common.entity.DeviceResult;
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
		}if(sp.Action.equals("getAssignmentByCheckId")){
			getAssignmentByCheckId(req,resp);
		}if(sp.Action.equals("save")){
			save(req,resp);
		}if(sp.Action.equals("getStatistics")){
			getStatistics(req,resp);
	}
	}
	private void showAssigment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		int managerId=ConvertUtils.toInt(req.getParameter("ManagerId"));
		int index=ConvertUtils.toInt(req.getParameter("Index"));
		int size=ConvertUtils.toInt(req.getParameter("Size"));
		Integer status=null;
		if(req.getParameter("Status")!=null){
			status=ConvertUtils.toInt(req.getParameter("Status"));
		}
		String keyword=req.getParameter("Keyword");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(assignmentService.getAssignmentPageByManager(companyId,managerId, index, size, status, keyword)));
		resp.getWriter().write(str);
	}
	private void getAssignmentByCheckId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int checkId=ConvertUtils.toInt(req.getParameter("CheckId"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(assignmentService.getAssignmentByCheckId(checkId)));
		resp.getWriter().write(str);
	}
	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Assignment assignment=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			assignment= (Assignment) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=Utils.objectToJson(new JsonResult(assignmentService.save(assignment)));
		resp.getWriter().write(str);
	}
	private void getStatistics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int managerId=ConvertUtils.toInt(req.getParameter("ManagerId"));
		Integer status=null;
		if(req.getParameter("Status")!=null){
			status=ConvertUtils.toInt(req.getParameter("Status"));
		}
		String keyword=req.getParameter("Keyword");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(assignmentService.getStatistics(managerId, status, keyword)));
		resp.getWriter().write(str);
	}
}
