package com.SH.Service.ServiceImpl;

import com.SH.Dao.IorderDao;
import com.SH.Domain.Orders;
import com.SH.Service.Iservice.IorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class orderServiceImpl implements IorderService {

    @Autowired
    private IorderDao orderDao;
    @Override
    public List<Orders> selectAll() throws Exception {
        return orderDao.selectAll();
    }

    @Override
    public Orders selectById(String orderId) throws Exception {
        return orderDao.selectById(orderId);
    }
}
