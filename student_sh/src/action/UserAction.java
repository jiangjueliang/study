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
	//�û���¼����
	public String login(){
		UserDao udao=new UsersDaoImpl();
		if(udao.usersLogin(users)){
			//��session�б����û���
			session.setAttribute("loginUserName", users.getUsername());
			return "login_success";
		}else {
			addFieldError("error", "�û������������");
			return"login_failure";
		}
	}
	//����֤����
	@Override
	public void validate() {
		if("".equals(users.getUsername().trim())||"".equals(users.getPassword().trim())){
			this.addFieldError("usernameError", "�û��������벻��Ϊ��");
		}
		if(users.getPassword().length()<6){
			this.addFieldError("passwordError", "���볤����Ҫ����6λ");
		}
	}
	
	@SkipValidation
	//�û�ע���ķ���
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
