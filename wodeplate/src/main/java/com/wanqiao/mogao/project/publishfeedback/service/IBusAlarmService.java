package com.wanqiao.mogao.project.publishfeedback.service;

import java.util.List;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarm;

/**
 * 报警： 包括电话报警、短信邮件等Service接口
 * 
 * @author cjj
 * @date 2021-05-06
 */
public interface IBusAlarmService 
{
    /**
     * 查询报警： 包括电话报警、短信邮件等
     * 
     * @param id 报警： 包括电话报警、短信邮件等ID
     * @return 报警： 包括电话报警、短信邮件等
     */
    public BusAlarm selectBusAlarmById(Long id);

    /**
     * 查询报警： 包括电话报警、短信邮件等列表
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 报警： 包括电话报警、短信邮件等集合
     */
    public List<BusAlarm> selectBusAlarmList(BusAlarm busAlarm);

    /**
     * 新增报警： 包括电话报警、短信邮件等
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 结果
     */
    public int insertBusAlarm(BusAlarm busAlarm);

    /**
     * 修改报警： 包括电话报警、短信邮件等
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 结果
     */
    public int updateBusAlarm(BusAlarm busAlarm);

    /**
     * 批量删除报警： 包括电话报警、短信邮件等
     * 
     * @param ids 需要删除的报警： 包括电话报警、短信邮件等ID
     * @return 结果
     */
    public int deleteBusAlarmByIds(Long[] ids);

    /**
     * 删除报警： 包括电话报警、短信邮件等信息
     * 
     * @param id 报警： 包括电话报警、短信邮件等ID
     * @return 结果
     */
    public int deleteBusAlarmById(Long id);
}
