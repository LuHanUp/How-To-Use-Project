package top.luhancc.shiro.springboot.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.luhancc.shiro.springboot.dao.UserDao;
import top.luhancc.shiro.springboot.domain.Permission;
import top.luhancc.shiro.springboot.domain.Role;
import top.luhancc.shiro.springboot.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luHan
 * @create 2021/5/17 22:03
 * @since 1.0.0
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;

    @Override
    public void setName(String name) {
        super.setName("authRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        User user = userDao.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return new SimpleAuthenticationInfo(user, password, getName());
        }
        throw new RuntimeException("用户名或密码错误");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<Role> roles = user.getRoles();
        Set<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
        Set<String> permIds = roles.stream().flatMap(role -> role.getPermissions().stream()).map(Permission::getId).collect(Collectors.toSet());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roleIds);
        simpleAuthorizationInfo.setStringPermissions(permIds);
        return simpleAuthorizationInfo;
    }

}
