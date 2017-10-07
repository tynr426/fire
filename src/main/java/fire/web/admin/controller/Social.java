package fire.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fire.web.controller.ExceptionController;

@Controller
@RequestMapping("/admin/social")
public class Social extends ExceptionController {
	@RequestMapping("/toWechatSet.do")
	public String Company(){
		return "WebAdmin/System/WechatSet";
	}
}
