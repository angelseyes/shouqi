package cn.concar.portal.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class FormAuthFilter extends FormAuthenticationFilter {

	private static final Logger LOG = Logger.getLogger(FormAuthFilter.class);

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
	        ServletResponse response) throws Exception {
		LOG.info("inside onLoginSuccess.");
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
	        ServletResponse response) {
		LOG.info("inside onLoginFailure.");
		return super.onLoginFailure(token, e, request, response);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LOG.info("inside onAccessDenied.");
		LOG.debug("Redirect url: " + getLoginUrl());
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (!"XMLHttpRequest".equalsIgnoreCase((httpServletRequest).getHeader("x-requested-with"))) {
			LOG.debug("Not ajax request.");
			saveRequestAndRedirectToLogin(request, response);
		} else {
			LOG.debug("Ajax request, forward to login page.");
			httpServletResponse.setHeader("errorMsg", "Access Denied");
		}
		return false;
	}
}
