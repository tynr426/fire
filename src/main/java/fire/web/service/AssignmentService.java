package fire.web.service;




import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.web.utils.PageInfo;

public interface AssignmentService {
	
	public Assignment getAssignment(int id);
	public int deleteAssignment(int id);
	public PageInfo<AssignmentResult> getAssignmentPage(int companyId,int index,int size);
	public int updateStatus(Integer id, Integer status);
	public int save(Assignment entity);
	public Assignment getAssignmentByCheckId(int checkId);
}
