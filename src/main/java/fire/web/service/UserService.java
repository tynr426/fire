package fire.web.service;

import fire.common.entity.User;


public interface UserService {
	public User login(String username,String password) throws NameException,PasswordException;
	public int loginOut();
	public int updatePwd(String oldPwd,String pwd,int id);
}
