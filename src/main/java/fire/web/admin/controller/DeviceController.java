package fire.web.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fire.web.controller.ExceptionController;


@Controller("reportDevice")
@RequestMapping("/admin/device")
public class DeviceController extends ExceptionController{
	@RequestMapping("/toDeviceNumSummary.do")
	public String Api(){
		return "WebAdmin/Report/DeviceNumSummary";
	}
	
}
