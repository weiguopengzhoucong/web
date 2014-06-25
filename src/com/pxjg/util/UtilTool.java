package com.pxjg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.sql.CLOB;

/**
 * @项目名：houseInfo
 * @包名：net.hlj.common.util
 * @文件名：UtilTool.java
 * @日期：Jan 31, 2012 2:43:35 PM
 * @备注:工具类
 * @作者：apple
 */
public class UtilTool {
	
	/** 
     * 数字转换字符串或者字符串 不足长度补零 
     * @param param 
     * @param bit 
     * @return 
     */  
    public static String fillBit(String param,int bit)  
    {  
    	param = param.replace(".", "");
        String strBit ="";  
        for(int i=0;i<bit;i++)  
        {  
            strBit+="0";  
        }  
        return strBit.substring(0,bit-param.length())+param;  
    }
    
	/*
	 * 返回四位数字
	 */
	public static String fourNumber(String number) {
		String ret = "";
		int a = Integer.parseInt(number);
		if (a < 10) {
			ret = "000" + number;

		} else if (a >= 10 && a <= 99) {
			ret = "00" + number;
		} else if (a >= 100 && a <= 999) {
			ret = "0" + number;
		} else if (a >= 1000 && a <= 9999) {
			ret = number;
		}

		return ret;

	}

	public static boolean isNull(String str) {
		if (str == null || "".equals(str)|| str.equals("null")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isAbsoluteNull(String str) {
		if(str!=null){
		   str = str.trim();
		}
		return isNull(str);
	}


	public static String toGb(String str) throws Exception {
		str = new String(str.getBytes("ISO-8859-1"), "GBK");
		return str;
	}

	public static String evalWhenNotNull(String obj) throws Exception {
		String str = isNull(obj) ? "" : obj.toString();
		return str;
	}

	public static String replaceAll(String sourceStr, String matchStr,
			String targetStr) {
		StringBuffer s = new StringBuffer(sourceStr);
		for (int pos = -1; (pos = s.indexOf(matchStr)) != -1;)
			s.replace(pos, pos + matchStr.length(), targetStr);

		return s.toString();
	}

	public static String leftOf(String str, int n) throws Exception {
		try {
			return str.substring(0, n) + "...";
		} catch (Exception Ex) {
			return str;
		}
	}

	/**
	 * 转换字符串
	 * @param str 需要转换的字符串
	 * @return
	 */
	public static String toHtmlString(String str) {
		if (str == null || ("").equals(str.trim())) {
			return "";
		}

		StringBuffer stringbuffer = new StringBuffer();
		int j = str.length();

		for (int i = 0; i < j; i++) {
			char c = str.charAt(i);

			switch (c) {
			case 39: // '
				stringbuffer.append("&acute;");
				break;
			case 60: // <
				stringbuffer.append("&lt;");
				break;
			case 62: // >
				stringbuffer.append("&gt;");
				break;
			case 38: // &
				stringbuffer.append("&amp;");
				break;
			case 34: // "
				stringbuffer.append("&quot;");
				break;
			/*case 169: //
				stringbuffer.append("&copy;");
				break;
			case 174: //
				stringbuffer.append("&reg;");
				break;
			case 165: //
				stringbuffer.append("&yen;");
				break;
			case 8364: //
				stringbuffer.append("&euro;");
				break;
			case 8482: //
				stringbuffer.append("&#153;");
				break;*/
			case 13:
				if (i < j - 1 && str.charAt(i + 1) == 10) {
					stringbuffer.append("<br/>");
					i++;
				}
				break;
			case 32:
				stringbuffer.append("&nbsp;");
				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * 还原字符串
	 * @param str 需要还原的字符串
	 * @param acute 是否还原单引号
	 * @return
	 */
	public static String unToHtmlSring(String str,boolean acute){
		String msg = "";
		if (isNull(str) || "null".equals(str)) {
			return "";
		} else {
			msg = str;
			if(acute){
				msg = msg.replaceAll("&acute;", "'");
			}
			msg = msg.replaceAll("&lt;", "<");
			msg = msg.replaceAll("&gt;", ">");
			msg = msg.replaceAll("&amp;", "&");
			msg = msg.replaceAll("&quot;", "\"");
			msg = msg.replaceAll("&nbsp;", " ");
			return msg;
		}
	}

	public static String htmlToString(String src){
		String msg = "";
		if (isNull(src) || "null".equals(src)) {
			return "";
		} else {
			msg = src.replaceAll("<[^img|^p|^/p].*?>", "");
			msg = msg.replaceAll("<\\/[^p].*?>", "");
			msg = msg.replaceAll("<p.*?>", "");
			msg = msg.replaceAll("<P.*?>", "");
			msg = msg.replaceAll("<p>", "");
			msg = msg.replaceAll("</p>", "");
			return msg;
		}
	}

	// add by lbq
	public static String addZero(int num) {
		String sNum = null;
		if (num < 10 && num >= 0)
			sNum = (new StringBuilder("0")).append(String.valueOf(num))
					.toString();
		else
			sNum = String.valueOf(num);
		return sNum;
	}

	/**
	 * 计算两个日期间的天数
	 *
	 * @param fromDate
	 *            起始日期
	 * @param toDate
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int dateDiff(String fromDate, String toDate) {
		int days = 0;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date from = null, to = null;
		try {
			from = df.parse(fromDate);
			to = df.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		days = (int) Math.abs((to.getTime() - from.getTime())
				/ (24 * 60 * 60 * 1000)) + 1;

		return days;
	}
	/**
	 * 以指定格式转换时间
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static String getDateFormat(String dateStr, String formatStr) {
		String returnDateStr = "";
		if (!isNull(dateStr)) {
			DateFormat df = new SimpleDateFormat(formatStr);
			try {
				Date date = df.parse(dateStr);
				returnDateStr = df.format(date);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
		return returnDateStr;
	}

	public static String getDateFormat(Date date, String formatStr) {
		String returnDateStr = "";
		if (date != null) {
			DateFormat df = new SimpleDateFormat(formatStr);
			returnDateStr = df.format(date);
		}
		return returnDateStr;
	}


	public static String getNowDate(){
		SimpleDateFormat dd=new SimpleDateFormat("yyyy年MM月dd日");
        String d=dd.format(new Date());
        return d;
	}

	// 将字CLOB转成STRING类型
	public static String ClobToString(CLOB clob) throws SQLException, IOException {

		String reString = "";
		if(clob!=null){
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		}
		return reString;
	}


	//得到最近num天的日期
	public static String getNearlyDate(int num)
	{
	    Date date = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int day = calendar.get(5);
	    calendar.set(5, day - num);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String sDay = sdf.format(calendar.getTime());
	    return sDay;
	}


	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 *
	 * @param char
	 *            c, 需要判断的字符
	 * @return boolean, 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
	    int k = 0x80;
	    return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 *
	 * @param String
	 *            s ,需要得到长度的字符串
	 * @return int, 得到的字符串长度
	 */
	public static int length(String s) {
	    if (s == null)
	        return 0;
	    char[] c = s.toCharArray();
	    int len = 0;
	    for (int i = 0; i < c.length; i++) {
	        len++;
	        if (!isLetter(c[i])) {
	            len++;
	        }
	    }
	    return len;
	}
	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 *
	 * @author patriotlml
	 * @param String
	 *            origin, 原始字符串
	 * @param int
	 *            len, 截取长度(一个汉字长度按2算的)
	 * @return String, 返回的字符串
	 */
	public static String substring(String origin, int len) {
	    if (origin == null || origin.equals("")||len<1)
	        return "";
	    byte[] strByte = new byte[len];
	    if (len > UtilTool.length(origin)){
	        return origin;}
	    System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
	    int count = 0;
	    for (int i = 0; i < len; i++) {
	        int value = (int) strByte[i];
	        if (value < 0) {
	            count++;
	        }
	    }
	    if (count % 2 != 0) {
	        len = (len == 1) ? ++len : --len;
	    }
	    return new String(strByte, 0, len);
	}

	private static String _webinfPath = null;

	public static String getWebinfPath() {
		if (_webinfPath == null) {
			String res = null;
			UtilTool u = new UtilTool();
			String classname = u.getClass().getName().replace('.', '/')
					+ ".class";
			ClassLoader cl = u.getClass().getClassLoader();
			if (cl != null) {
				java.net.URL url = cl.getResource(classname);
				if (url != null) {
					String path = url.getFile();
					int fileStrPosition = path.indexOf("file:/");
					int begin = 0;
					int end = path.length();

					if (fileStrPosition >= 0)
						begin = fileStrPosition + 5;

					// 先判断是否是未打包文件


					end = path.indexOf("classes/" + classname);
					if (end < 0) {
						// 如果是在webModule下面的jar包


						end = path.indexOf("lib/");
						if (end < 0) {
							// 在普通目录下的jar包


							int tmpend = path.indexOf("!/");
							end = path.substring(0, tmpend).lastIndexOf("/");
						}
					}
					String rf = path.substring(begin, end);
					res = new File(rf).getAbsolutePath().replace('\\', '/')
							+ "/";
				}
			}
			try {
				_webinfPath = java.net.URLDecoder.decode(res, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
			}
			//System.out.println("WEB-INF Path=" + _webinfPath);
		}
		return _webinfPath;
	}

	private static String _systemPath = null;

	public static String getSystemPath() {
		if (_systemPath == null) {
			String res = getWebinfPath();
			if (res.indexOf("WEB-INF/") > 0 && res.length() > 10) {
				res = res.substring(0, res.lastIndexOf("/", res.length() - 12))
						+ "/";
			}
			_systemPath = res;
			//System.out.println("System Path=" + _systemPath);
		}
		return _systemPath;
	}

	public static String getInfPath() {
		if (_systemPath == null) {
			String res = getWebinfPath();
			_systemPath = res;
			//System.out.println("System Path=" + _systemPath);
		}
		return _systemPath;
	}
	/*
	 * 获取屏幕宽度
	 */
	public static int getScreenWidth() {

		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	}
	/*
	 * 获取屏幕高度
	 */
	public static int getScreenHeight() {

		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	/**
	 * 获取登陆超时跳转地址
	 * @return
	 */
	public static String getUrl(){
		String gotoUrl = "/";
		String proFileAddr =UtilTool.getInfPath()+"common.properties";
		Configuration config = new Configuration(proFileAddr);
		String url = config.getValue("goUrl");
		if(!isNull(url)){
			gotoUrl = url;
		}
		return gotoUrl;
	}

	public static String null2Nbsp(String str){
		String rtn = str;
		if((null == str) || ("".equals(str))){
			rtn = "&nbsp;";
		}
		return rtn;
	}
	/**
	 * 数字转换汉字星期
	 * 例如：7 转换 后为'日'
	 * @param number 需要转换的阿拉伯数字
	 * @return 汉字星期数字
	 */
	public static String num2Chinese(String number){
		String[] chineseNum = {"一","二","三","四","五","六","日"};
		String rtn_number = "";
		for(int i = 0;i<number.length();i++){
			String pos_num = String.valueOf(number.charAt(i));
			int num = Integer.parseInt(pos_num);
			if(num>0&&num<8){
				rtn_number+=chineseNum[num-1];
			}
		}
		return rtn_number;
	}
	/**
	 * ip合法性验证
	 * 
	 * */
	public static boolean ipvalid(String s){
		String regex0="(2[0-4]\\d)" + "|(25[0-5])";
		String regex1="1\\d{2}";
		String regex2="[1-9]\\d";
		String regex3="\\d";
		String regex="("+regex0+")|("+regex1+")|("+regex2+")|("+regex3+")";
		regex="("+regex+").("+regex+").("+regex+").("+regex+")";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(s);
		return m.matches();
		}
	

	//将实体所有字段值转换为html
	/**
	 * @param obj 待转换的实体类
	 */
	public static void entityFieldToHtml(Object obj){
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if (!"serialVersionUID".equals(fieldName)) {
				String firstLetter = fieldName.substring(0, 1)
						.toUpperCase();
				// 获得和属性对应的getXXX()方法的名字
				String getMethodName = "get" + firstLetter
						+ fieldName.substring(1);
				String setMethodName = "set" + firstLetter
						+ fieldName.substring(1);
				try {
					// 获得和属性对应的getXXX()方法
					Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
					// 调用原对象的getXXX()方法
					Object value = getMethod.invoke(obj,new Object[] {});
					if(value!=null&&value instanceof java.lang.String){
						//获得和属性对应的setXXX()方法
						Method setMethod = clazz.getMethod(setMethodName, new Class[] {value.getClass()});
						// 调用原对象的setXXX()方法
						setMethod.invoke(obj, new Object[] {toHtmlString(value.toString())});
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e){
					e.printStackTrace();
				} catch (IllegalAccessException e){
					e.printStackTrace();
				}
			}
		}
	}
	//将实体所有字段的html还原
	/**
	 * @param obj 待转换的实体类
	 * @param acute 是否转换单引号
	 */
	public static void entityFieldUnToHtml(Object obj,boolean acute){
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if (!"serialVersionUID".equals(fieldName)) {
				String firstLetter = fieldName.substring(0, 1)
				.toUpperCase();
				// 获得和属性对应的getXXX()方法的名字
				String getMethodName = "get" + firstLetter
				+ fieldName.substring(1);
				String setMethodName = "set" + firstLetter
				+ fieldName.substring(1);
				try {
					// 获得和属性对应的getXXX()方法
					Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
					// 调用原对象的getXXX()方法
					Object value = getMethod.invoke(obj,new Object[] {});
					if(value!=null&&value instanceof java.lang.String){
						//获得和属性对应的setXXX()方法
						Method setMethod = clazz.getMethod(setMethodName, new Class[] {value.getClass()});
						// 调用原对象的setXXX()方法
						setMethod.invoke(obj, new Object[] {unToHtmlSring(value.toString(),acute)});
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e){
					e.printStackTrace();
				} catch (IllegalAccessException e){
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 要保留的标签
	 * 
	 * @param s
	 * @return 要保留的标签正则
	 */
	public static String ReservationsHtmlTags() {
		String[] holdTags = {  "img" };// 要保留的 tag
		// <(?!((/?\s?li)|(/?\s?ul)|(/?\s?a)|(/?\s?img)|(/?\s?br)|(/?\s?span)|(/?\s?b)))[^>]+>
		StringBuffer sbTR = new StringBuffer();
		sbTR.append("<(?!(");
		// String regStrHead="<(?!(";
		for (int i = 0; i < holdTags.length; i++) {
			// 是否是第一个
			if (i != 0)
				sbTR.append("|");
			sbTR.append("(/?\\s?" + holdTags[i] + ")");

		}
		sbTR.append("))[^>]+>");
		return sbTR.toString();
	}
	public static void main(String[] args) {
		System.out.println(fillBit("1",12));
		
	}
	
}
