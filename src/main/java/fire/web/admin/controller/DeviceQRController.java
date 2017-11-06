package fire.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceQRResult;
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
		PageInfo<DeviceQRResult> pi = deviceQRService.getDeviceQRPage(index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/search.do")
	@ResponseBody
	public Object search(String model,Integer deviceTypeId,int index,int size){
		PageInfo<DeviceQRResult> pi = deviceQRService.search(model,deviceTypeId, index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public Object delete(int id){
		return new JsonResult(deviceQRService.deleteDeviceQR(id));
	}
	@RequestMapping("/showQRList.do")
	@ResponseBody
	public Object getDeviceQR(String batch){
		List<DeviceQR> pi = deviceQRService.getDeviceQRList(batch);
		return new JsonResult(pi);	
	}
}
