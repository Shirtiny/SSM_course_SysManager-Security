package com.SH.Domain;

import com.SH.ConvertUtils.DateAndString;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 商品类
 * */

public class Product {
    private String id;//主键
    private String productNum;//商品编号
    private String productName;//商品名
    private String cityName;//出发城市
//    @JsonFormat(pattern = "yyyy-mm-dd HH:mm",timezone = "GMT+8")
    private Date departureTime;//出发时间
    private String departureTimeStr;//出发时间的字符串，不在数据库中
    private double productPrice;//商品价格
    private String productDesc;//商品描述
    private Integer productStatus;//值0为关闭状态，值1为打开状态
    private String productStatusStr;//商品状态的字符串，不在数据库中

    public Product() {

    }

    public Product(String id, String productNum, String productName, String cityName, Date departureTime, String departureTimeStr, double productPrice, String productDesc, Integer productStatus, String productStatusStr) {
        this.id = id;
        this.productNum = productNum;
        this.productName = productName;
        this.cityName = cityName;
        this.departureTime = departureTime;
        this.departureTimeStr = departureTimeStr;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productStatus = productStatus;
        this.productStatusStr = productStatusStr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
           if (departureTime!=null){
               String pattern="yyyy-MM-dd HH:mm";
               departureTimeStr = DateAndString.DateToString(departureTime, pattern);
           }
            return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus!=null){
            if (productStatus==1){
                productStatusStr= "开启";
            }else if (productStatus==0){
                productStatusStr= "关闭";
            }
        }
            return productStatusStr;

    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }
}
