package com.webcode.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.webcode.pojo.User;

public class UserTest {
	
	@Test
	public void test() throws IOException {
		//获取指定的资源
		InputStream iStream = Resources.getResourceAsStream("mybatis-config.xml");
		//通过sqlSessionFactoryBuilder创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(iStream);
		System.out.println(sqlSessionFactory);
		//相当于 以前的Connection 对象，每次用完都要关闭
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			//selectOne方法用来执行select查询语句，并返回一个对象
			// 放名称空间+id
			User user = openSession.selectOne("com.webcode.pojo.User.selectUserById",1);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			openSession.close();//释放资源
		}
	}
}
