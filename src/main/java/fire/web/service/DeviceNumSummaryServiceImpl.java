package fire.web.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import fire.common.entity.DeviceNumSummary;
import fire.web.dao.ReportDAO;

@Service("deviceNumSummaryService")
public class DeviceNumSummaryServiceImpl implements DeviceNumSummaryService{
	@Resource
	private ReportDAO reportDAO;
	public List<DeviceNumSummary> getDeviceNumSummaryList(int companyId, int deviceTypeId, Date startTime,Date endTime) {
		List<DeviceNumSummary> list = reportDAO.getDeviceNumSummaryList(companyId, deviceTypeId, startTime, endTime);
		return list;
	}

}
