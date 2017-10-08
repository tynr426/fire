package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.DeviceType;
import fire.common.entity.DeviceTypeResult;


public interface DeviceTypeDAO {
	public DeviceType getDeviceType(String name);
	public List<DeviceTypeResult> findAll();
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
