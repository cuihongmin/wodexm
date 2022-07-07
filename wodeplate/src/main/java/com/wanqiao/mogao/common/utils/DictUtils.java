package com.wanqiao.mogao.common.utils;

import com.wanqiao.mogao.common.constant.Constants;
import com.wanqiao.mogao.common.utils.spring.SpringUtils;
import com.wanqiao.mogao.framework.redis.RedisCache;
import com.wanqiao.mogao.project.system.domain.SysDictData;

import java.util.Collection;
import java.util.List;

/**
 * 字典工具类
 *
 * @author wanqiao
 */
public class DictUtils {
    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas) {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key) {
        Object cacheObj = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj)) {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            return DictDatas;
        }
        return null;
    }

    /**
     * 通过字典的value获取字典的label
     * @param key
     * @param value
     * @return
     */
    public static String getDictByKeyAndValue(String key, String value) {
        Object cacheObj = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj)) {
            List<SysDictData> DictDatas = StringUtils.cast(cacheObj);
            for (SysDictData sysDictData : DictDatas) {
                if (value.equals(sysDictData.getDictValue())) {
                    return sysDictData.getDictLabel();
                }
            }
            return null;
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
