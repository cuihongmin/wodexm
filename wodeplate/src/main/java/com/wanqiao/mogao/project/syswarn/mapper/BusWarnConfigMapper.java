package com.wanqiao.mogao.project.syswarn.mapper;

import java.util.List;

import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfig;

/**
 * 预警设置Mapper接口
 * 
 * @author cjj
 * @date 2021-05-10
 */
public interface BusWarnConfigMapper 
{
    /**
     * 查询预警设置
     * 
     * @param id 预警设置ID
     * @return 预警设置
     */
    public BusWarnConfig selectBusWarnConfigById(Long id);

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
    public int insertBusWarnConfig(BusWarnConfig busWarnConfig);

    /**
     * 修改预警设置
     * 
     * @param busWarnConfig 预警设置
     * @return 结果
     */
    public int updateBusWarnConfig(BusWarnConfig busWarnConfig);

    /**
     * 删除预警设置
     * 
     * @param id 预警设置ID
     * @return 结果
     */
    public int deleteBusWarnConfigById(Long id);

    /**
     * 批量删除预警设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusWarnConfigByIds(Long[] ids);

    List<BusAlarmWay> findAlarmWayByConfigId(Long id);

    List<BusSafetyPeople> findSafteyPeopleByConfigId(Long id);
}
