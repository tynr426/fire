package fire.web.service;

import java.util.List;

import fire.common.entity.CheckDeviceResult;
import fire.common.entity.StatusStatistics;
import fire.web.utils.PageInfo;

public interface CheckDeviceService {
	public int addCD(CheckDeviceResult cd);
	public int updateCD(CheckDeviceResult cd);
	public CheckDeviceResult getCheckDevice(int id);
	public int deleteCD(int id);
	public PageInfo<CheckDeviceResult> getCheckDevicePage(int companyId,int index,int size,Integer status,String keyword);
	public List<StatusStatistics> getStatistics(int companyId,Integer status,String keyword);
}
