package com.hql.bean;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class myRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(myRealm.class);

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=principalCollection.getPrimaryPrincipal().toString();

        log.info(username);
        if(username.equals("admin")){
            Set<String> roles=new HashSet<>();
            roles.add("root");
            return new SimpleAuthorizationInfo(roles);
        }
        return null;
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username=authenticationToken.getPrincipal().toString();
        log.info(username);
        if(username.equals("admin")){
             return new SimpleAuthenticationInfo(username,"123","myRealm");
        }
        return null;
    }
}
