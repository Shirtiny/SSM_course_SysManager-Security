package com.SH.Dao;

import com.SH.Domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IproductDao {

     @Select("select * from product")
     List<Product> findAll() throws Exception;


     @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
     void insertOne(Product product) throws  Exception;

     @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
     void updateOne(Product product) throws  Exception;

     @Delete("delete from product where productNum=#{productNum}")
     void deleteOne(String productNum) throws Exception;

     @Select("select * from product where id=#{id}")
     Product selectByid(String id);
}
