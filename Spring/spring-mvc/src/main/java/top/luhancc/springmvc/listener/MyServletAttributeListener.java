package top.luhancc.springmvc.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 当Servlet中三大域中的属性发生变化的监听器
 *
 * @author luHan
 * @create 2021/5/11 17:14
 * @since 1.0.0
 */
public class MyServletAttributeListener implements ServletContextAttributeListener,
        HttpSessionAttributeListener, ServletRequestAttributeListener {

    // ===========================ServletContext属性变化的相关监听方法===========================================

    /**
     * 向ServletContext中添加属性时会被触发
     *
     * @param scab
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent scab) {
        System.out.println("向ServletContext添加属性:属性名:" + scab.getName() + ",属性值:" + scab.getValue());
    }

    /**
     * 从ServletContext移除属性时会被触发
     *
     * @param scab
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scab) {
        System.out.println("从ServletContext移除属性:属性名:" + scab.getName() + ",属性值:" + scab.getValue());
    }

    /**
     * 替换ServletContext原有的属性时会被触发
     *
     * @param scab
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        System.out.println("替换ServletContext原有属性:属性名:" + scab.getName() + ",属性值:" + scab.getValue());
    }

    // ===========================HttpSession属性变化的相关监听方法===========================================

    /**
     * 向HttpSession中添加属性时会被触发
     *
     * @param se
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("向HttpSession中添加属性:属性名:" + se.getName() + ",属性值:" + se.getValue());
    }

    /**
     * 向HttpSession中移除属性时会被触发
     *
     * @param se
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("从HttpSession中移除属性:属性名:" + se.getName() + ",属性值:" + se.getValue());
    }

    /**
     * 替换HttpSession中原有属性时会被触发
     *
     * @param se
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("替换HttpSession原有属性:属性名:" + se.getName() + ",属性值:" + se.getValue());
    }

    // ===========================ServletRequest属性变化的相关监听方法===========================================

    /**
     * 向ServletRequest中添加属性时会被触发
     *
     * @param srae
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("向ServletRequest中添加属性:属性名:" + srae.getName() + ",属性值:" + srae.getValue());
    }

    /**
     * 从ServletRequest中移除属性时会被触发
     *
     * @param srae
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("从ServletRequest中移除属性:属性名:" + srae.getName() + ",属性值:" + srae.getValue());
    }

    /**
     * 替换ServletRequest中原有属性时会被触发
     *
     * @param srae
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("替换ServletRequest原有属性:属性名:" + srae.getName() + ",属性值:" + srae.getValue());
    }
}
