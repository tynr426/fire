package fire.web.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.AuthBind;
import fire.common.entity.AuthBindResult;
import fire.web.dao.AuthBindDAO;
@Service("authBindService")
public class AuthBindServiceImpl implements AuthBindService{
	@Resource
	private AuthBindDAO abDAO;
	
	public int addAuthBind(AuthBind authBind) {
		AuthBind entity= findByOpenIdAndManagerId(authBind.getOpenId(),authBind.getManagerId());
		int n =0;
		if(entity!=null&&entity.getId()>0){
			authBind.setId(entity.getId());
			n=abDAO.updateAuthBind(authBind);
		}
		else{
			System.out.println("openId:"+authBind.getOpenId()+"managerId:"+authBind.getManagerId()+""+(entity!=null));
		authBind.setAuthoreTime(new Date());
		
		 n = abDAO.addAuthBind(authBind);
		}
		return n;
	}

	public AuthBind getAuthBind(String openId) {
		AuthBind authBind = abDAO.findByOpenId(openId);
		return authBind;
	}

	public AuthBind findByOpenIdAndManagerId(String openId, Integer managerId) {
		AuthBind authBind = abDAO.findByOpenIdAndManagerId(openId, managerId);
		return authBind;
	}

	public int updateAuthBind(AuthBind authBind) {
		return abDAO.updateAuthBind(authBind);
	}

	public List<AuthBindResult> getBindList(int companyId) {
		return abDAO.getBindList(companyId);

	}

	public int deleteById(int id) {
		return abDAO.deleteById(id);
	}

}
