package com.wanqiao.mogao.project.datacollect.service.impl;

import java.util.List;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.DictUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.StringUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.datacollect.mapper.BusArtificialPatrolMapper;
import com.wanqiao.mogao.project.datacollect.domain.BusArtificialPatrol;
import com.wanqiao.mogao.project.datacollect.service.IBusArtificialPatrolService;

/**
 * 人工巡检登记Service业务层处理
 *
 * @author zhangguangbin
 * @date 2021-05-10
 */
@Service
public class BusArtificialPatrolServiceImpl implements IBusArtificialPatrolService {
    @Autowired
    private BusArtificialPatrolMapper busArtificialPatrolMapper;

    /**
     * 查询人工巡检登记
     *
     * @param id 人工巡检登记ID
     * @return 人工巡检登记
     */
    @Override
    public BusArtificialPatrol selectBusArtificialPatrolById(Long id) {
        BusArtificialPatrol busArtificialPatrol = busArtificialPatrolMapper.selectBusArtificialPatrolById(id);
        if(StringUtils.isNotEmpty(busArtificialPatrol.getSafetyAssessment())){
            String safetyAssessmentName = DictUtils.getDictByKeyAndValue("patrol_safety_assessment", busArtificialPatrol.getSafetyAssessment());
            busArtificialPatrol.setSafetyAssessmentName(safetyAssessmentName);
        }
        if(StringUtils.isNotEmpty(busArtificialPatrol.getPatrolTypeId())){
            String patrolTypeName = DictUtils.getDictByKeyAndValue("patrol_type", busArtificialPatrol.getPatrolTypeId());
            busArtificialPatrol.setPatrolTypeName(patrolTypeName);
        }
        if(StringUtils.isNotEmpty(busArtificialPatrol.getStateRelease())){
            String stateReleaseName = DictUtils.getDictByKeyAndValue("patrol_state_release", busArtificialPatrol.getStateRelease());
            busArtificialPatrol.setStateReleaseName(stateReleaseName);
        }
        return busArtificialPatrol;
    }

    /**
     * 查询人工巡检登记列表
     *
     * @param busArtificialPatrol 人工巡检登记
     * @return 人工巡检登记
     */
    @Override
    public List<BusArtificialPatrol> selectBusArtificialPatrolList(BusArtificialPatrol busArtificialPatrol) {
        List<BusArtificialPatrol> busArtificialPatrols = busArtificialPatrolMapper.selectBusArtificialPatrolList(busArtificialPatrol);
        return busArtificialPatrols;
    }

    /**
     * 新增人工巡检登记
     *
     * @param busArtificialPatrol 人工巡检登记
     * @return 结果
     */
    @Override
    public int insertBusArtificialPatrol(BusArtificialPatrol busArtificialPatrol) {
        busArtificialPatrol.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busArtificialPatrol.setCreateTime(DateUtils.getNowDate());
        busArtificialPatrol.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        busArtificialPatrol.setWarnState("0");
        return busArtificialPatrolMapper.insertBusArtificialPatrol(busArtificialPatrol);
    }

    /**
     * 修改人工巡检登记
     *
     * @param busArtificialPatrol 人工巡检登记
     * @return 结果
     */
    @Override
    public int updateBusArtificialPatrol(BusArtificialPatrol busArtificialPatrol) {
        busArtificialPatrol.setUpdateTime(DateUtils.getNowDate());
        busArtificialPatrol.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return busArtificialPatrolMapper.updateBusArtificialPatrol(busArtificialPatrol);
    }

    /**
     * 人工巡检解除预警
     *
     * @param busArtificialPatrol 人工巡检解除预警
     * @return 结果
     */
    @Override
    public int relieveWarning(BusArtificialPatrol busArtificialPatrol) {
        return busArtificialPatrolMapper.updateBusArtificialPatrol(busArtificialPatrol);
    }

    /**
     * 批量删除人工巡检登记
     *
     * @param ids 需要删除的人工巡检登记ID
     * @return 结果
     */
    @Override
    public int deleteBusArtificialPatrolByIds(Long[] ids) {
        return busArtificialPatrolMapper.deleteBusArtificialPatrolByIds(ids);
    }

    /**
     * 删除人工巡检登记信息
     *
     * @param id 人工巡检登记ID
     * @return 结果
     */
    @Override
    public int deleteBusArtificialPatrolById(Long id) {
        return busArtificialPatrolMapper.deleteBusArtificialPatrolById(id);
    }
}
