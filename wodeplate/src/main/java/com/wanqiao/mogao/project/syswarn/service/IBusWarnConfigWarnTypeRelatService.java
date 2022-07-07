package com.wanqiao.mogao.project.syswarn.service;

import java.util.List;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfigWarnTypeRelat;

/**
 * 预警配置和预警方式关系表Service接口
 * 
 * @author cjj
 * @date 2021-05-10
 */
public interface IBusWarnConfigWarnTypeRelatService 
{
    /**
     * 查询预警配置和预警方式关系表
     * 
     * @param id 预警配置和预警方式关系表ID
     * @return 预警配置和预警方式关系表
     */
    public BusWarnConfigWarnTypeRelat selectBusWarnConfigWarnTypeRelatById(Long id);

    /**
     * 查询预警配置和预警方式关系表列表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 预警配置和预警方式关系表集合
     */
    public List<BusWarnConfigWarnTypeRelat> selectBusWarnConfigWarnTypeRelatList(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat);

    /**
     * 新增预警配置和预警方式关系表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 结果
     */
    public int insertBusWarnConfigWarnTypeRelat(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat);

    /**
     * 修改预警配置和预警方式关系表
     * 
     * @param busWarnConfigWarnTypeRelat 预警配置和预警方式关系表
     * @return 结果
     */
    public int updateBusWarnConfigWarnTypeRelat(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat);

    /**
     * 批量删除预警配置和预警方式关系表
     * 
     * @param ids 需要删除的预警配置和预警方式关系表ID
     * @return 结果
     */
    public int deleteBusWarnConfigWarnTypeRelatByIds(Long[] ids);

    /**
     * 删除预警配置和预警方式关系表信息
     * 
     * @param id 预警配置和预警方式关系表ID
     * @return 结果
     */
    public int deleteBusWarnConfigWarnTypeRelatById(Long id);
}
