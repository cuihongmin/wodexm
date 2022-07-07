package com.wanqiao.mogao.project.equipmentindexrelat.mapper;

import java.util.List;
import com.wanqiao.mogao.project.equipmentindexrelat.domain.BusEquipmentIndexRelat;

/**
 * 设备和指标关联关系Mapper接口
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public interface BusEquipmentIndexRelatMapper 
{
    /**
     * 查询设备和指标关联关系
     * 
     * @param id 设备和指标关联关系ID
     * @return 设备和指标关联关系
     */
    public BusEquipmentIndexRelat selectBusEquipmentIndexRelatById(Long id);

    /**
     * 查询设备和指标关联关系列表
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 设备和指标关联关系集合
     */
    public List<BusEquipmentIndexRelat> selectBusEquipmentIndexRelatList(BusEquipmentIndexRelat busEquipmentIndexRelat);

    /**
     * 新增设备和指标关联关系
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 结果
     */
    public int insertBusEquipmentIndexRelat(BusEquipmentIndexRelat busEquipmentIndexRelat);

    /**
     * 修改设备和指标关联关系
     * 
     * @param busEquipmentIndexRelat 设备和指标关联关系
     * @return 结果
     */
    public int updateBusEquipmentIndexRelat(BusEquipmentIndexRelat busEquipmentIndexRelat);

    /**
     * 删除设备和指标关联关系
     * 
     * @param id 设备和指标关联关系ID
     * @return 结果
     */
    public int deleteBusEquipmentIndexRelatById(Long id);

    /**
     * 批量删除设备和指标关联关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusEquipmentIndexRelatByIds(Long[] ids);
}
