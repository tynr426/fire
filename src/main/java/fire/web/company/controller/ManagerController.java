package fire.web.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.Manager;
import fire.web.utils.PageInfo;
import fire.web.controller.ExceptionController;
import fire.web.service.ManagerService;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;

@Controller
@RequestMapping("/company/manager")
public class ManagerController extends ExceptionController{
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/toManager.do")
	public String toManager(){
		return "WebCompany/System/Manager";
	}
	@ExceptionHandler(NameException.class)
	@ResponseBody
	public Object nameExp(NameException e){
		e.printStackTrace();		
		return new JsonResult(2,e);	
	}
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public Object pwdExp(PasswordException e){
		e.printStackTrace();		
		return new JsonResult(3,e);	
	}
	
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getManagerPage(int index,int size){
		PageInfo<Manager> pi = managerService.getManagerPage(Company.getCompanyId(),index, size);
		return new JsonResult(pi);	
	}
	
	
	@RequestMapping("/add.do")
	@ResponseBody	
	public JsonResult add(Manager manager){
		manager.setCompanyId(Company.getCompanyId());
		int n = managerService.addManager(manager);
		return new JsonResult(n);	
	}
	@RequestMapping("/getManager.do")
	@ResponseBody	
	public JsonResult getManager(int id){
		Manager Manager = managerService.getManager(id);	
		return new JsonResult(Manager);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateManager(Manager manager){
		int n = managerService.updateManager(manager);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteManager(Integer id){
		int n = managerService.deleteManager(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult updateStatus(Integer id,int status){
		int n =managerService.updateStatus(id, status);
		return new JsonResult(n);
	}
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public JsonResult updatePwd(String oldPwd,String pwd){
		int n =managerService.updatePwd(oldPwd, pwd, Company.getCompany().getManagerId());
		return new JsonResult(n);
	}
	@RequestMapping("/getManagerList.do")
	@ResponseBody
	public JsonResult getManagerList(){
		return new JsonResult(managerService.getManagerList(Company.getCompanyId()));
	}
}
