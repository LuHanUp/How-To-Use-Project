package top.luhancc.springmvc.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 钝化与活化的监听器HttpSessionActivationListener
 *
 * <pre>{@code
 *  <Context>
 *   <!-- maxIdleSwap:session中的对象多长时间不使用就钝化 -->
 *   <!-- directory:钝化后的对象的文件写到磁盘的哪个目录下  配置钝化的对象文件在                                                                       work/catalina/localhost/钝化文件 -->
 *   <Manager className="org.apache.catalina.session.PersistentManager"                                                                                                                      maxIdleSwap="1">
 *    <Store className="org.apache.catalina.session.FileStore" directory="igeekhome" />
 *   </Manager>
 *  </Context>
 * }
 * </pre>
 *
 * @author luHan
 * @create 2021/5/11 17:26
 * @since 1.0.0
 */
public class MyHttpSessionActivationListener implements HttpSessionActivationListener {

    /**
     * 当HttpSession中的对象被钝化时会被触发
     *
     * @param se
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("对象被钝化...");
    }

    /**
     * 当HttpSession的对象被活化时会被触发
     *
     * @param se
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("对象被活化...");
    }
}
