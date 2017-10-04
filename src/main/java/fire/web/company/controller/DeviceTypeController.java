package fire.web.company.controller;

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

@Controller("companyDeviceType")
@RequestMapping("/company/deviceType")
public class DeviceTypeController {
	@Resource
	private DeviceTypeService deviceTypeService;
	@RequestMapping("/findAll.do")
	@ResponseBody
	public Object findAll(){
		List<DeviceTypeResult> list = deviceTypeService.findDeviceTypeResult();
		return new JsonResult(list);	
	}
	
	
}
