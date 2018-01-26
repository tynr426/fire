package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.DeviceCheckParameterValue;

public interface DeviceCheckParameterValueDAO {
	public int updateDeviceCheckParameterValue(@Param("dpvs")List<DeviceCheckParameterValue> dpvs);
	public List<DeviceCheckParameterValue> getDeviceCheckParameterValues(Integer checkId);
	public int addDeviceCheckParameterValue(@Param("dpvs")List<DeviceCheckParameterValue> dpvs);
	public int delete(int checkId);
}
