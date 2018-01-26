package fire.web.service;

import java.util.Date;
import java.util.List;

import fire.common.entity.AssignmentSummary;
import fire.common.entity.DeviceNumSummary;
import fire.common.entity.ReportSummary;

public interface ReportSummaryService {
	public List<DeviceNumSummary> getDeviceNumSummaryList(int companyId,int deviceTypeId,Date startTime,Date endTime,
			Integer unitproperties,Integer buildingtype,Boolean isimport);
	public ReportSummary getAssignmentSummaryList(int companyId,int deviceTypeId,String year,Integer unitproperties,Integer buildingtype,Boolean isimport);
}
