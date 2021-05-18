package top.luhancc.shiro.springboot.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义实现session manager实现将session会话通过redis来进行管理
 *
 * @author luHan
 * @create 2021/5/18 11:40
 * @since 1.0.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 获取sessionId
     * <p>
     * 从请求头中获取sessionId
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader("Authorization");
        if (StringUtils.isEmpty(sessionId)) {
            // 如果请求头中没有携带Authorization请求头,那么就返回父类层的sessionId
            return super.getSessionId(request, response);
        } else {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        }
        return sessionId;
    }
}
