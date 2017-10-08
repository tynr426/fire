package fire.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.sdk.utils.EncrypDES;
import fire.web.dao.WeChatAccountDAO;
import fire.web.entity.WeChatAccount;
import fire.web.utils.Md5;
@Service("weChatAccountService")
public class WeChatAccountServiceImpl implements WeChatAccountService{
	@Resource
	private WeChatAccountDAO wcaDao;
	
	public WeChatAccount getWeChatAccount() {
		WeChatAccount wca = wcaDao.getWeChatAccount();
		return wca;
	}

	public int save(WeChatAccount wca) {
		if(wca.getId()==0){
			wca.setToken(Md5.createID().substring(0, 4));
			wca.setEncodingAESKey(Md5.getMd5(wca.getToken()));
			return wcaDao.addWeChatAccount(wca);
		}
		return wcaDao.updateWeChatAccount(wca);
	}
}
