package com.corp.mms.user.service;

import com.corp.mms.user.dao.UserDao;
import com.corp.mms.user.vo.User;

public class UserService {
	UserDao userDao=new UserDao();
	//�û���¼У��,�û�����������ȷ����1,���򷵻�0
	public int CheckUser(User user){
		return userDao.CheckUser(user);
	}
}
