package fire.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.DeviceQR;
import fire.web.service.DeviceQRService;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/deviceQR")
public class DeviceQRController {
	@Resource
	private DeviceQRService deviceQRService;
	
	@RequestMapping("/toDeviceQR.do")
	public String DeviceQR(){
		return "WebAdmin/System/DeviceQR";
	}
	@RequestMapping("/toQRList.do")
	public String QRList(){
		return "WebAdmin/System/qrList";
	}
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDeviceQRPage(int index,int size){
		PageInfo<DeviceQR> pi = deviceQRService.getDeviceQRPage(index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/showQRList.do")
	@ResponseBody
	public Object getDeviceQR(String batch){
		List<DeviceQR> pi = deviceQRService.getDeviceQRList(batch);
		return new JsonResult(pi);	
	}
}
