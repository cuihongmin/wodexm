package com.wanqiao.mogao.project.syswarn.controller;

import java.util.List;
import com.wanqiao.mogao.project.publishfeedback.domain.BusAlarmWay;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfig;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnConfigService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 预警设置Controller
 * @author cjj
 * @date 2021-05-10
 */
@Slf4j
@Api(value = "预警配置")
@RestController
@RequestMapping("/warn/config")
public class BusWarnConfigController extends BaseController
{
    @Autowired
    private IBusWarnConfigService busWarnConfigService;

    /**
     * 查询预警设置列表
     */
    @ApiOperation(value = "查询预警设置列表")
    @PreAuthorize("@ss.hasPermi('warn:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(name = "type", required = false, defaultValue = "") Integer type,
                              @RequestParam(name = "level", required = false, defaultValue = "") Integer level)
    {
        BusWarnConfig config = new BusWarnConfig();
        if(type != null) {
            config.setType(type);
        }

        if(level != null) {
            config.setWarnLevel(level);
        }
        startPage();
        List<BusWarnConfig> list = busWarnConfigService.selectBusWarnConfigList(config);
        return getDataTable(list);
    }


    @ApiOperation(value = "编辑执行动作", notes = "execWayIds 执行动作id,多个id之间用逗号分割")
    @PutMapping("/{id}/execute/way")
    public AjaxResult editExecutePeople(@PathVariable("id") Long id, @RequestParam("execWayIds") String execWayIds) {
//        log.info("编辑执行动作 id = {}, execWayIds = {}", id, execWayIds);
        return toAjax(busWarnConfigService.endExecuteExecuteWay(id, execWayIds));
    }

    @ApiOperation(value = "编辑安全人员")
    @PutMapping("/{id}/safety/people")
    public AjaxResult editSafetyPeople(@PathVariable("id") Long id, @RequestParam("peopleIds") String peopleIds) {
//        log.info("编辑安全人员  id= {}, peopleIds  = {}", id, peopleIds);
        return toAjax(busWarnConfigService.endSafetyPeople(id, peopleIds));
    }

    @ApiOperation(value = "根据预警配置id获取报警方式列表")
    @GetMapping("{id}/alarm/ways")
    public TableDataInfo findAlarmWayByConfigId(@PathVariable("id") Long id) {
//        log.info("获取报警方式列表  id = {}", id);
        List<BusAlarmWay> list = busWarnConfigService.findAlarmWayByConfigId(id);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据预警配置id获取安全人员列表列表")
    @GetMapping("{id}/safety/people")
    public TableDataInfo findSafteyPeopleByConfigId(@PathVariable("id") Long id) {
//        log.info("获取安全人员列表");
        List<BusSafetyPeople> list = busWarnConfigService.findSafteyPeopleByConfigId(id);
        return getDataTable(list);
    }

   /* *//**
     * 导出预警设置列表
     *//*
    @PreAuthorize("@ss.hasPermi('warn:config:export')")
    @Log(title = "预警设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusWarnConfig busWarnConfig)
    {
        List<BusWarnConfig> list = busWarnConfigService.selectBusWarnConfigList(busWarnConfig);
        ExcelUtil<BusWarnConfig> util = new ExcelUtil<BusWarnConfig>(BusWarnConfig.class);
        return util.exportExcel(list, "config");
    }

    *//**
     * 获取预警设置详细信息
     *//*
    @PreAuthorize("@ss.hasPermi('warn:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busWarnConfigService.selectBusWarnConfigById(id));
    }

    *//**
     * 新增预警设置
     *//*
    @PreAuthorize("@ss.hasPermi('warn:config:add')")
    @Log(title = "预警设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusWarnConfig busWarnConfig)
    {
        return toAjax(busWarnConfigService.insertBusWarnConfig(busWarnConfig));
    }

    *//**
     * 修改预警设置
     *//*
    @PreAuthorize("@ss.hasPermi('warn:config:edit')")
    @Log(title = "预警设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusWarnConfig busWarnConfig)
    {
        return toAjax(busWarnConfigService.updateBusWarnConfig(busWarnConfig));
    }

    *//**
     * 删除预警设置
     *//*
    @PreAuthorize("@ss.hasPermi('warn:config:remove')")
    @Log(title = "预警设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busWarnConfigService.deleteBusWarnConfigByIds(ids));
    }*/
}
