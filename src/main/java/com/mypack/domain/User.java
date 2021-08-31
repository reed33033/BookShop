package com.mypack.domain;
/**
 * 用户类
 */

import java.util.Date;

/*
 	`uid` INT PRIMARY KEY AUTO_INCREMENT,
	`uname` VARCHAR(100) NOT NULL,
	`gender` INT(1),
	`phone` VARCHAR(20) UNIQUE,
	`area` VARCHAR(100) NOT NULL,
	`manager` INT(1) DEFAULT 1,
	`username` VARCHAR(50) UNIQUE,
	`password` VARCHAR(50) NOT NULL,
	`photo` VARCHAR(100),
	`create_time` DATETIME
 */
public class User {
	private Integer uid;//主键
	private String uname;//姓名
	private Integer gender;//性别
	private String phone;//电话
	private String area;//地区
	private Integer manager;//用户身份	默认为1代表普通用户	0代表管理员，管理员才可以登录后台
	private String username;//用户名
	private String password;//密码
	private String photo;//头像
	private Date create_time;//注册时间
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public User(Integer uid, String uname, Integer gender, String phone, String area, Integer manager, String username,
                String password, String photo, Date create_time) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.gender = gender;
		this.phone = phone;
		this.area = area;
		this.manager = manager;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	public User(String uname, Integer gender, String phone, String area, String username, String password, String photo,
                Date create_time) {
		super();
		this.uname = uname;
		this.gender = gender;
		this.phone = phone;
		this.area = area;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(Integer uid, Integer manager) {
		super();
		this.uid = uid;
		this.manager = manager;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", gender=" + gender + ", phone=" + phone + ", area=" + area
				+ ", manager=" + manager + ", username=" + username + ", password=" + password + ", photo=" + photo
				+ ", create_time=" + create_time + "]";
	}
	
}





















