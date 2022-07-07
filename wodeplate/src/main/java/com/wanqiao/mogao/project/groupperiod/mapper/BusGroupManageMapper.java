package com.wanqiao.mogao.project.groupperiod.mapper;

import java.util.List;
import com.wanqiao.mogao.project.groupperiod.domain.BusGroupManage;

/**
 * 群组管理Mapper接口
 * 
 * @author zhangguangbin
 * @date 2021-05-08
 */
public interface BusGroupManageMapper 
{
    /**
     * 查询群组管理
     * 
     * @param id 群组管理ID
     * @return 群组管理
     */
    public BusGroupManage selectBusGroupManageById(Long id);

    /**
     * 查询群组管理列表
     * 
     * @param busGroupManage 群组管理
     * @return 群组管理集合
     */
    public List<BusGroupManage> selectBusGroupManageList(BusGroupManage busGroupManage);

    /**
     * 新增群组管理
     * 
     * @param busGroupManage 群组管理
     * @return 结果
     */
    public int insertBusGroupManage(BusGroupManage busGroupManage);

    /**
     * 修改群组管理
     * 
     * @param busGroupManage 群组管理
     * @return 结果
     */
    public int updateBusGroupManage(BusGroupManage busGroupManage);

    /**
     * 删除群组管理
     * 
     * @param id 群组管理ID
     * @return 结果
     */
    public int deleteBusGroupManageById(Long id);

    /**
     * 批量删除群组管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusGroupManageByIds(Long[] ids);
}
