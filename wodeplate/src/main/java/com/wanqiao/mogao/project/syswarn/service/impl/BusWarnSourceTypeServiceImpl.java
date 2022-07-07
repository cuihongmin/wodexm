package com.wanqiao.mogao.project.syswarn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnSourceTypeMapper;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSourceType;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnSourceTypeService;

/**
 * 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Service
public class BusWarnSourceTypeServiceImpl implements IBusWarnSourceTypeService 
{
    @Autowired
    private BusWarnSourceTypeMapper busWarnSourceTypeMapper;

    /**
     * 查询预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param id 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     */
    @Override
    public BusWarnSourceType selectBusWarnSourceTypeById(Long id)
    {
        return busWarnSourceTypeMapper.selectBusWarnSourceTypeById(id);
    }

    /**
     * 查询预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等列表
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     */
    @Override
    public List<BusWarnSourceType> selectBusWarnSourceTypeList(BusWarnSourceType busWarnSourceType)
    {
        return busWarnSourceTypeMapper.selectBusWarnSourceTypeList(busWarnSourceType);
    }

    /**
     * 新增预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 结果
     */
    @Override
    public int insertBusWarnSourceType(BusWarnSourceType busWarnSourceType)
    {
        return busWarnSourceTypeMapper.insertBusWarnSourceType(busWarnSourceType);
    }

    /**
     * 修改预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 结果
     */
    @Override
    public int updateBusWarnSourceType(BusWarnSourceType busWarnSourceType)
    {
        return busWarnSourceTypeMapper.updateBusWarnSourceType(busWarnSourceType);
    }

    /**
     * 批量删除预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param ids 需要删除的预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnSourceTypeByIds(Long[] ids)
    {
        return busWarnSourceTypeMapper.deleteBusWarnSourceTypeByIds(ids);
    }

    /**
     * 删除预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等信息
     * 
     * @param id 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 结果
     */
    @Override
    public int deleteBusWarnSourceTypeById(Long id)
    {
        return busWarnSourceTypeMapper.deleteBusWarnSourceTypeById(id);
    }
}
