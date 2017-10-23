package fire.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.Assignment;
import fire.web.dao.AssignmentDAO;
import fire.web.utils.PageInfo;
@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService{
	@Resource
	private AssignmentDAO assignmentDAO;
	
	public int addAssignment(Assignment assignment) throws NameException {
		assignment.setStatus(1);
		int n = assignmentDAO.addAssignment(assignment);
		return n;
	}

	public int updateAssignment(Assignment assignment) {
		int n = assignmentDAO.updateAssignment(assignment);
		return n;
	}

	public Assignment getAssignment(int id) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("Id不存在");
		}
		return assignment;
	}

	public int deleteAssignment(int id) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("Id不存在");
		}
		return assignmentDAO.delete(id);
	}

	public PageInfo<Assignment> getDevicePage(int companyId, int index, int size) {
		PageInfo<Assignment> pi = new PageInfo<Assignment>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(assignmentDAO.findAssignmentCount());
		pi.setList(assignmentDAO.findByLimit(companyId,pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, Integer status) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("id不存在");
		}
		assignment.setStatus(status);
		int n = assignmentDAO.updateStatus(assignment);
		return n;
	}

}
