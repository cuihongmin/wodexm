package com.wanqiao.mogao.project.syswarn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnConfigWarnTypeRelatMapper;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfigWarnTypeRelat;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnConfigWarnTypeRelatService;

/**
 * 预警配置和预警方式关系表Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-10
 */
@Service
public class BusWarnConfigWarnTypeRelatServiceImpl implements IBusWarnConfigWarnTypeRelatService 
{
    @Autowired
    private BusWarnConfigWarnTypeRelatMapper busWarnConfigWarnTypeRelatMapper;

    /**
     * 查询预警配置和预警方式关系表
     * 
     * @param id 预警配置和预警方式关系表ID
     * @return 预警配置和预警方式关系表
     */
    @Override
    public BusWarnConfigWarnTypeRelat selectBusWarnConfigWarnTypeRelatById(Long id)
    {
        return busWarnConfigWarnTypeRelatMapper.selectBusWarnConfigWarnTypeRelatById(id);
    }

    /**
     * 查询预警配置和预警方式关系表列表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 预警配置和预警方式关系表
     */
    @Override
    public List<BusWarnConfigWarnTypeRelat> selectBusWarnConfigWarnTypeRelatList(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        return busWarnConfigWarnTypeRelatMapper.selectBusWarnConfigWarnTypeRelatList(busWarnConfigWarnTypeRelat);
    }

    /**
     * 新增预警配置和预警方式关系表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 结果
     */
    @Override
    public int insertBusWarnConfigWarnTypeRelat(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        return busWarnConfigWarnTypeRelatMapper.insertBusWarnConfigWarnTypeRelat(busWarnConfigWarnTypeRelat);
    }

    /**
     * 修改预警配置和预警方式关系表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 结果
     */
    @Override
    public int updateBusWarnConfigWarnTypeRelat(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        return busWarnConfigWarnTypeRelatMapper.updateBusWarnConfigWarnTypeRelat(busWarnConfigWarnTypeRelat);
    }

    /**
     * 批量删除预警配置和预警方式关系表
     * 
     * @param ids 需要删除的预警配置和预警方式关系表ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnConfigWarnTypeRelatByIds(Long[] ids)
    {
        return busWarnConfigWarnTypeRelatMapper.deleteBusWarnConfigWarnTypeRelatByIds(ids);
    }

    /**
     * 删除预警配置和预警方式关系表信息
     * 
     * @param id 预警配置和预警方式关系表ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnConfigWarnTypeRelatById(Long id)
    {
        return busWarnConfigWarnTypeRelatMapper.deleteBusWarnConfigWarnTypeRelatById(id);
    }
}
