package com.wanqiao.mogao.project.equipmentmanage.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.equipmentmanage.mapper.BusSaturationLineDataMapper;
import com.wanqiao.mogao.project.equipmentmanage.domain.BusSaturationLineData;
import com.wanqiao.mogao.project.equipmentmanage.service.IBusSaturationLineDataService;

/**
 * 浸润线通过渗压计采集数据计算的来Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-11
 */
@Service
public class BusSaturationLineDataServiceImpl implements IBusSaturationLineDataService 
{
    @Autowired
    private BusSaturationLineDataMapper busSaturationLineDataMapper;

    /**
     * 查询浸润线通过渗压计采集数据计算的来
     * 
     * @param id 浸润线通过渗压计采集数据计算的来ID
     * @return 浸润线通过渗压计采集数据计算的来
     */
    @Override
    public BusSaturationLineData selectBusSaturationLineDataById(Long id)
    {
        return busSaturationLineDataMapper.selectBusSaturationLineDataById(id);
    }

    /**
     * 查询浸润线通过渗压计采集数据计算的来列表
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 浸润线通过渗压计采集数据计算的来
     */
    @Override
    public List<BusSaturationLineData> selectBusSaturationLineDataList(BusSaturationLineData busSaturationLineData)
    {
        return busSaturationLineDataMapper.selectBusSaturationLineDataList(busSaturationLineData);
    }

    /**
     * 新增浸润线通过渗压计采集数据计算的来
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 结果
     */
    @Override
    public int insertBusSaturationLineData(BusSaturationLineData busSaturationLineData)
    {
        busSaturationLineData.setCreateTime(DateUtils.getNowDate());
        return busSaturationLineDataMapper.insertBusSaturationLineData(busSaturationLineData);
    }

    /**
     * 修改浸润线通过渗压计采集数据计算的来
     * 
     * @param busSaturationLineData 浸润线通过渗压计采集数据计算的来
     * @return 结果
     */
    @Override
    public int updateBusSaturationLineData(BusSaturationLineData busSaturationLineData)
    {
        return busSaturationLineDataMapper.updateBusSaturationLineData(busSaturationLineData);
    }

    /**
     * 批量删除浸润线通过渗压计采集数据计算的来
     * 
     * @param ids 需要删除的浸润线通过渗压计采集数据计算的来ID
     * @return 结果
     */
    @Override
    public int deleteBusSaturationLineDataByIds(Long[] ids)
    {
        return busSaturationLineDataMapper.deleteBusSaturationLineDataByIds(ids);
    }

    /**
     * 删除浸润线通过渗压计采集数据计算的来信息
     * 
     * @param id 浸润线通过渗压计采集数据计算的来ID
     * @return 结果
     */
    @Override
    public int deleteBusSaturationLineDataById(Long id)
    {
        return busSaturationLineDataMapper.deleteBusSaturationLineDataById(id);
    }
}
