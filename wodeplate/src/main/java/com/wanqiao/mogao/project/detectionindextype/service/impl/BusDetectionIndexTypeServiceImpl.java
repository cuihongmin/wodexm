package com.wanqiao.mogao.project.detectionindextype.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.detectionindextype.mapper.BusDetectionIndexTypeMapper;
import com.wanqiao.mogao.project.detectionindextype.domain.BusDetectionIndexType;
import com.wanqiao.mogao.project.detectionindextype.service.IBusDetectionIndexTypeService;

/**
 * 监测指标类型Service业务层处理
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Service
public class BusDetectionIndexTypeServiceImpl implements IBusDetectionIndexTypeService 
{
    @Autowired
    private BusDetectionIndexTypeMapper busDetectionIndexTypeMapper;

    /**
     * 查询监测指标类型
     * 
     * @param id 监测指标类型ID
     * @return 监测指标类型
     */
    @Override
    public BusDetectionIndexType selectBusDetectionIndexTypeById(Long id)
    {
        return busDetectionIndexTypeMapper.selectBusDetectionIndexTypeById(id);
    }

    /**
     * 查询监测指标类型列表
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 监测指标类型
     */
    @Override
    public List<BusDetectionIndexType> selectBusDetectionIndexTypeList(BusDetectionIndexType busDetectionIndexType)
    {
        return busDetectionIndexTypeMapper.selectBusDetectionIndexTypeList(busDetectionIndexType);
    }

    /**
     * 新增监测指标类型
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 结果
     */
    @Override
    public int insertBusDetectionIndexType(BusDetectionIndexType busDetectionIndexType)
    {
        busDetectionIndexType.setCreateTime(DateUtils.getNowDate());
        return busDetectionIndexTypeMapper.insertBusDetectionIndexType(busDetectionIndexType);
    }

    /**
     * 修改监测指标类型
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 结果
     */
    @Override
    public int updateBusDetectionIndexType(BusDetectionIndexType busDetectionIndexType)
    {
        return busDetectionIndexTypeMapper.updateBusDetectionIndexType(busDetectionIndexType);
    }

    /**
     * 批量删除监测指标类型
     * 
     * @param ids 需要删除的监测指标类型ID
     * @return 结果
     */
    @Override
    public int deleteBusDetectionIndexTypeByIds(Long[] ids)
    {
        return busDetectionIndexTypeMapper.deleteBusDetectionIndexTypeByIds(ids);
    }

    /**
     * 删除监测指标类型信息
     * 
     * @param id 监测指标类型ID
     * @return 结果
     */
    @Override
    public int deleteBusDetectionIndexTypeById(Long id)
    {
        return busDetectionIndexTypeMapper.deleteBusDetectionIndexTypeById(id);
    }
}
