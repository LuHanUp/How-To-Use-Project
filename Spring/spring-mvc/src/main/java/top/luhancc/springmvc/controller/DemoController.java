package top.luhancc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author luHan
 * @create 2021/5/11 10:38
 * @since 1.0.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/handle01")
    public ModelAndView handle01() {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();// 封装了页面和数据的Model
        modelAndView.addObject("date", date);// 向请求域中放入一数据
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
