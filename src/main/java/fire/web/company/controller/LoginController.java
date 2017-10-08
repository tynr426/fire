package fire.web.company.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.entity.Manager;
import fire.web.utils.PageInfo;
import fire.company.entity.CompanyResult;
import fire.web.controller.ExceptionController;
import fire.web.service.ManagerService;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.VerifyCodeException;
import fire.web.utils.Company;
import fire.web.utils.Constants;
import fire.web.utils.CookiesUtil;
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
	@RequestMapping("/toMain.do")
	public String toMain(){
		return "WebCompany/Main";
	}
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String username,String password,String verifyCode,String code,HttpSession session){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie cookie = CookiesUtil.getCookieByName(request, "VerifyCode");
		
        if(cookie==null){
            throw new VerifyCodeException("验证码过期");   
        }else if(!verifyCode.equals(cookie.getValue().toLowerCase())){
        	throw new VerifyCodeException("验证码输入错误"); 
        }
        
		CompanyResult manager = managerService.login(username, password,code);
		session.setAttribute(Constants.CompanyPre+Constants.LoginCacheKey, manager);
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
