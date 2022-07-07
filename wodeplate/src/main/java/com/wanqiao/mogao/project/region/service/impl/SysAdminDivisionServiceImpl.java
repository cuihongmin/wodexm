package com.wanqiao.mogao.project.region.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.region.mapper.SysAdminDivisionMapper;
import com.wanqiao.mogao.project.region.domain.SysAdminDivision;
import com.wanqiao.mogao.project.region.service.ISysAdminDivisionService;

/**
 * 行政区划Service业务层处理
 * 
 * @author xubobo
 * @date 2020-07-15
 */
@Service
public class SysAdminDivisionServiceImpl implements ISysAdminDivisionService 
{
    @Autowired
    private SysAdminDivisionMapper sysAdminDivisionMapper;

    /**
     * 查询行政区划
     * 
     * @param id 行政区划ID
     * @return 行政区划
     */
    @Override
    public SysAdminDivision selectSysAdminDivisionById(Long id)
    {
        return sysAdminDivisionMapper.selectSysAdminDivisionById(id);
    }

    /**
     * 查询行政区划列表
     * 
     * @param sysAdminDivision 行政区划
     * @return 行政区划
     */
    @Override
    public List<SysAdminDivision> selectSysAdminDivisionList(SysAdminDivision sysAdminDivision)
    {
        return sysAdminDivisionMapper.selectSysAdminDivisionList(sysAdminDivision);
    }

    /**
     * 新增行政区划
     * 
     * @param sysAdminDivision 行政区划
     * @return 结果
     */
    @Override
    public int insertSysAdminDivision(SysAdminDivision sysAdminDivision)
    {
        return sysAdminDivisionMapper.insertSysAdminDivision(sysAdminDivision);
    }

    /**
     * 修改行政区划
     * 
     * @param sysAdminDivision 行政区划
     * @return 结果
     */
    @Override
    public int updateSysAdminDivision(SysAdminDivision sysAdminDivision)
    {
        return sysAdminDivisionMapper.updateSysAdminDivision(sysAdminDivision);
    }

    /**
     * 批量删除行政区划
     * 
     * @param ids 需要删除的行政区划ID
     * @return 结果
     */
    @Override
    public int deleteSysAdminDivisionByIds(Long[] ids)
    {
        return sysAdminDivisionMapper.deleteSysAdminDivisionByIds(ids);
    }

    /**
     * 删除行政区划信息
     * 
     * @param id 行政区划ID
     * @return 结果
     */
    @Override
    public int deleteSysAdminDivisionById(Long id)
    {
        return sysAdminDivisionMapper.deleteSysAdminDivisionById(id);
    }
}
