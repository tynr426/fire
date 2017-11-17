package fire.web.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.web.controller.ExceptionController;
import fire.web.service.AssignmentService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/company/assignment")
public class AssignmentController extends ExceptionController{
	@Resource
	private AssignmentService assignmentService;
	
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
	public Object save(Assignment assignment){
		
		return new JsonResult(assignmentService.save(assignment));	
	}

}
