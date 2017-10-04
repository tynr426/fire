package fire.web.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.web.dao.DeviceDAO;
import fire.web.dao.DeviceParameterValueDAO;
import fire.web.entity.Device;
import fire.web.entity.DeviceParameterValue;
import fire.web.entity.DeviceResult;
import fire.web.utils.PageInfo;
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{
	@Resource
	private DeviceDAO deviceDAO;
	@Resource
	private DeviceParameterValueDAO deviceParameterValueDAO;
	/*
	 * ����豸
	 * */
	@Transactional
	public int addDevice(DeviceResult device){
		if(device.getCompanyId()==0)
			throw new NameException("û�л�ȡ����˾����");
		int n = deviceDAO.addDevice(device);
		List<DeviceParameterValue> list=device.getList();
		if(list!=null){
			for(DeviceParameterValue entity:list){
				entity.setDeviceId(device.getId());
			}
			deviceParameterValueDAO.addDeviceParameterValue(list);
		}
		return n;
	}
	/*
	 * �޸��豸
	 * 
	 * */
	@Transactional
	public int updateDevice(DeviceResult device) {
		int n = deviceDAO.updateDevice(device);
		List<DeviceParameterValue> list=device.getList();
		//֮ǰ����
		List<DeviceParameterValue> preParameterList=deviceParameterValueDAO.getDeviceParameterValues(device.getId());
		//����ӵ�
		List<DeviceParameterValue> addList=new ArrayList<DeviceParameterValue>();
		//���޸ĵ�
		List<DeviceParameterValue> updateList=new ArrayList<DeviceParameterValue>();
		for(DeviceParameterValue entity:list){
			entity.setDeviceId(device.getId());
			if(!getUpdate(entity,preParameterList,updateList)){
				addList.add(entity);
			}
		}
		if(addList.size()>0){
			deviceParameterValueDAO.addDeviceParameterValue(list);
		}
		if(updateList.size()>0){
			deviceParameterValueDAO.updateDeviceParameterValue(updateList);
		}
		return n;
	}
	private boolean getUpdate(DeviceParameterValue entity,List<DeviceParameterValue> preParameterList,List<DeviceParameterValue> updateList){
		
		for(DeviceParameterValue p:preParameterList)
			if(p.getDeviceId()==entity.getDeviceId()&&p.getParameterId()==entity.getParameterId()&&entity.getDeviceTypeId()==entity.getDeviceTypeId()){
				updateList.add(entity);
				return true;
			}
		return false;
	}

	public DeviceResult getDevice(int id) {
		DeviceResult device = deviceDAO.findById(id);
		device.setList(deviceParameterValueDAO.getDeviceParameterValues(id));
		return device;
	}
	@Transactional
	public int deleteDevice(int id) {
		if(id<1){
			throw new NameException("ID����Ϊ��");
		}
		Device device = deviceDAO.findById(id);
		if(device==null){
			throw new NameException("�û�������");
		}
		int n = deviceDAO.delete(id);
		deviceParameterValueDAO.delete(id);
		return n;
	}

	public PageInfo<DeviceResult> getDevicePage(int companyId,int index, int size) {
		PageInfo<DeviceResult> pi = new PageInfo<DeviceResult>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(deviceDAO.findDeviceCount());
		pi.setList(deviceDAO.findByLimit(companyId,pi.getBegin(), size));
		return pi;
	}

}
