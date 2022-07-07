package com.wanqiao.mogao.project.maintain.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Log;
import com.wanqiao.mogao.framework.aspectj.lang.enums.BusinessType;
import com.wanqiao.mogao.project.maintain.domain.SysInfoMaintain;
import com.wanqiao.mogao.project.maintain.service.ISysInfoMaintainService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 系统信息维护Controller
 * 
 * @author zsh
 * @date 2021-04-22
 */
@Api("系统信息维护")
@RestController
@RequestMapping("/maintain/maintain")
public class SysInfoMaintainController extends BaseController
{
    @Autowired
    private ISysInfoMaintainService sysInfoMaintainService;

    /**
     * 查询系统信息维护列表
     */
    @ApiOperation("获取系统信息维护列表")
    @GetMapping("/list")
    public TableDataInfo list(SysInfoMaintain sysInfoMaintain)
    {
        startPage();
        List<SysInfoMaintain> list = sysInfoMaintainService.selectSysInfoMaintainList(sysInfoMaintain);
        return getDataTable(list);
    }

    /**
     * 导出系统信息维护列表
     */
    @ApiOperation("导出系统信息维护列表")
    @ApiImplicitParam(name = "sysInfoMaintain", value = "导出系统信息", dataType = "SysInfoMaintain")
    @Log(title = "系统信息维护", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysInfoMaintain sysInfoMaintain)
    {
        List<SysInfoMaintain> list = sysInfoMaintainService.selectSysInfoMaintainList(sysInfoMaintain);
        ExcelUtil<SysInfoMaintain> util = new ExcelUtil<SysInfoMaintain>(SysInfoMaintain.class);
        return util.exportExcel(list, "maintain");
    }

    /**
     * 获取系统信息维护详细信息
     */
    @ApiOperation("获取系统信息维护详细信息")
    @ApiImplicitParam(name = "sysId", value = "系统ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{sysId}")
    public AjaxResult getInfo(@PathVariable("sysId") Long sysId)
    {
        return AjaxResult.success(sysInfoMaintainService.selectSysInfoMaintainById(sysId));
    }

    /**
     * 获取系统信息维护详细信息
     */
    @ApiOperation("获取系统信息维护详细信息")
    @ApiImplicitParam(name = "sysColumn", value = "系统标题", required = true, dataType = "String")
    @GetMapping(value = "/title")
    public AjaxResult getInfoByTitle(@RequestParam("sysColumn") String sysColumn)
    {
        return AjaxResult.success(sysInfoMaintainService.selectSysInfoMaintainByTitle(sysColumn));
    }

    /**
     * 新增系统信息维护
     */
    @ApiOperation("新增系统信息维护")
    @ApiImplicitParam(name = "sysInfoMaintain", value = "新增系统信息维护", dataType = "SysInfoMaintain")
    @Log(title = "系统信息维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysInfoMaintain sysInfoMaintain)
    {
        return toAjax(sysInfoMaintainService.insertSysInfoMaintain(sysInfoMaintain));
    }

    /**
     * 修改系统信息维护
     */
    @ApiOperation("修改系统信息维护")
    @ApiImplicitParam(name = "sysInfoMaintain", value = "修改系统信息维护", dataType = "SysInfoMaintain")
    @Log(title = "系统信息维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInfoMaintain sysInfoMaintain)
    {
        return toAjax(sysInfoMaintainService.updateSysInfoMaintain(sysInfoMaintain));
    }

    /**
     * 删除系统信息维护
     */
    @ApiOperation("删除系统信息维护")
    @ApiImplicitParam(name = "sysIds", value = "系统信息ID", required = true, dataType = "Long[]", paramType = "path")
    @Log(title = "系统信息维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sysIds}")
    public AjaxResult remove(@PathVariable Long[] sysIds)
    {
        return toAjax(sysInfoMaintainService.deleteSysInfoMaintainByIds(sysIds));
    }
}
