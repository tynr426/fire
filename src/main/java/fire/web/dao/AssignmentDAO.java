package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;

public interface AssignmentDAO {
	public int updateAssignment(Assignment assignment);
	public Assignment findById(int id);
	public int addAssignment(Assignment assignment);
	public int delete(int id);
	public int findAssignmentCount(int companyId);
	public List<AssignmentResult> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int findByManagerCount(@Param("managerId")int managerId,
			@Param("deviceTypeId") Integer deviceTypeId,
			@Param("keyword") String keyword
			);
	public List<AssignmentResult> findByManagerLimit(
			@Param("managerId")int managerId,
			@Param("begin") Integer begin,
			@Param("size") Integer size,
			@Param("deviceTypeId") Integer deviceTypeId,
			@Param("keyword") String keyword
			);
	public int updateStatus(Assignment assignment);
	public Assignment getAssignmentByCheckId(int checkId);
	public AssignmentResult getAssignmentByDeviceId(
			@Param("deviceId")int deviceId,
			@Param("toManagerId")int toManagerId);
}
