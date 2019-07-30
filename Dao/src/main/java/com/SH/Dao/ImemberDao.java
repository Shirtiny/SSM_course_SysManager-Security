package com.SH.Dao;

import com.SH.Domain.Member;
import org.apache.ibatis.annotations.Select;

public interface ImemberDao {

    @Select("select * from member where id=#{id}")
    Member selectById(String id);

}
