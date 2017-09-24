package fire.web.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.entity.Device;
import fire.web.service.DeviceService;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/deviceUnit")
public class DeviceController {
	@RequestMapping("/device.do")
	public String Api(){
		return "System/Device";
	}
	@Resource
	private DeviceService deviceService;
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDevicePage(int index,int size){
		PageInfo<Device> pi = deviceService.getDevicePage(index, size);
		return new JsonResult(pi);	
	}
	
	
	@RequestMapping("/add.do")
	@ResponseBody	
	public JsonResult addDevice(Device device){
		int n = deviceService.addDevice(device);
		return new JsonResult(n);	
	}
	@RequestMapping("/getDevice.do")
	@ResponseBody	
	public JsonResult getDevice(int id){
		Device device = deviceService.getDevice(id);	
		return new JsonResult(device);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateDeviceparameter(Device device){
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