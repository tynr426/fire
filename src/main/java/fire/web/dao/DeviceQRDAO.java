package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.DeviceQR;

public interface DeviceQRDAO {
	public DeviceQR findById(int id);
	public int addDeviceQR(List<DeviceQR> list);
	public int delete(int id);
	public int findDeviceQRCount();
	public List<DeviceQR> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
}
