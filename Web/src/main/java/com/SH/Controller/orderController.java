package com.SH.Controller;

import com.SH.Domain.Orders;
import com.SH.Service.Iservice.IorderService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/orderController")
public class orderController {

    @Autowired
    private IorderService orderService;

    @RequestMapping(value = "/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = orderService.selectAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping(value = "findById")
    public ModelAndView findById(String orderId) throws  Exception{
        ModelAndView mv=new ModelAndView();
        Orders order = orderService.selectById(orderId);

        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
