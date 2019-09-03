package com.mp;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mp.dao.UserMapper;
import com.mp.entity.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insert() {
		
		User  u = new User();
//		u.setUsername("陈庆");
		u.setId(1268732089092060324L);
//		u.setName("陈庆");
		u.setAge(22);
		u.setEmail("23432@qq.com");
		u.setManagerId(1168737089097060354L);
		u.setCreateTime(LocalDateTime.now());
		u.setRemark("我是一个备注信息");
		User.setPostil("我是批注");
		
		int rows = userMapper.insert(u);
		System.out.print("影响记录数"+ rows +"条");
		
	}
}
