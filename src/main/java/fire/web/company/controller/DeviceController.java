package fire.web.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.controller.ExceptionController;
import fire.web.entity.Device;
import fire.web.entity.DeviceResult;
import fire.web.service.DeviceService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/device")
public class DeviceController extends ExceptionController{
	@RequestMapping("/device.do")
	public String Api(){
		return "WebCompany/System/Device";
	}
	@Resource
	private DeviceService deviceService;
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDevicePage(int index,int size){
		PageInfo<DeviceResult> pi = deviceService.getDevicePage(Company.getCompanyId(),index, size);
		return new JsonResult(pi);	
	}
	
	
	@RequestMapping("/add.do")
	@ResponseBody	
	public JsonResult addDevice(DeviceResult device){
		device.setCompanyId(Company.getCompanyId());
		int n = deviceService.addDevice(device);
		return new JsonResult(n);	
	}
	@RequestMapping("/getDevice.do")
	@ResponseBody	
	public JsonResult getDevice(int id){
		DeviceResult device = deviceService.getDevice(id);	
		return new JsonResult(device);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateDeviceparameter(DeviceResult device){
		int n = deviceService.updateDevice(device);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteDeviceparameter(Integer id){
		int n = deviceService.deleteDevice(id);
		return new JsonResult(n);
	}
	
}
