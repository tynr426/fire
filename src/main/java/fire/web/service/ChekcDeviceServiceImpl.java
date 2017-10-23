package fire.web.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.CheckDevice;
import fire.web.dao.CheckDeviceDAO;
@Service("checkDeviceService")
public class ChekcDeviceServiceImpl implements CheckDeviceService{
	@Resource
	private CheckDeviceDAO cDDAO;
	
	public int addCD(CheckDevice cd) {
		cd.setAddTime(new Date());
		return cDDAO.addCD(cd);
	}

}
