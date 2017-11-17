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
	public int findAssignmentCount();
	public List<AssignmentResult> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	public int updateStatus(Assignment assignment);
	public Assignment getAssignmentByCheckId(int checkId);
}
