package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.AuthBind;

public interface AuthBindDAO {
	public int addAuthBind(AuthBind authBind);
	public AuthBind findByOpenId(String openId);
	public List<AuthBind> findOpenIds(int managerId);
	public AuthBind findByOpenIdAndManagerId(
			@Param("openId") String openId,
			@Param("managerId") Integer managerId
			);
	public int updateAuthBind(AuthBind authBind
			);
}
