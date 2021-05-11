package top.luhancc.springmvc.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 监听ServletRequest域创建与销毁的监听器ServletRequestListener
 *
 * @author luHan
 * @create 2021/5/11 17:08
 * @since 1.0.0
 */
public class MyServletRequestListener implements ServletRequestListener {

    /**
     * 当ServletRequest被创建时触发
     *
     * @param sre
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequest被创建了...");
    }

    /**
     * 当ServletRequest被销毁时触发
     *
     * @param sre
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequest被销毁了...");
    }
}
