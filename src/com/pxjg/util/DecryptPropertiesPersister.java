package com.pxjg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.StringUtils;

/**
 * 重载DefaultPropertiesPersister类
 */
public class DecryptPropertiesPersister extends DefaultPropertiesPersister {
	// 加密后的字符串
	private String encryptContent;

	public String getEncryptContent() {
		return encryptContent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doLoad(Properties props, Reader reader) throws IOException {
		BufferedReader in = new BufferedReader(reader);

		// 最后写入的内容
		StringBuilder sbContent = new StringBuilder();

		// 循环读取文件
		while (true) {
			// 读取每一行
			String line = in.readLine();

			// 非空检查
			if (line == null) {
				break;
			}

			// 去掉空格
			line = StringUtils.trimLeadingWhitespace(line);

			// 读取行为空，跳出循环
			if (line.length() == 0) {
				// 长度为0,换行
				sbContent.append("\n");
				continue;
			}

			// 每行的第一个字符
			char firstChar = line.charAt(0);

			// 第一个字符不是#和!
			if (firstChar != '#' && firstChar != '!') {
				while (endsWithContinuationMarker(line)) {
					String nextLine = in.readLine();
					line = line.substring(0, line.length() - 1);

					// 非空检查
					if (nextLine != null) {
						line += StringUtils.trimLeadingWhitespace(nextLine);
					}
				}

				// 查找等号所有位置的索引
				int separatorIndex = line.indexOf("=");

				// 没有等号
				if (separatorIndex == -1) {
					separatorIndex = line.indexOf(":");
				}

				// 取KEY
				String key = (separatorIndex != -1) ? line.substring(0,
						separatorIndex) : line;

				// 取KEY的值
				String value = (separatorIndex != -1) ? line
						.substring(separatorIndex + 1) : "";

				// 去掉空格
				key = StringUtils.trimTrailingWhitespace(key);
				value = StringUtils.trimLeadingWhitespace(value);

				// 将所有的属性放到持久的属性集*
				props.put(unescape(key), unescape(value));

				// DB属性文件
//				if ("jdbc.password".equals(key)) {
//					// 实例加密工具类
//					ThreeDES des = new ThreeDES("Digital House");
//					// DB密码解密
//					if (value.startsWith("{DES}")) {
//						// 去掉标识
//						value = value.substring(5);
//						// 对加密的属性进行3DES解密
//						value = des.getDesString(value);
//						// 解密的值放到props中
//						props.put(unescape(key), unescape(value));
//					}
//					// DB密码加密
//					else {
//						// 加密指定的值
//						String strEncrypt = des.getEncString(value);
//						// 加密后的值添加一个标识,区分解密、加密
//						value = "{DES}" + strEncrypt;
//						// 加密后的行
//						line = key + "=" + value;
//						sbContent.append(line + "\n");
//					}
//				}
//				// 追加其它的属性
//				else {
//					sbContent.append(line + "\n");
//				}
				sbContent.append(line + "\n");
			} else {
				// 追加读取的注释内容
				sbContent.append(line + "\n");
			}
		}
		encryptContent = sbContent.toString();
	}
}