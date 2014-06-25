package com.pxjg.util;

/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.util
 * @文件名：UtilConst.java
 * @日期：Jan 31, 2012 2:43:35 PM
 * @备注:定义常量
 * @作者：apple
 */
public  class UtilConst {
	public static int FILE_SIZE;//struts2文件上传-上传文件大小
	public final static String FLOWLINKCOND_NONE = "1"; //业务流程，无条件流向
	
	static{//静态初始化块      
		init();   
    } 
	private static void init() {
//		String proFileAddr = UtilTool.getInfPath()
//		+ "common.properties";
//		Configuration config = new Configuration(proFileAddr);
//		String file_size = config.getValue("dbUser");
		FILE_SIZE =1024*16;
	}
}
