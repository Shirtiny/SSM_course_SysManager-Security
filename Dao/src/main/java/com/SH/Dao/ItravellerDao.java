package com.SH.Dao;

import com.SH.Domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{orderId} )")
    List<Traveller> selectByid(String orderId);

}
