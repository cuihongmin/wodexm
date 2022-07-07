package com.wanqiao.mogao.project.detectionindex.mapper;

import java.util.List;
import com.wanqiao.mogao.project.detectionindex.domain.BusDetectionIndex;

/**
 * 监测指标Mapper接口
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public interface BusDetectionIndexMapper 
{
    /**
     * 查询监测指标
     * 
     * @param id 监测指标ID
     * @return 监测指标
     */
    public BusDetectionIndex selectBusDetectionIndexById(Long id);

    /**
     * 查询监测指标列表
     * 
     * @param busDetectionIndex 监测指标
     * @return 监测指标集合
     */
    public List<BusDetectionIndex> selectBusDetectionIndexList(BusDetectionIndex busDetectionIndex);

    /**
     * 新增监测指标
     * 
     * @param busDetectionIndex 监测指标
     * @return 结果
     */
    public int insertBusDetectionIndex(BusDetectionIndex busDetectionIndex);

    /**
     * 修改监测指标
     * 
     * @param busDetectionIndex 监测指标
     * @return 结果
     */
    public int updateBusDetectionIndex(BusDetectionIndex busDetectionIndex);

    /**
     * 删除监测指标
     * 
     * @param id 监测指标ID
     * @return 结果
     */
    public int deleteBusDetectionIndexById(Long id);

    /**
     * 批量删除监测指标
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusDetectionIndexByIds(Long[] ids);
}
