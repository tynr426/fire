package fire.web.dao;

import fire.common.entity.WeChatAccount;

public interface WeChatAccountDAO {
	public WeChatAccount getWeChatAccount();
	public int addWeChatAccount(WeChatAccount wca);
	public int updateWeChatAccount(WeChatAccount wca);
}
