package fire.web.service;

import javax.annotation.Resource;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.dao.UserDAO;
import fire.common.entity.User;
import fire.web.utils.Md5;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDAO userDAO;
	
	@HttpConstraint
	//登录
	public User login(String username, String password) throws NameException, PasswordException{
		if(username==null||username.trim().isEmpty()){
			throw new NameException("用户名为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码为空");
		}
		User user = userDAO.findByUserName(username);
		if(user==null){
			throw new NameException("用户名不正确");
		}   
		String md5Password = Md5.getMd5(password);
		if(user.getPassword().equals(md5Password)){			
			return user;
		}else {
			throw new PasswordException("密码错误");
		}
	}
	//退出登录
	public int loginOut() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("user");
		return 0;
	}
	public int updatePwd(String oldPwd, String pwd, int id) {
		User user = userDAO.findById(id);
		if(user==null){
			throw new NameException("不存在改用户");
		}
		if(!Md5.getMd5(oldPwd).equals(user.getPassword())){
			throw new PasswordException("原密码不正确");
		}
		user.setPassword(Md5.getMd5(pwd));
		return userDAO.updateUser(user);
	}

}
