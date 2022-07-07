package com.wanqiao.mogao.project.publishfeedback.service;

import java.util.List;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;

/**
 * publishfeedbackService接口
 * 
 * @author cjj
 * @date 2021-05-07
 */
public interface IBusAlarmWayService 
{
    /**
     * 查询publishfeedback
     * 
     * @param id publishfeedbackID
     * @return publishfeedback
     */
    public BusAlarmWay selectBusAlarmWayById(Long id);

    /**
     * 查询publishfeedback列表
     * 
     * @param busAlarmWay publishfeedback
     * @return publishfeedback集合
     */
    public List<BusAlarmWay> selectBusAlarmWayList(BusAlarmWay busAlarmWay);

    /**
     * 新增publishfeedback
     * 
     * @param busAlarmWay publishfeedback
     * @return 结果
     */
    public int insertBusAlarmWay(BusAlarmWay busAlarmWay);

    /**
     * 修改publishfeedback
     * 
     * @param busAlarmWay publishfeedback
     * @return 结果
     */
    public int updateBusAlarmWay(BusAlarmWay busAlarmWay);

    /**
     * 批量删除publishfeedback
     * 
     * @param ids 需要删除的publishfeedbackID
     * @return 结果
     */
    public int deleteBusAlarmWayByIds(Long[] ids);

    /**
     * 删除publishfeedback信息
     * 
     * @param id publishfeedbackID
     * @return 结果
     */
    public int deleteBusAlarmWayById(Long id);
}
