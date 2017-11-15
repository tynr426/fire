package fire.web.service;




import fire.common.entity.Assignment;
import fire.web.utils.PageInfo;

public interface AssignmentService {
	
	public Assignment getAssignment(int id);
	public int deleteAssignment(int id);
	public PageInfo<Assignment> getAssignmentPage(int companyId,int index,int size);
	public int updateStatus(Integer id, Integer status);
	public int save(Assignment entity);

}
