package com.wanqiao.mogao.project.equipmentmanage.controller;

import java.util.List;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
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
import com.wanqiao.mogao.project.equipmentmanage.domain.BusOsmometer;
import com.wanqiao.mogao.project.equipmentmanage.service.IBusOsmometerService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 渗压计Controller
 * 
 * @author cjj
 * @date 2021-05-10
 */
@Api(value = "传感分类-渗压计", tags = "传感分类-渗压计")
@RestController
@RequestMapping("/equipmentmanage/osmometer")
public class BusOsmometerController extends BaseController
{
    @Autowired
    private IBusOsmometerService busOsmometerService;

    /**
     * 查询渗压计列表
     */
    @ApiOperation(value = "渗压计设备列表")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusOsmometer busOsmometer)
    {
        startPage();
        List<BusOsmometer> list = busOsmometerService.selectBusOsmometerList(busOsmometer);
        return getDataTable(list);
    }

    /**
     * 导出渗压计列表
     */
    @ApiOperation(value = "导出渗压计列表")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:export')")
    @Log(title = "渗压计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusOsmometer busOsmometer)
    {
        List<BusOsmometer> list = busOsmometerService.selectBusOsmometerList(busOsmometer);
        ExcelUtil<BusOsmometer> util = new ExcelUtil<BusOsmometer>(BusOsmometer.class);
        return util.exportExcel(list, "osmometer");
    }

    /**
     * 获取渗压计详细信息
     */
    @ApiOperation(value = "获取设备详细信息")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busOsmometerService.selectBusOsmometerById(id));
    }

    /**
     * 新增渗压计
     */
    @ApiOperation(value = "新增渗压计")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:add')")
    @Log(title = "渗压计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusOsmometer busOsmometer)
    {
        busOsmometer.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busOsmometer.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId());
        busOsmometer.setCreateTime(DateUtils.getNowDate());
        return toAjax(busOsmometerService.insertBusOsmometer(busOsmometer));
    }

    /**
     * 修改渗压计
     */
    @ApiOperation(value = "修改渗压计")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:edit')")
    @Log(title = "渗压计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusOsmometer busOsmometer)
    {
        return toAjax(busOsmometerService.updateBusOsmometer(busOsmometer));
    }

    /**
     * 删除渗压计
     */
    @ApiOperation(value = "删除渗压计")
    @PreAuthorize("@ss.hasPermi('equipmentmanage:osmometer:remove')")
    @Log(title = "渗压计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busOsmometerService.deleteBusOsmometerByIds(ids));
    }
}
