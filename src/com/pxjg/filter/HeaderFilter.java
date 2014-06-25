package com.pxjg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 * @项目名：registration_Demo
 * @包名：net.hlj.common.filter
 * @文件名：HeaderFilter.java
 * @日期：Jan 31, 2012 2:43:35 PM
 * @备注:内部iframe的cookie丢失问题处理
 * @作者：apple
 */
public class HeaderFilter extends HttpServlet implements Filter {
	
	private static final long serialVersionUID = 1L;
	
	public HeaderFilter() {
		super();

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		// iframe引起的内部cookie丢失
		res.setHeader("P3P", "CP=CAO PSA OUR");
		if (chain != null)
			chain.doFilter(request, response);

	}
	
	public void destroy() {

	}

}
