package fire.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;

public interface CheckDeviceDAO {
	public int addCD(CheckDevice cd);
	public int updateCD(CheckDevice cd);
	public CheckDeviceResult findById(int id);
	public int delete(int id);
	public int findCDCount();
	public List<CheckDeviceResult> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size,
			@Param("managerName") String managerName,
			@Param("model") String model,
			@Param("deviceTypeId") Integer deviceTypeId
			);
	public int updateStatus(CheckDevice cd);
}
