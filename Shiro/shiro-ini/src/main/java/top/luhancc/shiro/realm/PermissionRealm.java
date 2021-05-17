package top.luhancc.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义shiro realm
 *
 * @author luHan
 * @create 2021/5/17 21:08
 * @since 1.0.0
 */
public class PermissionRealm extends AuthorizingRealm {

    /**
     * 定义realm名称
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("permissionRealm");
    }

    /**
     * 认证
     * <p>
     * 主要目的是比较用户名和密码是否与数据库中的一致。
     * 之后将数据存入shiro后由shiro进行保管
     *
     * @param token
     * @return AuthenticationInfo认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行doGetAuthenticationInfo认证方法");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        // 因为这里没有使用数据库,所以直接比较一个固定的密码,这里可以修改为比较数据库中的密码
        if ("123456".equals(password)) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
            return simpleAuthenticationInfo;
        } else {
            throw new RuntimeException("用户名或密码错误");
        }
    }

    /**
     * 授权
     * <p>
     * 主要目的就是根据认证的数据获取到用户的权限数据
     * <p>
     * 查询用户的权限数据然后返回给shiro即可
     *
     * @param principals 包含了所有已认证的数据
     * @return AuthorizationInfo授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行doGetAuthorizationInfo授权方法");
        String username = (String) principals.getPrimaryPrincipal();
        // 因为这里没有接入数据库,就模拟一下权限数据
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");
        List<String> perms = new ArrayList<>();
        perms.add("user:save");
        perms.add("user:find");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(perms);
        simpleAuthorizationInfo.addRoles(roles);
        return simpleAuthorizationInfo;
    }
}
