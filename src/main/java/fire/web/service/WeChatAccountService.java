package fire.web.service;

import fire.web.entity.WeChatAccount;

public interface WeChatAccountService {
	public WeChatAccount getWeChatAccount();
	public int addWeChatAccount(WeChatAccount wca);
	public int updateWeChatAccount(WeChatAccount wca);
}
