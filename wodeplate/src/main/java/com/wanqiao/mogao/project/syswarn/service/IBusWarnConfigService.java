package com.wanqiao.mogao.project.syswarn.service;

import java.util.List;

import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfig;

/**
 * 预警设置Service接口
 * 
 * @author cjj
 * @date 2021-05-10
 */
public interface IBusWarnConfigService 
{
    /**
     * 查询预警设置
     * 
     * @param id 预警设置ID
     * @return 预警设置
     */
    //public BusWarnConfig selectBusWarnConfigById(Long id);

    /**
     * 查询预警设置列表
     * 
     * @param busWarnConfig 预警设置
     * @return 预警设置集合
     */
    public List<BusWarnConfig> selectBusWarnConfigList(BusWarnConfig busWarnConfig);

    /**
     * 新增预警设置
     * 
     * @param busWarnConfig 预警设置
     * @return 结果
     */
   // public int insertBusWarnConfig(BusWarnConfig busWarnConfig);

    /**
     * 修改预警设置
     * 
     * @param busWarnConfig 预警设置
     * @return 结果
     */
    //public int updateBusWarnConfig(BusWarnConfig busWarnConfig);

    /**
     * 批量删除预警设置
     * 
     * @param ids 需要删除的预警设置ID
     * @return 结果
     */
    //public int deleteBusWarnConfigByIds(Long[] ids);

    /**
     * 删除预警设置信息
     * 
     * @param id 预警设置ID
     * @return 结果
     */
    //public int deleteBusWarnConfigById(Long id);

    /**
     * 编辑执行动作
     * @param id
     * @param execWayIds
     * @return
     */
    int endExecuteExecuteWay(Long id, String execWayIds);

    /**
     * 编辑安全人员
     * @param id
     * @param peopleIds
     * @return
     */
    int endSafetyPeople(Long id, String peopleIds);

    List<BusAlarmWay> findAlarmWayByConfigId(Long id);

    List<BusSafetyPeople> findSafteyPeopleByConfigId(Long id);
}
