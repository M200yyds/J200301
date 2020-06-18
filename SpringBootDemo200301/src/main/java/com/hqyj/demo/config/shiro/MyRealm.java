package com.hqyj.demo.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hqyj.demo.modules.account.entity.User;
import com.hqyj.demo.modules.account.service.UserService;

@Component
public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = userService.selectUserByName(userName);
		if (user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}

		// 身份验证器，包装用户名和密码
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
	}

}
