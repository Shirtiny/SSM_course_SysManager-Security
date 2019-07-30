package com.SH.Service.Iservice;

import com.SH.Domain.Product;

import java.util.List;

public interface IproductService {
     //查询全部商品
     List<Product> findAll() throws Exception;
     //增加商品
     void insertOne(Product product) throws  Exception;
     //删除商品
     void deleteOne(String productNum) throws Exception;

     Product selectByid(String id);

     void updateOne(Product product) throws  Exception;
}
