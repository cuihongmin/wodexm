package com.wanqiao.mogao.project.equipmentmanage.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.equipmentmanage.mapper.BusOsmometerMapper;
import com.wanqiao.mogao.project.equipmentmanage.domain.BusOsmometer;
import com.wanqiao.mogao.project.equipmentmanage.service.IBusOsmometerService;
import javax.annotation.Resource;

/**
 * 渗压计Service业务层处理
 *
 * @author cjj
 * @date 2021-05-10
 */
@Service
public class BusOsmometerServiceImpl implements IBusOsmometerService 
{
    @Resource
    private BusOsmometerMapper busOsmometerMapper;

    /**
     * 查询渗压计
     * 
     * @param id 渗压计ID
     * @return 渗压计
     */
    @Override
    public BusOsmometer selectBusOsmometerById(Long id)
    {
        return busOsmometerMapper.selectBusOsmometerById(id);
    }

    /**
     * 查询渗压计列表
     * 
     * @param busOsmometer 渗压计
     * @return 渗压计
     */
    @Override
    public List<BusOsmometer> selectBusOsmometerList(BusOsmometer busOsmometer)
    {
        return busOsmometerMapper.selectBusOsmometerList(busOsmometer);
    }

    /**
     * 新增渗压计
     * 
     * @param busOsmometer 渗压计
     * @return 结果
     */
    @Override
    public int insertBusOsmometer(BusOsmometer busOsmometer)
    {
        busOsmometer.setCreateTime(DateUtils.getNowDate());
        return busOsmometerMapper.insertBusOsmometer(busOsmometer);
    }

    /**
     * 修改渗压计
     * 
     * @param busOsmometer 渗压计
     * @return 结果
     */
    @Override
    public int updateBusOsmometer(BusOsmometer busOsmometer)
    {
        return busOsmometerMapper.updateBusOsmometer(busOsmometer);
    }

    /**
     * 批量删除渗压计
     * 
     * @param ids 需要删除的渗压计ID
     * @return 结果
     */
    @Override
    public int deleteBusOsmometerByIds(Long[] ids)
    {
        return busOsmometerMapper.deleteBusOsmometerByIds(ids);
    }

    /**
     * 删除渗压计信息
     * 
     * @param id 渗压计ID
     * @return 结果
     */
    @Override
    public int deleteBusOsmometerById(Long id)
    {
        return busOsmometerMapper.deleteBusOsmometerById(id);
    }
}
