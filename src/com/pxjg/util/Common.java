package com.pxjg.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Common {
	
	/**
	 * 保存cookie
	 * 
	 * @param request
	 * @param response
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String value)
    {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies)
        {
            for (Cookie s : cookies)
            {
                if (("UID").equals(s.getName()))
                {
                    cookie = s;
                }
            }
        }
        if (null == cookie)
        {
            cookie = new Cookie("UID", value);
        }
        else
        {
            cookie.setValue(value);
        }
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(10000000);
        response.addCookie(cookie);
    }

    /**
	 * 
	 * 重定向到省时，重定向的URL生成
	 * 
	 * @param request
	 * @return
	 */

    public static String getDirectURL(String ssoserverURL, HttpServletRequest request)
    {
        String url = null;

        if (ssoserverURL.indexOf('?') > 0)
        {
            url = ssoserverURL + "backurl=" + request.getRequestURL() + getParms(request);
        }
        else
        {
            url = ssoserverURL + '?' + "backurl=" + request.getRequestURL() + getParms(request);
        }
        return url;
    }

    /**
	 * 
	 * 得到cookie
	 * 
	 * @param request
	 * @return
	 */
    public static String getCookieValue(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                if (("UID").equals(cookie.getName()))
                {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
	 * 从请求中获得参数值，而后去除掉一个参数后，组装成新的字符串返回。
	 * 
	 * @param request
	 *            请求
	 * @param exceptParm
	 *            要去除掉的参数名。
	 * @return String 返回字符串
	 */
    public static String getParms(HttpServletRequest request)
    {
        StringBuffer result = new StringBuffer();

        Enumeration enumeration = request.getParameterNames();
        String parmName = null;
        while (enumeration.hasMoreElements())
        {
            parmName = enumeration.nextElement().toString();

            if (!"sticket".equalsIgnoreCase(parmName))
            {
                result.append("&").append(parmName).append("=")
                        .append(request.getParameter(parmName));
            }
        }

        return result.toString();
    }

    /**
	 * 校验ST
	 * 
	 * @param urlAddress  请求地址
	 * @return bodyContent  请求报文 如果为空请用""代替
	 */
    public static String sendHTML(String urlAddress,String bodyContent)
    {
    	URL url;      
    	HttpURLConnection uRLConnection=null;  
    	try {              
    	url = new URL(urlAddress);
    	uRLConnection = (HttpURLConnection)url.openConnection();
    	uRLConnection.setDoInput(true);
    	uRLConnection.setDoOutput(true);
    	uRLConnection.setRequestMethod("POST");
    	uRLConnection.setUseCaches(false);
    	uRLConnection.setInstanceFollowRedirects(false);
    	uRLConnection.setRequestProperty("contentType", "text/xml"); 
    	uRLConnection.setRequestProperty("contentLength", "365");
    	uRLConnection.setRequestProperty("msgname", "ValidTicketReq"); 
    	uRLConnection.connect();
    	DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
    	/*String content = "<samlp:ArtifactResolve"+
	    " xmlns:samlp='urn:oasis:names:tc:SAML:2.0:protocol'"+
	    " xmlns:saml='urn:oasis:names:tc:SAML:2.0:assertion'"+
	    " ID='id_1'"+
	    " Version='2.0'"+
	    " IssueInstant='2007-12-05T09:21:59Z'>"+  
	    "<saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>"+
	    "<samlp:Artifact>"+sticket+"</samlp:Artifact>"+  
	    "</samlp:ArtifactResolve>";
    	out.writeBytes(content);
    	out.flush();
    	out.close();
    	*/
    	if(!"".equals(bodyContent))
    	{
    		out.writeBytes(bodyContent);
        	out.flush();
        	out.close();
    	}
    	InputStream is = uRLConnection.getInputStream();
    	BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
    	String response = "";
    	String readLine = null;
    	while((readLine =br.readLine()) != null)
    	{   
    		response = response + readLine;
    	}              
    	is.close();              
    	br.close();              
    	uRLConnection.disconnect();              
    	return response;          
    	} 
    	catch (MalformedURLException e) {              
    		e.printStackTrace();              
    		return null;          
    		} 
    		catch (IOException e)
    		{              
    			e.printStackTrace();              
    			return null;          
    		}  
    }
    
    public static String sendHTML(String urlAddress,String bodyContent,String code)
    {
    	URL url;      
    	HttpURLConnection uRLConnection=null;  
    	try {              
    	url = new URL(urlAddress);
    	uRLConnection = (HttpURLConnection)url.openConnection();
    	uRLConnection.setDoInput(true);
    	uRLConnection.setDoOutput(true);
    	uRLConnection.setRequestMethod("POST");
    	uRLConnection.setUseCaches(false);
    	uRLConnection.setInstanceFollowRedirects(false);
    	uRLConnection.setRequestProperty("contentType", "text/xml"); 
    	uRLConnection.setRequestProperty("sendaddress", "BJ");
    	uRLConnection.setRequestProperty("transactionid", "XXX");
    	uRLConnection.setRequestProperty("recvaddress", "XXX");
    	uRLConnection.setRequestProperty("msgversion", "XXX");
    	uRLConnection.setRequestProperty("sendareacode", "XXX");
    	uRLConnection.setRequestProperty("recvareacode", "XXX");
    	uRLConnection.setRequestProperty("contentLength", "365");
    	uRLConnection.setRequestProperty("msgname", "ValidTicketReq"); 
    	uRLConnection.connect();
    	DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
    	/*String content = "<samlp:ArtifactResolve"+
	    " xmlns:samlp='urn:oasis:names:tc:SAML:2.0:protocol'"+
	    " xmlns:saml='urn:oasis:names:tc:SAML:2.0:assertion'"+
	    " ID='id_1'"+
	    " Version='2.0'"+
	    " IssueInstant='2007-12-05T09:21:59Z'>"+  
	    "<saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>"+
	    "<samlp:Artifact>"+sticket+"</samlp:Artifact>"+  
	    "</samlp:ArtifactResolve>";
    	out.writeBytes(content);
    	out.flush();
    	out.close();
    	*/
    	if(!"".equals(bodyContent))
    	{
    		out.writeBytes(bodyContent);
        	out.flush();
        	out.close();
    	}
    	InputStream is = uRLConnection.getInputStream();
    	BufferedReader br = new BufferedReader(new InputStreamReader(is,code));
    	String response = "";
    	String readLine = null;
    	while((readLine =br.readLine()) != null)
    	{   
    		response = response + readLine;
    	}              
    	is.close();              
    	br.close();              
    	uRLConnection.disconnect();              
    	return response;          
    	} 
    	catch (MalformedURLException e) {              
    		e.printStackTrace();              
    		return null;          
    		} 
    		catch (IOException e)
    		{              
    			e.printStackTrace();              
    			return null;          
    		}  
    }
    
    public static void main(String[] args)
    {
    	
    	String url = "http://218.203.53.51:8380/sso-core/ssoservlet";
    	
    	String content = 
    	"<samlp:ArtifactResolve"+
	    " xmlns:samlp='urn:oasis:names:tc:SAML:2.0:protocol'"+
	    " xmlns:saml='urn:oasis:names:tc:SAML:2.0:assertion'"+
	    " ID='req msg id'"+
	    " Version='2.0'"+
	    " IssueInstant='Wed Aug 14 02:58:20 GMT 2013'>"+  
	    "<saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>"+
	    "<samlp:Artifact>STWGD00000013764607718910010000000896573tWE3lty87sAf49c4</samlp:Artifact>"+  
	    "</samlp:ArtifactResolve>";
    	
    	//String content = "<samlp:ArtifactResolve xmlns:samlp='urn:oasis:names:tc:SAML:2.0:protocol' xmlns:saml='urn:oasis:names:tc:SAML:2.0:assertion' ID='req msg id ' Version='2.0' IssueInstant='Wed Aug 14 02:58:20 GMT 2013'><saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer><samlp:Artifact>STWGD00000013764478389060010000000889666598aQH4yrDtJ7j1F</samlp:Artifact></samlp:ArtifactResolve>";
    	
		String re = sendHTML(url,content,"gb2312");
		
		System.out.println(re);
	
    }
    
}
