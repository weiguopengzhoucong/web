package com.pxjg.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.util
 * @文件名：FileUpLoad.java
 * @日期：Mar 12, 2012 4:13:19 PM
 * @备注：上传类
 * @作者：apple
 */
public class FileUpLoad {
	
	/**
	 * 文件上传
	 * @param src
	 * @param dst
	 */
	public static boolean upLoadFile(File src,File dst)
	{
		String fileName=dst.getName();//获取文件名
		String extenName=getExtention(fileName);//获取文件扩展名
		String proFileAddr = UtilTool.getInfPath()
		+ "common.properties";
		Configuration config = new Configuration(proFileAddr);
		String fileExtenName = config.getValue("fileExtenName");
		String [] fileExtenNames=fileExtenName.split(",");
		boolean flag=false;
		//检验文件类型
		for(int i=0;i<fileExtenNames.length;i++){
			if(extenName.equals(fileExtenNames[i])){
				flag=true;
			}
		}
		
		if(flag){
			InputStream in=null;
			OutputStream out=null;
			try
			{
				in=new BufferedInputStream(new FileInputStream(src),UtilConst.FILE_SIZE);
				out=new BufferedOutputStream(new FileOutputStream(dst),UtilConst.FILE_SIZE);
				byte[] buffer=new byte[UtilConst.FILE_SIZE];
				while(in.read(buffer)>0)
				{
					out.write(buffer);
				}
			}
			catch(IOException ex)
			{
			   ex.printStackTrace();
			}
			finally
			{
			   try
			   {
				   if(null!=in)
				   {
					   in.close();
				   }
				   if(null!=out)
				   {
					   out.close();
				   }
			   }
			   catch(IOException ex){}
			 }
		}
		return flag;
	}
	/**
	 * 截取扩展名
	 * @param fileName
	 * @return
	 */
	public static String getExtention(String fileName){
		int pos=fileName.lastIndexOf(".");
		return fileName.substring(pos+1);
	}
	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String extenName){
		String fileType="x";
		String proFileAddr = UtilTool.getInfPath()
		+ "common.properties";
		Configuration config = new Configuration(proFileAddr);
		//图片类型
		String fileTypeP = config.getValue("fileTypeP");
		String [] fileTypePs=fileTypeP.split(",");
		//检验文件类型
		for(int i=0;i<fileTypePs.length;i++){
			if(extenName.equals(fileTypePs[i])){
				fileType="p";
			}
		}
		
		//文件类型
		String fileTypeF = config.getValue("fileTypeF");
		String [] fileTypeFs=fileTypeF.split(",");
		//检验文件类型
		for(int i=0;i<fileTypeFs.length;i++){
			if(extenName.equals(fileTypeFs[i])){
				fileType="f";
			}
		}
		
		//cad格式
		String fileTypeD = config.getValue("fileTypeD");
		String [] fileTypeDs=fileTypeD.split(",");
		//检验文件类型
		for(int i=0;i<fileTypeDs.length;i++){
			if(extenName.equals(fileTypeDs[i])){
				fileType="d";
			}
		}
		return fileType;
	}
	/**
	 * 获取上传路径+文件名
	 * @param fileName
	 * @return
	 */
	public static String getUpFilePathName(String fileName){
		String myFilePath=ServletActionContext.getServletContext().getRealPath("uploadtemp");//获取上传路径
		String myFilePathName=myFilePath+"/"+fileName;//获取上传路径+文件名
		return myFilePathName;
	}
	/**
	 * 获取下载路径+文件名
	 * @param fileName
	 * @return
	 */
	public static String getDownloadFilePathName(String fileName){
		String myFilePath=ServletActionContext.getServletContext().getRealPath("downloadtemp");//获取上传路径
		String myFilePathName=myFilePath+"/"+fileName;//获取上传路径+文件名
		return myFilePathName;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
