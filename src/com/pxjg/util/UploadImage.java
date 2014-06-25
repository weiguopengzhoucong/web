package com.pxjg.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;



public class UploadImage {
	public static String uploadImageCek(File file,String fileName,String oldFilePath){
		HttpServletRequest request = ServletActionContext.getRequest();
		 //根据图片地址查找原图片并删除
		  if(oldFilePath!=null){
			  File oldFile = new File(ServletActionContext.getServletContext().getRealPath(file.separator+"frontJsp"+file.separator+"wisEstateFront")+file.separator+oldFilePath);
			  if(oldFile.exists()){
				  oldFile.delete();
			  }
		  }
		  //文件名为日期格式
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		  //上传文件夹每天产生一个
		  SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		  Date curDate = new Date(System.currentTimeMillis());
		  String now = sf.format(curDate);
		  String dayPath = sf1.format(curDate);
		  String path = ServletActionContext.getServletContext().getRealPath(file.separator+"frontJsp"+file.separator+"wisEstateFront"+file.separator+"uploads")+file.separator+dayPath;
		  String path1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + file.separator+"frontJsp"+file.separator+"wisEstateFront"+file.separator+"uploads"+file.separator + dayPath; 
		  File realpath = new File(path);
		  if(!realpath.exists()){
			  realpath.mkdirs();
		  }
		  String newFileName = now+fileName.substring(fileName.lastIndexOf("."));
		  File savefile = new File(realpath, newFileName);
			try {
				FileUtils.copyFile(file, savefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 //返回处理之后的图片地址
			
		 return path1+"/"+newFileName;
	}
	
	public static String uploadImage(File file,String fileName,String oldFilePath){
		 //根据图片地址查找原图片并删除
		  if(oldFilePath!=null){
			  File oldFile = new File(ServletActionContext.getServletContext().getRealPath(file.separator+"frontJsp"+file.separator+"wisEstateFront")+file.separator+oldFilePath);
			  if(oldFile.exists()){
				  oldFile.delete();
			  }
		  }
		  //文件名为日期格式
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		  //上传文件夹每天产生一个
		  SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		  Date curDate = new Date(System.currentTimeMillis());
		  String now = sf.format(curDate);
		  String dayPath = sf1.format(curDate);
		  String path = ServletActionContext.getServletContext().getRealPath(file.separator+"frontJsp"+file.separator+"wisEstateFront"+file.separator+"uploads")+file.separator+dayPath;
		  File realpath = new File(path);
		  if(!realpath.exists()){
			  realpath.mkdirs();
		  }
		  String newFileName = now+fileName.substring(fileName.lastIndexOf("."));
		  File savefile = new File(realpath, newFileName);
			try {
				FileUtils.copyFile(file, savefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 String image_path = path.substring(path.lastIndexOf("frontJsp"))+file.separator+newFileName;
		 //返回处理之后的图片地址
		 
		 image_path = image_path.replace("\\", "/");
		 return image_path;
	}
	
	public static String uploadImageByPath(File file,String fileName,String oldFilePath,String where){
		 //根据图片地址查找原图片并删除
		  if(oldFilePath!=null){
			  File oldFile = new File(ServletActionContext.getServletContext().getRealPath(where)+file.separator+oldFilePath);
			  if(oldFile.exists()){
				  oldFile.delete();
			  }
		  }
		  //文件名为日期格式
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		  //上传文件夹每天产生一个
		  SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		  Date curDate = new Date(System.currentTimeMillis());
		  String now = sf.format(curDate);
		  String dayPath = sf1.format(curDate);
		  String path = ServletActionContext.getServletContext().getRealPath(where+file.separator+"uploads")+file.separator+dayPath;
		  File realpath = new File(path);
		  if(!realpath.exists()){
			  realpath.mkdirs();
		  }
		  String newFileName = now+fileName.substring(fileName.lastIndexOf("."));
		  File savefile = new File(realpath, newFileName);
			try {
				FileUtils.copyFile(file, savefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 String image_path = path.substring(path.lastIndexOf("uploads"))+file.separator+newFileName;
		 //返回处理之后的图片地址
		 
		 image_path = image_path.replace("\\", "/");
		 return image_path;
	}
}
