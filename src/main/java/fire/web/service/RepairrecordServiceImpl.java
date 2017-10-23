package fire.web.service;

import java.util.Date;

import javax.annotation.Resource;

import fire.common.entity.Repairrecord;
import fire.web.dao.RepairrecordDAO;
import fire.web.utils.PageInfo;

public class RepairrecordServiceImpl implements RepairrecordService{
	@Resource
	private RepairrecordDAO repairrecordDAO;
	
	public int addRepairrecord(Repairrecord repairrecord) throws NameException {
		repairrecord.setStatus(1);
		repairrecord.setAddTime(new Date());
		int n =repairrecordDAO.addRepairrecord(repairrecord);
		return n;
	}

	public int updateRepairrecord(Repairrecord repairrecord) {
		int n = repairrecordDAO.updateRepairrecord(repairrecord);
		return n;
	}

	public Repairrecord getRepairrecord(int id) {
		Repairrecord repairrecord = repairrecordDAO.findById(id);
		if(repairrecord==null){
			throw new NameException("Id不存在");
		}
		return repairrecord;
	}

	public int deleteRepairrecord(int id) {
		Repairrecord repairrecord = repairrecordDAO.findById(id);
		if(repairrecord==null){
			throw new NameException("Id不存在");
		}
		return repairrecordDAO.delete(id);
	}

	public PageInfo<Repairrecord> getRepairrecord(int index, int size) {
		PageInfo<Repairrecord> pi = new PageInfo<Repairrecord>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(repairrecordDAO.findRepairrecordCount());
		pi.setList(repairrecordDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

}
