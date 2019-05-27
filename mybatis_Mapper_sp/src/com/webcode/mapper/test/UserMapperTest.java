package com.webcode.mapper.test;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.webcode.mapper.UserMapper;
import com.webcode.pojo.User;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserMapperTest {
	static SqlSessionFactory sqlSessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			 sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
	}

	@Test
	public void testQueryUserById() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			System.out.println(mapper.queryUserById(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Test
	public void testQueryUsers() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.queryUsers().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Test
	public void testSaveUser() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			int saveUser = mapper.saveUser(new User(null,"吴",11));
			session.commit();
			System.out.println(saveUser);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Test
	public void testDeleteUserById() {
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			int deleteUserById = mapper.deleteUserById(2);
			openSession.commit();
			System.out.println(deleteUserById);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			openSession.close();
		}
	}

	@Test
	public void testUpdateUser() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			int updateUser = mapper.updateUser(new User(1,"少",33));
			session.commit();
			System.out.println(updateUser);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
