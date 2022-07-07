package com.cui.common.valid;

import com.cui.common.utils.IDCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Tile:验证工具
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:www.itcast.com
 * </p>
 * <p>
 * Email:wuyilei@huaxiafinance.com
 * </p>
 * 
 * @author 华夏信财股权投资管理有限公司 吴轶雷
 * @date 2015-10-4 上午11:10:25
 * @version 1.0
 */
public class ValidUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(com.cui.common.valid.ValidUtil.class);

	private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

	private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	public static final String PhoneNumPattern = "1[345789]\\d{9}";
	
	/**
	 * 电话号码正则表达式
	 */
	private static final Pattern mobilePattern = Pattern.compile(PhoneNumPattern);

	/**
	 * <p>
	 * Description:账号(手机号码)验证
	 * 
	 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
	 *
	 * 　　 * 联通：130、131、132、152、155、156、185、186
	 *
	 * 　　 * 电信：133、153、180、189、（1349卫通）
	 * </p>
	 * 
	 * @date 2015-10-4 上午11:11:02
	 * @author 华夏信财股权投资管理有限公司
	 * @param mobile
	 * @return
	 */
	public static boolean checkAcctNum(String mobile) {
		// 通过正则验证，如果使用第一句就只能符合上面的号码
		// Pattern p=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Pattern p = Pattern.compile("^\\d{11}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
		// return false;
	}

	/**
	 * <p>
	 * Description:密码验证
	 * </p>
	 * 
	 * @date 2015-10-4 上午11:40:54
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param passowrd
	 * @return
	 */
	public static boolean checkPassword(String passowrd) {
		// 规则是：不是纯的0-9，也不是纯的a-z或A-Z
		Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,36}$");
		Matcher m = p.matcher(passowrd);
		return m.matches();
	}

	// 日期格式化类型
	private static SimpleDateFormat[] sds = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd"), };

	/**
	 * <p>
	 * Description:日期格式化校验，返回null表示校验失败
	 * </p>
	 * 
	 * @date 2015-10-4 下午9:26:30
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param date
	 * @return
	 */
	public static Date checkDate(String date) {
		for (int i = 0; i < sds.length; i++) {
			try {
				sds[i].setLenient(false);
				// 参数绑定成功直接返回
				Date tempDate = sds[i].parse(date);
				if (null != tempDate && tempDate.getTime() > new Date().getTime()) {
					return null;
				}
				return tempDate;
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Description:身份证验证
	 * </p>
	 * 
	 * @date 2015-10-4 下午10:04:11
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param identitycard
	 * @return
	 */
	public static boolean checkIdentitycard(String identitycard) {
		IDCard cc = new IDCard();
		if (cc.IDCardValidate(identitycard).equals("YES")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * Description:邮箱地址验证
	 * </p>
	 * 
	 * @date 2015-10-4 下午10:05:50
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if (email.length() > 50) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * <p>
	 * Description:判断是否是整数
	 * </p>
	 * 
	 * @date 2015-10-6 上午1:54:43
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param money
	 * @return
	 */
	public static boolean checkLong(String money) {
		try {
			Long.parseLong(money);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	// ------------下面是所有pojo一些状态字段的验证-------//
	/**
	 * <p>
	 * Description:用户状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkUserStatus(String status) {
		if (!"0".equals(status) && !"1".equals(status)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:用户删除状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkUserDeleteFlag(String deleteFlag) {
		if (!"0".equals(deleteFlag) && !"1".equals(deleteFlag)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:用户详细中的性别校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkUserInfoGender(String gender) {
		if (!"0".equals(gender) && !"1".equals(gender)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:用户详细中的婚姻状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkUserInfoMarriageStatus(String marriagestatus) {
		if (!"0".equals(marriagestatus) && !"1".equals(marriagestatus)
				&& !"2".equals(marriagestatus) && !"3".equals(marriagestatus)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:验证码的类型校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkVcodeType(String type) {
		if (!"0".equals(type) && !"1".equals(type) && !"2".equals(type) && !"3".equals(type)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:产品中的使用状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkProductUseStatus(String usestatus) {
		if (!"0".equals(usestatus) && !"1".equals(usestatus)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:产品中的首页是否显示状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkProductHomePageStatus(String homepagestatus) {
		if (!"0".equals(homepagestatus) && !"1".equals(homepagestatus)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:理财人中的启用状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkFinancialPlannerStatus(String status) {
		if (!"0".equals(status) && !"1".equals(status)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:理财人中的首页是否显示校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkFinancialPlannerHomePageStatus(String homepagestatus) {
		if (!"0".equals(homepagestatus) && !"1".equals(homepagestatus)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:订单中的状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkOrderInfoStatus(String status) {
		if (!"0".equals(status) && !"1".equals(status) && !"2".equals(status)
				&& !"-1".equals(status)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:广告中启用状态校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkIndexAdsStatus(String status) {
		if (!"0".equals(status) && !"1".equals(status)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:广告中位置类型校验
	 * </p>
	 * 
	 * @date 2015-10-6 上午3:25:38
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param status
	 * @return
	 */
	public static boolean checkIndexAdsTypeId(String typeId) {
		if (!"0".equals(typeId) && !"1".equals(typeId) && !"2".equals(typeId)
				&& !"3".equals(typeId) && !"4".equals(typeId) && !"5".equals(typeId)) {
			return false;
		}
		return true;
	}

	/**
	 * 证件类型1的验证，暂时不确定，因此直接返回true
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @date 2015-10-19 上午11:36:28
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param string
	 * @return
	 */
	public static boolean checkIdentitycard2(String string) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @date 2015-10-27 下午1:56:29
	 * @author 华夏信财股权投资管理有限公司-吴轶雷
	 * @param documenttype
	 * @return
	 */
	public static boolean checkdocumenttype(String documenttype) {
		if ("0".equals(documenttype) || "1".equals(documenttype) || "2".equals(documenttype)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkbankcard(String string) {
		if (string.length() > 19) {
			return false;
		} else {
			Pattern pattern = Pattern.compile("^[0-9]*$");
			Matcher matcher = pattern.matcher(string);
			return matcher.matches();
		}
	}

	/**
	 * 过滤SQL
	 * @param val
	 * @return
	 */
	public static String filterForSQL(String val) {
		if (isEmpty(val))
			return "";

		Matcher m = sqlPattern.matcher(val);
		if (m.find())
			return m.replaceAll("");
		else
			return val;
	}

	/**
	 * 从右侧截取字串
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String rSubstr(String str, int len) {
		if (isEmpty(str))
			return "";
		else {
			int length = str.length();
			if (length <= len)
				return str;
			else {
				int start = length - len;
				int end = length;
				return new String(str.substring(start, end));
			}
		}
	}

	/**
	 * 从左侧截取字串
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String lSubstr(String str, int len) {
		if (isEmpty(str))
			return "";
		else {
			int length = str.length();
			if (length <= len)
				return str;
			else
				return new String(str.substring(0, len));
		}
	}

	/**
	 * 存在空值判断
	 * 
	 * @param params
	 * @return
	 */
	public static boolean existEmpty(String... params) {
		if (params == null || params.length == 0)
			return false;
		for (String str : params)
			if (isEmpty(str))
				return true;

		return false;
	}

	/**
	 * 不存在空值判断
	 * 
	 * @param params
	 * @return
	 */
	public static boolean notExistEmpty(String... params) {
		if (params == null || params.length == 0)
			return true;
		for (String str : params)
			if (isEmpty(str))
				return false;

		return true;
	}

	/**
	 * 必须参数检查
	 * 
	 * @param args
	 * @return
	 */
	public static boolean necessaryArgCheck(String... args) {
		boolean blnRet = true;
		for (String arg : args)
			if (isEmpty(arg.trim())) {
				blnRet = false;
				break;
			}
		return blnRet;
	}

	/**
	 * 是否不存在空值
	 * 
	 * @param params
	 * @return
	 */
	public static boolean noExistEmpty(String... params) {
		boolean ret = true;
		if (params == null | params.length == 0)
			return ret;
		else {
			for (String str : params)
				if (isEmpty(str))
					return false;
		}
		return ret;
	}

	/**
	 * 检查手机号码，空值或者不合正则表达式的均返回false
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if (isEmpty(mobile))
			return false;

		if (!mobilePattern.matcher(mobile).find()) {
			return false;
		} else if (mobile.length() != 11) {
			return false;
		}

		return true;
	}

	private static Random rand = new Random();

	/**
	 * 生成随机码
	 * 
	 * @param length
	 * @return
	 */
	public static String randCode(int length) {
		length = Math.abs(length);
		String RandCode_Format = "%0" + length + "d";
		long scope = (long) Math.pow(10, length);
		int val = (int) (rand.nextDouble() * scope);
		return String.format(RandCode_Format, val);
	}

	/**
	 * 判断字符串是否全为某字符组成
	 * 
	 * @param aChar
	 * @return
	 */
	public static boolean isComposeByChar(String aStr, char aChar) {
		if ((aStr == null) || "".equals(aStr))
			return false;
		boolean flag = true;
		for (int i = 0; i < aStr.length(); i++) {
			if (aStr.charAt(i) != aChar) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * @Title: filterSpace
	 * @Description: 过滤字符串中的空格、回车、换行符、制表符
	 * @param str
	 *            需要过滤的字符串
	 * @return String 过滤后的字符串
	 */
	public static String filterSpace(String str) {
		return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
	}

	/**
	 * Description: 集合判空
	 * 
	 * @param collection
	 *            需要判空的集合
	 * @return boolean 是否为空,true为非空,false为空
	 * @author xianlong@huaxiafinance.com
	 * @date 2015年8月27日 上午10:10:42
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmptyCollection(Collection collection) {
		return collection != null && !collection.isEmpty();
	}

	/**
	 * Description: 集合判空
	 * 
	 * @param collection
	 *            需要判空的集合
	 * @return boolean 是否为空,true为空,false为非空
	 * @author xianlong@huaxiafinance.com
	 * @date 2015年8月27日 上午10:10:42
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyCollection(Collection collection) {
		return !isNotEmptyCollection(collection);
	}

	/**
	 * Description:判断字符是否为""、null、" "
	 * 
	 * @param character
	 *            需要检验的字符串
	 * @return ""、null、" "等字符串返回true；否则返回false
	 * @author xianlong@huaxiafinance.com
	 * @date 2015年8月27日 上午1:07:14
	 */
	public static boolean isEmpty(String character) {
		int strLen;
		if (character == null || (strLen = character.length()) == 0 || "null".equals(character)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(character.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Description: 检查手机号码，空值或者不合正则表达式的均返回false
	 * 
	 * @param mobile
	 *            需要检查的号码
	 * @return boolean 是手机号为true,否则false
	 * @author xianlong@huaxiafinance.com
	 * @date 2015年8月27日 上午10:10:42
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile))
			return false;
		Pattern mobilePattern = Pattern.compile(PhoneNumPattern);
		if (!mobilePattern.matcher(mobile).find()) {
			return false;
		} else if (mobile.length() != 11) {
			return false;
		}
		return true;
	}

	/***
	 * <p>
	 * @Description: 获取32位GUID
	 * </p>
	 * 
	 * @Company:华夏信财互联网金融信息有限责任公司
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年3月28日 下午2:04:14
	 * @version V1.0
	 */
	public static String getUUId() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			UUID uuid = UUID.randomUUID();
			String guidStr = uuid.toString();
			messageDigest.update(guidStr.getBytes(), 0, guidStr.length());
			return new BigInteger(1, messageDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("获取32位GUID异常：", e);
			return null;
		}
	}

	/***
	 * <P>
	 * @Description: 根据指定的内容将 需替换 的内容替换为 需要的内容
	 * </P>
	 * 
	 * @param content
	 *            指定的内容
	 * @param replace
	 *            替换前的内容
	 * @param replaceAs
	 *            替换后的内容
	 * @return 返回需要的内容
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月1日 下午5:02:31
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String replaceAll(String content, String replace, String replaceAs) {
		return content.replaceAll(replace, replaceAs);
	}

	/**
	 * 
	 * <p>
	 * Description:根据key判断值是存在
	 * </p>
	 * 
	 * @date 2016年9月13日 下午5:38:17
	 * @author 吴轶雷
	 * @param maps
	 * @param key
	 * @return 存在返回false，否则返回true
	 */
	public static boolean isEmpty(Map maps, String key) {
		if (maps != null && maps.get(key) != null && !maps.get(key).equals("")) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * 
	 * <p>
	 * Description:<检查传入的值是否在枚举范围内/p>
	 * 
	 * @date 2016年10月25日 下午5:49:46
	 * @author 吴轶雷
	 * @param e
	 *            枚举集合
	 * @param value
	 *            检查的值
	 * @return 在范围内返回true，否则范围false
	 * @throws Exception
	 */
	public static <E> boolean checkCodeEnum(Class cs, String code) throws Exception {
		E[] e = (E[]) cs.getMethod("values", null).invoke(cs, null);
		for (E t : e) {
			if (code.toString().equals(
					t.getClass().getMethod("getCode", null).invoke(t, null).toString())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * <p>
	 * Description:<检查传入的值是否在枚举范围内/p>
	 * 
	 * @date 2016年10月25日 下午5:49:46
	 * @author 吴轶雷
	 * @param e
	 *            枚举集合
	 * @param value
	 *            检查的值
	 * @return 在范围内返回true，否则范围false
	 * @throws Exception
	 */
	public static <E> boolean checkEnum(Class cs, Object value) throws Exception {
		E[] e = (E[]) cs.getMethod("values", null).invoke(cs, null);
		for (E t : e) {
			if (value.toString().equals(
					t.getClass().getMethod("getValue", null).invoke(t, null).toString())) {
				return true;
			}
		}
		return false;
	}
	
	
	// 手机头部，简单版本
	// private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone",
	// "MQQBrowser" };
	// 手机头部信息
	private static String[] mobileAgents = { "iphone", "android", "phone", "mobile", "wap",
			"netfront", "java", "opera mobi", "opera mini", "ucweb", "windows ce", "symbian",
			"series", "webos", "sony", "blackberry", "dopod", "nokia", "samsung", "palmsource",
			"xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "docomo", "up.browser",
			"up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
			"techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson",
			"philips", "sagem", "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice",
			"bird", "zte-", "longcos", "pantech", "gionee", "portalmmm", "jig browser", "hiptop",
			"benq", "haier", "^lct", "320x320", "240x320", "176x220", "w3c ", "acs-", "alav",
			"alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell", "cldc",
			"cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi", "keji",
			"leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
			"mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil",
			"play", "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri",
			"sgh-", "shar", "sie-", "siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli",
			"tim-", /* "tosh", */"tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi",
			"wapp", "wapr", "webc", "winw", "winw", "xda", "xda-", "Googlebot-Mobile" };

	/**
	 * 
	 * <p>Description:检查是否是app访问</p>
	 * @date 2016年12月6日 下午12:55:18
	 * @author 吴轶雷
	 * @param request
	 * @return 是的返回true
	 */
	public static boolean JudgeIsMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		if (null != request.getHeader("User-Agent")) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
					// System.out.println(mobileAgent);
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}
}
