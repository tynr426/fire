package fire.web.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.DeviceResult;
import fire.common.entity.Repairrecord;
import fire.common.entity.RepairrecordResult;
import fire.web.controller.ExceptionController;
import fire.web.service.DeviceService;
import fire.web.service.RepairrecordService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/company/device")
public class DeviceController extends ExceptionController{
	@RequestMapping("/toDevice.do")
	public String Api(){
		return "WebCompany/System/Device";
	}
	@RequestMapping("/toRepairrecord.do")
	public String toRepairrecord(){
		return "WebCompany/System/Repairrecord";
	}
	@Resource
	private DeviceService deviceService;
	@Resource
	private RepairrecordService repairrecordService;
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
	public JsonResult deleteDeviceparameter(int id){
		int n = deviceService.deleteDevice(id);
		return new JsonResult(n);
	}
	@RequestMapping("/showRepairrecord.do")
	@ResponseBody
	public Object getRepairrecordPage(int index,int size){
		PageInfo<RepairrecordResult> pi = repairrecordService.getRepairrecordpage(index, size);
		return new JsonResult(pi);
	}
	
	
	@RequestMapping("/addRepairrecord.do")
	@ResponseBody	
	public JsonResult addRepairrecord(RepairrecordResult repairrecordResult){
		int n = repairrecordService.addRepairrecord(repairrecordResult);
		return new JsonResult(n);	
	}
	@RequestMapping("/getRepairrecord.do")
	@ResponseBody	
	public JsonResult getRepairrecord(int id){
		RepairrecordResult repairrecordResult = repairrecordService.getRepairrecord(id);	
		return new JsonResult(repairrecordResult);
	}
	@RequestMapping("/updateRepairrecord.do")
	@ResponseBody	
	public JsonResult updateRepairrecord(Repairrecord repairrecord){
		int n = repairrecordService.updateRepairrecord(repairrecord);	
		return new JsonResult(n);
	}
	@RequestMapping("/deleteRepairrecord.do")
	@ResponseBody
	public JsonResult deleteRepairrecord(int id){
		int n = repairrecordService.deleteRepairrecord(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult switchStatus(int id,int status){
		int n = repairrecordService.updateStatus(id, status);
		return new JsonResult(n);
	}
	
}
