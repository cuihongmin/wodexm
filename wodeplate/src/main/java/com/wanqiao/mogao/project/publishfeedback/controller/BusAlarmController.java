package com.wanqiao.mogao.project.publishfeedback.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarm;
import com.wanqiao.mogao.project.publishfeedback.service.IBusAlarmService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 报警： 包括电话报警、短信邮件等Controller
 * 
 * @author cjj
 * @date 2021-05-06
 */
@Slf4j
@Api(value = "报警列表：包括电话报警、短信邮件")
@RestController
@RequestMapping("/publishfeedback/alarm")
public class BusAlarmController extends BaseController
{
    @Autowired
    private IBusAlarmService busAlarmService;

    //@PreAuthorize("@ss.hasPermi('publishfeedback:alarm:list')")
    @ApiOperation(value = "查询报警列表", notes = "根据不同报警方式id获取不同的报警列表")
    @GetMapping("/list")
    public TableDataInfo list(BusAlarm busAlarm)
    {
        log.info("查询报警列表");
        startPage();
        List<BusAlarm> list = busAlarmService.selectBusAlarmList(busAlarm);
        return getDataTable(list);
    }

    //@PreAuthorize("@ss.hasPermi('publishfeedback:alarm:export')")
    @ApiOperation(value = "导出列表", notes = "根绝不同的报警类型导出不同列表")
    @Log(title = "报警： 包括电话报警、短信邮件等", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusAlarm busAlarm)
    {
        log.info("导出报警列表");
        List<BusAlarm> list = busAlarmService.selectBusAlarmList(busAlarm);
        ExcelUtil<BusAlarm> util = new ExcelUtil<BusAlarm>(BusAlarm.class);
        return util.exportExcel(list, "alarm");
    }


    //@PreAuthorize("@ss.hasPermi('publishfeedback:alarm:query')")
    /*@ApiOperation(value = "获取报警详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busAlarmService.selectBusAlarmById(id));
    }*/

    /**
     * 新增报警： 包括电话报警、短信邮件等
     */
    //@PreAuthorize("@ss.hasPermi('publishfeedback:alarm:add')")
    @ApiOperation(value = "新增报警")
    @PostMapping
    public AjaxResult add(@RequestBody BusAlarm busAlarm)
    {
        return toAjax(busAlarmService.insertBusAlarm(busAlarm));
    }

   /* *//**
     * 修改报警： 包括电话报警、短信邮件等
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:alarm:edit')")
    @Log(title = "报警： 包括电话报警、短信邮件等", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusAlarm busAlarm)
    {
        return toAjax(busAlarmService.updateBusAlarm(busAlarm));
    }

    *//**
     * 删除报警： 包括电话报警、短信邮件等
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:alarm:remove')")
    @Log(title = "报警： 包括电话报警、短信邮件等", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busAlarmService.deleteBusAlarmByIds(ids));
    }
*/}
