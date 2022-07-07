package com.cui.common.utils;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * <p>
 * @Description: 集合排序工具类
 * </p>
 * 
 * @author 沈显龙
 * @date 2016年6月29日 下午6:03:29
 * @Company:华夏信财投资管理(上海)有限责任公司
 * @version V1.0
 */
public class SortCollectionUtil {
	
	private static final Logger LOGGER = Logger.getLogger(com.cui.common.utils.SortCollectionUtil.class);

	/***
	 * <P>@Description:按键排序 </P> 
	 * @param dict 排序集合
	 * @author 沈显龙
	 * @date 2016年6月29日 下午6:04:58
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 * @param <T>
	 */
	public static <T> Map<String, List<T>> sortMapByKey(Map<String, List<T>> dict) {
		if (dict == null || dict.isEmpty()) {
			return null;
		}
		Map<String, List<T>> sortedMap = new TreeMap<String, List<T>>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				int intKey1 = 0, intKey2 = 0;
				try {
					intKey1 = getInt(key1);
					intKey2 = getInt(key2);
				} catch (Exception e) {
					intKey1 = 0;
					intKey2 = 0;
				}
				return intKey1 - intKey2;
			}
		});
		sortedMap.putAll(dict);
		dict = null;
		return sortedMap;
	}

	private static int getInt(String str) {
		int i = 0;
		try {
			Pattern p = Pattern.compile("^\\d+");
			Matcher m = p.matcher(str);
			if (m.find()) {
				i = Integer.valueOf(m.group());
			}
			p = null;
			m = null;
		} catch (NumberFormatException e) {
			LOGGER.error("sortMapByKey排序字符转换异常：",e);
		}
		return i;
	}

	/***
	 * <P>@Description: 按值排序</P> 
	 * @param oriMap 排序集合
	 * @author 沈显龙
	 * @date 2016年6月29日 下午6:06:02
	 * @Company:华夏信财投资管理(上海)有限责任公司
	 * @version V1.0
	 */
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Entry<String, String>> entryList = new ArrayList<Entry<String, String>>(oriMap.entrySet());
			Collections.sort(entryList,new Comparator<Entry<String, String>>() {
				public int compare(Entry<String, String> entry1,
						Entry<String, String> entry2) {
					int value1 = 0, value2 = 0;
					try {
						value1 = getInt(entry1.getValue());
						value2 = getInt(entry2.getValue());
					} catch (NumberFormatException e) {
						value1 = 0;
						value2 = 0;
					}
					return value2 - value1;
				}
			});
			Iterator<Entry<String, String>> iter = entryList.iterator();
			Entry<String, String> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
		}
		return sortedMap;
	}

}
