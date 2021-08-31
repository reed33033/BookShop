package com.mypack.domain;
/**
 * 图书分类类
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 	cid INT PRIMARY KEY AUTO_INCREMENT,
	cname VARCHAR(30) NOT NULL,
	state INT DEFAULT 1,
	order_number INT NOT NULL,
	description VARCHAR(100) NOT NULL,
	create_time DATETIME
 */
public class Category {
	private Integer cid;//主键
	private String cname;//分类名称
	private Integer state;//启用状态，默认为1启用	0未启用
	private Integer order_number;//排序序号，应用在前台首页面的分类导航中
	private String description;//分类描述
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;//分类添加日期
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrder_number() {
		return order_number;
	}
	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Category(Integer cid, String cname, Integer state, Integer order_number, String description,
                    Date create_time) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.state = state;
		this.order_number = order_number;
		this.description = description;
		this.create_time = create_time;
	}
	public Category(String cname, Integer state, Integer order_number, String description, Date create_time) {
		super();
		this.cname = cname;
		this.state = state;
		this.order_number = order_number;
		this.description = description;
		this.create_time = create_time;
	}
	public Category() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", state=" + state + ", order_number=" + order_number
				+ ", description=" + description + ", create_time=" + create_time + "]";
	}
	
}
