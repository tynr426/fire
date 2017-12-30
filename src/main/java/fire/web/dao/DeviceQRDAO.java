package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceQRResult;
import fire.common.entity.DeviceResult;
import fire.common.entity.ScanInfo;

public interface DeviceQRDAO {
	public DeviceQR findById(int id);
	public ScanInfo findByCode(String code);
	public int addDeviceQR(List<DeviceQR> list);
	public int update(@Param("code") String code,
			@Param("deviceId") Integer deviceId);
	public int delete(int id);
	public int findDeviceQRCount();
	public List<DeviceQRResult> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public List<DeviceQRResult> search(
			@Param("model") String model,
			@Param("deviceTypeId") Integer deviceTypeId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public List<DeviceQR> getQRList(
			String batch
			);
}
