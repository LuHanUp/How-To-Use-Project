package top.luhancc.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义SpringMvc拦截器
 * <p>
 * 注册方式:
 * <pre>{@code
 *    <!--注册自定义拦截器-->
 *     <mvc:interceptors>
 *         <mvc:interceptor>
 *             <!--表示拦截的请求规则-->
 *             <mvc:mapping path="/**"/>
 *             <!--表示不拦截请求的规则-->
 *             <!--<mvc:exclude-mapping path="/demo/**"/>-->
 *             <bean class="top.luhancc.springmvc.interceptor.MyInterceptor01"/>
 *         </mvc:interceptor>
 *     </mvc:interceptors>
 * }
 * </pre>
 *
 * @author luHan
 * @create 2021/5/11 16:47
 * @since 1.0.0
 */
public class MyInterceptor01 implements HandlerInterceptor {

    /**
     * 会在handler方法执行之前被触发
     *
     * @param request
     * @param response
     * @param handler
     * @return 返回值表示否是放行，true表示放行 false表示中止handle方法的执行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor01 preHandle...");
        return true;
    }

    /**
     * 会在handler方法业务逻辑执行完视图解析之前被触发
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView 封装了视图和数据，此时尚未跳转页面，可以在这里针对返回的数据进行修改
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor01 postHandle...");
    }

    /**
     * 会在handler方法视图解析完毕后触发
     *
     * @param request
     * @param response
     * @param handler
     * @param ex       可以在这里捕获异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor01 afterCompletion...");
    }
}
