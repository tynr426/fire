package fire.web.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.controller.ExceptionController;
import fire.common.entity.Company;
import fire.common.entity.CompanyResult;
import fire.web.service.CompanyService;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/admin/company")
public class CompanyController extends ExceptionController{
	@Resource
	private CompanyService companyService;
	@RequestMapping("/Company.do")
	public String Company(){
		return "WebAdmin/Company/CompanyManager";
	}
	@RequestMapping("/showCompanyPage.do")
	@ResponseBody
	public Object getCompanyPage(int index,int size){
		PageInfo<Company> pi = companyService.getCompanyPage(index, size);
		return new JsonResult(pi);	
	}
	
	
	@RequestMapping("/addCompany.do")
	@ResponseBody	
	public JsonResult regist(CompanyResult company){
		int n = companyService.addCompany(company);
		return new JsonResult(n);	
	}
	@RequestMapping("/getCompany.do")
	@ResponseBody	
	public JsonResult getCompany(int id){
		CompanyResult result = companyService.getCompany(id);	
		return new JsonResult(result);
	}
	
	
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateCompany(CompanyResult result){
		int n = companyService.updateCompany(result);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteCompany(Integer id){
		int n = companyService.deleteCompany(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult updateStatus(Integer id,int status){
		int n =companyService.updateStatus(id, status);
		return new JsonResult(n);
	}
}
