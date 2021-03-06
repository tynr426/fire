package fire.web.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.AssignmentSummary;
import fire.common.entity.DeviceNumSummary;

public interface ReportDAO {
	public List<DeviceNumSummary> getDeviceNumSummaryList(
			@Param("companyId")int companyId,
			@Param("deviceTypeId")int deviceTypeId,
			@Param("startTime") Date startTime,
			@Param("endTime") Date endTime,
			@Param("unitproperties") Integer unitproperties,
			@Param("buildingtype") Integer buildingtype,
			@Param("isimport") Boolean isimport
			);
	public List<AssignmentSummary> getAssignmentSummaryList(
			@Param("companyId")int companyId,
			@Param("deviceTypeId")int deviceTypeId,
			@Param("startTime") String year,
			@Param("unitproperties") Integer unitproperties,
			@Param("buildingtype") Integer buildingtype,
			@Param("isimport") Boolean isimport
			);
}
