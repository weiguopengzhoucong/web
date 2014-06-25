package com.pxjg.module.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pxjg.util.UploadImage;



public class FileUploadAction extends ActionSupport  {

	private static final long serialVersionUID = 572146812454l ;

	private static final int BUFFER_SIZE = 16 * 1024 ;



	private File myFile;         //网页中file字段的name

	@SuppressWarnings("unused")
	private String contentType;  //struts2中必须字段,主要是set方法

	private String fileName;     // struts2中必须字段,主要是set方法

	private String imageFileName;   //要上传的图片在服务器中的名称

	private String imagePath;       //保存服务器中的路径

	private String pagePath;        //页面中要引用的url



	public String getPagePath() {

		return pagePath;

	}



	public void setPagePath(String pagePath) {

		this.pagePath = pagePath;

	}



	public String getImagePath() {

		return imagePath;

	}



	public void setImagePath(String imagePath) {

		this.imagePath = imagePath;

	}



	public void setMyFileContentType(String contentType)  {

		this.contentType = contentType;

	}



	public void setMyFileFileName(String fileName)  {

		this.fileName = fileName;

	}

	public void setMyFile(File myFile)  {

		this .myFile = myFile;

	}



	public String getImageFileName()  {

		return imageFileName;

	}
	
	public String uploadsFiles(){
		
		return "uploadJsp";
		
	}

	@SuppressWarnings("unused")
	private static void copy(File src, File dst)  {

		try  {

			InputStream in = null ;

			OutputStream out = null ;

			try  {               

				in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);

				out = new BufferedOutputStream( new FileOutputStream(src), BUFFER_SIZE);

				byte [] buffer = new byte [BUFFER_SIZE];

				while (in.read(buffer) > 0 )  {
					System.out.println("123");

					out.write(buffer);

				}

			} finally  {

				if ( null != in)  {

					in.close();

				}

				if ( null != out)  {

					out.close();

				}

			}

		} catch (Exception e)  {

			e.printStackTrace();

		}

	}

	private String imagefile;

	public String getImagefile() {
		return imagefile;
	}



	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}



	@SuppressWarnings("unused")
	@Override

	public String execute() throws IOException      {     
//		FTPEngine f=new FTPEngine("218.203.53.60", 21, "gzwxcs", "hljxxg@2013");
//		try {
//			f.connect();
//
//			f.upload(myFile,fileName, imagefile);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//  imageFileName = new Date().getTime() + fileName;   //此名称用当前时间与上传图片名组成
//
       //imagePath = ServletActionContext.getServletContext().getRealPath( "ckeditor/images/Image" ) + "\\" + imageFileName;
//
//		//   System.out.println(imagePath);
//
//		pagePath = f.getDBPath(); //页面引用位置
//		imagePath= f.getDBPath();
//		System.out.println("pagePath is "+pagePath);
//
		pagePath = UploadImage.uploadImageCek(myFile, fileName, null);
		//File imageFile = new File(imagePath);
		System.out.println(pagePath);
	//// 
	    //copy(myFile, imageFile);
		return SUCCESS;

	}

	@SuppressWarnings("unused")
	private String getWebPath(){

		HttpServletRequest request = ServletActionContext.getRequest ();



		String path = request.getContextPath();

		String basePath = request.getScheme() + "://"

              + request.getServerName() + ":" + request.getServerPort()

              + path + "/";

		return basePath;

	}

}