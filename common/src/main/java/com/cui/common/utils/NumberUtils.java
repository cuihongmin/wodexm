package com.cui.common.utils;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;


/**
 * <p>
 * Description: 数据格式工具类
 * </p>
 * <p>
 * Company:华夏信财投资管理(上海)有限责任公司
 * </p>
 * 
 * @author xianlong@huaxiafinance.com
 * @version V1.0
 */
public class NumberUtils {

	private static Logger LOGGER = Logger.getLogger(com.cui.common.utils.NumberUtils.class);

	/**
	 * 汉语中数字大写
	 */
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
			"伍", "陆", "柒", "捌", "玖" };
	/**
	 * 汉语中货币单位大写，这样的设计类似于占位符
	 */
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
			"拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
			"佰", "仟" };
	
	private static final String[] CN_UPPER_MONETRAY_MAP_KEY = { "fen", "jiao", "yaun",
		"ten", "hundred", "thousand", "tenThousand", "hundredThousand", "million", "tenMillion", "hundredMillion"};
	
	/**
	 * 特殊字符：整
	 */
	private static final String CN_FULL = "整";
	/**
	 * 特殊字符：负
	 */
	private static final String CN_NEGATIVE = "负";
	/**
	 * 金额的精度，默认值为2
	 */
	private static final int MONEY_PRECISION = 2;
	/**
	 * 特殊字符：零元整
	 */
	private static final String CN_ZEOR_FULL = "零元" + CN_FULL;
	
	

	private final static int NUMBER_PERCISION_0 = 0;
	/** 保留四位有效数字的静态值 **/
	private final static int NUMBER_PERCISION_2 = 2;
	/** 保留三位位有效数字的静态值 **/
	private final static int NUMBER_PERCISION_3 = 3;
	/** 保留四位有效数字的静态值 **/
	private final static int NUMBER_PERCISION_4 = 4;

	/**
	 * 格式化为指定两位小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
	 * 
	 * @param String类型的数字对象
	 * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
	 */
	public static String stringNumber2(String number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_2, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	public static void main(String[] args) {
		System.out.println(stringNumber2("123"));
	}
	
	/**
	 * 格式化为指定四位小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
	 * 
	 * @param String类型的数字对象
	 * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
	 */
	public static String stringNumber4(String number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_4, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 格式化为指定位两小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。<br>
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
	 * 如果给定的数字没有小数，则转换之后将以0填充；例如：int 123 1 --> 123.0<br>
	 * 
	 * @param String类型的数字对象
	 * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
	 */
	public static String number2String2(Number number) {
		return stringNumber2(String.valueOf(number));
	}

	/**
	 * 格式化为指定位四小数的数字,返回未使用科学计数法表示的具有指定位数的字符串。<br>
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
	 * 如果给定的数字没有小数，则转换之后将以0填充；例如：int 123 1 --> 123.0<br>
	 * 
	 * @param String类型的数字对象
	 * @return 返回数字格式化后的字符串表示形式(注意返回的字符串未使用科学计数法)
	 */
	public static String number2String4(Number number) {
		return stringNumber4(String.valueOf(number));
	}

	/**
	 * 对double类型的数值保留指定位数的小数。<br>
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @param precision
	 *            小数位数
	 * @return double 如果数值较大，则使用科学计数法表示
	 */
	public static double doubleNumber(double number, int precision) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 对float类型的数值保留指定位数的小数。<br>
	 * 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @param precision
	 *            小数位数
	 * @return float 如果数值较大，则使用科学计数法表示
	 */
	public static float float2number(float number, int precision) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(precision, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 对double类型的数值保留指定 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return double 如果数值较大，则使用科学计数法表示
	 */
	public static String double2Str(double number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_0, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 对double类型的数值保留指定2位数的小数 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return double 如果数值较大，则使用科学计数法表示
	 */
	public static String double2String2(Double number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_2, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 对double类型的数值保留指定4位数的小数 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return double 如果数值较大，则使用科学计数法表示
	 */
	public static String double2String4(Double number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_4, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/***
	 * BigDecimal 转换为 string 保留小数点后0位有效数字
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return
	 */
	public static String bigToString2(BigDecimal bigDecimal) {
		if (null != bigDecimal) {
			return bigDecimal.setScale(NUMBER_PERCISION_2,
					BigDecimal.ROUND_HALF_UP).toString();
		}
		return "";
	}

	/***
	 * BigDecimal 转换为 string 保留小数点后两位有效数字
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return
	 */
	public static String bigToString(BigDecimal bigDecimal) {
		if (null != bigDecimal) {
			return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
		}
		return "";
	}

	/***
	 * BigDecimal 转换为 string 保留小数点后四位有效数字
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return string 类型
	 */
	public static String bigToString4(BigDecimal bigDecimal) {
		if (null != bigDecimal) {
			return bigDecimal.setScale(NUMBER_PERCISION_4,
					BigDecimal.ROUND_HALF_UP).toString();
		}
		return "";
	}

	/***
	 * BigDecimal 转换为 double 保留小数点后两位有效数字
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return string 类型
	 */
	public static double bigToDouble2(BigDecimal bigDecimal) {
		if (null != bigDecimal) {
			return bigDecimal.setScale(NUMBER_PERCISION_2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0.0;

	}

	/***
	 * BigDecimal 转换为 double 保留小数点后四位有效数字
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return double 类型
	 */
	public static double bigToDouble4(BigDecimal bigDecimal) {
		if (null != bigDecimal) {
			return bigDecimal.setScale(NUMBER_PERCISION_4,
					BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0.0;
	}

	/***
	 * BigDecimal 转换为 double
	 * 
	 * @param bigDecimal
	 *            表示十进制位的数
	 * @return double 类型
	 */
	public static double bigToDouble(BigDecimal bigDecimal) {
		return bigDecimal.doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param number1
	 *            被除数
	 * @param number2
	 *            除数
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double number1, double number2, int scale) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(number1));
		BigDecimal b2 = new BigDecimal(Double.toString(number2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 对double类型的数值保留指定2%位数的小数 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return String
	 */
	public static String double2Percentage(double number) {
		return div(number, 1, NUMBER_PERCISION_4) * 100 + "";
	}

	/**
	 * 对BigDecimal类型的数值保留指定2%位数的小数 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return String
	 */
	public static String big2Percent(BigDecimal bigDecimal) {

		double number = 0.0;
		if (bigDecimal == null) {
			return "";
		} else {
			number = bigToDouble4(bigDecimal);
			return div(number, 1, NUMBER_PERCISION_4) * 100 + "";
		}
	}

	/**
	 * 对double类型的数值保留指定3位数的小数 转为 String类型。<br>
	 * 
	 * @param number
	 *            要保留小数的数字
	 * @return double 如果数值较大，则使用科学计数法表示
	 */
	public static String doubleString3(Double number) {
		BigDecimal bg = new BigDecimal(number);
		return bg.setScale(NUMBER_PERCISION_3, BigDecimal.ROUND_HALF_UP)
				.toPlainString();
	}

	/**
	 * 
	 * <p>
	 * Description:String型数据 转 BigDecimal格式数据
	 * </p>
	 * 
	 * @param data
	 * @date 2016年2月1日 下午4:05:02
	 */
	public static BigDecimal bigDecimalString(String charData) {
		LOGGER.info(String.format("String型数据 转 BigDecimal格式数据charData[%s]",
				charData));
		try {
			if (charData == null || charData.length() == 0
					|| "null".equals(charData)) {
				return BigDecimal.ZERO;
			} else {
				return new BigDecimal(charData);
			}
		} catch (Exception e) {
			LOGGER.error(String.format(
					"String型数据 转 BigDecimal格式数据charData[%s]", charData), e);
			return BigDecimal.ZERO;
		}

	}

	private static final String[] capitalMoneyNumber = { "", "壹", "贰", "叁",
			"肆", "伍", "陆", "柒", "捌", "玖" };

	/***
	 * <p>
	 * @Description:
	 * </p>
	 * 
	 * @param paramObject
	 * @param paramString1
	 * @param paramString2
	 * @return
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年4月19日 下午3:03:21
	 * @Company:华夏信财互联网金融信息有限责任公司
	 * @version V1.0
	 */

	public static String format(Object paramObject, String paramString) {
		return format(paramObject, paramString, null);
	}

	public static String format(Object paramObject, String paramString1,
			String paramString2) {
		if (paramObject == null)
			return null;
		try {
			String str = paramObject.toString();
			BigDecimal localBigDecimal = new BigDecimal(str);
			if (paramString1.equalsIgnoreCase("capital"))
				return convertDigitToChineseCharacter(str, false);
			if (paramString1.equalsIgnoreCase("capitalMoney"))
				return convertMoneyToCapital(localBigDecimal);
			DecimalFormat localDecimalFormat = (DecimalFormat) (CommonUtils
					.isEmpty(paramString2) ? DecimalFormat
					.getInstance(new Locale(paramString2)) : DecimalFormat
					.getInstance());
			localDecimalFormat.applyPattern(paramString1);
			return localDecimalFormat.format(localBigDecimal);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static String convertMoneyToCapital(BigDecimal paramBigDecimal) {
		BigDecimal localBigDecimal = paramBigDecimal.setScale(5, 4).abs();
		if (localBigDecimal.compareTo(new BigDecimal(0)) == 0)
			return "零圆";
		String str1 = localBigDecimal.toString();
		int i = str1.indexOf(".");
		String str2 = i == -1 ? str1 : str1.substring(0, i);
		String str3 = "";
		if (i != -1)
			str3 = str1.substring(i + 1);
		String str4 = convertDigitToChineseCharacter(str2, true);
		if (str4.startsWith("壹拾"))
			str4 = str4.substring(1);
		if (!str4.equalsIgnoreCase(""))
			str4 = str4 + "圆";
		if (paramBigDecimal.compareTo(new BigDecimal("0")) < 0)
			str4 = "负" + str4;
		if ((i == -1) || (str3.equals("")) || (Integer.parseInt(str3) == 0)) {
			str4 = str4 + "整";
		} else {
			String[] arrayOfString = { "角", "分", "厘" };
			int k = 0;
			String str5 = "";
			int m = str3.length() > 3 ? 3 : str3.length();
			for (int n = m - 1; n >= 0; n--) {
				int j = Integer.valueOf(str3.substring(n, n + 1)).intValue();
				if (j != 0) {
					str5 = capitalMoneyNumber[j] + arrayOfString[n] + str5;
					k = 1;
				} else if (k != 0) {
					str5 = "零" + str5;
					k = 0;
				}
			}
			str4 = str4 + str5;
		}
		return str4;
	}

	public static String convertDigitToChineseCharacter(int paramInt) {
		return convertDigitToChineseCharacter(Integer.toString(paramInt), false);
	}

	private static String convertDigitToChineseCharacter(String paramString,
			boolean paramBoolean) {
		if (paramString.equals("0"))
			return "";
		String[] arrayOfString1 = { "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿",
				"拾", "佰", "仟", "万", "拾", "佰", "仟", "兆", "拾", "佰", "仟", "万",
				"拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "京" };
		String[] arrayOfString2 = { "十", "百", "千", "万", "十", "百", "千", "亿",
				"十", "百", "千", "万", "十", "百", "千", "兆", "十", "百", "千", "万",
				"十", "百", "千", "亿", "十", "百", "千", "万", "十", "百", "千", "京" };
		String[] arrayOfString3 = { "", "一", "二", "三", "四", "五", "六", "七", "八",
				"九", (String) (paramBoolean ? capitalMoneyNumber : "十") };
		String[] arrayOfString4 = paramBoolean ? arrayOfString1
				: arrayOfString2;
		int j = paramString.length();
		StringBuffer localStringBuffer = new StringBuffer("");
		for (int k = 0; k < j; k++) {
			int i = Integer.parseInt(paramString.substring(j - k - 1, j - k));
			if (i == 0) {
				if ((k != 0) && (localStringBuffer.indexOf("零") != 0))
					localStringBuffer.insert(0, "零");
				if ((k > 0) && (k % 4 == 0))
					localStringBuffer.insert(0, arrayOfString2[(k - 1)]);
			} else if (k == 0) {
				localStringBuffer.append(capitalMoneyNumber[i]);
			} else {
				localStringBuffer.insert(0, arrayOfString3[i]
						+ arrayOfString4[(k - 1)]);
			}
		}
		String str = localStringBuffer.toString();
		for (int m = str.lastIndexOf("零"); m == localStringBuffer.length() - 1; m = str
				.lastIndexOf("零"))
			str = str.substring(0, m);
		return str;
	}

	/***
	 * <P>
	 * @Description: 生成一个6位数
	 * </P>
	 * 
	 * @return 返回一个6位数的验证码
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月1日 下午4:54:55
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static Integer randomNumSix() {
		Integer randomNum = (int) ((Math.random() * 9 + 1) * 100000);
		return randomNum;
	}

	/**
	 * <P>
	 * @Description: 得到6位长度的随机字符串
	 * </P>
	 * 
	 * @author xianlong@huaxiafinance.com
	 * @date 2016年9月1日 下午5:46:16
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static String randCode() {
		Random rand = new Random();
		int val = (int) (rand.nextDouble() * 1000000);
		String vcode = String.format("%6d", val).replace(" ", "0");
		if (vcode.substring(0, 1).equals("0")) {
			vcode = vcode.replaceFirst("0", "8");
		}
		return vcode;
	}
	
	  /**
     * 把输入的金额转换为汉语中人民币的大写
     * 
     * @param numberOfMoney
     *            输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
    
    /**
    * @Description: 获取拆分处理后的大写金额
    * @author wangzhiguo
    * @date  2017年8月7日 下午1:24:49
    * @param numberOfMoney
    * @return 【{元=壹, 拾=零, 角=壹, 分=柒, 佰=壹}】
     */
    public static Map<String,String> getCNMontrayMap(BigDecimal numberOfMoney) {
        Map<String,String> cnMontrayMap = new HashMap<String,String>();
        int signum = numberOfMoney.signum(); // 正负号
        // 零元整的情况
        if (signum == 0) {
        	cnMontrayMap.put("yuan", "零");
        	cnMontrayMap.put("jiao", "零");
        	cnMontrayMap.put("fen", "零");
            return cnMontrayMap;
        }
        if(numberOfMoney.compareTo(new BigDecimal(1))<0 
        		&& numberOfMoney.compareTo(new BigDecimal(0))>0){
        	cnMontrayMap.put("yuan", "零");
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
            cnMontrayMap.put(CN_UPPER_MONETRAY_MAP_KEY[0], CN_UPPER_NUMBER[0]);
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            String mapKey = "";
            String mapValue = "";
            
            // 处理大于0小于1的金额 
            if(numberOfMoney.compareTo(new BigDecimal(1))<0 
            		&& numberOfMoney.compareTo(new BigDecimal(0))>0){
            	mapKey = CN_UPPER_MONETRAY_MAP_KEY[numIndex];
            	mapValue = CN_UPPER_NUMBER[numUnit];
            } else{
            	if (numUnit > 0) {
                    if ((numIndex == 9) && (zeroSize >= 3)) {
                    	mapKey = CN_UPPER_MONETRAY_MAP_KEY[6];
                    } else if ((numIndex == 13) && (zeroSize >= 3)) {
                    	mapKey = CN_UPPER_MONETRAY_MAP_KEY[10];
                    } else{
                    	mapKey =  CN_UPPER_MONETRAY_MAP_KEY[numIndex];
                    }
                    mapValue = CN_UPPER_NUMBER[numUnit];
                    
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        //sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    	mapKey = CN_UPPER_MONETRAY_MAP_KEY[numIndex];
                    	mapValue = CN_UPPER_NUMBER[numUnit];
                    } else{
                    	mapKey = CN_UPPER_MONETRAY_MAP_KEY[numIndex];
                    	mapValue = CN_UPPER_NUMBER[numUnit];
                    }
                    
                    getZero = true;
                }
            }
            
            cnMontrayMap.put(mapKey, mapValue);
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
        	cnMontrayMap.put("jiao", "零");
        	cnMontrayMap.put("fen", "零");
        }
        return cnMontrayMap;
    }
    
}
