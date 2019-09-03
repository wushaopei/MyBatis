package com.mp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.UserMapper;
import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void deleteById() {
		int rows = userMapper.deleteById(1168439950892429314L);
		System.out.println("影响记录数："+ rows +"条");
	}
	
	@Test
	public void deleteByMap() {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("name", "向后");
		columnMap.put("age", 25);
		int rows = userMapper.deleteByMap(columnMap);
		System.out.println("删除条数：" + rows + "条");
	}
	
	@Test
	public void deleteBatchIds() {
		int rows = userMapper.deleteBatchIds(Arrays.asList(1168440364299722753L,1168444966134968322L));
		System.out.println("删除条数：" +rows);
	}
	
	@Test
	public void deleteByWrapper() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.eq(User::getAge, 34).or().gt(User::getAge, 41);
		int rows = userMapper.delete(lambdaQuery);
		System.out.println("删除条数：" + rows);
	}
}
