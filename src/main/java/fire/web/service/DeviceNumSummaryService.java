package fire.web.service;

import java.util.Date;
import java.util.List;

import fire.common.entity.DeviceNumSummary;

public interface DeviceNumSummaryService {
	public List<DeviceNumSummary> getDeviceNumSummaryList(int companyId,int deviceTypeId,Date startTime,Date endTime);
}
