package fire.web.service;

import javax.annotation.Resource;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.dao.UserDAO;
import fire.web.entity.User;
import fire.web.utils.CookiesUtil;
import fire.web.utils.Md5;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDAO userDAO;
	
	@HttpConstraint
	//��¼
	public User login(String username, String password,String verifyCode) throws VerifyCodeException,NameException, PasswordException{
		if(username==null||username.trim().isEmpty()){
			throw new NameException("�û���Ϊ��");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("����Ϊ��");
		}
		User user = userDAO.findByUserName(username);
		if(user==null){
			throw new NameException("�û�������ȷ");
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie cookie = CookiesUtil.getCookieByName(request, "VerifyCode");
		
        if(cookie==null){
            throw new VerifyCodeException("��֤�����");   
        }else if(!verifyCode.equals(cookie.getValue().toLowerCase())){
        	throw new VerifyCodeException("��֤���������"); 
        }
        
		String md5Password = Md5.getMd5(password);
		if(user.getPassword().equals(md5Password)){			
			request.getSession().setAttribute("user",user );
			return user;
		}else {
			throw new PasswordException("�������");
		}
	}
	//�˳���¼
	public int loginOut() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("user");
		return 0;
	}

}
