package com.mp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
/*
 基本查询
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrieveTest {
	
	@Autowired
	private UserMapper userMapper;
	
	
	//根据单个id查询用户
//	@Test
	public void selectBy() {
		User user = userMapper.selectById(1168440364299722753L);
		System.out.println(user);
	}
	
	//根据 id  的 List<Long> 查询多个用户
//	@Test 
	public void selectIds() {
		List<Long> idsList = Arrays.asList(1168440364299722753L,1094592041087729666L,1094590409767661570L);
		List<User> userList = userMapper.selectBatchIds(idsList);
		userList.forEach(System.out::println);
	}
	
	//根据 map 的 key-value 中的属性和值 查询 用户
//	@Test
	public void selectByMap() {
		//map.put("name","王天风")
		//map.put("age",30)
		//where namem = "王天风" and age = 30
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("name", "王天风");
		columnMap.put("age", 25);
		List<User> userList = userMapper.selectByMap(columnMap);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 1、名字中包含与并且年龄小于40
	 *  name like `%雨%` and age <40
	 * */
//	@Test
	public void selectByWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//		QueryWrapper<User> queryWrapper = Wrappers.<User>query();
		queryWrapper.like("name", "雨").lt("age",40);
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 2、名字中包含雨 并且年龄大于等于20 且小于等于40 并且email 不为空
	 *  name like `%雨%`` and age between 20 and 40 and email is not null
	 * */
//	@Test
	public void selectByWrapper2() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照 id 升序排列
	 * name like `王%` or age>=40 order by age desc id asc
	 * */
//	@Test 
	public void selectByWrapper3() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name", "王").or().ge("age",25).orderByDesc("age")
		              .orderByDesc("id");
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	
	}
	
	
	/*
	 * 4、创建日期为 2019年2月14日 并且质数上级为名字为王姓
	 * date_format(create_time,'%Y-%m-%d') and manager_id
	 *  in (select id from user where name like '王%' )
	 * */
//	@Test
	public void selectByWrapper4() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14 " )
//		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={2019-02-14" ) //当前行与上一行效果是一样的，但是当前行存在SQL注入的风险，如下列行
//		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')=2019-02-14 or true or true" )  // 当前行存在SQL 注入的风险，在日期时间末尾添加了or true or true 后会见所有的结果都查询出来，会让用户看到不该看到的东西
		 .inSql("manager_id", "select id from mp_user where name like '王%'");
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 5、名字为王姓并且 (年龄小于40或邮箱不为空）
	 * name like '王%'  and (age<40 or email is not null)
	 * */
//	@Test 
	public void selectByWrapper5() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name", "王").and(wq->wq.lt("age",40).or().isNotNull("email"));
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	
	/*
	 * 6、名字为王姓或者（年龄小于40 并且年龄大于20 并且 邮箱不为空）
	 * name like'王%' or （age <40 and age>20 and email is notNull)
	 * */
//	@Test
	public void selectByWrapper6() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name", "王").or(wq->wq.lt("age", 40).gt("age",20)
				.isNotNull("email"));
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	
	
	
	/*
	 * 7、(年龄小于40或邮箱不为空) 并且名字为王姓
	 * (age<40 or email is not null) and name like '王%'
	 * */
//	@Test 
	public void selectByWrapper7() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email")
		.likeRight("name", "王"));
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 8、年龄为 30、31、34、35
	 * age in (30、31、34、35)
	 * */
//	@Test
	public void selectByWrapper8() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.in("age", Arrays.asList(30,31,34,35)).last("limit 1");
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * select 不列出全部字段 
	 * */
	
	/*
	 * 1、名字中包含雨并且年龄小于40
	 *  name like '%雨%' and age<40
	 * */
//	@Test 
	public void selectByWrapperSupper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40)
			.select(User.class,info->!info.getColumn().equals("create_time")&&
					!info.getColumn().equals("manager_id"));
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
//	@Test
	public void testCondition() {
		String name = "王";
		String email = "";
		condition(name,email);
	}
	
	/*
	 * condition 的作用
	 * */
	private void condition(String name,String email) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//		if(StringUtils.isNotEmpty(name)) {
//			queryWrapper.like("name", name);
//		}
//		if(StringUtils.isNotEmpty(email)) {
//			queryWrapper.like("email", email);
//		}
		queryWrapper.like(StringUtils.isNotEmpty(name), "name",name)
		  .like(StringUtils.isNotEmpty(email), "email",email);
		
		List<User> userList = userMapper.selectList(queryWrapper);
	    userList.forEach(System.out::println);
	}
	
	/*
	 * 实体作为条件构造器构造方法的参数
	 * */
	@Test
	public void selectByWrapperEntity() {
		User whereUser = new User();
		whereUser.setName("刘红雨");
		whereUser.setAge(32);
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(whereUser);
//		queryWrapper.like("name", "雨").lt("age",40);
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * AllEq用法
	 * */
	@Test
	public void selectByWrapperAllEq() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", "王天风");
		params.put("age", 25);
		queryWrapper.allEq(params);
		
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 其他使用条件构造器的方法
	 * */
	@Test
	public void selectByWrapperMaps() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name","雨").lt("age",40);
		
		List<Map<String,Object>> userList = userMapper.selectMaps(queryWrapper);
		userList.forEach(System.out::println);
				
	}
	
	/*
	 * 11、按照直属上级分组，查询每组的平均年龄，最大年龄、最小年龄
	 * 并且只取年龄综合小于500的组
	 * 
	 * select avg(age) avg_age,min(age) min_age,max(age) max_age
	 * from user
	 * group by manager_id
	 * having sum(age)<500
	 * */
	@Test
	public void selectByWrapperMaps2() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
		      .groupBy("manager_id").having("sum(age)<{0}",500);
		
		List<Map<String,Object>> userList = userMapper.selectMaps(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	@Test 
	public void selectByWrapperObjs() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
		
		List<Object> userList = userMapper.selectObjs(queryWrapper);
		userList.forEach(System.out::println);
	}
	
	@Test
	public void selectByWrapperCount() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").lt("age", 40);
		
		Integer count = userMapper.selectCount(queryWrapper);
		System.out.println("总记录数" + count);
		
	}
	
	@Test 
	public void selectByWrapperOne() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "刘红雨").lt("age", 40);
		
		User user = userMapper.selectOne(queryWrapper);
		System.out.println(user);
	}
	
	
	/*
	 * 3-11  lambda 条件构造器
	 * */
	@Test
	public void selectLambda() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.like(User::getName, "雨").lt(User::getAge, 40);
		//where name like '%雨'
		List<User> userList = userMapper.selectList(lambdaQuery);
		userList.forEach(System.out::println);
				
	}
	
	/*
	 * 5、名字为王姓并且(年龄小于40 或邮箱不为空)
	 *  name like '王%' and (age<40 or email is not null)
	 *  
	 * */
	@Test
	public void selectLambda2() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王")
			.and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
		List<User> userList = userMapper.selectList(lambdaQuery);
		userList.forEach(System.out::println);
	}
	
	/*
	 * 4-1 自定义sql
	 * */
	@Test
	public void selectMy() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王")
		 	.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		List<User> userList = userMapper.selectAll(lambdaQuery);
		userList.forEach(System.out::println);
	}
	
	@Test
	public void selectPage() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.ge("age",22);
		
		Page<User> page = new Page<User>(1,3);
		
		IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
		System.out.println("总页数"+iPage.getPages());
		System.out.println("总记录数" + iPage.getTotal());
		List<User> userList = iPage.getRecords();
		
		userList.forEach(System.out::println);
	}
	
	@Test
	public void selectPage2() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.ge("age",26);
		
		Page<User> page = new Page<User>(1,2);
		
		IPage<Map<String,Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
		System.out.println("总页数"+iPage.getPages());
		System.out.println("总记录数" + iPage.getTotal());
		List<Map<String,Object>> userList = iPage.getRecords();
		
		userList.forEach(System.out::println);
	}
	
	@Test
	public void selectMyPage3() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.ge("age",26);
		
		Page<User> page = new Page<User>(1,2);
		
		IPage<User> iPage = userMapper.selectUserPage(page, queryWrapper);
		System.out.println("总页数"+iPage.getPages());
		System.out.println("总记录数" + iPage.getTotal());
		List<User> userList = iPage.getRecords();
		
		userList.forEach(System.out::println);
	}
	
	
	
	
	
	
	
	
}
