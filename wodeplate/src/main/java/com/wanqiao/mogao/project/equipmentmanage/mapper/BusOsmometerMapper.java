package com.wanqiao.mogao.project.equipmentmanage.mapper;

import java.util.List;
import com.wanqiao.mogao.project.equipmentmanage.domain.BusOsmometer;

/**
 * 渗压计Mapper接口
 * 
 * @author cjj
 * @date 2021-05-10
 */
public interface BusOsmometerMapper 
{
    /**
     * 查询渗压计
     * 
     * @param id 渗压计ID
     * @return 渗压计
     */
    public BusOsmometer selectBusOsmometerById(Long id);

    /**
     * 查询渗压计列表
     * 
     * @param busOsmometer 渗压计
     * @return 渗压计集合
     */
    public List<BusOsmometer> selectBusOsmometerList(BusOsmometer busOsmometer);

    /**
     * 新增渗压计
     * 
     * @param busOsmometer 渗压计
     * @return 结果
     */
    public int insertBusOsmometer(BusOsmometer busOsmometer);

    /**
     * 修改渗压计
     * 
     * @param busOsmometer 渗压计
     * @return 结果
     */
    public int updateBusOsmometer(BusOsmometer busOsmometer);

    /**
     * 删除渗压计
     * 
     * @param id 渗压计ID
     * @return 结果
     */
    public int deleteBusOsmometerById(Long id);

    /**
     * 批量删除渗压计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusOsmometerByIds(Long[] ids);
}
