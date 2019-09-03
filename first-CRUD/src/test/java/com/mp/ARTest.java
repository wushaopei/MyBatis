//package com.mp;
//
//import java.time.LocalDateTime;
//
//import javax.sound.midi.Soundbank;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.mp.dao.UserMapper;
//import com.mp.entity.User;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ARTest {
//
//	
//	@Test
//	public void insert() {
//		User user = new User();
//		user.setName("张凉");
//		user.setAge(25);
//		user.setEmail("sf@baomidou.com");
//		user.setManagerId(1088248166370832385L);
//		user.setCreateTime(LocalDateTime.now());
//
//		boolean insert = user.insert();
//		System.out.println(insert);
//	}
//	
//	@Test
//	public void selectById() {
//		User user = new User();
//		User userSelect = user.selectById(1168443892250533890L);
//		System.out.println(userSelect == user);
//		System.out.println(userSelect);
//	}
//	
//	@Test
//	public void selectById2() {
//		User user = new User();
//		user.setId(1168443892250533890L);
//		User userSelect = user.selectById();
//		System.out.println(userSelect == user);
//		System.out.println(userSelect);
//	}
//	
//	@Test
//	public void updateById() {
//		User user = new User();
//		user.setId(1168443892250533890L);
//		user.setName("张草草");
//		boolean updateById = user.updateById();
//		System.out.println(updateById);
//	}
//}
