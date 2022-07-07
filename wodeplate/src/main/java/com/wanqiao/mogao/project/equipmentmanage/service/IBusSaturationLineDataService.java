package com.wanqiao.mogao.project.equipmentmanage.service;

import java.util.List;
import com.wanqiao.mogao.project.equipmentmanage.domain.BusSaturationLineData;

/**
 * 浸润线通过渗压计采集数据计算的来Service接口
 * 
 * @author cjj
 * @date 2021-05-11
 */
public interface IBusSaturationLineDataService 
{
    /**
     * 查询浸润线通过渗压计采集数据计算的来
     * 
     * @param id 浸润线通过渗压计采集数据计算的来ID
     * @return 浸润线通过渗压计采集数据计算的来
     */
    public BusSaturationLineData selectBusSaturationLineDataById(Long id);

    /**
     * 查询浸润线通过渗压计采集数据计算的来列表
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 浸润线通过渗压计采集数据计算的来集合
     */
    public List<BusSaturationLineData> selectBusSaturationLineDataList(BusSaturationLineData busSaturationLineData);

    /**
     * 新增浸润线通过渗压计采集数据计算的来
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 结果
     */
    public int insertBusSaturationLineData(BusSaturationLineData busSaturationLineData);

    /**
     * 修改浸润线通过渗压计采集数据计算的来
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 结果
     */
    public int updateBusSaturationLineData(BusSaturationLineData busSaturationLineData);

    /**
     * 批量删除浸润线通过渗压计采集数据计算的来
     * 
     * @param ids 需要删除的浸润线通过渗压计采集数据计算的来ID
     * @return 结果
     */
    public int deleteBusSaturationLineDataByIds(Long[] ids);

    /**
     * 删除浸润线通过渗压计采集数据计算的来信息
     * 
     * @param id 浸润线通过渗压计采集数据计算的来ID
     * @return 结果
     */
    public int deleteBusSaturationLineDataById(Long id);
}
