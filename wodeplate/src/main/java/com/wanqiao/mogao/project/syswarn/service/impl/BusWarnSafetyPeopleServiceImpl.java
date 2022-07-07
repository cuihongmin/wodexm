package com.wanqiao.mogao.project.syswarn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnSafetyPeopleMapper;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSafetyPeople;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnSafetyPeopleService;

/**
 * 预警配置和安全员关系表Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-10
 */
@Service
public class BusWarnSafetyPeopleServiceImpl implements IBusWarnSafetyPeopleService 
{
    @Autowired
    private BusWarnSafetyPeopleMapper busWarnSafetyPeopleMapper;

    /**
     * 查询预警配置和安全员关系表
     * 
     * @param id 预警配置和安全员关系表ID
     * @return 预警配置和安全员关系表
     */
    @Override
    public BusWarnSafetyPeople selectBusWarnSafetyPeopleById(Long id)
    {
        return busWarnSafetyPeopleMapper.selectBusWarnSafetyPeopleById(id);
    }

    /**
     * 查询预警配置和安全员关系表列表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 预警配置和安全员关系表
     */
    @Override
    public List<BusWarnSafetyPeople> selectBusWarnSafetyPeopleList(BusWarnSafetyPeople busWarnSafetyPeople)
    {
        return busWarnSafetyPeopleMapper.selectBusWarnSafetyPeopleList(busWarnSafetyPeople);
    }

    /**
     * 新增预警配置和安全员关系表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 结果
     */
    @Override
    public int insertBusWarnSafetyPeople(BusWarnSafetyPeople busWarnSafetyPeople)
    {
        return busWarnSafetyPeopleMapper.insertBusWarnSafetyPeople(busWarnSafetyPeople);
    }

    /**
     * 修改预警配置和安全员关系表
     * 
     * @param busWarnSafetyPeople 预警配置和安全员关系表
     * @return 结果
     */
    @Override
    public int updateBusWarnSafetyPeople(BusWarnSafetyPeople busWarnSafetyPeople)
    {
        return busWarnSafetyPeopleMapper.updateBusWarnSafetyPeople(busWarnSafetyPeople);
    }

    /**
     * 批量删除预警配置和安全员关系表
     * 
     * @param ids 需要删除的预警配置和安全员关系表ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnSafetyPeopleByIds(Long[] ids)
    {
        return busWarnSafetyPeopleMapper.deleteBusWarnSafetyPeopleByIds(ids);
    }

    /**
     * 删除预警配置和安全员关系表信息
     * 
     * @param id 预警配置和安全员关系表ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnSafetyPeopleById(Long id)
    {
        return busWarnSafetyPeopleMapper.deleteBusWarnSafetyPeopleById(id);
    }
}
