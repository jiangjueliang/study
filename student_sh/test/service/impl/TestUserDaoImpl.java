package service.impl;

import org.junit.Test;

import service.UserDao;
import entity.Users;

public class TestUserDaoImpl {

	@Test
	public void testUserLogin() {
		Users u = new Users(1, "zhangsan", "123456");
		UserDao udao=new UsersDaoImpl();
		System.out.println(udao.usersLogin(u));
	}
}
