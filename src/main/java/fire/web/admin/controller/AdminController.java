package fire.web.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.controller.ExceptionController;
import fire.web.entity.User;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.UserService;
import fire.web.utils.JsonResult;

@Controller
@RequestMapping("/admin")
public class AdminController extends ExceptionController{
	@Resource
	private UserService userService;
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "login";
	}
	@RequestMapping("/main.do")
	public String toMain(){
		return "Main";
	}
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String username,String password,String verifyCode,HttpSession session){
		User user = userService.login(username, password,verifyCode);
		session.setAttribute("loginUser", user);
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
