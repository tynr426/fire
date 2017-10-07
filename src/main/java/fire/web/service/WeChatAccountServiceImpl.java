package fire.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.web.dao.WeChatAccountDAO;
import fire.web.entity.WeChatAccount;
@Service("weChatAccountService")
public class WeChatAccountServiceImpl implements WeChatAccountService{
	@Resource
	private WeChatAccountDAO wcaDao;
	
	public WeChatAccount getWeChatAccount() {
		WeChatAccount wca = wcaDao.getWeChatAccount();
		return wca;
	}

	public int addWeChatAccount(WeChatAccount wca) {
		return wcaDao.addWeChatAccount(wca);
	}

	public int updateWeChatAccount(WeChatAccount wca) {
		return wcaDao.updateWeChatAccount(wca);
	}

}
