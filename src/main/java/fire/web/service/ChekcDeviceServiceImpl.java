package fire.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.common.entity.CheckDeviceResult;
import fire.common.entity.DeviceCheckParameterValue;
import fire.common.entity.StatusStatistics;
import fire.web.dao.CheckDeviceDAO;
import fire.web.dao.DeviceCheckParameterValueDAO;
import fire.web.utils.PageInfo;
@Service("checkDeviceService")
public class ChekcDeviceServiceImpl implements CheckDeviceService{
	@Resource
	private CheckDeviceDAO cDDAO;
	@Resource
	private DeviceCheckParameterValueDAO deviceCheckParameterValueDAO;
	@Transactional
	public int addCD(CheckDeviceResult cd) {
		cd.setAddTime(new Date());
		cd.setStatus(1);
		List<DeviceCheckParameterValue> list=cd.getList();
		if(list!=null){
			for(DeviceCheckParameterValue entity:list){
				entity.setCheckId(cd.getId());
			}
			if(!list.isEmpty()){				
				deviceCheckParameterValueDAO.addDeviceCheckParameterValue(list);
			}
		}
		return cDDAO.addCD(cd);
	}
	@Transactional
	public int updateCD(CheckDeviceResult cd) {
		List<DeviceCheckParameterValue> list=cd.getList();
		//之前参数
		List<DeviceCheckParameterValue> preParameterList=deviceCheckParameterValueDAO.getDeviceCheckParameterValues(cd.getId());
		//待添加的
		List<DeviceCheckParameterValue> addList=new ArrayList<DeviceCheckParameterValue>();
		//待修改的
		List<DeviceCheckParameterValue> updateList=new ArrayList<DeviceCheckParameterValue>();
		if(list!=null){
			for(DeviceCheckParameterValue entity:list){
				entity.setCheckId(cd.getId());
				if(!getUpdate(entity,preParameterList,updateList)){
					addList.add(entity);
				}
			}
		}
		if(addList.size()>0){
			deviceCheckParameterValueDAO.addDeviceCheckParameterValue(list);
		}
		if(updateList.size()>0){
			deviceCheckParameterValueDAO.updateDeviceCheckParameterValue(updateList);
		}
		return cDDAO.updateCD(cd);
	}
private boolean getUpdate(DeviceCheckParameterValue entity,List<DeviceCheckParameterValue> preParameterList,List<DeviceCheckParameterValue> updateList){
		
		for(DeviceCheckParameterValue p:preParameterList)
			if(p.getCheckId()==entity.getCheckId()&&p.getParameterId()==entity.getParameterId()){
				updateList.add(entity);
				return true;
			}
		return false;
	}
	
	public CheckDeviceResult getCheckDevice(int id) {
		CheckDeviceResult cdr = cDDAO.getCD(id);
		if(cdr==null){
			throw new NameException("Id不存在");
		}
		cdr.setList(deviceCheckParameterValueDAO.getDeviceCheckParameterValues(id));
		return cdr;
	}
	@Transactional
	public int deleteCD(int id) {
		CheckDeviceResult cdr = cDDAO.getCD(id);
		if(cdr==null){
			throw new NameException("Id不存在");
		}
		deviceCheckParameterValueDAO.delete(id);
		return cDDAO.delete(id);
	}

	public PageInfo<CheckDeviceResult> getCheckDevicePage(int companyId,int index,int size,Integer status,String keyword){
		PageInfo<CheckDeviceResult> pi = new PageInfo<CheckDeviceResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(cDDAO.findCDCount(companyId,status, keyword));
		pi.setList(cDDAO.findByLimit(companyId,pi.getBegin(), size,status, keyword));
		return pi;
	}
	public List<StatusStatistics> getStatistics(int companyId, Integer status, String keyword) {
		List<StatusStatistics> list = cDDAO.getStatistics(companyId, status, keyword);
		if(list==null){
			list=new ArrayList<StatusStatistics>();
		}
		int count = 0;
		for(StatusStatistics entity:list){
			count+=entity.getCount();
		}
		StatusStatistics entity = new StatusStatistics();
		entity.setCount(count);
		entity.setStatus(999);
		list.add(entity);
		return list;
	}





}
