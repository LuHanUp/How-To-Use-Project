package top.luhancc.shiro.springboot.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.luhancc.shiro.springboot.realm.AuthRealm;
import top.luhancc.shiro.springboot.session.CustomSessionManager;

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

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public AuthRealm authRealm() {
        return new AuthRealm();
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm, SessionManager sessionManager, CacheManager cacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(authRealm);
        // 将自定义的会话管理器注册到安全管理器中
        defaultWebSecurityManager.setSessionManager(sessionManager);
        // 将缓存管理器注册到安全管理器中
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
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

    //================================将session会话管理在redis的配置依赖项============================================
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    @Bean
    public CacheManager cacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }
    //================================将session会话管理在redis的配置依赖项============================================

}
