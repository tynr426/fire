package fire.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.entity.DeviceType;
import fire.web.entity.DeviceTypeResult;
import fire.web.service.DeviceTypeService;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/deviceType")
public class DeviceTypeController {
	@RequestMapping("/DeviceType.do")
	public String Api(){
		return "WebAdmin/System/DeviceType";
	}
	@Resource
	private DeviceTypeService deviceTypeService;
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDeviceTypePage(int index,int size){
		PageInfo<DeviceType> pi = deviceTypeService.getDeviceTypePage(index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/findAll.do")
	@ResponseBody
	public Object findAll(){
		List<DeviceTypeResult> list = deviceTypeService.findDeviceTypeResult();
		return new JsonResult(list);	
	}
	
	
	@RequestMapping("/addDeviceType.do")
	@ResponseBody	
	public JsonResult addDeviceType(DeviceType dt){
		int n = deviceTypeService.addDeviceType(dt);
		return new JsonResult(n);	
	}
	@RequestMapping("/getDeviceType.do")
	@ResponseBody	
	public JsonResult getdeviceType(int id){
		DeviceType dt = deviceTypeService.getDeviceType(id);	
		return new JsonResult(dt);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateDevicetype(DeviceType dt){
		int n = deviceTypeService.updateDeviceType(dt);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteDevicetype(Integer id){
		int n = deviceTypeService.deleteDeviceType(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult updateStatus(Integer id,int status){
		int n =deviceTypeService.updateStatus(id, status);
		return new JsonResult(n);
	}
}
