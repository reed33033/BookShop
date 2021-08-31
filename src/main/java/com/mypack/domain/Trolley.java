package com.mypack.domain;


/**
 * 购物车类
 */
public class Trolley {
	/*
 	tid INT PRIMARY KEY AUTO_INCREMENT,#主键
	uid INT,#关联用户的id
	gid INT,#关联商品的id
	number INT,#绑定商品的数量
	orders_number VARCHAR(100) DEFAULT NULL#订单编号
 */
	private Integer tid;//主键
	
	private Integer uid;//用户id
	private User user;//虚拟对象，这个属性不需要存储数据库，只是为了显示数据
	
	private Integer bid;//商品id
	private Books books;//虚拟对象，这个属性不需要存储数据库，只是为了显示数据
	
	private Integer number;//商品数量
	private String orders_number;//订单编号
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getOrders_number() {
		return orders_number;
	}
	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}
	
	public Trolley(Integer uid, Integer bid) {
		super();
		this.uid = uid;
		this.bid = bid;
	}
	public Trolley() {
		
	}
	@Override
	public String toString() {
		return "Trolley [tid=" + tid + ", uid=" + uid + ", user=" + user + ", bid=" + bid + ", books=" + books
				+ ", number=" + number + ", orders_number=" + orders_number + "]";
	}
	
}











