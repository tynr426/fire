package fire.web.service;




import fire.common.entity.Repairrecord;
import fire.web.utils.PageInfo;

public interface RepairrecordService {
	public int addRepairrecord(Repairrecord repairrecord)throws NameException;
	public int updateRepairrecord(Repairrecord repairrecord);
	public Repairrecord getRepairrecord(int id);
	public int deleteRepairrecord(int id);
	public PageInfo<Repairrecord> getRepairrecord(int index,int size);
}
