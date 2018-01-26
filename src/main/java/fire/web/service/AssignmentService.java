package fire.web.service;




import java.util.List;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.common.entity.RepairrecordResult;
import fire.common.entity.StatusStatistics;
import fire.web.utils.PageInfo;

public interface AssignmentService {
	
	public Assignment getAssignment(int id);
	public int deleteAssignment(int id);
	public PageInfo<AssignmentResult> getAssignmentPage(int companyId,int index,int size);
	public PageInfo<AssignmentResult> getAssignmentPageByManager(int managerId,int index,int size,Integer status,String keyword);
	public List<StatusStatistics> getStatistics(int managerId,Integer status,String keyword);
	public int updateStatus(Integer id, Integer status);
	public int save(Assignment entity);
	public AssignmentResult getAssignmentByCheckId(int checkId);
}
