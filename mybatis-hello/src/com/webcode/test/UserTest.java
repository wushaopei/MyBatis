package com.webcode.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.webcode.pojo.User;

public class UserTest {
	
	@Test
	public void test1() throws Exception {
		// 读取指定的资源
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		// 通过sqlSessionFactoryBuilder创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		System.out.println( sqlSessionFactory );
		// 相当于 以前的Connection对象，每次用来都用关闭
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
//			selectOne方法用来执行select查询语句，并返回一个对象
			// 放名称空间+id
			User user = session.selectOne("com.webcode.pojo.User.selectUserById", 1);
			System.out.println( user );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();//释放资源
		}
		
	}
}
