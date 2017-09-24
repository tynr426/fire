package fire.web.service;

import fire.web.entity.User;


public interface UserService {
	public User login(String username,String password,String verifyCode) throws VerifyCodeException,NameException,PasswordException;
	public int loginOut();
}
