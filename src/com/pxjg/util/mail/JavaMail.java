package com.pxjg.util.mail;

import java.util.Date;

import com.ck.utils.date.DateUtils;
import com.ck.utils.mail.SendMail;

/****************************************************************
 * 对QQ邮箱使用（对于其他的使用类似的你应该也会更改了） 首先把QQ邮箱的POP3 SMTP打开 首先确定你的网络正常、非代理
 * 【确定你的QQ邮箱开启了SMTP】！！！！ 如果没有开启，那么这样设置下 设置->账户 --在下面-- 把这个选上 [√]SMTP发信后保存到服务器
 ****************************************************************/
public class JavaMail {
	
	public static void sendMail(String email,String url){
		
		try {
			SendMail sendMail = new SendMail("smtp.126.com", 25, true,
					"13244587561@126.com", "qinxulong891107", false);
			sendMail.setFrom("13244587561@126.com");
			sendMail.setTo(email);//
			sendMail.setSubject("激活邮件");
			sendMail.setHtmlBody("请点击链接激活<a href='"+url+"'>链接</a>，或者把一下链接复制到浏览器地址栏中回车即可。"+url+" ");
			sendMail.send();
			System.out.println("发送邮件消息成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

	public static void main(String[] args){
		try {
			SendMail sendMail = new SendMail("smtp.126.com", 25, true,
					"13244587561@126.com", "qinxulong891107", false);
			sendMail.setFrom("13244587561@126.com");
			sendMail.setTo("huqiuyu0725@163.com");//
			String strDate = DateUtils.formatDate(new Date(),
					DateUtils.DEFAULT_DATE_PATTERN);
			// sendMail.setFileAffix(new String[]{"c:\\夜审数据.xls"});
	
			sendMail.setSubject("激活邮件" + strDate);
			sendMail.setHtmlBody("发送激活邮件");
			sendMail.send();
			System.out.println("发送邮件消息成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
