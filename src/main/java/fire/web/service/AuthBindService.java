package fire.web.service;


import java.util.List;

import fire.common.entity.AuthBind;
import fire.common.entity.AuthBindResult;

public interface AuthBindService {
	public int addAuthBind(AuthBind authBind);
	public AuthBind getAuthBind(String openId);
	public AuthBind findByOpenIdAndManagerId(String openId,Integer managerId
			);
	public int updateAuthBind(AuthBind authBind
			);
	public List<AuthBindResult> getBindList(int companyId);
	public int deleteById(int id);
}
