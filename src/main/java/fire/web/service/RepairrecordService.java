package fire.web.service;




import fire.common.entity.Repairrecord;
import fire.common.entity.RepairrecordResult;
import fire.web.utils.PageInfo;

public interface RepairrecordService {
	public int addRepairrecord(Repairrecord repairrecord)throws NameException;
	public int updateRepairrecord(Repairrecord repairrecord);
	public RepairrecordResult getRepairrecord(int id);
	public int deleteRepairrecord(int id);
	public PageInfo<RepairrecordResult> getRepairrecordpage(int index,int size);
	public int updateStatus(Integer id,int status);
}
