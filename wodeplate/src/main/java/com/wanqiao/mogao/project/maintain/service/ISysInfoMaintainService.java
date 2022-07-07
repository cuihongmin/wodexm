package com.wanqiao.mogao.project.maintain.service;

import java.util.List;
import com.wanqiao.mogao.project.maintain.domain.SysInfoMaintain;

/**
 * 系统信息维护Service接口
 * 
 * @author zsh
 * @date 2021-04-22
 */
public interface ISysInfoMaintainService 
{
    /**
     * 查询系统信息维护
     * 
     * @param sysId 系统信息维护ID
     * @return 系统信息维护
     */
    public SysInfoMaintain selectSysInfoMaintainById(Long sysId);

    /**
     * 查询系统信息维护
     *
     * @return 系统信息维护
     */
    public SysInfoMaintain selectSysInfoMaintainByTitle(String sysColumn);

    /**
     * 查询系统信息维护列表
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 系统信息维护集合
     */
    public List<SysInfoMaintain> selectSysInfoMaintainList(SysInfoMaintain sysInfoMaintain);

    /**
     * 新增系统信息维护
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 结果
     */
    public int insertSysInfoMaintain(SysInfoMaintain sysInfoMaintain);

    /**
     * 修改系统信息维护
     * 
     * @param sysInfoMaintain 系统信息维护
     * @return 结果
     */
    public int updateSysInfoMaintain(SysInfoMaintain sysInfoMaintain);

    /**
     * 批量删除系统信息维护
     * 
     * @param sysIds 需要删除的系统信息维护ID
     * @return 结果
     */
    public int deleteSysInfoMaintainByIds(Long[] sysIds);

    /**
     * 删除系统信息维护信息
     * 
     * @param sysId 系统信息维护ID
     * @return 结果
     */
    public int deleteSysInfoMaintainById(Long sysId);
}
