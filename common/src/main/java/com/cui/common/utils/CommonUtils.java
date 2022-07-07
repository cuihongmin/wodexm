package com.cui.common.utils;

import com.cui.common.valid.IdCardValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description: 通用工具类，提供SQL防止注入过滤以及常规字符串和时间等处理 </p> 
 * <p>Company:华夏信财互联网金融信息有限公司</p>
 * @author xianlong@huaxiafinance.com
 * @version V1.0
 */
public class CommonUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(com.cui.common.utils.CommonUtils.class);
	
	private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

	private static Pattern sqlPattern = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);

	public static final String PhoneNumPattern = "1[23456789]\\d{9}";
	/**
	 * 电话号码正则表达式
	 */
	private static final Pattern mobilePattern = Pattern.compile(PhoneNumPattern);
	
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
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String...mobile) {
		if (mobile == null || mobile.length == 0){
			return false;
		}
		for (String str : mobile){
			if (!mobilePattern.matcher(str).find()) {
				return false;
			} else if (str.length() != 11) {
				return false;
			}
		}
		return true;
	}

	private static Random rand = new Random();

	/**
	 * 生成随机码
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
	 * @param collection 需要判空的集合
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
	 * @param collection 需要判空的集合
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
	 * @param character 需要检验的字符串
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
	 * @param mobile  需要检查的号码
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
	
	/**
	 * 验证固定电话号码
	 * @param fixedPhone
	 * @return
	 */
	public static boolean isFixedPhone(String fixedPhone){
		String reg="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
				"(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		return Pattern.matches(reg, fixedPhone);
	}

	/***
	 * <p>@Description: 获取32位GUID </p> 
	 * @Company:华夏信财互联网金融信息有限责任公司
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date  2016年3月28日 下午2:04:14
	 * @version V1.0
	 */
	public static String getUUId(){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			UUID uuid = UUID.randomUUID();
			String guidStr = uuid.toString();
			messageDigest.update(guidStr.getBytes(), 0, guidStr.length());
			return new BigInteger(1, messageDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("获取32位GUID异常：",e);
			return null;
		}
	}
	
	/***
	 * <P>@Description: 根据指定的内容将 需替换 的内容替换为 需要的内容</P> 
	 * @param content 指定的内容
	 * @param replace 替换前的内容
	 * @param replaceAs 替换后的内容
	 * @return 返回需要的内容
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月1日 下午5:02:31
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String replaceAll(String content,String replace,String replaceAs) {
		return content.replaceAll(replace,replaceAs);
	}
	
	
	
	
	/**
	 * <p>
	 * Description:获取实际路径的集合，该方法内部已实现了检查实际路径是否存在的验证
	 * </p>
	 * @date 2016-4-15 下午10:35:35
	 * @author 吴轶雷
	 * @param filesPaths 文件数据
	 * @param webRootPath web根路径
	 * @param flagExpt 是否需要抛出异常
	 * @return 返回文件数据集合
	 * @throws Exception
	 */
	public static List<String> getFilesPaths(Object filesPaths,String rootPath,boolean flagExpt) throws Exception{
		String[] fpts=null;//实际文件路径
		if(filesPaths!=null&&!filesPaths.equals("")){
			if(filesPaths instanceof String []){
				fpts=(String[])filesPaths;
			}else{
				fpts=new String[1];
				fpts[0]=(String)filesPaths;
			}
		}
		List<String> fptsList=null;
		//进来判断实际文件路径如果不是空字符串的话是否存在
		if(fpts!=null&&fpts.length>0){
			for(int t=0;t<fpts.length;t++){
				if(!fpts[t].trim().equals("")){
					//组装相对路径成文件绝对路径，并File对象
					String absFilePath=rootPath+fpts[t];
					File file=new File(absFilePath);
					if(file.exists()){
						if(null==fptsList){
							fptsList=new ArrayList<String>();
						}
						fptsList.add(fpts[t]);
					}else{
						//判断标记是否需要抛出异常
						if(flagExpt){
							throw new Exception(fpts[t]+"的实际路径不存在!");
						}
					}
				}
			}
		}
		return fptsList;
	}
	
	/**
	 * 
	 * <p>Description:获取枚举值的描述</p>
	 * @date 2016年11月16日 下午5:41:56
	 * @author 吴轶雷
	 * @param cs 枚举的class
	 * @param value 枚举值
	 * @return
	 * @throws Exception
	 */
	public static <E> String getEnumDesc(Class cs,Object value)throws Exception{
		if(null!=value){
			E[] e=(E[]) cs.getMethod("values",null).invoke(cs,null);
			if(null!=e&&e.length>0){
				for (E t :e) {
					if(t.getClass().getMethod("getValue",null).invoke(t,null).toString().equals(value.toString())){
						return t.getClass().getMethod("getDesc",null).invoke(t,null).toString();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description:获取枚举值的url</p>
	 * @date 2016年11月16日 下午5:41:56
	 * @author 吴轶雷
	 * @param cs 枚举的class
	 * @param value 枚举值
	 * @return
	 * @throws Exception
	 */
	public static <E> String getEnumUrl(Class cs,Object value) throws Exception{
		if(null!=value){
			E[] e=(E[]) cs.getMethod("values",null).invoke(cs,null);
			if(null!=e&&e.length>0){
				for (E t :e) {
					if(t.getClass().getMethod("getValue",null).invoke(t,null).toString().equals(value.toString())){
						return t.getClass().getMethod("getUrl",null).invoke(t,null).toString();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description:根据身份证获取用户性别标识</p>
	 * @date 2016年11月30日 上午11:48:13
	 * @author 吴轶雷
	 * @param idCard 身份证
	 * @return
	 */
	public static Integer getSexByIdCard(String idCard){
		if (idCard.length() == 15) {
			idCard = IdCardValid.convertIdcarBy15bit(idCard);
		}
		String id17 = idCard.substring(16, 17);
		if (Integer.parseInt(id17) % 2 != 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * 检查证件是否成年
	 * @author 吴轶雷
	 * @param idCard
	 * @return
	 * @throws ParseException
	 */
	public static Boolean checkCnIdCard(String idCard) throws ParseException{
		if (idCard.length() == 15) {
			idCard = IdCardValid.convertIdcarBy15bit(idCard);
		}
		int year = Integer.parseInt(idCard.substring(6, 10));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Date update = sdf.parse(String.valueOf(year + 18) + idCard.substring(10, 14));
	    Date today = new Date();
	    return today.after(update);
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(checkCnIdCard("310110199906083254"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>Description:根据身份证获取出生日期</p>
	 * @date 2016年12月9日 下午6:11:01
	 * @author 吴轶雷
	 * @param idCard 身份证
	 * @return
	 * @return
	 * @throws ParseException
	 */
	public static Date getBirthday(String idCard) throws ParseException{
		if (idCard.length() == 15) {
			idCard = IdCardValid.convertIdcarBy15bit(idCard);
		}
		String birthday = idCard.substring(6, 14);
		return new SimpleDateFormat("yyyyMMdd").parse(birthday);
	}
	
	/**
	 * 
	 * <p>Description:手机号掩码</p>
	 * @date 2016年12月21日 下午4:25:28
	 * @author 吴轶雷
	 * @param mobile
	 * @return
	 */
	public static String getMobileMark(String mobile){
		if(null!=mobile&&mobile.length()==11){
			String mobileMark=mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
			return mobileMark;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 
	 * <p>Description:邮箱掩码</p>
	 * @date 2016年12月21日 下午4:28:00
	 * @author 吴轶雷
	 * @param mobile
	 * @return
	 */
	public static String getEmailMark(String email){
		if(null!=email&&email.length()>4){
			return email.substring(0,email.indexOf("@")-3)+"***"+email.substring(email.indexOf("@"),email.length());
		}else{
			return null;
		}
	}
}

