package com.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.dao.UserMapper;
import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {

	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void updateById() {
		User user = new User();
		user.setId(1168443892250533890L);
		user.setAge(26);
		user.setEmail("wewre@baomidou.com");
		int rows = userMapper.updateById(user);
		System.out.println("影响记录数：" + rows + "条");
	}
	
	@Test
	public void updateByWrapper() {
		UpdateWrapper<User> updateWrapper = new  UpdateWrapper<User>();
		updateWrapper.eq("name", "李艺伟").eq("age", 28);
		User user = new User();
		user.setEmail("zhangwe@baomidou.com");
		user.setAge(29);
		int rows = userMapper.update(user, updateWrapper);
		System.out.println("影响记录数：" + rows + "条");
	}
	
	@Test
	public void updateByWrapperLambda() {
		LambdaUpdateWrapper<User> lambdaUpdate = new LambdaUpdateWrapper<User>();
		lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge,29).set(User::getAge, 31);
		
		int rows = userMapper.update(null, lambdaUpdate);
		System.out.println("影响记录数: " + rows);
	}
}
