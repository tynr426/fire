package fire.web.service;

import fire.common.entity.WeChatAccount;

public interface WeChatAccountService {
	public WeChatAccount getWeChatAccount();
	public int save(WeChatAccount wca);
	
}
