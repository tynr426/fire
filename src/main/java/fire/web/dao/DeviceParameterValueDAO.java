package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.DeviceParameterValue;

public interface DeviceParameterValueDAO {
	public int updateDeviceParameterValue(@Param("dpvs")List<DeviceParameterValue> dpvs);
	public List<DeviceParameterValue> getDeviceParameterValues(Integer deviceId);
	public int addDeviceParameterValue(@Param("dpvs")List<DeviceParameterValue> dpvs);
	public int delete(int deviceId);
}
