package com.SH.Controller;


import com.SH.Domain.Product;
import com.SH.Service.Iservice.IproductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/productController")
public class productController {

    @Autowired
    private IproductService productService;

    //展示全部商品
    @RequestMapping(value = "/findAll")
    public String findAll(Map<String,Object> map) throws Exception {//aop前后通知里将Map获取为BindingAwareModelMap，会报异常，可将Map换为BindingAwareModelMap，暂时解决
        List<Product> productList = productService.findAll();
        map.put("productList",productList);
        return "product-list";
    }

    @RequestMapping("/findAll2")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        return mv;

    }

    @RequestMapping(value = "/saveOne")
    public String saveOne(Product product) throws Exception{
        productService.insertOne(product);
        System.out.println(product);
        return "redirect:selectByPage.action";
    }

    @RequestMapping("/selectByid")
    public ModelAndView selectByid(String id) throws Exception{
        Product product = productService.selectByid(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("product",product);
        mv.setViewName("ok");
        return mv;
    }

    @RequestMapping("/updateOne")
    public ModelAndView updateOne(String id,Product product){
        Product oneProduct = productService.selectByid(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("product",oneProduct);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/updateByid")
    public String updateByid(Product product) throws Exception {

        productService.updateOne(product);

        return "redirect:selectByPage.action";
    }

    @RequestMapping("/selectByPage")
    public ModelAndView selectByPage(@RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                     @RequestParam(name="pageSize",required = true,defaultValue = "3") int pageSize) throws Exception {

        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productService.findAll();
        PageInfo pageInfo=new PageInfo<>(productList);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;

    }
}
