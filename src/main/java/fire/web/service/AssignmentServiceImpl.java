package fire.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.common.entity.Assignment;
import fire.common.entity.AssignmentResult;
import fire.common.entity.CheckDevice;
import fire.web.dao.AssignmentDAO;
import fire.web.dao.CheckDeviceDAO;
import fire.web.utils.Company;
import fire.web.utils.PageInfo;
@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService{
	@Resource
	private AssignmentDAO assignmentDAO;
	@Resource
	private CheckDeviceDAO cdDAO;
	@Transactional
	public int save(Assignment entity){
		    CheckDevice cd = cdDAO.getCD(entity.getCheckId());
			entity.setCompanyId(Company.getCompanyId());
			entity.setFromManagerId(Company.getCompany().getManagerId());
			entity.setAddTime(new Date());
			cd.setStatus(2);//将检查报告中设备的状态设置为待整改
			cdDAO.updateCD(cd);
			return assignmentDAO.addAssignment(entity);
	}
	public Assignment getAssignment(int id) {
		Assignment assignment = assignmentDAO.findById(id);
		if(assignment==null){
			throw new NameException("Id不存在");
		}
		return assignment;
	}
	public Assignment getAssignmentByCheckId(int checkId) {
		Assignment assignment = assignmentDAO.getAssignmentByCheckId(checkId);
		if(assignment==null){
			throw new NameException("checkId不存在");
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

	public PageInfo<AssignmentResult> getAssignmentPage(int companyId, int index, int size) {
		PageInfo<AssignmentResult> pi = new PageInfo<AssignmentResult>();
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
