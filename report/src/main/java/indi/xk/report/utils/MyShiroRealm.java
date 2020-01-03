package indi.xk.report.utils;

import indi.xk.report.pojo.SysPermission;
import indi.xk.report.pojo.SysRole;
import indi.xk.report.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by zxy on 2020/1/2.
 */
public class MyShiroRealm extends AuthorizingRealm {

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user  = (User)principals.getPrimaryPrincipal();
        System.out.println("执行授权逻辑shouquanlem");
//        for(SysRole role:user.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return null;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("执行认证逻辑renzhenglema");
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String name="admin";
        String password="123456";
        UsernamePasswordToken token1=(UsernamePasswordToken)token;
        if(!token1.getUsername().equals(name)){
            return null;
        }


        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

        return new SimpleAuthenticationInfo("",password,"");
    }

}
