package com.wanqiao.mogao.project.syswarn.service;

import java.util.List;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSafetyPeople;

/**
 * 预警配置和安全员关系表Service接口
 * 
 * @author cjj
 * @date 2021-05-10
 */
public interface IBusWarnSafetyPeopleService 
{
    /**
     * 查询预警配置和安全员关系表
     * 
     * @param id 预警配置和安全员关系表ID
     * @return 预警配置和安全员关系表
     */
    public BusWarnSafetyPeople selectBusWarnSafetyPeopleById(Long id);

    /**
     * 查询预警配置和安全员关系表列表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 预警配置和安全员关系表集合
     */
    public List<BusWarnSafetyPeople> selectBusWarnSafetyPeopleList(BusWarnSafetyPeople busWarnSafetyPeople);

    /**
     * 新增预警配置和安全员关系表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 结果
     */
    public int insertBusWarnSafetyPeople(BusWarnSafetyPeople busWarnSafetyPeople);

    /**
     * 修改预警配置和安全员关系表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 结果
     */
    public int updateBusWarnSafetyPeople(BusWarnSafetyPeople busWarnSafetyPeople);

    /**
     * 批量删除预警配置和安全员关系表
     * 
     * @param ids 需要删除的预警配置和安全员关系表ID
     * @return 结果
     */
    public int deleteBusWarnSafetyPeopleByIds(Long[] ids);

    /**
     * 删除预警配置和安全员关系表信息
     * 
     * @param id 预警配置和安全员关系表ID
     * @return 结果
     */
    public int deleteBusWarnSafetyPeopleById(Long id);
}
