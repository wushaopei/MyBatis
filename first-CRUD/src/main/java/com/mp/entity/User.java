package com.mp.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("mp_user")
@EqualsAndHashCode(callSuper=false)
public class User extends Model<User>{	

	private static final long serialVersionUID = 1L;
	//主键
	@TableId //主键策略
	private Long id;
    //用户名
	@TableField("name")
	private String name;
//	private String username;
	//年龄
	@TableField(condition="%s&lt;#{%s}")
	private Integer age;
	//邮箱
	private String email;
	//管理id
	private Long managerId;
	//创建时间
	private LocalDateTime createTime;
//	//备注
//	private transient String Remark;
	@TableField(exist=false)
	private transient String Remark;
	//批注
	private static String Postil;
	
	public static void setPostil(String Postil) {
		Postil = Postil;
	}
	
	public static String getPostil() {
		return Postil;
	}
	
	
}
