package fire.web.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.web.controller.ExceptionController;
import fire.common.entity.Devicetypeparameter;
import fire.web.service.DevicetypeparameterService;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/admin/dtp")
public class DevicetypeparameterController extends ExceptionController{
	@Resource
	private DevicetypeparameterService devicetypeparameterService;
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDeviceTypeParameterPage(int deviceTypeId,int parameterType,int index,int size){
		PageInfo<Devicetypeparameter> pi = devicetypeparameterService.getDevicetypeparameterPage(deviceTypeId,parameterType,index, size);
		return new JsonResult(pi);	
	}
	
	
	@RequestMapping("/add.do")
	@ResponseBody	
	public JsonResult addDevicetypeparameter(Devicetypeparameter devicetypeparameter){
		int n = devicetypeparameterService.addDevicetypeparameter(devicetypeparameter);
		return new JsonResult(n);	
	}
	@RequestMapping("/getDeviceTypeParameter.do")
	@ResponseBody	
	public JsonResult getdevicetypeparameter(int id){
		Devicetypeparameter devicetypeparameter = devicetypeparameterService.getDevicetypeparameter(id);	
		return new JsonResult(devicetypeparameter);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateDevicetypeparameter(Devicetypeparameter devicetypeparameter){
		int n = devicetypeparameterService.updateDevicetypeparameter(devicetypeparameter);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteDevicetypeparameter(Integer id){
		int n = devicetypeparameterService.deleteDevicetypeparameter(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult updateStatus(Integer id,int status){
		int n =devicetypeparameterService.updateStatus(id, status);
		return new JsonResult(n);
	}
}
