package fire.web.company.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.web.controller.ExceptionController;
import fire.web.service.CheckDeviceService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/company/checkdevice")
public class CheckDeviceController extends ExceptionController{
	@Resource
	private CheckDeviceService cdService;
	
	@RequestMapping("/toCheckDevice.do")
	public String toCheckDevice(){
		return "WebCompany/System/CheckDevice";
	}
	@RequestMapping("/getCheckDevice.do")
	@ResponseBody	
	public JsonResult getCheckDevice(int id){
		CheckDeviceResult cdr = cdService.getCheckDevice(id);	
		return new JsonResult(cdr);
	}
	@RequestMapping("/updateCheckDevice.do")
	@ResponseBody	
	public JsonResult updateCheckDevice(CheckDevice cd){
		int n = cdService.updateCD(cd);
		return new JsonResult(n);
	}
	@RequestMapping("/deleteCheckDevice.do")
	@ResponseBody
	public JsonResult deleteCheckDevice(int id){
		int n = cdService.deleteCD(id);
		return new JsonResult(n);
	}
	@RequestMapping("/showCheckDevice.do")
	@ResponseBody
	public Object getCheckDevicePage(int index,int size,Integer managerId){
		PageInfo<CheckDeviceResult> pi = cdService.getCheckDevicePage(Company.getCompanyId(), index, size, managerId);
		return new JsonResult(pi);
	}
}
