package com.wanqiao.mogao.project.publishfeedback.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.publishfeedback.mapper.BusAlarmWayMapper;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.service.IBusAlarmWayService;

import javax.annotation.Resource;

/**
 * publishfeedbackService业务层处理
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Service
public class BusAlarmWayServiceImpl implements IBusAlarmWayService 
{
    @Resource
    private BusAlarmWayMapper busAlarmWayMapper;

    /**
     * 查询publishfeedback
     * 
     * @param id publishfeedbackID
     * @return publishfeedback
     */
    @Override
    public BusAlarmWay selectBusAlarmWayById(Long id)
    {
        return busAlarmWayMapper.selectBusAlarmWayById(id);
    }

    /**
     * 查询publishfeedback列表
     * 
     * @param busAlarmWay publishfeedback
     * @return publishfeedback
     */
    @Override
    public List<BusAlarmWay> selectBusAlarmWayList(BusAlarmWay busAlarmWay)
    {
        return busAlarmWayMapper.selectBusAlarmWayList(busAlarmWay);
    }

    /**
     * 新增publishfeedback
     * 
     * @param busAlarmWay publishfeedback
     * @return 结果
     */
    @Override
    public int insertBusAlarmWay(BusAlarmWay busAlarmWay)
    {
        return busAlarmWayMapper.insertBusAlarmWay(busAlarmWay);
    }

    /**
     * 修改publishfeedback
     * 
     * @param busAlarmWay publishfeedback
     * @return 结果
     */
    @Override
    public int updateBusAlarmWay(BusAlarmWay busAlarmWay)
    {
        return busAlarmWayMapper.updateBusAlarmWay(busAlarmWay);
    }

    /**
     * 批量删除publishfeedback
     * 
     * @param ids 需要删除的publishfeedbackID
     * @return 结果
     */
    @Override
    public int deleteBusAlarmWayByIds(Long[] ids)
    {
        return busAlarmWayMapper.deleteBusAlarmWayByIds(ids);
    }

    /**
     * 删除publishfeedback信息
     * 
     * @param id publishfeedbackID
     * @return 结果
     */
    @Override
    public int deleteBusAlarmWayById(Long id)
    {
        return busAlarmWayMapper.deleteBusAlarmWayById(id);
    }
}
