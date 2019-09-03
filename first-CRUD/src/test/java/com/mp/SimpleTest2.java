package com.mp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mp.dao.UserMapper;
import com.mp.entity.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest2 {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void select() {
		List<User> list = userMapper.selectList(null);
		Assert.assertEquals(5, list.size());
		list.forEach(System.out::println);
	}
}
