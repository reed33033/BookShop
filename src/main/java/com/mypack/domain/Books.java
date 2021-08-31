package com.mypack.domain;

import java.util.Date;

/**
 * 图书实体类
 */
public class Books {

  private Integer bid;//主键
  private Integer cid;//关联分类的外键
    /*
           此属性不需要存储数据库，只是为了保存分类数据的！
           存储当前商品对应的分类信息
       */
    private Category category;

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

  private String bname;//图书名称
  private String author;//作者
  private String publish;//出版社
  private String isbn;//ISBN
  private Integer words;//字数
  private double price;//价格
  private String description;//简介
  private String full_description;//内容描述
  private String pic;//图片
  private Integer state;//状态分类 0是正常 1是人气推荐
  private String version;//版本
  private Date product_date;//出版日期


    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    public Books() {
    }

    public Books(Integer cid, String bname, String author, String publish, String isbn, Integer words, double price, String description, String full_description, String pic, Integer state, String version, Date product_date) {
        this.cid = cid;
        this.bname = bname;
        this.author = author;
        this.publish = publish;
        this.isbn = isbn;
        this.words = words;
        this.price = price;
        this.description = description;
        this.full_description = full_description;
        this.pic = pic;
        this.state = state;
        this.version = version;
        this.product_date = product_date;
    }

    public Books(Integer bid, Integer cid, String bname, String author, String publish, String isbn, Integer words, double price, String description, String full_description, String pic, Integer state, String version, Date product_date) {
        this.bid = bid;
        this.cid = cid;
        this.bname = bname;
        this.author = author;
        this.publish = publish;
        this.isbn = isbn;
        this.words = words;
        this.price = price;
        this.description = description;
        this.full_description = full_description;
        this.pic = pic;
        this.state = state;
        this.version = version;
        this.product_date = product_date;
    }

    public void setProductDate(Date product_date) {
        this.product_date = product_date;
    }


    @Override
    public String toString() {
        return "Books{" +
                "bid=" + bid +
                ", cid=" + cid +
                ", category=" + category +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", isbn='" + isbn + '\'' +
                ", words=" + words +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", full_description='" + full_description + '\'' +
                ", pic='" + pic + '\'' +
                ", state=" + state +
                ", version='" + version + '\'' +
                ", product_date=" + product_date +
                '}';
    }
}
