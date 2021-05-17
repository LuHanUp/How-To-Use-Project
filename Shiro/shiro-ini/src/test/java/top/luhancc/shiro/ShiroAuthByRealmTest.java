package top.luhancc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试通过realm进行认证和授权
 *
 * @author luHan
 * @create 2021/5/17 21:23
 * @since 1.0.0
 */
public class ShiroAuthByRealmTest {
    SecurityManager securityManager;

    @Before
    public void init() {
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-auth2.ini");
        securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);// 将SecurityManager绑定到当前运行环境
    }

    @Test
    public void testAuth() {
        Subject subject = SecurityUtils.getSubject();
        // 构造shiro登录数据
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        // 进行登录
        subject.login(usernamePasswordToken);
        System.out.println("是否拥有role1角色:" + subject.hasRole("role1"));
        System.out.println("是否拥有user:find权限:" + subject.isPermitted("user:find"));
        System.out.println("是否拥有user:aaaaa权限:" + subject.isPermitted("user:aaaaa"));
    }
}
