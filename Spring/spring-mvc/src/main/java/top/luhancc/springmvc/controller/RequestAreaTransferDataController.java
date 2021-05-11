package top.luhancc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * 演示SpringMvc 向请求域传值的方式
 *
 * @author luHan
 * @create 2021/5/11 10:38
 * @since 1.0.0
 */
@Controller
@RequestMapping("/model")
public class RequestAreaTransferDataController {

    @RequestMapping("/returnModelAndView")
    public ModelAndView returnModelAndView() {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();// 封装了页面和数据的Model
        modelAndView.addObject("date", date);// 向请求域中放入一数据
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMvc在handler方法上传入Map、Model和ModelMap参数，并向这些参数中保存数据（放入请求域），都可以在页面获取到
     * <p>
     * 它们之间有什么关系？
     * 通过运行后打印class类型，发现具体类型都是BindingAwareModelMap，相当于给BindingAwareModelMap中保存的数据都会放到请求域中
     * <p>
     * Map(jdk中的接口)        Model(spring的接口)
     * ModelMap(class,实现Map接口)
     * <p>
     * BindingAwareModelMap继承了ExtendedModelMap，ExtendedModelMap继承了ModelMap,实现了Model接口
     */

    @RequestMapping("/useModelMap")
    public String useModelMap(ModelMap modelMap) {
        Date date = new Date();
        modelMap.addAttribute("date", date);// 向请求域中放入一数据
        System.out.println("ModelMap:" + modelMap +
                ",class=" + modelMap.getClass() + // org.springframework.validation.support.BindingAwareModelMap
                ",hashCode=" + modelMap.hashCode() + // hashCode=1507176310
                ",identity=" + System.identityHashCode(modelMap));// identity=1853335318
        return "success";
    }

    @RequestMapping("/useModel")
    public String useModel(Model model) {
        Date date = new Date();
        model.addAttribute("date", date);// 向请求域中放入一数据
        System.out.println("Model:" + model +
                ",class=" + model.getClass() + // org.springframework.validation.support.BindingAwareModelMap
                ",hashCode=" + model.hashCode() + // hashCode=1507066403
                ",identity=" + System.identityHashCode(model)); // identity=1772886197
        return "success";
    }

    @RequestMapping("/useMap")
    public String useMap(Map<String, Object> map) {
        Date date = new Date();
        map.put("date", date);// 向请求域中放入一数据
        System.out.println("Map:" + map +
                ",class=" + map.getClass() + // org.springframework.validation.support.BindingAwareModelMap
                ",hashCode=" + map.hashCode() + //hashCode=1507112315
                ",identity=" + System.identityHashCode(map));// identity=106444286
        return "success";
    }
}
