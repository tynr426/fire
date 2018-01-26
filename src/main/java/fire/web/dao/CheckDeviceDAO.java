package fire.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.common.entity.StatusStatistics;

public interface CheckDeviceDAO {
	public int addCD(CheckDeviceResult cd);
	public int updateCD(CheckDeviceResult cd);
	public CheckDeviceResult getCD(int id);
	public int delete(int id);
	public int findCDCount(@Param("companyId")int companyId,
			@Param("status") Integer status,
			@Param("keyword") String keyword);
	public List<StatusStatistics> getStatistics(
			@Param("companyId")int companyId,
			@Param("status") Integer status,
			@Param("keyword") String keyword);
	public List<CheckDeviceResult> findByLimit(
			@Param("companyId") int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size,
			@Param("status") Integer status,
			@Param("keyword") String keyword
			);
	public int updateStatus(CheckDevice cd);
}
