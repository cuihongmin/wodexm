package com.wanqiao.mogao.project.detectionindex.service;

import java.util.List;
import com.wanqiao.mogao.project.detectionindex.domain.BusDetectionIndex;
import com.wanqiao.mogao.project.detectionindex.dto.BusDetectionIndexDTO;

/**
 * 监测指标Service接口
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
public interface IBusDetectionIndexService 
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
    public int insertBusDetectionIndex(BusDetectionIndexDTO busDetectionIndexDTO);

    /**
     * 修改监测指标
     * 
     * @param busDetectionIndex 监测指标
     * @return 结果
     */
    public int updateBusDetectionIndex(BusDetectionIndexDTO busDetectionIndexDTO);

    /**
     * 批量删除监测指标
     * 
     * @param ids 需要删除的监测指标ID
     * @return 结果
     */
    public int deleteBusDetectionIndexByIds(Long[] ids);

    /**
     * 删除监测指标信息
     * 
     * @param id 监测指标ID
     * @return 结果
     */
    public int deleteBusDetectionIndexById(Long id);
}
