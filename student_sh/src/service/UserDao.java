package service;

import entity.Users;

//用户业务逻辑接口
public interface UserDao {

	//用户登录方法
	public boolean usersLogin(Users u);
	

}
