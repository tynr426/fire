package fire.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.common.entity.Assignment;
import fire.common.entity.CheckDeviceResult;
import fire.common.entity.Repairrecord;
import fire.common.entity.RepairrecordResult;
import fire.web.dao.AssignmentDAO;
import fire.web.dao.CheckDeviceDAO;
import fire.web.dao.RepairrecordDAO;
import fire.web.utils.PageInfo;
@Service("repairrecordService")
public class RepairrecordServiceImpl implements RepairrecordService{
	@Resource
	private RepairrecordDAO repairrecordDAO;
	@Resource
	private AssignmentDAO assignmentDAO;
	@Resource
	private CheckDeviceDAO checkDeviceDAO;
	@Transactional
	public int addRepairrecord(Repairrecord repairrecord) throws NameException {
		repairrecord.setAddTime(new Date());
		if(repairrecord.getIsFinish()){
			Assignment assignment = assignmentDAO.findById(repairrecord.getAssignmentId());
			assignment.setStatus(1);
			assignmentDAO.updateAssignment(assignment);
			CheckDeviceResult checkDevice = checkDeviceDAO.getCD(assignment.getCheckId());
			checkDevice.setStatus(3);
			checkDeviceDAO.updateCD(checkDevice);
		}
		int n =repairrecordDAO.addRepairrecord(repairrecord);
		return n;
	}

	public int updateRepairrecord(Repairrecord repairrecord) {
		int n = repairrecordDAO.updateRepairrecord(repairrecord);
		return n;
	}

	public RepairrecordResult getRepairrecord(int id) {
		RepairrecordResult repairrecordResult = repairrecordDAO.findById(id);
		if(repairrecordResult==null){
			throw new NameException("Id不存在");
		}
		return repairrecordResult;
	}

	public int deleteRepairrecord(int id) {
		Repairrecord repairrecord = repairrecordDAO.findById(id);
		if(repairrecord==null){
			throw new NameException("Id不存在");
		}
		return repairrecordDAO.delete(id);
	}

	public PageInfo<RepairrecordResult> getRepairrecordpage(int index, int size) {
		PageInfo<RepairrecordResult> pi = new PageInfo<RepairrecordResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(repairrecordDAO.findRepairrecordCount());
		pi.setList(repairrecordDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, int status) {
		Repairrecord repairrecord = repairrecordDAO.findById(id);
		if(repairrecord==null){
			throw new NameException("id不存在");
		}
		repairrecord.setStatus(status);
		int n = repairrecordDAO.updateStatus(repairrecord);
		return n;
	}

}
