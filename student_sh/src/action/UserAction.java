package action;

import javax.xml.registry.infomodel.User;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UserDao;
import service.impl.UsersDaoImpl;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UserAction extends SuperAction implements ModelDriven<Users> {

	private Users users=new Users();
	//用户登录动作
	public String login(){
		UserDao udao=new UsersDaoImpl();
		if(udao.usersLogin(users)){
			//在session中保存用户名
			session.setAttribute("loginUserName", users.getUsername());
			return "login_success";
		}else {
			addFieldError("error", "用户名或密码错误");
			return"login_failure";
		}
	}
	//表单验证功能
	@Override
	public void validate() {
		if("".equals(users.getUsername().trim())||"".equals(users.getPassword().trim())){
			this.addFieldError("usernameError", "用户名或密码不能为空");
		}
		if(users.getPassword().length()<6){
			this.addFieldError("passwordError", "密码长度需要大于6位");
		}
	}
	
	@SkipValidation
	//用户注销的方法
	public String loginOut(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "loginOut_success";
	}
	
	
	
	
	
	
	
	
	public Users getModel() {
		// TODO Auto-generated method stub
		return users;
	}

}
