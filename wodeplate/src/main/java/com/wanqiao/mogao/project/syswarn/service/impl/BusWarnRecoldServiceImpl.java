package com.wanqiao.mogao.project.syswarn.service.impl;

import java.util.Date;
import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.project.syswarn.constant.SysWarnConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.syswarn.mapper.BusWarnRecoldMapper;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnRecold;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnRecoldService;

import javax.annotation.Resource;

/**
 * warnService业务层处理
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Service
public class BusWarnRecoldServiceImpl implements IBusWarnRecoldService 
{
    @Resource
    private BusWarnRecoldMapper busWarnRecoldMapper;
    /**
     * 查询warn
     * 
     * @param id warnID
     * @return warn
     */
    @Override
    public BusWarnRecold selectBusWarnRecoldById(Long id)
    {
        return busWarnRecoldMapper.selectBusWarnRecoldById(id);
    }

    /**
     * 查询warn列表
     * 
     * @param busWarnRecold warn
     * @return warn
     */
    @Override
    public List<BusWarnRecold> selectBusWarnRecoldList(BusWarnRecold busWarnRecold)
    {
        return busWarnRecoldMapper.selectBusWarnRecoldList(busWarnRecold);
    }

    /**
     * 新增warn
     * 
     * @param busWarnRecold warn
     * @return 结果
     */
    @Override
    public int insertBusWarnRecold(BusWarnRecold busWarnRecold)
    {
        busWarnRecold.setCreateTime(DateUtils.getNowDate());
        return busWarnRecoldMapper.insertBusWarnRecold(busWarnRecold);
    }

    /**
     * 修改warn
     * 
     * @param busWarnRecold warn
     * @return 结果
     */
    @Override
    public int updateBusWarnRecold(BusWarnRecold busWarnRecold)
    {
        return busWarnRecoldMapper.updateBusWarnRecold(busWarnRecold);
    }

    /**
     * 批量删除warn
     * 
     * @param ids 需要删除的warnID
     * @return 结果
     */
    @Override
    public int deleteBusWarnRecoldByIds(Long[] ids)
    {
        return busWarnRecoldMapper.deleteBusWarnRecoldByIds(ids);
    }

    /**
     * 删除warn信息
     * 
     * @param id warnID
     * @return 结果
     */
    @Override
    public int deleteBusWarnRecoldById(Long id)
    {
        return busWarnRecoldMapper.deleteBusWarnRecoldById(id);
    }

    @Override
    public int relieveWarn(long id) {
        BusWarnRecold busWarnRecold = new BusWarnRecold();
        busWarnRecold.setId(id);
        busWarnRecold.setRelieveWarnTime(new Date());
        busWarnRecold.setWarnState(SysWarnConstant.RELIEVE_WARN_STATE);
        return busWarnRecoldMapper.updateBusWarnRecold(busWarnRecold);
    }
}
