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
	 * 添加设备
	 * */
	@Transactional
	public int addDevice(DeviceResult device){

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
	 * 修改设备
	 * 
	 * */
	@Transactional
	public int updateDevice(DeviceResult device) {
		int n = deviceDAO.updateDevice(device);
		List<DeviceParameterValue> list=device.getList();
		List<DeviceParameterValue> addList=new ArrayList<DeviceParameterValue>();
		List<DeviceParameterValue> updateList=new ArrayList<DeviceParameterValue>();
		for(DeviceParameterValue entity:list){
			if(entity.getId()==0){
				addList.add(entity);
			}
			else{
				updateList.add(entity);
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

	public DeviceResult getDevice(int id) {
		DeviceResult device = deviceDAO.findById(id);
		return device;
	}

	public int deleteDevice(Integer id) {
		if(id==null){
			throw new NameException("ID不能为空");
		}
		Device device = deviceDAO.findById(id);
		if(device==null){
			throw new NameException("用户不存在");
		}
		int n = deviceDAO.delete(id);
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
