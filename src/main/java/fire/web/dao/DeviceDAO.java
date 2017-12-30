package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.Device;
import fire.common.entity.DeviceResult;

public interface DeviceDAO {
	public int updateDevice(Device device);
	public DeviceResult findById(int id);
	public int addDevice(DeviceResult device);
	public int delete(int id);
	public int findDeviceCount(int companyId);
	public List<DeviceResult> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size,
			@Param("deviceTypeId") int deviceTypeId
			);
	public int updateStatus(Device device);
}
