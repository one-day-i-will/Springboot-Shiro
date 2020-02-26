package com.xian.demo.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String principal = (String) token.getPrincipal();
        HashMap<String,String> map=new HashMap<>();
        map.put("user","123");
        map.put("admin","88212f91e2e9cf36981a91b6c518af5c");
        map.put("xian","a52a055021342c79691b76ef9f2c2219");

        if(!map.containsKey(principal)){
            return null;
        }
        String password = map.get(principal);
        ByteSource bytes = ByteSource.Util.bytes(principal);
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal,password,bytes,"ShiroRealm");
        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
       String primaryPrincipal = (String) principal.getPrimaryPrincipal();

        Set<String> roles=new HashSet<>();
        if("admin".equals(primaryPrincipal)){
            roles.add("admin");
        }else if("xian".equals(primaryPrincipal)) {
            roles.add("superUser");
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        return info;
    }


}
