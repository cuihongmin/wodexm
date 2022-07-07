package com.wanqiao.mogao.project.publishfeedback.mapper;

import java.util.List;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;

/**
 * publishfeedbackMapper接口
 * 
 * @author cjj
 * @date 2021-05-07
 */
public interface BusAlarmWayMapper 
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
     * 删除publishfeedback
     * 
     * @param id publishfeedbackID
     * @return 结果
     */
    public int deleteBusAlarmWayById(Long id);

    /**
     * 批量删除publishfeedback
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusAlarmWayByIds(Long[] ids);
}
