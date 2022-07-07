package com.wanqiao.mogao.project.sensor.classify.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.project.sensor.classify.domain.BusSensorClassify;
import com.wanqiao.mogao.project.sensor.classify.mapper.BusSensorClassifyMapper;
import com.wanqiao.mogao.project.sensor.classify.service.IBusSensorClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 传感器类型Service业务层处理
 * 
 * @author lixiangping
 * @date 2021-05-07
 */
@Service
public class BusSensorClassifyServiceImpl implements IBusSensorClassifyService
{
    @Autowired
    private BusSensorClassifyMapper busSensorClassifyMapper;

    /**
     * 查询传感器类型
     * 
     * @param id 传感器类型ID
     * @return 传感器类型
     */
    @Override
    public BusSensorClassify selectBusSensorClassifyById(Long id)
    {
        BusSensorClassify busSensorClassify = busSensorClassifyMapper.selectBusSensorClassifyById(id);
        return busSensorClassify;
    }

    /**
     * 查询传感器类型列表
     * 
     * @param busSensorClassify 传感器类型
     * @return 传感器类型
     */
    @Override
    public List<BusSensorClassify> selectBusSensorClassifyList(BusSensorClassify busSensorClassify)
    {
        return busSensorClassifyMapper.selectBusSensorClassifyList(busSensorClassify);
    }

    /**
     * 新增传感器类型
     * 
     * @param busSensorClassify 传感器类型
     * @return 结果
     */
    @Override
    public int insertBusSensorClassify(BusSensorClassify busSensorClassify)
    {
        busSensorClassify.setCreateTime(DateUtils.getNowDate());
        return busSensorClassifyMapper.insertBusSensorClassify(busSensorClassify);
    }

    /**
     * 修改传感器类型
     * 
     * @param busSensorClassify 传感器类型
     * @return 结果
     */
    @Override
    public int updateBusSensorClassify(BusSensorClassify busSensorClassify)
    {
        return busSensorClassifyMapper.updateBusSensorClassify(busSensorClassify);
    }

    /**
     * 批量删除传感器类型
     * 
     * @param ids 需要删除的传感器类型ID
     * @return 结果
     */
    @Override
    public int deleteBusSensorClassifyByIds(Long[] ids)
    {
        return busSensorClassifyMapper.deleteBusSensorClassifyByIds(ids);
    }

    /**
     * 删除传感器类型信息
     * 
     * @param id 传感器类型ID
     * @return 结果
     */
    @Override
    public int deleteBusSensorClassifyById(Long id)
    {
        return busSensorClassifyMapper.deleteBusSensorClassifyById(id);
    }
}
