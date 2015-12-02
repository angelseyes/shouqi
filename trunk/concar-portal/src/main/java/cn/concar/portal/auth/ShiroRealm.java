package cn.concar.portal.auth;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.transaction.annotation.Transactional;

import cn.concar.service.model.AdminModel;
import cn.concar.service.proxy.AdminServiceProxy;

@Transactional
public class ShiroRealm extends AuthorizingRealm {

	private static final Logger LOG = Logger.getLogger(ShiroRealm.class);

	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOG.info("Inside ShiroRealm.doGetAuthorizationInfo.");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		LOG.info("Inside ShiroRealm.doGetAuthenticationInfo.");
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		try {
			AdminModel admin = AdminServiceProxy.findAdminByUsername(token.getUsername());
			if (admin != null) {
				return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error("Failed in ShiroRealm.doGetAuthenticationInfo!", e);
			return null;
		}

	}
}
