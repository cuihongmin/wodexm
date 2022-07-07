package com.wanqiao.mogao.project.datacollect.service;

import java.util.List;
import com.wanqiao.mogao.project.datacollect.domain.BusArtificialPatrol;

/**
 * 人工巡检登记Service接口
 * 
 * @author zhangguangbin
 * @date 2021-05-10
 */
public interface IBusArtificialPatrolService 
{
    /**
     * 查询人工巡检登记
     * 
     * @param id 人工巡检登记ID
     * @return 人工巡检登记
     */
    public BusArtificialPatrol selectBusArtificialPatrolById(Long id);

    /**
     * 查询人工巡检登记列表
     * 
     * @param busArtificialPatrol 人工巡检登记
     * @return 人工巡检登记集合
     */
    public List<BusArtificialPatrol> selectBusArtificialPatrolList(BusArtificialPatrol busArtificialPatrol);

    /**
     * 新增人工巡检登记
     * 
     * @param busArtificialPatrol 人工巡检登记
     * @return 结果
     */
    public int insertBusArtificialPatrol(BusArtificialPatrol busArtificialPatrol);

    /**
     * 修改人工巡检登记
     * 
     * @param busArtificialPatrol 人工巡检登记
     * @return 结果
     */
    public int updateBusArtificialPatrol(BusArtificialPatrol busArtificialPatrol);

    /**
     * 人工巡检解除预警
     *
     * @param busArtificialPatrol 人工巡检解除预警
     * @return 结果
     */
    public int relieveWarning(BusArtificialPatrol busArtificialPatrol);

    /**
     * 批量删除人工巡检登记
     * 
     * @param ids 需要删除的人工巡检登记ID
     * @return 结果
     */
    public int deleteBusArtificialPatrolByIds(Long[] ids);

    /**
     * 删除人工巡检登记信息
     * 
     * @param id 人工巡检登记ID
     * @return 结果
     */
    public int deleteBusArtificialPatrolById(Long id);
}
