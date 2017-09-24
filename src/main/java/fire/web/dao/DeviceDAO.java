package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Device;

public interface DeviceDAO {
	public List<Device> findAll();
	public int updateDevice(Device device);
	public Device findById(int id);
	public int addDevice(Device device);
	public int delete(Integer id);
	public int findDeviceCount();
	public List<Device> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int updateStatus(Device device);
}
