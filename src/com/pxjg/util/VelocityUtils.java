package com.pxjg.util;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.util
 * @文件名：VelocityUtils.java
 * @日期：Apr 24, 2012 10:56:33 AM
 * @备注：使用Velocity生成内容的工具类
 * @作者：apple
 */
public class VelocityUtils {
	static {
		try {
			Velocity.init();
		} catch (Exception e) {
			throw new RuntimeException("Exception occurs while initialize the velociy.", e);
		}
	}

	/**
	 * 渲染内容.
	 * 
	 * @param template 模板内容.
	 * @param model 变量Map.
	 */
	public static String render(String template, Map<String, ?> model) {
		try {
			VelocityContext velocityContext = new VelocityContext(model);
			StringWriter result = new StringWriter();
			Velocity.evaluate(velocityContext, result, "", template);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException("Parse template failed.", e);
		}
	}
}
