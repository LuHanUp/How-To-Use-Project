package top.luhancc.shiro.springboot.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.luhancc.shiro.springboot.realm.AuthRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 *
 * @author luHan
 * @create 2021/5/17 22:54
 * @since 1.0.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public AuthRealm authRealm() {
        return new AuthRealm();
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm) {
        return new DefaultWebSecurityManager(authRealm);
    }

    /**
     * 在web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/authError");// 跳转登录页面的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/authError");// 跳转到未授权的url
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/home", "anon");// /user/home这个请求可以匿名访问
        /**
         * 通过过滤器的形式指定请求地址的授权
         */
//        filterMap.put("/user/home", "perms[权限唯一code,...]");// 表示需要用户拥有权限才能访问/user/home这个路径
//        filterMap.put("/user/home", "roles[角色唯一code,...]");// 表示用户拥有角色才能访问/user/home这个路径
        filterMap.put("/user/**", "authc");// 满足请求地址的需要认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro注解授权支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
