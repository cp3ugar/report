package indi.xk.report.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zxy on 2020/1/2.
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("SecurityManager") SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置不会被拦截的链接 顺序判断
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/img/*", "anon");
        filterChainDefinitionMap.put("/js/*", "anon");
        filterChainDefinitionMap.put("/layui/*", "anon");
        filterChainDefinitionMap.put("/toListStudent", "anon");
        filterChainDefinitionMap.put("/views/jsp/*", "anon");
        filterChainDefinitionMap.put("/404", "anon");
        filterChainDefinitionMap.put("/403", "anon");
        filterChainDefinitionMap.put("/500", "anon");
        filterChainDefinitionMap.put("/cats/**", "anon");

        filterChainDefinitionMap.put("/toLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/testThymeleaf", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "MyShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean(name ="SecurityManager" )
    public SecurityManager securityManager(@Qualifier("MyShiroRealm")MyShiroRealm myShiroRealm){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    /**
     * 开启aop注解支持
     * 即在controller中使用 @RequiresPermissions("user/userList")
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        //设置安全管理器
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
