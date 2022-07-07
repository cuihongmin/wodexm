package com.wanqiao.mogao.project.groupperiod.service.impl;

import java.util.List;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.groupperiod.mapper.BusGroupManageMapper;
import com.wanqiao.mogao.project.groupperiod.domain.BusGroupManage;
import com.wanqiao.mogao.project.groupperiod.service.IBusGroupManageService;

/**
 * 群组管理Service业务层处理
 *
 * @author zhangguangbin
 * @date 2021-05-08
 */
@Api("群组信息管理")
@Service
public class BusGroupManageServiceImpl implements IBusGroupManageService {
    @Autowired
    private BusGroupManageMapper busGroupManageMapper;

    /**
     * 查询群组管理
     *
     * @param id 群组管理ID
     * @return 群组管理
     */
    @Override
    public BusGroupManage selectBusGroupManageById(Long id) {
        return busGroupManageMapper.selectBusGroupManageById(id);
    }

    /**
     * 查询群组管理列表
     *
     * @param busGroupManage 群组管理
     * @return 群组管理
     */
    @Override
    public List<BusGroupManage> selectBusGroupManageList(BusGroupManage busGroupManage) {
        return busGroupManageMapper.selectBusGroupManageList(busGroupManage);
    }

    /**
     * 新增群组管理
     *
     * @param busGroupManage 群组管理
     * @return 结果
     */
    @Override
    public int insertBusGroupManage(BusGroupManage busGroupManage) {
        busGroupManage.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busGroupManage.setCreateTime(DateUtils.getNowDate());
        busGroupManage.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return busGroupManageMapper.insertBusGroupManage(busGroupManage);
    }

    /**
     * 修改群组管理
     *
     * @param busGroupManage 群组管理
     * @return 结果
     */
    @Override
    public int updateBusGroupManage(BusGroupManage busGroupManage) {
        busGroupManage.setUpdateTime(DateUtils.getNowDate());
        busGroupManage.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return busGroupManageMapper.updateBusGroupManage(busGroupManage);
    }

    /**
     * 批量删除群组管理
     *
     * @param ids 需要删除的群组管理ID
     * @return 结果
     */
    @Override
    public int deleteBusGroupManageByIds(Long[] ids) {
        return busGroupManageMapper.deleteBusGroupManageByIds(ids);
    }

    /**
     * 删除群组管理信息
     *
     * @param id 群组管理ID
     * @return 结果
     */
    @Override
    public int deleteBusGroupManageById(Long id) {
        return busGroupManageMapper.deleteBusGroupManageById(id);
    }
}
