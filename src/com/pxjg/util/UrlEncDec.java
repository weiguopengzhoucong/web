package com.pxjg.util;


public class UrlEncDec {

	
	

	
	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	private static String getEncString(String strMing) {
		
		
		String strMi = "";
		try {
			return byte2hex(strMing.getBytes());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strMi;
	}

	

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	private static String getDesString(String strMi) {
		
		
		String strMing = "";
		try {
			return new String(hex2byte(strMi.getBytes()));

			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return strMing;
	}

	

	

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	

	
	/**
	 * 返回加密字符串
	 * 
	 * @return
	 */
	public static String encStr(String estr) {
		if (estr != null&&!"".equals(estr)) {
			
				return getEncString(estr);
			
		}
		return null;
	}

	/**
	 * 解密字符串
	 * 
	 * @param estr
	 */
	public static String decStr(String estr) {
		if (estr != null && !"".equals(estr)) {
			//String decStrAll = getDesString(estr);
			return getDesString(estr);
		}
		return null;
	}

	/**
	 * 通过解密后的字符串，取参数
	 * @param key
	 * @return
	 */
	public  static String getParm(String key,String desstr) {
		if(desstr!=null && key!=null){
			int position1=desstr.indexOf(key+"=");
			if(position1>=0){
				int position2=desstr.indexOf("&",position1+1);
				if(position2>0){
					return desstr.substring(position1+key.length()+1, position2);
				}else{
					return desstr.substring(position1+key.length()+1);
				}
			}			
		}
		return null;
	}

}