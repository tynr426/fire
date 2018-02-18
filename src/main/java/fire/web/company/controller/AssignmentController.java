package fire.web.company.controller;



import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.web.controller.ExceptionController;
import fire.web.service.AssignmentService;
import fire.web.service.AuthBindService;
import fire.web.service.ReportSummaryService;
import fire.web.service.WeChatAccountService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/company/assignment")
public class AssignmentController extends ExceptionController{
	@Resource
	private AssignmentService assignmentService;
	@Resource
	private ReportSummaryService reportService;
	@Resource
	private WeChatAccountService weChatAccountService;
	@Resource
	private AuthBindService authBindService;
	@RequestMapping("/toAssignment.do")
	public String toManager(){
		return "WebCompany/System/Assignment";
	}
	
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getManagerPage(int index,int size){
		PageInfo<AssignmentResult> pi = assignmentService.getAssignmentPage(Company.getCompanyId(), index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(Assignment entity){
		entity.setCompanyId(Company.getCompanyId());
		entity.setFromManagerId(Company.getCompany().getManagerId());
		return new JsonResult(assignmentService.save(entity));	
	}
	@RequestMapping("/getAssignmentByCheckId.do")
	@ResponseBody
	public Object getAssignmentByCheckId(int checkId){
		return new JsonResult(assignmentService.getAssignmentByCheckId(checkId));	
	}
	@RequestMapping("/toRectificationRate.do")
	public String toRectificationRate(){
		return "WebCompany/Report/RectificationRate";
	}
	
	@RequestMapping("getAssignmentSummaryList.do")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Object getAssignmentSummaryList(int deviceTypeId,String year,Integer unitproperties,Integer buildingtype,Boolean isimport){
		return new JsonResult(reportService.getAssignmentSummaryList(Company.getCompanyId(), deviceTypeId, year, unitproperties, buildingtype, isimport));
	}
}
