package top.luhancc.springmvc.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听HttpSession域的创建于销毁的监听器HttpSessionListener
 * <p>类描述</p>
 *
 * @author luHan
 * @create 2021/5/11 17:07
 * @since 1.0.0
 */
public class MyHttpSessionListener implements HttpSessionListener {

    /**
     * 当HttpSession被创建时触发
     *
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSession被创建了....");
    }

    /**
     * 当HttpSession销毁时触发
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSession被销毁了....");
    }
}
