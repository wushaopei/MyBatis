package com.webcode.Dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.webcode.pojo.User;
public class UserDao {
	
	
	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public User queryUserById(Integer id) {
			
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.selectOne("com.webcode.pojo.User.selectUserById",1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
			
		return null;
	
	}
	/*
	 * 查询所有用户
	 * */
	public List<User> queryUsers(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return	session.selectList("com.webcode.pojo.User.queryUsers");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;		
	}
	/*
	 * 插入一个用户
	 * */
	public int saveUser(User user) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int result =  session.insert("com.webcode.pojo.User.saveUser",user);
			session.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return -1;
	}
	
	/*
	 * 
	 * 删除一个用户
	 */
	public int deleteUserById(Integer id) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int update = session.update("com.webcode.pojo.User.deleteUser", 1);
			session.commit();
			return update;
		} catch (Exception e) {
			e.getSuppressed();
		}finally {
			session.close();
		}
		return -1;
	}
	
	/*
	 * 修改用户
	 * */
	public int updateUser(User user) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int update = session.update("com.webcode.pojo.User", user);
			session.commit();
			return update;
			
		} catch (Exception e) {
			e.getSuppressed();
		}finally{
			session.close();
		}
		return -1;
	}
} 
