package fire.web.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.controller.ExceptionController;
import fire.web.entity.WeChatAccount;
import fire.web.service.WeChatAccountService;
import fire.web.utils.JsonResult;

@Controller
@RequestMapping("/admin/social")
public class Social extends ExceptionController {
	@Resource
	private WeChatAccountService wcaService;
	@RequestMapping("/toWechatSet.do")
	public String Company(){
		return "WebAdmin/System/WechatSet";
	}
	@RequestMapping("/getWeChatAccount.do")
	@ResponseBody	
	public JsonResult getWeChatAccount(){
		return new JsonResult(wcaService.getWeChatAccount());	
	}
	@RequestMapping("/save.do")
	@ResponseBody	
	public JsonResult save(WeChatAccount wca){
		int n = wcaService.save(wca);
		return new JsonResult(n);	
	}
	
}
