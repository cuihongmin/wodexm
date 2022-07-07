package com.cui.user.dao;

import com.cui.user.entity.SmsSendLogs;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SmsSendLogsDao {
	/**
	 * 获得SmsSendLogs数据的总行数
	 * @return
	 */
    long getSmsSendLogsRowCount();
	/**
	 * 获得SmsSendLogs数据集合
	 * @return
	 */
    List<SmsSendLogs> selectSmsSendLogs();
	/**
	 * 获得一个SmsSendLogs对象,以参数SmsSendLogs对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    SmsSendLogs selectSmsSendLogsByObj(SmsSendLogs obj);
	/**
	 * 通过SmsSendLogs的id获得SmsSendLogs对象
	 * @param id
	 * @return
	 */
    SmsSendLogs selectSmsSendLogsById(Integer id);
	/**
	 * 插入SmsSendLogs到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmsSendLogs(SmsSendLogs value);
	/**
	 * 插入SmsSendLogs中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptySmsSendLogs(SmsSendLogs value);
	/**
	 * 批量插入SmsSendLogs到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertSmsSendLogsByBatch(List<SmsSendLogs> value);
	/**
	 * 通过SmsSendLogs的id删除SmsSendLogs
	 * @param id
	 * @return
	 */
    int deleteSmsSendLogsById(Integer id);
	/**
	 * 通过SmsSendLogs的id更新SmsSendLogs中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateSmsSendLogsById(SmsSendLogs enti);
	/**
	 * 通过SmsSendLogs的id更新SmsSendLogs中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptySmsSendLogsById(SmsSendLogs enti);
}