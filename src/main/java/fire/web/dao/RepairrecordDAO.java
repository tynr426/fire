package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.Repairrecord;
import fire.common.entity.RepairrecordResult;

public interface RepairrecordDAO {
	public int updateRepairrecord(Repairrecord repairrecord);
	public RepairrecordResult findById(int id);
	public int addRepairrecord(Repairrecord repairrecord);
	public int delete(int id);
	public int findRepairrecordCount();
	public List<RepairrecordResult> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int updateStatus(Repairrecord repairrecord);
}
