package top.luhancc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * SpringMvc中重定向传递参数的问题
 * <pre>
 * 转发：A 找 B 借400元 B没有，偷偷的去找C借了400元给A
 *      转发url不会改变，参数不会丢失
 * 重定向：A 找 B 借400元 B没有 接着A带着400元的需求又去找C借了400元
 *      重定向url会发生改变，会导致参数丢失
 * </pre>
 *
 * @author luHan
 * @create 2021/5/12 10:40
 * @since 1.0.0
 */
@Controller
@RequestMapping("redirect")
public class RedirectParameterController {

    @RequestMapping("handle01")
    public String handle01(Integer money, RedirectAttributes redirectAttributes) {
        // 方案一: 此方案会导致不安全，参数长度也有局限性
//        redirectAttributes.addAttribute("money", money);// 与下面的return效果是一样的
//        return "redirect:redirect/handle02?money=" + money;

        // 方案二:使用flash属性，会将其暂时保存到session中，当跳转页面后会自动进行销毁
        redirectAttributes.addFlashAttribute("money", money);
        return "redirect:handle02";
    }

    @RequestMapping("handle02")
    public void handle02(@ModelAttribute("money") Integer money) {
        System.out.println("接收重定向的参数:" + money);
    }
}
