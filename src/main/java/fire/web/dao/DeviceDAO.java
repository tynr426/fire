package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Device;
import fire.web.entity.DeviceResult;

public interface DeviceDAO {
	public int updateDevice(Device device);
	public DeviceResult findById(int id);
	public int addDevice(DeviceResult device);
	public int delete(Integer id);
	public int findDeviceCount();
	public List<DeviceResult> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int updateStatus(Device device);
}
