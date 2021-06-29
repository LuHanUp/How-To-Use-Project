package top.luhancc.springmvc.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 钝化与活化的监听器HttpSessionActivationListener
 * <p>
 * 钝化：就是对象进行序列化
 * 活化：就是反序列化为对象
 * <p>
 * session钝化配置:META-INF中加入一个名为context.xml的配置文件
 * <pre>{@code
 *  <Context>
 *   <!-- maxIdleSwap:session中的对象多长时间不使用就钝化 -->
 *   <!-- directory:钝化后的对象的文件写到磁盘的哪个目录下配置钝化的对象文件在work/catalina/localhost/钝化文件 -->
 *   <Manager className="org.apache.catalina.session.PersistentManager"  maxIdleSwap="1">
 *    <Store className="org.apache.catalina.session.FileStore" directory="" />
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
     * 将HttpSession序列化
     *
     * @param se
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("对象被钝化...");
    }

    /**
     * 反序列化HttpSession
     *
     * @param se
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("对象被活化...");
    }
}
