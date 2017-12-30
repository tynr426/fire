package fire.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;

public interface CheckDeviceDAO {
	public int addCD(CheckDevice cd);
	public int updateCD(CheckDevice cd);
	public CheckDeviceResult getCD(int id);
	public int delete(int id);
	public int findCDCount(@Param("companyId")int companyId,
			@Param("managerId")Integer managerId);
	public List<CheckDeviceResult> findByLimit(
			@Param("companyId") int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size,
			@Param("managerId") Integer managerId
			);
	public int updateStatus(CheckDevice cd);
}
