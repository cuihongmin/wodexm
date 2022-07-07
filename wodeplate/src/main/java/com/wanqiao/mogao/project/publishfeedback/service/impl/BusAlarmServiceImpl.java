package com.wanqiao.mogao.project.publishfeedback.service.impl;

import java.util.List;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.project.publishfeedback.constant.Constants;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.publishfeedback.domain.MailConfig;
import com.wanqiao.mogao.project.publishfeedback.enums.AlarmWayEnum;
import com.wanqiao.mogao.project.publishfeedback.mapper.BusSafetyPeopleMapper;
import com.wanqiao.mogao.project.publishfeedback.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.publishfeedback.mapper.BusAlarmMapper;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarm;
import com.wanqiao.mogao.project.publishfeedback.service.IBusAlarmService;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * 报警： 包括电话报警、短信邮件等Service业务层处理
 * 
 * @author cjj
 * @date 2021-05-06
 */
@Slf4j
@Service
public class BusAlarmServiceImpl implements IBusAlarmService
{
    @Resource
    private BusAlarmMapper busAlarmMapper;

    @Resource
    private BusSafetyPeopleMapper busSafetyPeopleMapper;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private JavaMailSender sender;
    /**
     * 查询报警： 包括电话报警、短信邮件等
     * 
     * @param id 报警： 包括电话报警、短信邮件等ID
     * @return 报警： 包括电话报警、短信邮件等
     */
    @Override
    public BusAlarm selectBusAlarmById(Long id)
    {
        return busAlarmMapper.selectBusAlarmById(id);
    }

    /**
     * 查询报警： 包括电话报警、短信邮件等列表
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 报警： 包括电话报警、短信邮件等
     */
    @Override
    public List<BusAlarm> selectBusAlarmList(BusAlarm busAlarm)
    {
        return busAlarmMapper.selectBusAlarmList(busAlarm);
    }

    /**
     * 新增报警： 包括电话报警、短信邮件等
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusAlarm(BusAlarm busAlarm)
    {
        busAlarm.setCreateTime(DateUtils.getNowDate());
        busAlarm.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId());
        for(Long id : busAlarm.getSafetyPeopleIds()) {
            BusSafetyPeople safetyPeople = this.busSafetyPeopleMapper.selectBusSafetyPeopleById(id);
            busAlarm.setSafetyId(id);
            if(AlarmWayEnum.MESSAGE.getId().equals(busAlarm.getAlarmWayId())) {
                try {
                    MailUtil.sendMail(sender, "报警", mailConfig.getFrom(), safetyPeople.getEmail(),
                            safetyPeople.getEmail(), busAlarm.getContent());
                    busAlarm.setSendState(Constants.MESSAGE_SEND_SUCCESS);
                } catch (Exception e) {
                    log.error("邮件报警报错  e = {}", e);
                    busAlarm.setSendState(Constants.MESSAGE_SEND_FAIL);
                }
            }
            busAlarmMapper.insertBusAlarm(busAlarm);
        }
        return 1;
    }



    /**
     * 修改报警： 包括电话报警、短信邮件等
     * 
     * @param busAlarm 报警： 包括电话报警、短信邮件等
     * @return 结果
     */
    @Override
    public int updateBusAlarm(BusAlarm busAlarm)
    {
        return busAlarmMapper.updateBusAlarm(busAlarm);
    }

    /**
     * 批量删除报警： 包括电话报警、短信邮件等
     * 
     * @param ids 需要删除的报警： 包括电话报警、短信邮件等ID
     * @return 结果
     */
    @Override
    public int deleteBusAlarmByIds(Long[] ids)
    {
        return busAlarmMapper.deleteBusAlarmByIds(ids);
    }

    /**
     * 删除报警： 包括电话报警、短信邮件等信息
     * 
     * @param id 报警： 包括电话报警、短信邮件等ID
     * @return 结果
     */
    @Override
    public int deleteBusAlarmById(Long id)
    {
        return busAlarmMapper.deleteBusAlarmById(id);
    }
}
