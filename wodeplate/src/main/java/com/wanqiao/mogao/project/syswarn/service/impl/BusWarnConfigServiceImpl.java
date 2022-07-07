package com.wanqiao.mogao.project.syswarn.service.impl;

import java.util.List;

import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfigWarnTypeRelat;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSafetyPeople;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnConfigWarnTypeRelatMapper;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnSafetyPeopleMapper;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnConfigMapper;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfig;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnConfigService;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * 预警设置Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-10
 */
@Service
public class BusWarnConfigServiceImpl implements IBusWarnConfigService 
{
    @Resource
    private BusWarnConfigMapper busWarnConfigMapper;

    @Resource
    private BusWarnConfigWarnTypeRelatMapper busWarnConfigWarnTypeRelatMapper;

    @Resource
    private BusWarnSafetyPeopleMapper busWarnSafetyPeopleMapper;
    /**
     * 查询预警设置
     * 
     * @param id 预警设置ID
     * @return 预警设置
     */
    /*@Override
    public BusWarnConfig selectBusWarnConfigById(Long id)
    {
        return busWarnConfigMapper.selectBusWarnConfigById(id);
    }
*/
    /**
     * 查询预警设置列表
     * 
     * @param busWarnConfig 预警设置
     * @return 预警设置
     */
    @Override
    public List<BusWarnConfig> selectBusWarnConfigList(BusWarnConfig busWarnConfig)
    {
        return busWarnConfigMapper.selectBusWarnConfigList(busWarnConfig);
    }

    @Transactional
    @Override
    public int endExecuteExecuteWay(Long id, String execWayIds) {
        busWarnConfigWarnTypeRelatMapper.deleteByConfigId(id);
        for(String execWayId : execWayIds.split(",")) {
            BusWarnConfigWarnTypeRelat relat = new BusWarnConfigWarnTypeRelat();
            relat.setWarnConfigId(id);
            relat.setWarnTypeId(Long.valueOf(execWayId));
            busWarnConfigWarnTypeRelatMapper.insertBusWarnConfigWarnTypeRelat(relat);
        }
        return 1;
    }

    @Transactional
    @Override
    public int endSafetyPeople(Long id, String peopleIds) {
        busWarnSafetyPeopleMapper.deleteByConfigId(id);
        for(String peopleId : peopleIds.split(",")) {
            BusWarnSafetyPeople people = new BusWarnSafetyPeople();
            people.setConfigId(id);
            people.setSafetyBy(Long.valueOf(peopleId));
            busWarnSafetyPeopleMapper.insertBusWarnSafetyPeople(people);
        }
        return 1;
    }

    @Override
    public List<BusAlarmWay> findAlarmWayByConfigId(Long id) {
        return busWarnConfigMapper.findAlarmWayByConfigId(id);
    }

    @Override
    public List<BusSafetyPeople> findSafteyPeopleByConfigId(Long id) {
        return busWarnConfigMapper.findSafteyPeopleByConfigId(id);
    }

    /**
     * 新增预警设置
     * 
     * @param busWarnConfig 预警设置
     * @return 结果
     */
    /*@Override
    public int insertBusWarnConfig(BusWarnConfig busWarnConfig)
    {
        busWarnConfig.setCreateTime(DateUtils.getNowDate());
        return busWarnConfigMapper.insertBusWarnConfig(busWarnConfig);
    }

    *//**
     * 修改预警设置
     * 
     * @param busWarnConfig 预警设置
     * @return 结果
     *//*
    @Override
    public int updateBusWarnConfig(BusWarnConfig busWarnConfig)
    {
        return busWarnConfigMapper.updateBusWarnConfig(busWarnConfig);
    }

    *//**
     * 批量删除预警设置
     * 
     * @param ids 需要删除的预警设置ID
     * @return 结果
     *//*
    @Override
    public int deleteBusWarnConfigByIds(Long[] ids)
    {
        return busWarnConfigMapper.deleteBusWarnConfigByIds(ids);
    }

    *//**
     * 删除预警设置信息
     * 
     * @param id 预警设置ID
     * @return 结果
     *//*
    @Override
    public int deleteBusWarnConfigById(Long id)
    {
        return busWarnConfigMapper.deleteBusWarnConfigById(id);
    }
*/
}
