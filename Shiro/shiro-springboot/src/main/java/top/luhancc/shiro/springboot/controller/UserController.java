package top.luhancc.shiro.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.luhancc.shiro.springboot.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //添加
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String add() {
        return "添加用户成功";
    }

    //查询
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String find() {
        return "查询用户成功";
    }

    //更新
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String update(String id) {
        return "更新用户成功";
    }

    //删除
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete() {
        return "删除用户成功";
    }

    //用户登录
    @RequestMapping(value = "/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        /**
         * MD5加密方式
         *
         * Object source:原始密码
         * Object salt:加密盐
         * int hashIterations:加密次数
         */
        Md5Hash md5Hash = new Md5Hash(password, username + "abcd", 3);
        try {
            token.setPassword(md5Hash.toString().toCharArray());
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            return "登录失败";
        }
    }

    @RequestMapping("/authError")
    public String authError() {
        return "授权未通过";
    }
}
