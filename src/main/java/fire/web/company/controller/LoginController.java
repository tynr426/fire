package fire.web.company.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.entity.Manager;
import fire.web.utils.PageInfo;
import fire.web.controller.ExceptionController;
import fire.web.service.ManagerService;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;

@Controller("companyLoginController")
@RequestMapping("/company")
public class LoginController extends ExceptionController{
	@Resource
	private ManagerService managerService;
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "WebCompany/loginCM";
	}
	@RequestMapping("/main.do")
	public String toMain(){
		return "WebCompany/Main";
	}
	@RequestMapping("/manager.do")
	public String toManager(){
		return "WebCompany/System/Manager";
	}
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String username,String password,String verifyCode,String code,HttpSession session){
		Manager manager = managerService.login(username, password,verifyCode,code);
		session.setAttribute("companyManager", manager);
		return new JsonResult(manager);
	}
	@RequestMapping("/loginOut.do")
	@ResponseBody
	public Object loginOut(){
		return new JsonResult(managerService.loginOut());	
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
	

}
