package com.corp.mms.user.service;

import com.corp.mms.user.dao.UserDao;
import com.corp.mms.user.vo.User;

public class UserService {
	UserDao userDao=new UserDao();
	//用户登录校验,用户名与密码正确返回1,否则返回0
	public int CheckUser(User user){
		return userDao.CheckUser(user);
	}
}
