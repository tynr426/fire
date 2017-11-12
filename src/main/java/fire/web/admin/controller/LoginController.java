package fire.web.admin.controller;

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

import fire.web.controller.ExceptionController;
import fire.common.entity.User;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.UserService;
import fire.web.service.VerifyCodeException;
import fire.web.utils.Admin;
import fire.web.utils.CookiesUtil;
import fire.web.utils.JsonResult;

@Controller
@RequestMapping("/admin")
public class LoginController extends ExceptionController{
	@Resource
	private UserService userService;
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "WebAdmin/login";
	}
	@RequestMapping("/toMain.do")
	public String toMain(){
		return "WebAdmin/Main";
	}
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String username,String password,String verifyCode,HttpSession session){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie cookie = CookiesUtil.getCookieByName(request, "VerifyCode");
		
        if(cookie==null){
            throw new VerifyCodeException("验证码过期");   
        }else if(!verifyCode.equals(cookie.getValue().toLowerCase())){
        	throw new VerifyCodeException("验证码输入错误"); 
        }
		User user = userService.login(username, password);
		Admin.setCookie(user);
		return new JsonResult(user);
	}
	@RequestMapping("/loginOut.do")
	@ResponseBody
	public Object loginOut(){
		return new JsonResult(userService.loginOut());	
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
