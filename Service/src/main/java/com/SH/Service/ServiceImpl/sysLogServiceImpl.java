package com.SH.Service.ServiceImpl;

import com.SH.Dao.IsysLogDao;
import com.SH.Domain.SysLog;
import com.SH.Service.Iservice.IsysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class sysLogServiceImpl implements IsysLogService {

    @Autowired
    private IsysLogDao sysLogDao;

    @Override
    public boolean insertOne(SysLog sysLog) throws Exception {

        return sysLogDao.insertOne(sysLog);
    }

    @Override
    public List<SysLog> selectAll() throws Exception {
        return sysLogDao.selectAll();
    }
}
