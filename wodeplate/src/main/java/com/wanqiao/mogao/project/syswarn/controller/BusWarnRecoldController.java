package com.wanqiao.mogao.project.syswarn.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.wanqiao.mogao.project.syswarn.domain.BusWarnRecold;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnRecoldService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * warnController
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Api(value = "预警记录")
@RestController
@RequestMapping("/syswarn/warn")
public class BusWarnRecoldController extends BaseController
{
    @Autowired
    private IBusWarnRecoldService busWarnRecoldService;

    /**
     * 查询warn列表
     */
    @ApiOperation(value = "查询列表")
    //@PreAuthorize("@ss.hasPermi('syswarn:warn:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusWarnRecold busWarnRecold)
    {
        startPage();
        List<BusWarnRecold> list = busWarnRecoldService.selectBusWarnRecoldList(busWarnRecold);
        return getDataTable(list);
    }

    /**
     * 导出warn列表
     */
    @ApiOperation(value = "导出列表")
    //@PreAuthorize("@ss.hasPermi('syswarn:warn:export')")
    @Log(title = "warn", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusWarnRecold busWarnRecold)
    {
        List<BusWarnRecold> list = busWarnRecoldService.selectBusWarnRecoldList(busWarnRecold);
        ExcelUtil<BusWarnRecold> util = new ExcelUtil<BusWarnRecold>(BusWarnRecold.class);
        return util.exportExcel(list, "warn");
    }

    @ApiOperation(value = "解除预警")
    @PutMapping("/relieve/{id}")
    public AjaxResult relieveWarn(@PathVariable("id") long id)
    {
        return toAjax(busWarnRecoldService.relieveWarn(id));
    }

  /*  *//**
     * 获取warn详细信息
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busWarnRecoldService.selectBusWarnRecoldById(id));
    }

    *//**
     * 新增warn
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warn:add')")
    @Log(title = "warn", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusWarnRecold busWarnRecold)
    {
        return toAjax(busWarnRecoldService.insertBusWarnRecold(busWarnRecold));
    }

    *//**
     * 修改warn
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warn:edit')")
    @Log(title = "warn", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusWarnRecold busWarnRecold)
    {
        return toAjax(busWarnRecoldService.updateBusWarnRecold(busWarnRecold));
    }

    *//**
     * 删除warn
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warn:remove')")
    @Log(title = "warn", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busWarnRecoldService.deleteBusWarnRecoldByIds(ids));
    }*/
}
