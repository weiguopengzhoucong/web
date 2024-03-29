package com.pxjg.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.filter
 * @文件名：CharFilter.java
 * @日期：Feb 13, 2012 10:54:07 AM
 * @备注：get提交过滤器
 * @作者：apple
 */
public class CharFilter implements Filter {

	private FilterConfig filterConfig = null;

	public void destroy() {
		this.filterConfig = null;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = this.filterConfig.getInitParameter("encoding");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse resp = (HttpServletResponse) response;
		request.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset="+encoding);
		// 处理GET参数
		if (request.getMethod().equalsIgnoreCase("get")) {
			@SuppressWarnings("rawtypes")
			Map paramMap = req.getParameterMap();
			String[] queryStringArray = { "" };
			if (request.getQueryString() != null) {
				queryStringArray = request.getQueryString().split("&");
			}
			for (int i = 0; i < queryStringArray.length; i++) {
				queryStringArray[i] = queryStringArray[i].replaceAll("(.*)=(.*)", "$1");
			}
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				// check where param from
				boolean isFromGet = false;
				for (String paramFromGet : queryStringArray) {
					if (key.equals(paramFromGet)) {
						isFromGet = true;
					}
				}
				if (!isFromGet) {
					continue;
				}
				String[] paramArray = (String[]) paramMap.get(key);
				for (int i = 0; i < paramArray.length; i++) {
					paramArray[i] = new String(paramArray[i].getBytes("iso-8859-1"), encoding);
				}
			}
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
