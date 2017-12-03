package fire.web.admin.controller;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;

import fire.web.controller.ExceptionController;
import fire.web.service.ReportSummaryService;
import fire.web.utils.Company;
import fire.web.utils.JsonResult;


@Controller("reportDevice")
@RequestMapping("/admin/device")
public class DeviceController extends ExceptionController{

	@Resource
	private ReportSummaryService reportService;
	@RequestMapping("/toDeviceNumSummary.do")
	public String Api(){
		return "WebAdmin/Report/DeviceNumSummary";
	}
	@RequestMapping("getDeviceNumSummaryList.do")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	public Object getDeviceNumSummaryList(int companyId, int deviceTypeId,Date startTime,Date endTime){
		return new JsonResult(reportService.getDeviceNumSummaryList(companyId, deviceTypeId, startTime, endTime));
	}
//	@InitBinder  
//	public void initBinder(WebDataBinder binder, WebRequest request) {  
//
//		//转换日期  
//		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");  
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器  
//	} 
	@RequestMapping("/toRectificationRate.do")
	public String toRectificationRate(){
		return "WebAdmin/Report/RectificationRate";
	}
	
	@RequestMapping("getAssignmentSummaryList.do")
	@ResponseBody
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Object getAssignmentSummaryList(int companyId,int deviceTypeId,String year){
		return new JsonResult(reportService.getAssignmentSummaryList(companyId, deviceTypeId, year));
	}
}
