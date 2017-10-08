package fire.web.service;


import java.util.List;

import fire.common.entity.Devicetypeparameter;
import fire.web.utils.PageInfo;

public interface DevicetypeparameterService {
	public List<Devicetypeparameter> showDevicetypeparameter();
	public int addDevicetypeparameter(Devicetypeparameter dtp)throws NameException;
	public int updateDevicetypeparameter(Devicetypeparameter dtp);
	public Devicetypeparameter getDevicetypeparameter(int Id);
	public int deleteDevicetypeparameter(Integer Id);
	public PageInfo<Devicetypeparameter> getDevicetypeparameterPage(int deviceTypeId,int index,int size);
	public int updateStatus(Integer id,int status);
}
