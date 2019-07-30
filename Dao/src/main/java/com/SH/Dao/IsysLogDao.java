package com.SH.Dao;

import com.SH.Domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface IsysLogDao {


    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    boolean insertOne(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    @Results({

            @Result(id = true,property = "id",column = "id"),
            @Result(property = "visitTime",column = "visitTime",javaType = Date.class,jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "username",column = "username"),
            @Result(property = "ip",column = "ip"),
            @Result(property = "url",column = "url"),
            @Result(property = "executionTime",column = "executionTime",javaType = long.class,jdbcType = JdbcType.INTEGER),
            @Result(property = "method",column = "method")

    })
    List<SysLog> selectAll() throws Exception;

}
