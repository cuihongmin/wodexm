package com.wanqiao.mogao.project.region.mapper;

import java.util.List;
import com.wanqiao.mogao.project.region.domain.SysAdminDivision;

/**
 * 行政区划Mapper接口
 * 
 * @author xubobo
 * @date 2020-07-15
 */
public interface SysAdminDivisionMapper 
{
    /**
     * 查询行政区划
     * 
     * @param id 行政区划ID
     * @return 行政区划
     */
    public SysAdminDivision selectSysAdminDivisionById(Long id);

    /**
     * 查询行政区划列表
     * 
     * @param sysAdminDivision 行政区划
     * @return 行政区划集合
     */
    public List<SysAdminDivision> selectSysAdminDivisionList(SysAdminDivision sysAdminDivision);

    /**
     * 新增行政区划
     * 
     * @param sysAdminDivision 行政区划
     * @return 结果
     */
    public int insertSysAdminDivision(SysAdminDivision sysAdminDivision);

    /**
     * 修改行政区划
     * 
     * @param sysAdminDivision 行政区划
     * @return 结果
     */
    public int updateSysAdminDivision(SysAdminDivision sysAdminDivision);

    /**
     * 删除行政区划
     * 
     * @param id 行政区划ID
     * @return 结果
     */
    public int deleteSysAdminDivisionById(Long id);

    /**
     * 批量删除行政区划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysAdminDivisionByIds(Long[] ids);
}
