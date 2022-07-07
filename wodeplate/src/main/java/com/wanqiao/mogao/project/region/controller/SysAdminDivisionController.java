package com.wanqiao.mogao.project.region.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Log;
import com.wanqiao.mogao.framework.aspectj.lang.enums.BusinessType;
import com.wanqiao.mogao.project.region.domain.SysAdminDivision;
import com.wanqiao.mogao.project.region.service.ISysAdminDivisionService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 行政区划Controller
 * 
 * @author xubobo
 * @date 2020-07-15
 */
@RestController
@RequestMapping("/region/division")
public class SysAdminDivisionController extends BaseController
{
    @Autowired
    private ISysAdminDivisionService sysAdminDivisionService;

    /**
     * 查询行政区划列表
     */
    @PreAuthorize("@ss.hasPermi('region:division:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAdminDivision sysAdminDivision)
    {
        startPage();
        List<SysAdminDivision> list = sysAdminDivisionService.selectSysAdminDivisionList(sysAdminDivision);
        return getDataTable(list);
    }

    /**
     * 导出行政区划列表
     */
    @PreAuthorize("@ss.hasPermi('region:division:export')")
    @Log(title = "行政区划", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysAdminDivision sysAdminDivision)
    {
        List<SysAdminDivision> list = sysAdminDivisionService.selectSysAdminDivisionList(sysAdminDivision);
        ExcelUtil<SysAdminDivision> util = new ExcelUtil<SysAdminDivision>(SysAdminDivision.class);
        return util.exportExcel(list, "division");
    }

    /**
     * 获取行政区划详细信息
     */
    @PreAuthorize("@ss.hasPermi('region:division:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysAdminDivisionService.selectSysAdminDivisionById(id));
    }

    /**
     * 新增行政区划
     */
    @PreAuthorize("@ss.hasPermi('region:division:add')")
    @Log(title = "行政区划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAdminDivision sysAdminDivision)
    {
        return toAjax(sysAdminDivisionService.insertSysAdminDivision(sysAdminDivision));
    }

    /**
     * 修改行政区划
     */
    @PreAuthorize("@ss.hasPermi('region:division:edit')")
    @Log(title = "行政区划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAdminDivision sysAdminDivision)
    {
        return toAjax(sysAdminDivisionService.updateSysAdminDivision(sysAdminDivision));
    }

    /**
     * 删除行政区划
     */
    @PreAuthorize("@ss.hasPermi('region:division:remove')")
    @Log(title = "行政区划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysAdminDivisionService.deleteSysAdminDivisionByIds(ids));
    }
}
