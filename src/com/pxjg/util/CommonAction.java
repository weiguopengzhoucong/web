package com.pxjg.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Logger logger = Logger.getLogger(this.getClass());
	private String winId;// struts2文件上传-父窗口id
	private File upload; // struts2文件上传-文件
	private String uploadContentType;// struts2文件上传-文件类型
	private String uploadFileName;// struts2文件上传-文件名
	private String myFileName;// struts2文件上传-返回文件名
	private String downloadFileName;// 下载文件名

	/**
	 * 进入文件上传页
	 * 
	 * @return
	 */
	public String fileUploadIndex() {
		String strMethod = "fileUploadIndex";
		logger.debug(strMethod + "Start.");
		String strReturn = "";
		myFileName = null;
		strReturn = "success";// 返回成功
		logger.debug(strMethod + "End.");
		return strReturn;
	}

	/**
	 * struts2上传
	 * 
	 * @return
	 */
	public String fileUpload() {
		String strMethod = "fileUpload";
		logger.debug(strMethod + "Start.");
		String strReturn = "";

		myFileName = new Date().getTime() + "."
				+ FileUpLoad.getExtention(uploadFileName);// 生成文件名
		String myFilePath = ServletActionContext.getServletContext()
				.getRealPath("upload")// 获取上传路径
				+ "/" + this.myFileName;
		File dst = new File(myFilePath);
		FileUpLoad.upLoadFile(upload, dst);
		strReturn = "success";// 返回成功

		logger.debug(strMethod + "End.");
		return strReturn;
	}

	public String download() {

		// 文件下载目录路径

		String downloadFile = ServletActionContext.getServletContext()
				.getRealPath("downloadtemp")
				+ "/" + downloadFileName;

		File file = new File(downloadFile);
		String filename = file.getName();

		// 以流的形式下载文件。
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(
					downloadFile));
			try {
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				ActionContext ctx = ActionContext.getContext();
				HttpServletResponse response = (HttpServletResponse) ctx
						.get(ServletActionContext.HTTP_RESPONSE);
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(filename.getBytes()));
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(response
						.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String getWinId() {
		return winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getMyFileName() {
		return myFileName;
	}

	public void setMyFileName(String myFileName) {
		this.myFileName = myFileName;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

}
