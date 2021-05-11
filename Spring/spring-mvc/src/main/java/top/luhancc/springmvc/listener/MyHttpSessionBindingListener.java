package top.luhancc.springmvc.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 绑定与解绑的监听器HttpSessionBindingListener
 *
 * @author luHan
 * @create 2021/5/11 17:24
 * @since 1.0.0
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {

    /**
     * 当一个对象被绑定到HttpSession中时会被触发
     *
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("将对象绑定到HttpSession:" + event.getValue());
    }

    /**
     * 当对象从HttpSession中解绑时会被触发
     *
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("对象从HttpSession解绑:" + event.getValue());
    }
}
