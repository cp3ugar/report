package indi.xk.report.utils;

import indi.xk.report.pojo.SysPermission;
import indi.xk.report.pojo.SysRole;
import indi.xk.report.pojo.User;
import indi.xk.report.pojo.User1;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxy on 2020/1/2.
 */
public class MyShiroRealm extends AuthorizingRealm {

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        //获取当前登录用户
        User1 user= (User1) principals.getPrimaryPrincipal();
        //通过SimpleAuthorizationInfo做授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        authorizationInfo.addRole(user.getRole());
        //添加权限
        authorizationInfo.addStringPermissions(user.getPermissions());
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //1.获取用户输入的账号
        System.out.println("fenpei ziyuan");
        String username = (String)token.getPrincipal();
        //2.通过username从数据库中查找到user实体
        User1 user = getUserByUserName(username);
        if(user == null){
            return null;
        }
        //3.通过SimpleAuthenticationInfo做身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        //4.用户账号状态验证等其他业务操作
        if(!user.getAvailable()){
            throw new AuthenticationException("该账号已经被禁用");
        }
        //5.返回身份处理对象
        return simpleAuthenticationInfo;

    }
    /**
     * 模拟通过username从数据库中查找到user实体
     * @param username
     * @return
     */
    private User1 getUserByUserName(String username){
        List<User1> users1 = getUsers();
        for(User1 user : users1){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    /**
     * 模拟数据库数据
     * @return
     */
    private List<User1> getUsers(){
        List<User1> users = new ArrayList<>(2);
        List<String> cat = new ArrayList<>(2);
        cat.add("sing");
        cat.add("rap");
        List<String> dog = new ArrayList<>(2);
        dog.add("jump");
        dog.add("basketball");
        users.add(new User1("许可弟弟","123",true,"cat",cat));
        users.add(new User1("孤儿","123",true,"dog",dog));
        return users;
    }

}
