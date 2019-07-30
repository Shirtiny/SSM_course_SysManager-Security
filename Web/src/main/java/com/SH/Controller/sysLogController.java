package com.SH.Controller;

import com.SH.Domain.SysLog;
import com.SH.Service.Iservice.IsysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysLogController")
public class sysLogController {

    @Autowired
    private IsysLogService sysLogService;

    @RequestMapping("/findAll")
    public String findAll(BindingAwareModelMap map) throws Exception {
        List<SysLog> sysLogs = sysLogService.selectAll();
        map.put("sysLogs",sysLogs);
        return "syslog-list";
    }

    @RequestMapping("/selectBypage")
    public ModelAndView selectBypage(@RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize) throws Exception {

        PageHelper.startPage(pageNum,pageSize);
        List<SysLog> sysLogList = sysLogService.selectAll();
        PageInfo pageInfo=new PageInfo<>(sysLogList);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("syslog-list");
        mv.addObject("pageInfo",pageInfo);

        return mv;
    }

}
