package com.SH.Service.ServiceImpl;

import com.SH.Domain.Product;
import com.SH.Dao.IproductDao;
import com.SH.Service.Iservice.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class productServiceImpl implements IproductService {

    @Autowired
    private IproductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {

        return productDao.findAll();
    }

    @Override
    public void insertOne(Product product) throws Exception {
        productDao.insertOne(product);
    }

    @Override
    public void deleteOne(String productNum) throws Exception {
        productDao.deleteOne(productNum);
    }

    @Override
    public Product selectByid(String id) {

        return productDao.selectByid(id);
    }

    @Override
    public void updateOne(Product product) throws Exception {
        productDao.updateOne(product);
    }
}
