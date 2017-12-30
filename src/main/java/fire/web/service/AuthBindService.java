package fire.web.service;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.AuthBind;

public interface AuthBindService {
	public int addAuthBind(AuthBind authBind);
	public AuthBind getAuthBind(String openId);
	public AuthBind findByOpenIdAndManagerId(String openId,Integer managerId
			);
	public int updateAuthBind(AuthBind authBind
			);
}
