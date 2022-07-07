package com.cui.common.utils;

import com.cui.common.text.StrFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 公共方法类
 * </p>
 * <p>
 * 提供字符串处理的实用方法集
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * @author Weiwenqi
 * @version 1.0
 *
 */

public class StringUtil {
	/** 空字符串 */
	private static final String NULLSTR = "";

	/** 下划线 */
	private static final char SEPARATOR = '_';
	public StringUtil() {
	}

	public static final String escapeForIntro(String string) {
		// String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");

	}

	/**
	 * 得到非空的字符串，若字符串对象为null，则返回""。
	 * 
	 * @param objValue
	 *            Object待转换的原字符串对象
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(Object objValue) {
		return (objValue == null ? "" : objValue.toString());
	}

	/**
	 * 得到非空的字符串，若字符串为null，则返回""。
	 * 
	 * @param strValue
	 *            String待转换的原字符串
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(String strValue) {
		return (strValue == null ? "" : strValue.trim());
	}

	// /**
	// * 使用正则表达式进行字符串内容替换
	// * @param regularExpression 正则表达式
	// * @param sub 替换的字符串
	// * @param input 要替换的字符串
	// * @return String 替换后的字符串
	// */
	// public static synchronized String regexReplace(String regularExpression,
	// String sub, String input)
	// {
	// Pattern pattern = PatternFactory.getPattern(regularExpression);
	// Matcher matcher = pattern.matcher(input);
	// StringBuffer sb = new StringBuffer();
	// while (matcher.find())
	// {
	// matcher.appendReplacement(sb, sub);
	// }
	// matcher.appendTail(sb);
	// return sb.toString();
	// }

	/**
	 * 判断一个字符串中是否包含符合正则表达式定义的匹配条件的子串
	 * 
	 * @param regularExpression
	 *            - 正则表达式
	 * @param input
	 *            - 待检查字符串
	 * @return - 若输入字符串中包含符合正则表达式定义的匹配条件的子串，则返回true，否则返回false
	 */
	// //正则表达式匹配判断
	// public static synchronized boolean exist(String regularExpression, String
	// input)
	// {
	// Pattern pattern = PatternFactory.getPattern(regularExpression);
	// Matcher matcher = pattern.matcher(input);
	// return matcher.find();
	// }

	/**
	 * 用"0"补足一个字符串到指定长度
	 * 
	 * @param str
	 *            - 源字符串
	 * @param size
	 *            - 补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size) {
		String result;
		if (str.length() < size) {
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++) {
				s[i] = '0';
			}
			result = new String(s) + str;
		} else {
			result = str;
		}
		return result;
	}

	/**
	 * 根据字符串（文件类型或者多行输入类型）获取字符串数组
	 * 
	 * @param str1
	 *            String 输入字符串
	 * @return String[] 返回结果
	 */
	public static String[] getStrArryByString(String str1) {
		if (str1.indexOf("\t") > 0) {
			for (int i = 0; i < str1.length(); i++) {
				if (str1.substring(i, i + 1).equals("\t")) {
					str1 = str1.substring(0, i) + " "
							+ str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}

	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * 
	 * @param inStr
	 *            inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return false;
		} else if (inStr.equals("")) {
			return false;
		} else if (inStr.equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * 
	 * @param inStr
	 *            inStr
	 * @return boolean
	 */
	public static boolean checkNotNull(String str) {
		boolean flag = false;

		if (str != null && str.trim().length() != 0)
			flag = true;
		return flag;
	}

	/**
	 * 获得指定长度的空格
	 * 
	 * @param spaceNum
	 *            spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum) {
		return getStrWithSameElement(spaceNum, " ");
	}

	/**
	 * 获得指定长度的字符串
	 * 
	 * @param num
	 *            int
	 * @param element
	 *            String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element) {
		if (num <= 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(element);
		}
		return sb.toString();
	}

	/**
	 * 从右或左加空格
	 * 
	 * @param strIn
	 *            String
	 * @param totalLength
	 *            int
	 * @param isRightAlign
	 *            boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength,
			boolean isRightAlign) {
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null) {
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0) {
			spaceLength = 0;
		}
		spaces = com.cui.common.utils.StringUtil.getStrSpace(spaceLength);

		if (isRightAlign) {
			strOut = spaces + strIn;
		} else {
			strOut = strIn + spaces;

		}
		return strOut;
	}

	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * 
	 * @param t
	 *            Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 转换字符串第一个字符为大写
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str) {
		try {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 转换字符串第一个字符为小写
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str) {
		try {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue) {
		if (null == strValue) {
			return 0;
		}
		int iValue = 0;
		try {
			iValue = new Integer(strValue.trim()).intValue();
		} catch (Exception ex) {
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 * */
	public static double getStrToDouble(String strValue) {
		if (null == strValue) {
			return 0;
		}
		double dValue = 0;
		try {
			dValue = Double.parseDouble(strValue.trim());
		} catch (Exception ex) {
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * 通过字符串转换成相应的短整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return short 转换完成的短整型
	 * */
	public static short getStrToShort(String strValue) {
		if (null == strValue) {
			return 0;
		}
		short iValue = 0;
		try {
			iValue = new Short(strValue.trim()).shortValue();
		} catch (Exception ex) {
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 通过字符串转换成相应的长整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return long 转换完成的长整型
	 * */
	public static long getStrToLong(String strValue) {
		if (null == strValue) {
			return 0;
		}
		long lValue = 0;
		try {
			lValue = new Long(strValue.trim()).longValue();
		} catch (Exception ex) {
			lValue = 0;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length) {
		if (null != str) {
			if (str.length() <= length) {
				return str;
			} else {
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		} else {
			return "";
		}
	}

	/**
	 * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
	 * 
	 * @param str
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static String toLengthForIntroduce(String str, int length) {
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2)) {
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++) {
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2)) {
					break;
				}
				buff.append(charArray[i]);

			}
			buff.append("..");
			str = buff.toString();
		}

		// str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;

	}

	public static String delTag(String str) {
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0) {
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0) {
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length()) {
				str = str.substring(end + 1, str.length());
			} else {
				str = "";
			}

		}
		String result = buff.toString();

		while (result.startsWith(" ")) {

			result = result.substring(result.indexOf(" ") + 1, result.length());

		}
		return result;

	}

	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;

	}

	// Replace
	public static String Replace(String source, String oldString,
			String newString) {
		if (source == null) {
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	// 此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtml(String s) {
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");
		// s = Replace(s, " ", "&nbsp;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");
		// s = Replace(s, "&", "&amp;");
		return s;
	}

	// 逆
	public static String unHtml(String s) {

		// s = Replace(s, "&lt;", "<");
		// s = Replace(s, "&gt;", ">");
		// s = Replace(s, "    ", "\t");
		// s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		// s = Replace(s, "&nbsp;", " ");
		// s = Replace(s, "&amp;", "&");
		// s = Replace(s, "&#39;", "'");
		// s = Replace(s, "&#92;", "\\");
		// s = Replace(s, "％", "%");
		return s;
	}

	// 此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtmlBack(String s) {
		// 显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		// s = Replace(s, "\n", "<br>");
		// s = Replace(s, " ", "&nbsp;");
		// s = Replace(s, "'", "&#39;");
		// s = Replace(s, "%", "%");

		return s;
	}

	// 逆
	public static String unHtmlBack(String s) {
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}

	/*
	 * public static String toHtml(String s) { //显示 s = Replace(s, "&",
	 * "&amp;"); s = Replace(s, "\\", "&#92;"); s = Replace(s, "\"", "&quot;");
	 * s = Replace(s, "<", "&lt;"); s = Replace(s, ">", "&gt;"); s = Replace(s,
	 * "\t", "    "); s = Replace(s, "\r\n", "\n"); // s = Replace(s, "\n",
	 * "<br>"); s = Replace(s, " ", "&nbsp;"); // s = Replace(s, "'", "&#39;");
	 * // s = Replace(s, "%", "%");
	 * 
	 * return s; }
	 * 
	 * public static String unHtml(String s) { s = Replace(s, "&lt;", "<"); s =
	 * Replace(s, "&gt;", ">"); s = Replace(s, "    ", "\t"); s = Replace(s,
	 * "\n", "\r\n"); s = Replace(s, "<br>", "\n"); s = Replace(s, "&nbsp;",
	 * " "); s = Replace(s, "&amp;", "&"); s = Replace(s, "&#39;", "'"); s =
	 * Replace(s, "&#92;", "\\"); s = Replace(s, "％", "%"); return s; }
	 */
	// 判断是否含有中文，如果含有中文返回ture
	public static boolean containsChinese(String str)
			throws UnsupportedEncodingException {

		if (str.length() < (str.getBytes()).length)
			return true;

		return false;

		// for (int i = 0; i < username.length(); i++) {
		// String unit=Character.toString(username.charAt(i));
		// byte[] unitByte=unit.getBytes("GBK");
		// // ((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		// if (unitByte.length == 2)
		// {
		// return true;
		// }
		// }
		// return false;

	}
	/**
	 * * 判断一个字符串是否为空串
	 *
	 * @param str String
	 * @return true：为空 false：非空
	 */

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	/**
	 * * 判断一个对象数组是否为空
	 *
	 * @param objects 要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects)
	{
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Jul 30, 2011 <br>
	 * 
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(String[] arrs) {
		StringBuffer result = new StringBuffer("");
		if (arrs != null && arrs.length > 0) {
			for (int i = 0; i < arrs.length; i++) {

				if (!result.toString().equals("")) {
					result.append(",");
				}
				if (arrs[i] != null && !"".equals(arrs[i].trim())) {
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Jul 30, 2011 <br>
	 * 
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(Object[] arrs) {
		StringBuffer result = new StringBuffer("");
		if (arrs != null && arrs.length > 0) {
			for (int i = 0; i < arrs.length; i++) {

				if (!result.toString().equals("")) {
					result.append(",");
				}
				if (arrs[i] != null && !"".equals(arrs[i].toString().trim())) {
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>替换回车<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Oct 26, 2011 <br>
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHuiche(String str) {
		String after = str.replaceAll("\r\n", "");
		return after;
	}

	/**
	 * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>
	 * 换行
	 * 
	 * @param str
	 *            输入的字符串
	 * @param len
	 *            输入的长度
	 * @return 处理过后的字符串
	 */
	public static String truncateStr(String str, int len) {
		if (str != null && !("").equalsIgnoreCase(str)) {

			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0) {
				for (int i = 0; i < strs.length; i++) {
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len) {
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}

			}
			return buff.toString();
		} else {
			return "";
		}
	}

	public static String truncateStr2(String str, int len) {
		if (str != null && !("").equalsIgnoreCase(str) && len != 0) {
			String strs[] = str.split(" ");

			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len) {
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ", "&nbsp; ");
					tempstr = tempstr.replaceAll("<", "&lt; ");
					tempstr = tempstr.replaceAll("\n", "<br> ")
							.replaceAll("\"", "&quot; ")
							.replaceAll("'", "&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);

					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ", "&nbsp; ");
				tempstr = tempstr.replaceAll("<", "&lt; ");
				tempstr = tempstr.replaceAll("\n", "<br> ")
						.replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");

				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}

			if (buff.length() > 0)
				return buff.toString().substring(0, buff.length() - 1);
			else
				return str;
		} else {
			return "";
		}
	}

	/**
	 * 编码转换，从unicode转换为GBK
	 * 
	 * @param str
	 *            输入字符串
	 * @return str编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source)
			throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}

	/**
	 * 编码转换，从GBK转换为unicode
	 * 
//	 * @param str
	 *            输入字符串
	 * @return str 编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source)
			throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals("")) {
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 * 
	 * <p>
	 * It escapes both <tt>'</tt> and <tt>"</tt>. In additional it escapes
	 * <tt>></tt> as <tt>\></tt> (to avoid <tt>&lt;/script></tt>). Furthermore,
	 * all characters under UCS code point 0x20, that has no dedicated escape
	 * sequence in JavaScript language, will be replaced with hexadecimal escape
	 * (<tt>\x<i>XX</i></tt>).
	 */
	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"') {
						b.append("\\\"");
					} else if (c == '\'') {
						b.append("\\'");
					} else if (c == '\\') {
						b.append("\\\\");
					} else if (c == '>') {
						b.append("\\>");
					} else if (c < 0x20) {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 0x10;
							b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
							x = c & 0xF;
							b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
						}
					} else {
						b.append(c);
					}
					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}

	private static com.cui.common.utils.StringUtil instance = null;

	public static synchronized com.cui.common.utils.StringUtil getInstance() {
		if (instance == null) {
			instance = new com.cui.common.utils.StringUtil();
		}
		return instance;
	}

	/**
	 * 将多个连续空格替换为一个,"a  b"-->"a b"
	 * 
	 * @param src
	 * @return
	 * @author WilliamLau
	 * @desc
	 */
	public static String trimContinuousSpace(String src) {
		return src.replaceAll("\\s+", " ");
	}

	public static String toFloatNumber(String num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}

	public static String toFloatNumber(Double num, int accuracy) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(num);
	}

	public static String wcsUnescape(String str) {
		str = str.replace("#lt;", "<");
		str = str.replace("#gt;", ">");
		str = str.replace("#quot;", "\"");
		str = str.replace("#amp;amp;", "&");
		str = str.replace("#amp;", "&");
		str = str.replace("#039;", "'");
		return str;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>返回string型的字节数<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Sep 2, 2011 <br>
	 * 
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str) {
		if (str == null) {
			return 0;
		}
		return str.getBytes().length;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>详细的功能描述<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Sep 2, 2011 <br>
	 * 
	 * @param str
	 *            字符
	 * @param limitLen
	 *            长度
	 * @return
	 */
	public static String getByteStr(String str, int limitLen) {
		StringBuffer sb = new StringBuffer();
		char[] chars = getNotNullStr(str).toCharArray();
		int len = 0;
		for (char c : chars) {
			len += getByteLength(String.valueOf(c));
			if (len <= limitLen) {
				sb.append(c);
			}
		}
		return sb.toString();

	}

	/**
	 * @todo 通过正则表达式判断是否匹配
	 * @param source
	 * @param regex
	 * @return
	 */
	public static boolean matches(String source, String regex) {
		return matches(Pattern.compile(regex), source);
	}

	/**
	 * @todo 通过正则表达式判断是否匹配
	 * @param Pattern
	 * @param source
	 * @return
	 */
	public static boolean matches(Pattern p, String source) {
		return p.matcher(source).find();
	}

	/**
	 * @todo 找到匹配的位置
	 * @param source
	 * @param regex
	 * @return
	 */
	public static int matchIndex(String source, String regex) {
		return matchIndex(source, Pattern.compile(regex));
	}

	public static int matchIndex(String source, Pattern p) {
		Matcher m = p.matcher(source);
		if (m.find())
			return m.start();
		else
			return -1;
	}

	public static int matchLastIndex(String source, String regex) {
		return matchLastIndex(source, Pattern.compile(regex));
	}

	public static int matchLastIndex(String source, Pattern p) {
		Matcher m = p.matcher(source);
		int matchIndex = -1;
		while (m.find()) {
			matchIndex = m.start();
		}
		return matchIndex;
	}

	/**
	 * @todo 获取匹配成功的个数
	 * @param source
	 * @param regex
	 * @return
	 */
	public static int matchCnt(String source, String regex) {
		return matchCnt(Pattern.compile(regex), source);
	}

	/**
	 * @todo 获取匹配成功的个数
	 * @param Pattern
	 * @param source
	 * @return
	 */
	public static int matchCnt(Pattern p, String source) {
		Matcher m = p.matcher(source);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}

	/**
	 * @todo 获取匹配成功的个数
	 * @param source
	 * @param regex
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static int matchCnt(String source, String regex, int beginIndex,
			int endIndex) {
		Pattern p = Pattern.compile(regex);
		return matchCnt(p, source.substring(beginIndex, endIndex));
	}

	/**
	 * @todo 获取包含嵌套匹配成功的个数
	 * @param source
	 * @param regex
	 * @return
	 */
	public static int nestMatchCnt(String source, String regex) {
		return nestMatchCnt(Pattern.compile(regex), source);
	}

	/**
	 * @todo 获取包含嵌套匹配成功的个数
	 * @param Pattern
	 * @param source
	 * @return
	 */
	public static int nestMatchCnt(Pattern p, String source) {
		Matcher m = p.matcher(source);
		int count = 0;
		int index = 0;
		while (m.find()) {
			count++;
			index = m.start();
			source = source.substring(index + 1);
			m = p.matcher(source);
		}
		return count;
	}

	/**
	 * @todo 判断字符串是空或者空白
	 * @param str
	 * @return
	 */
	public static boolean isNotNullAndBlank(Object str) {
		return !isNullOrBlank(str);
	}

	public static boolean isNullOrBlank(Object str) {
		if (null == str || str.toString().trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	/**
	 * 手机号加中间*
	 * 
	 * @param mobileNo
	 * @return
	 */
	public static String maskPhone(String mobileNo) {
		StringBuilder sb = new StringBuilder(mobileNo);
		return sb.replace(3, 7, "****").toString();
	}

	/**
	 * 根据用户名的不同长度，来进行替换 ，达到保密效果
	 *
	 * @param userName
	 *            用户名
	 * @return 替换后的用户名
	 */
	public static String userNameReplaceWithStar(String userName) {
		String userNameAfterReplaced = "";
		if (userName == null) {
			userName = "";
		}
		int nameLength = userName.length();

		if (nameLength <= 1) {
			userNameAfterReplaced = "*";
		} else if (nameLength == 2) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{0})\\d(?=\\d{1})");
		} else if (nameLength >= 3 && nameLength <= 6) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{1})\\d(?=\\d{1})");
		} else if (nameLength == 7) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{1})\\d(?=\\d{2})");
		} else if (nameLength == 8) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{2})\\d(?=\\d{2})");
		} else if (nameLength == 9) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{2})\\d(?=\\d{3})");
		} else if (nameLength == 10) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{3})\\d(?=\\d{3})");
		} else if (nameLength >= 11) {
			userNameAfterReplaced = replaceAction(userName,
					"(?<=\\d{3})\\d(?=\\d{4})");
		}
		return userNameAfterReplaced;
	}

	/**
	 * 实际替换动作
	 * 
	 * @param username
	 *            username
	 * @param regular
	 *            正则
	 * @return
	 */
	private static String replaceAction(String username, String regular) {
		return username.replaceAll(regular, "");
	}

	/**
	 * 身份证号替换，保留前四位和后四位
	 *
	 * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 *
	 * @param idCard
	 *            身份证号
	 * @return
	 */
	public static String idCardReplaceWithStar(String idCard) {

		if (idCard.isEmpty() || idCard == null) {
			return null;
		} else {
			return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
		}
	}

	/**
	 * 银行卡替换，保留后四位 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 * 
	 * @param bankCard
	 *            银行卡号
	 * @return
	 */
	public static String bankCardReplaceWithStar(String bankCard) {
		StringBuilder sb = new StringBuilder(bankCard);
		return sb.replace(0, bankCard.length() - 4, "**** **** **** ").toString();
	}

	/**
	 * 对字符串处理:将指定位置到指定位置的字符以星号代替
	 * 
	 * @param content
	 *            传入的字符串
	 * @param begin
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static String getStarString(String content, int begin, int end) {
		if (begin >= content.length() || begin < 0) {
			return content;
		}
		if (end >= content.length() || end < 0) {
			return content;
		}
		if (begin >= end) {
			return content;
		}
		String starStr = "";
		for (int i = begin; i < end; i++) {
			starStr = starStr + "*";
		}
		return content.substring(0, begin) + starStr
				+ content.substring(end, content.length());
	}

	/**
	 * 掩码用户姓名
	 * 
	 * @param content
	 * @return
	 */
	public static String getMaskUserName(String content) {
		if (com.cui.common.utils.StringUtil.isNullOrBlank(content))
			return "";
		int len = content.length();
		String ss = content.substring(0, 1);
		for (int i = 0; i < len - 1; i++) {
			ss = ss + "*";
		}
		return ss;

	}
	
	/**
	 * 身份证掩码
	 * @param content
	 * @return
	 */
	public static String getMaskIdCard(String content) {
		content = new StringBuffer().append(content.substring(0,6)).append("********")
				.append(content.substring(14)).toString();
		return content;
	}
	
	/**
	* @Description: 身份证掩码
	* @author wangzhiguo
	* @date  2017年8月12日 下午5:24:25
	* @param idCard
	* @return
	 */
	public static String maskIdCard(String idCard){
		if (idCard.isEmpty() || idCard == null) {
			return "";
		}
		int strLen = idCard.length();
		return idCard.substring(0,3)+"************"+idCard.substring(strLen-3, strLen);
	}

	/**
	 * 获取去掉横线的长度为32的UUID
	 * 
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 500; i++) {
			System.out.println(com.cui.common.utils.StringUtil.get32UUID());
		}
	}
	
    /** 
     * 验证输入的邮箱格式是否符合 
     * @param email 
     * @return 是否合法 
     */  
	public static boolean emailFormat(String email)  
    {  
        boolean tag = true;  
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
        final Pattern pattern = Pattern.compile(pattern1);  
        final Matcher mat = pattern.matcher(email);  
        if (!mat.find()) {  
            tag = false;  
        }  
        return tag;  
    }
	/**
	 * 去空格
	 */
	public static String trim(String str)
	{
		return (str == null ? "" : str.trim());
	}

	/**
	 * 是否包含字符串
	 *
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs)
	{
		if (str != null && strs != null)
		{
			for (String s : strs)
			{
				if (str.equalsIgnoreCase(trim(s)))
				{
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * * 判断一个对象是否为空
	 *
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object)
	{
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object)
	{
		return !isNull(object);
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 *
	 * 括号及其里面的字符 (称作格式化字段) 将会被 format() 中的参数替换
	 * eg:  format("请求访问：{}，认证失败，无法访问系统资源", http:www.cui.com)  -->请求访问：http:www.cui.com，认证失败，无法访问系统资源
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param params 参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params)
	{
		if (isEmpty1(params) || isEmpty(template))
		{
			return template;
		}
		return StrFormatter.format(template, params);
	}

	public static boolean isEmpty1(Object[] objects)
	{
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * * 判断一个字符串是否为非空串
	 *
	 * @param str String
	 * @return true：非空串 false：空串
	 */
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}

	/**
	 * 截取字符串
	 *
	 * @param str 字符串
	 * @param start 开始
	 * @param end 结束
	 * @return 结果
	 */
	public static String substring(final String str, int start, int end)
	{
		if (str == null)
		{
			return NULLSTR;
		}

		if (end < 0)
		{
			end = str.length() + end;
		}
		if (start < 0)
		{
			start = str.length() + start;
		}

		if (end > str.length())
		{
			end = str.length();
		}

		if (start > end)
		{
			return NULLSTR;
		}

		if (start < 0)
		{
			start = 0;
		}
		if (end < 0)
		{
			end = 0;
		}

		return str.substring(start, end);
	}




}
