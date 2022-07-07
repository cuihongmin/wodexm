package com.wanqiao.mogao.project.maintain.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.maintain.mapper.SysInfoMaintainMapper;
import com.wanqiao.mogao.project.maintain.domain.SysInfoMaintain;
import com.wanqiao.mogao.project.maintain.service.ISysInfoMaintainService;

/**
 * 系统信息维护Service业务层处理
 * 
 * @author zsh
 * @date 2021-04-22
 */
@Service
public class SysInfoMaintainServiceImpl implements ISysInfoMaintainService 
{
    @Autowired
    private SysInfoMaintainMapper sysInfoMaintainMapper;

    /**
     * 查询系统信息维护
     * 
     * @param sysId 系统信息维护ID
     * @return 系统信息维护
     */
    @Override
    public SysInfoMaintain selectSysInfoMaintainById(Long sysId)
    {
        return sysInfoMaintainMapper.selectSysInfoMaintainById(sysId);
    }

    /**
     * 查询系统信息维护
     *
     * @return 系统信息维护
     */
    @Override
    public SysInfoMaintain selectSysInfoMaintainByTitle(String sysColumn) {
        return sysInfoMaintainMapper.selectSysInfoMaintainByTitle(sysColumn);
    }

    /**
     * 查询系统信息维护列表
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 系统信息维护
     */
    @Override
    public List<SysInfoMaintain> selectSysInfoMaintainList(SysInfoMaintain sysInfoMaintain)
    {
        return sysInfoMaintainMapper.selectSysInfoMaintainList(sysInfoMaintain);
    }

    /**
     * 新增系统信息维护
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 结果
     */
    @Override
    public int insertSysInfoMaintain(SysInfoMaintain sysInfoMaintain)
    {
        sysInfoMaintain.setCreateTime(DateUtils.getNowDate());
        return sysInfoMaintainMapper.insertSysInfoMaintain(sysInfoMaintain);
    }

    /**
     * 修改系统信息维护
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 结果
     */
    @Override
    public int updateSysInfoMaintain(SysInfoMaintain sysInfoMaintain)
    {
        sysInfoMaintain.setUpdateTime(DateUtils.getNowDate());
        return sysInfoMaintainMapper.updateSysInfoMaintain(sysInfoMaintain);
    }

    /**
     * 批量删除系统信息维护
     * 
     * @param sysIds 需要删除的系统信息维护ID
     * @return 结果
     */
    @Override
    public int deleteSysInfoMaintainByIds(Long[] sysIds)
    {
        return sysInfoMaintainMapper.deleteSysInfoMaintainByIds(sysIds);
    }

    /**
     * 删除系统信息维护信息
     * 
     * @param sysId 系统信息维护ID
     * @return 结果
     */
    @Override
    public int deleteSysInfoMaintainById(Long sysId)
    {
        return sysInfoMaintainMapper.deleteSysInfoMaintainById(sysId);
    }
}
