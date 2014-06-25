package com.pxjg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.pxjg.util.CommonUtil;


public class LoginFilter implements Filter {
	/**
	 * 日志
	 */
	protected Logger logger = Logger.getLogger(this.getClass());

	protected FilterConfig filterConfig;
	public void destroy() {
		filterConfig = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String enabled = filterConfig.getInitParameter("enabled");
		if ("false".equals(enabled)) {
			chain.doFilter(request, response);
		} else {
			HttpServletRequest req = (HttpServletRequest) request;
			//项目路径
			String path = req.getContextPath();
			String basePath = req.getScheme() + "://"
				+ req.getServerName() + ":" + req.getServerPort()
				+ path + "/";
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("userBean") == null) {
				String keywords = filterConfig.getInitParameter("keywords");
				String uri = req.getRequestURI();
				if (!CommonUtil.contains(uri, keywords.split(";"))) {
					chain.doFilter(request, response);
					return;
				}
				String ignored = filterConfig.getInitParameter("ignored");
				if (CommonUtil.contains(uri, ignored.split(";"))) {
					chain.doFilter(request, response);
					return;
				}
				
				String input = filterConfig.getInitParameter("input");
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect(basePath+input);
			} else {
				chain.doFilter(request, response);
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

}
