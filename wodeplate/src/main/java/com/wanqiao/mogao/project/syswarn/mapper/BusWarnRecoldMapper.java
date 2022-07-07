package com.wanqiao.mogao.project.syswarn.mapper;

import java.util.List;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnRecold;

/**
 * warnMapper接口
 * 
 * @author cjj
 * @date 2021-05-07
 */
public interface BusWarnRecoldMapper 
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
     * 删除warn
     * 
     * @param id warnID
     * @return 结果
     */
    public int deleteBusWarnRecoldById(Long id);

    /**
     * 批量删除warn
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusWarnRecoldByIds(Long[] ids);
}
