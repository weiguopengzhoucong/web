package com.pxjg.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

public class ImageUtil {
	public static byte[] getBytes(File file){   
		byte[] buffer = null;   
		try {   

			FileInputStream fis = new FileInputStream(file);   
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);   
			byte[] b = new byte[1000];   
			int n;   
			while ((n = fis.read(b)) != -1) {   
				bos.write(b, 0, n);   
			}   
			fis.close();   
			bos.close();   
			buffer = bos.toByteArray();   
		} catch (FileNotFoundException e) {   
			e.printStackTrace();   
		} catch (IOException e) {   
			e.printStackTrace();   
		}   
		return buffer;   
	}   
	/**  
	 * 根据byte数组，生成文件  
	 */  
	public static void getFile(byte[] bfile, String filePath,String fileName,String where) {   
		BufferedOutputStream bos = null;   
		FileOutputStream fos = null;   
		File file = null;   
		try {   
			File dir = new File(ServletActionContext.getServletContext().getRealPath(where)+"/"+filePath);   
			if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在   
				dir.mkdirs();   
			}   
			file = new File(filePath+"\\"+fileName);   
			fos = new FileOutputStream(file);   
			bos = new BufferedOutputStream(fos);   
			bos.write(bfile);   
		} catch (Exception e) {   
			e.printStackTrace();   
		} finally {   
			if (bos != null) {   
				try {   
					bos.close();   
				} catch (IOException e1) {   
					e1.printStackTrace();   
				}   
			}   
			if (fos != null) {   
				try {   
					fos.close();   
				} catch (IOException e1) {   
					e1.printStackTrace();   
				}   
			}   
		}   
	}   
	public static void readBlob(FileInputStream inputStream, String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, len);// 写
			}
			inputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
