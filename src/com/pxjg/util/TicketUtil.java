package com.pxjg.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class TicketUtil {
	public static String checkTicket(String sticket){
		String url = "http://218.203.53.51:8380/sso-core/ssoservlet";
    	
    	String content = "<samlp:ArtifactResolve "
    	+"xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\""
    	+" xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\""
    	+" ID=\"id_1\" Version=\"2.0\" IssueInstant=\"2007-12-05T09:21:59Z\">  "
    	+" <saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>  "
    	+" <samlp:Artifact>"
    	+sticket
    	+"</samlp:Artifact>" 
    			
    	+ "</samlp:ArtifactResolve>";
    	
		String re = Common.sendHTML(url,content);
		return getAttributeValue(re,"USessionID");
	}
	
	public static String checkTicket(String sticket,String paramName){
		String url = "http://218.203.53.51:8380/sso-core/ssoservlet";
    	
    	String content = "<samlp:ArtifactResolve "
    	+"xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\""
    	+" xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\""
    	+" ID=\"id_1\" Version=\"2.0\" IssueInstant=\"2007-12-05T09:21:59Z\">  "
    	+" <saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>  "
    	+" <samlp:Artifact>"
    	+sticket
    	+"</samlp:Artifact>" 
    			
    	+ "</samlp:ArtifactResolve>";
    	
		String re = Common.sendHTML(url,content,"utf-8");
		return getAttributeValue(re,paramName);
	}
	
	public static Map<String,String> checkTicket(String sticket,String[] paramName){
		String url = "http://218.203.53.51:8380/sso-core/ssoservlet";
    	
    	String content = "<samlp:ArtifactResolve "
    	+"xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\""
    	+" xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\""
    	+" ID=\"id_1\" Version=\"2.0\" IssueInstant=\"2007-12-05T09:21:59Z\">  "
    	+" <saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer>  "
    	+" <samlp:Artifact>"
    	+sticket
    	+"</samlp:Artifact>" 
    			
    	+ "</samlp:ArtifactResolve>";
    	
		String re = Common.sendHTML(url,content,"utf-8");
		System.out.println("reeeeeeeeeee:" + re);
		return getAttributeValue(re,paramName);
	}
	
	//根据attribute里的name查询value
	public static String getAttributeValue(String xmlDoc,String attribute) {
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            //得到根元素所有子元素的集合
            System.out.print(root.getText());
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
               if(et.getName().equals("Assertion")){
            	   List a = et.getChildren();
            	   for(int j=0;j<a.size();j++){
            		   Element et1 = (Element)a.get(j);
            		   if(et1.getName().equals("AttributeStatement")){
            			   Iterator b = et1.getChildren().iterator();
                    	   while(b.hasNext()){
                    		   Element e = (Element)b.next();
                    		   System.out.println(e.getName()+"==="+e.getValue());
                    		   System.out.println(e.getAttribute("name").getValue());
                    		   if(e.getAttribute("name").getValue().equals(attribute)){
                    			   Element la  = (Element)e.getChildren().get(0);
                    			   System.out.println(la.getText());
                    			   return la.getText();
                    		   }
                    		   
                    	   }
            		   }
            	   }
               }
            }
           
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return null;
    }
	
	//根据attribute数组里的name查询value
	public static Map<String,String> getAttributeValue(String xmlDoc,String[] attribute) {
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        Map<String,String> map = new HashMap<String,String>();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            //得到根元素所有子元素的集合
            System.out.print(root.getText());
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
               if(et.getName().equals("Assertion")){
            	   List a = et.getChildren();
            	   for(int j=0;j<a.size();j++){
            		   Element et1 = (Element)a.get(j);
            		   if(et1.getName().equals("AttributeStatement")){
            			   Iterator b = et1.getChildren().iterator();
                    	   while(b.hasNext()){
                    		   Element e = (Element)b.next();
                    		   System.out.println(e.getName()+"==="+e.getValue());
                    		   System.out.println("========="+e.getAttribute("name").getValue());
                    		   for(int k=0;k<attribute.length;k++){
                    			   if(e.getAttribute("name").getValue()!=null&&e.getAttribute("name").getValue().equals(attribute[k])){
                        			   Element la  = (Element)e.getChildren().get(0);
                        			   System.out.println(la.getText());
                        			   map.put(attribute[k], la.getText());
                        		   }
                    		   }
                    	   }
            		   }
            	   }
               }
            }
           
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return map;
    }
}
