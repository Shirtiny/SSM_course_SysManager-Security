package com.SH.Service.Iservice;

import com.SH.Domain.SysLog;

import java.util.List;

public interface IsysLogService {

    boolean insertOne(SysLog sysLog) throws Exception;

    List<SysLog> selectAll() throws Exception;

}
