package com.webcode.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.webcode.Dao.UserDao;
import com.webcode.pojo.User;

public class UserDaoTest {
	
	private static UserDao userDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		userDao = new UserDao();
		userDao.setSqlSessionFactory(sqlSessionFactory);
		
	}

	

	@Test
	public void testQueryUserById() {
		userDao.queryUserById(1);
	}

	@Test
	public void testQueryUsers() {
		userDao.queryUsers().forEach(System.out::println);
	}

	@Test
	public void testSaveUser() {
		userDao.saveUser(new User(null,"xxxx",1));
	}
	
	@Test
	public void testSaveUser2() {
		int s = userDao.saveUser(new User(null,"xxxx",1));
		System.out.println(s);
	}
	@Test
	public void testDeleteUserById() {
		userDao.deleteUserById(1);
	}

	@Test
	public void testUpdateUser() {
		userDao.updateUser(new User(3,"2222",0));
	}

}
