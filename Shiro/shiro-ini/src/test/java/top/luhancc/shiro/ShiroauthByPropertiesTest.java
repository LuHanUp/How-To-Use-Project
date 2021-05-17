package top.luhancc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试读取properties中的用户和权限信息进行认证和授权
 *
 * @author luHan
 * @create 2021/5/17 20:47
 * @since 1.0.0
 */
public class ShiroauthByPropertiesTest {
    SecurityManager securityManager;

    @Before
    public void init() {
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-auth1.ini");
        securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);// 将SecurityManager绑定到当前运行环境
    }

    /**
     * 测试用户认证
     * <p>
     * 1、根据配置文件创建SecurityManagerFactory
     * 2、通过SecurityManagerFactory获取SecurityManager
     * 3、将SecurityManager绑定到当前运行环境
     * 4、从当前运行环境构造subject
     * 5、构造shiro登录数据
     * 6、完成subject登录
     */
    @Test
    public void testLogin() {
        Subject subject = SecurityUtils.getSubject();
        // 构造shiro登录数据
        String username = "zhangsan";
        String password = "123456";
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        // 进行登录
        subject.login(usernamePasswordToken);

        // 验证用户是否登录成功
        System.out.println("用户是否登录成功:" + subject.isAuthenticated());
        System.out.println("subject数据:" + subject.getPrincipal());
    }

    @Test
    public void testAuth() {
        Subject subject = SecurityUtils.getSubject();
        // 构造shiro登录数据
        String username = "lisi";
        String password = "654321";
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        // 进行登录
        subject.login(usernamePasswordToken);

        System.out.println("是否拥有role1角色:" + subject.hasRole("role1"));
        System.out.println("是否拥有user:save权限:" + subject.isPermitted("user:save"));
    }
}
