package com.SH.Service.Iservice;

import com.SH.Domain.Orders;

import java.util.List;

public interface IorderService {

    List<Orders> selectAll() throws  Exception;

    Orders selectById(String orderId) throws  Exception;

}
