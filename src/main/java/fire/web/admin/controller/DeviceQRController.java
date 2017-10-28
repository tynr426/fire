package fire.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deviceQR")
public class DeviceQRController {
	@RequestMapping("/toDeviceQR.do")
	public String DeviceQR(){
		return "WebAdmin/System/DeviceQR";
	}
	
}
