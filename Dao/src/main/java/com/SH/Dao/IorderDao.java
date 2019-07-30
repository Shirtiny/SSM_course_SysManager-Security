package com.SH.Dao;

import com.SH.Domain.Member;
import com.SH.Domain.Orders;
import com.SH.Domain.Product;
import com.SH.Domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IorderDao {

    @Select("select * from orders")
    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(property = "orderTime",column = "orderTime",javaType = Date.class,jdbcType =JdbcType.TIMESTAMP),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.SH.Dao.IproductDao.selectByid")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.SH.Dao.ItravellerDao.selectByid")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.SH.Dao.ImemberDao.selectById")),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc")
    })
    List<Orders> selectAll() throws  Exception;


    //查询一个订单的具体信息
    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(property = "orderTime",column = "orderTime",javaType = Date.class,jdbcType =JdbcType.TIMESTAMP),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.SH.Dao.IproductDao.selectByid")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.SH.Dao.ItravellerDao.selectByid")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.SH.Dao.ImemberDao.selectById")),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc")
    })
    Orders selectById(String orderId) throws  Exception;


}
