package fire.web.service;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.web.utils.PageInfo;

public interface CheckDeviceService {
	public int addCD(CheckDevice cd);
	public int updateCD(CheckDevice cd);
	public CheckDeviceResult getCheckDevice(int id);
	public int deleteCD(int id);
	public PageInfo<CheckDeviceResult> getCheckDevicePage(int companyId,int index,int size,Integer managerId);

}
