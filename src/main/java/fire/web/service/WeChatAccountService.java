package fire.web.service;

import fire.web.entity.WeChatAccount;

public interface WeChatAccountService {
	public WeChatAccount getWeChatAccount();
	public int save(WeChatAccount wca);
}
