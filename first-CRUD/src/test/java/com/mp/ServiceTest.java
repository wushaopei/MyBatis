package com.mp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.entity.User;
import com.mp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void getOne() {
		User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);
		System.out.println(one);
	}
}
