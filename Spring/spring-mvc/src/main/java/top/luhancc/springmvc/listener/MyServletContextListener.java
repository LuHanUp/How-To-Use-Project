package top.luhancc.springmvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听ServletContext域的创建与销毁的监听器ServletContextListener
 * <p>
 * ServletContextListener监听器的主要作用
 * 初始化的工作：初始化对象 初始化数据 ---- 加载数据库驱动  连接池的初始化
 *
 * @author luHan
 * @create 2021/5/11 17:04
 * @since 1.0.0
 */
public class MyServletContextListener implements ServletContextListener {

    /**
     * 当ServletContext被创建时触发
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext被创建了...");
    }

    /**
     * 当ServletContext被销毁时触发
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext被销毁了...");
    }
}
