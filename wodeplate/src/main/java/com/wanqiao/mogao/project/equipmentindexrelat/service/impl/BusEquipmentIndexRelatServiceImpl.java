package com.wanqiao.mogao.project.equipmentindexrelat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.equipmentindexrelat.mapper.BusEquipmentIndexRelatMapper;
import com.wanqiao.mogao.project.equipmentindexrelat.domain.BusEquipmentIndexRelat;
import com.wanqiao.mogao.project.equipmentindexrelat.service.IBusEquipmentIndexRelatService;

/**
 * 设备和指标关联关系Service业务层处理
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Service
public class BusEquipmentIndexRelatServiceImpl implements IBusEquipmentIndexRelatService 
{
    @Autowired
    private BusEquipmentIndexRelatMapper busEquipmentIndexRelatMapper;

    /**
     * 查询设备和指标关联关系
     * 
     * @param id 设备和指标关联关系ID
     * @return 设备和指标关联关系
     */
    @Override
    public BusEquipmentIndexRelat selectBusEquipmentIndexRelatById(Long id)
    {
        return busEquipmentIndexRelatMapper.selectBusEquipmentIndexRelatById(id);
    }

    /**
     * 查询设备和指标关联关系列表
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 设备和指标关联关系
     */
    @Override
    public List<BusEquipmentIndexRelat> selectBusEquipmentIndexRelatList(BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        return busEquipmentIndexRelatMapper.selectBusEquipmentIndexRelatList(busEquipmentIndexRelat);
    }

    /**
     * 新增设备和指标关联关系
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 结果
     */
    @Override
    public int insertBusEquipmentIndexRelat(BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        return busEquipmentIndexRelatMapper.insertBusEquipmentIndexRelat(busEquipmentIndexRelat);
    }

    /**
     * 修改设备和指标关联关系
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 结果
     */
    @Override
    public int updateBusEquipmentIndexRelat(BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        return busEquipmentIndexRelatMapper.updateBusEquipmentIndexRelat(busEquipmentIndexRelat);
    }

    /**
     * 批量删除设备和指标关联关系
     * 
     * @param ids 需要删除的设备和指标关联关系ID
     * @return 结果
     */
    @Override
    public int deleteBusEquipmentIndexRelatByIds(Long[] ids)
    {
        return busEquipmentIndexRelatMapper.deleteBusEquipmentIndexRelatByIds(ids);
    }

    /**
     * 删除设备和指标关联关系信息
     * 
     * @param id 设备和指标关联关系ID
     * @return 结果
     */
    @Override
    public int deleteBusEquipmentIndexRelatById(Long id)
    {
        return busEquipmentIndexRelatMapper.deleteBusEquipmentIndexRelatById(id);
    }
}
