package com.wanqiao.mogao.project.publishfeedback.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.service.IBusAlarmWayService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * publishfeedbackController
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Api(value = "报警方式： 邮箱报警和短信报警等")
@RestController
@RequestMapping("/publishfeedback/alarmway")
public class BusAlarmWayController extends BaseController
{
    @Autowired
    private IBusAlarmWayService busAlarmWayService;

    /**
     * 查询publishfeedback列表
     */
    //@PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:list')")
    @ApiOperation(value = "获取报警方式列表", notes = "电话邮件等方式")
    @GetMapping("/list")
    public TableDataInfo list(BusAlarmWay busAlarmWay)
    {
        startPage();
        List<BusAlarmWay> list = busAlarmWayService.selectBusAlarmWayList(busAlarmWay);
        return getDataTable(list);
    }

    /**
     * 导出publishfeedback列表
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:export')")
    @Log(title = "publishfeedback", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusAlarmWay busAlarmWay)
    {
        List<BusAlarmWay> list = busAlarmWayService.selectBusAlarmWayList(busAlarmWay);
        ExcelUtil<BusAlarmWay> util = new ExcelUtil<BusAlarmWay>(BusAlarmWay.class);
        return util.exportExcel(list, "publishfeedback");
    }

    *//**
     * 获取publishfeedback详细信息
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busAlarmWayService.selectBusAlarmWayById(id));
    }

    *//**
     * 新增publishfeedback
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:add')")
    @Log(title = "publishfeedback", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusAlarmWay busAlarmWay)
    {
        return toAjax(busAlarmWayService.insertBusAlarmWay(busAlarmWay));
    }

    *//**
     * 修改publishfeedback
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:edit')")
    @Log(title = "publishfeedback", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusAlarmWay busAlarmWay)
    {
        return toAjax(busAlarmWayService.updateBusAlarmWay(busAlarmWay));
    }

    *//**
     * 删除publishfeedback
     *//*
    @PreAuthorize("@ss.hasPermi('publishfeedback:publishfeedback:remove')")
    @Log(title = "publishfeedback", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busAlarmWayService.deleteBusAlarmWayByIds(ids));
    }*/
}
