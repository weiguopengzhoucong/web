package com.pxjg.module.front;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.util.GsonExclusionStrategy;

public class BaseAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String forward;

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	protected String forward(String forward) {
		this.forward = forward;
		return "forward";
	}

	protected void writeToJson(Object obj) {
		writeToJson(obj, "yyyy-MM-dd HH:mm:ss");
	}

	protected void writeToJson(Object obj, String dateFormat) {

		try {
			String json = new GsonBuilder().setDateFormat(dateFormat).create().toJson(obj);
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/plain; charset=UTF-8");
			ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void writeToExclusionStrategiesJson(Object obj) {
		writeToExclusionStrategiesJson(obj, "yyyy-MM-dd HH:mm:ss");
	}

	protected void writeToExclusionStrategiesJson(Object obj, String dateFormat) {

		try {
			String json = new GsonBuilder().setExclusionStrategies(new GsonExclusionStrategy()).setDateFormat(dateFormat).create().toJson(obj);

			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/plain; charset=UTF-8");
			ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void writeToString(String str) {

		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().setContentType("text/plain; charset=UTF-8");
			ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
