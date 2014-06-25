package com.pxjg.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
 * @文件名：CrossDomainFilter.java
 * @日期：Feb 9, 2012 3:49:24 PM
 * @备注：防跨域
 * @作者：apple
 */
public class CrossDomainFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String servername_str = request.getServerName();//取前一个地址
		String currentURI = request.getRequestURI();
		@SuppressWarnings("rawtypes")
		Enumeration headerValues = request.getHeaders("Referer");
		String tmpHeaderValue = "";
		boolean isValid = true;
		//指定需要跳过拦截的页面地址，如果需要新增，可直接在数组中添加。
		String [] ignoreURIS={"/back/"};
		while (headerValues.hasMoreElements()) {
			// 得到完整的路径：如“http://www.xxx.com/xxx/xxx.jsp?id=xxx”
		    tmpHeaderValue = (String) headerValues.nextElement();
		    }

		if ("".equals(tmpHeaderValue)) {
		    isValid = false;
		   } else {
		    tmpHeaderValue = tmpHeaderValue.toLowerCase();
		    servername_str = servername_str.toLowerCase();

		    int len = 0;
		    if (tmpHeaderValue.startsWith("https://")) {
		     len = 8;
		    } else if (tmpHeaderValue.startsWith("http://")) {
		     len = 7;
		    }

		    String tmp = tmpHeaderValue.substring(len, servername_str.length() + len);
		    if (tmp.length() < servername_str.length()) { // 长度不够
		     isValid = false;
		    } else if (!tmp.equals(servername_str)) {// 比较字符串（主机名称）是否相同
		     isValid = false;
		    }
		   }
		  
		  
		   // 跳过指定需要拦截的页面地址
		   for (String ignoreURI : ignoreURIS) {
		    if(currentURI.contains(ignoreURI)){
		     isValid=true;
		    }
		   }
		   
		   //如果第一次访问
		   if(tmpHeaderValue.equals("")){
			   isValid=true;
		   }
		   
		   if (!isValid) {
		   
//		    response.sendRedirect("/example/exampleIndex.jsp");
			   
			PrintWriter out=response.getWriter();
			out.print("Page Not Found!");
		   } else {
		    arg2.doFilter(arg0, arg1);
		   }


	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
