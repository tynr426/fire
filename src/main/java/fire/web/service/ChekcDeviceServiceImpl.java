package fire.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.common.entity.RepairrecordResult;
import fire.web.dao.CheckDeviceDAO;
import fire.web.utils.PageInfo;
@Service("checkDeviceService")
public class ChekcDeviceServiceImpl implements CheckDeviceService{
	@Resource
	private CheckDeviceDAO cDDAO;
	
	public int addCD(CheckDevice cd) {
		cd.setAddTime(new Date());
		return cDDAO.addCD(cd);
	}

	public int updateCD(CheckDevice cd) {
		return cDDAO.updateCD(cd);
	}

	public CheckDeviceResult getCheckDevice(int id) {
		CheckDeviceResult cdr = cDDAO.findById(id);
		if(cdr==null){
			throw new NameException("Id不存在");
		}
		return cdr;
	}

	public int deleteCD(int id) {
		CheckDeviceResult cdr = cDDAO.findById(id);
		if(cdr==null){
			throw new NameException("Id不存在");
		}
		return cDDAO.delete(id);
	}

	public PageInfo<CheckDeviceResult> getCheckDevicePage(int companyId,int index,int size,String managerName,String model,Integer deviceTypeId){
		PageInfo<CheckDeviceResult> pi = new PageInfo<CheckDeviceResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(cDDAO.findCDCount());
		pi.setList(cDDAO.findByLimit(companyId,pi.getBegin(), size,managerName,model,deviceTypeId));
		return pi;
	}





}
