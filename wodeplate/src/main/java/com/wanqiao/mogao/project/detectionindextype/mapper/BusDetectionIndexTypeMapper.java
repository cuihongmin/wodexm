package com.wanqiao.mogao.project.detectionindextype.mapper;

import java.util.List;
import com.wanqiao.mogao.project.detectionindextype.domain.BusDetectionIndexType;

/**
 * 监测指标类型Mapper接口
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public interface BusDetectionIndexTypeMapper 
{
    /**
     * 查询监测指标类型
     * 
     * @param id 监测指标类型ID
     * @return 监测指标类型
     */
    public BusDetectionIndexType selectBusDetectionIndexTypeById(Long id);

    /**
     * 查询监测指标类型列表
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 监测指标类型集合
     */
    public List<BusDetectionIndexType> selectBusDetectionIndexTypeList(BusDetectionIndexType busDetectionIndexType);

    /**
     * 新增监测指标类型
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 结果
     */
    public int insertBusDetectionIndexType(BusDetectionIndexType busDetectionIndexType);

    /**
     * 修改监测指标类型
     * 
     * @param busDetectionIndexType 监测指标类型
     * @return 结果
     */
    public int updateBusDetectionIndexType(BusDetectionIndexType busDetectionIndexType);

    /**
     * 删除监测指标类型
     * 
     * @param id 监测指标类型ID
     * @return 结果
     */
    public int deleteBusDetectionIndexTypeById(Long id);

    /**
     * 批量删除监测指标类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDetectionIndexTypeByIds(Long[] ids);
}
