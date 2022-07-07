package com.wanqiao.mogao.project.syswarn.service;

import java.util.List;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnRecold;

/**
 * warnService接口
 * 
 * @author cjj
 * @date 2021-05-07
 */
public interface IBusWarnRecoldService 
{
    /**
     * 查询warn
     * 
     * @param id warnID
     * @return warn
     */
    public BusWarnRecold selectBusWarnRecoldById(Long id);

    /**
     * 查询warn列表
     * 
     * @param busWarnRecold warn
     * @return warn集合
     */
    public List<BusWarnRecold> selectBusWarnRecoldList(BusWarnRecold busWarnRecold);

    /**
     * 新增warn
     * 
     * @param busWarnRecold warn
     * @return 结果
     */
    public int insertBusWarnRecold(BusWarnRecold busWarnRecold);

    /**
     * 修改warn
     * 
     * @param busWarnRecold warn
     * @return 结果
     */
    public int updateBusWarnRecold(BusWarnRecold busWarnRecold);

    /**
     * 批量删除warn
     * 
     * @param ids 需要删除的warnID
     * @return 结果
     */
    public int deleteBusWarnRecoldByIds(Long[] ids);

    /**
     * 删除warn信息
     * 
     * @param id warnID
     * @return 结果
     */
    public int deleteBusWarnRecoldById(Long id);

    int relieveWarn(long id);
}
