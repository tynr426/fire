package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.DeviceType;


public interface DeviceTypeDAO {
	public DeviceType getDeviceType(String name);
	public List<DeviceType> findAll();
	public int updateDeviceType(DeviceType dt);
	public DeviceType findById(int Id);
	public int addDeviceType(DeviceType dt);
	public int delete(Integer Id);
	public int findDeviceTypeCount();
	public List<DeviceType> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int updateStatus(DeviceType dt);
}
