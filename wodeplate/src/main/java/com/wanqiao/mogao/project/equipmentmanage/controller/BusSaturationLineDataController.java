package com.wanqiao.mogao.project.equipmentmanage.controller;

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
import com.wanqiao.mogao.project.equipmentmanage.domain.BusSaturationLineData;
import com.wanqiao.mogao.project.equipmentmanage.service.IBusSaturationLineDataService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 浸润线通过渗压计采集数据计算的来Controller
 * 
 * @author cjj
 * @date 2021-05-11
 */
//@Api(value = "浸润线数据采集", tags = "浸润线数据采集")
@Api(value = "传感分类-浸润线", tags = "浸润线数据采集")
@RestController
@RequestMapping("/datacollect/saturationline")
public class BusSaturationLineDataController extends BaseController
{
    @Autowired
    private IBusSaturationLineDataService busSaturationLineDataService;

    /**
     * 查询浸润线通过渗压计采集数据计算的来列表
     */
    @ApiOperation(value = "查询浸润线列表")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusSaturationLineData busSaturationLineData)
    {
        startPage();
        List<BusSaturationLineData> list = busSaturationLineDataService.selectBusSaturationLineDataList(busSaturationLineData);
        return getDataTable(list);
    }

    /**
     * 导出浸润线通过渗压计采集数据计算的来列表
     */
    @ApiOperation(value = "导出数据")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:export')")
    @Log(title = "浸润线通过渗压计采集数据计算的来", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusSaturationLineData busSaturationLineData)
    {
        List<BusSaturationLineData> list = busSaturationLineDataService.selectBusSaturationLineDataList(busSaturationLineData);
        ExcelUtil<BusSaturationLineData> util = new ExcelUtil<BusSaturationLineData>(BusSaturationLineData.class);
        return util.exportExcel(list, "saturationline");
    }

    /**
     * 获取浸润线通过渗压计采集数据计算的来详细信息
     */
   /* @PreAuthorize("@ss.hasPermi('datacollect:saturationline:query')")
    @ApiOperation("获取详情")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busSaturationLineDataService.selectBusSaturationLineDataById(id));
    }*/

    /**
     * 新增浸润线通过渗压计采集数据计算的来
     */
    @ApiOperation("新增操作")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:add')")
    @Log(title = "浸润线通过渗压计采集数据计算的来", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusSaturationLineData busSaturationLineData)
    {
        return toAjax(busSaturationLineDataService.insertBusSaturationLineData(busSaturationLineData));
    }

    /**
     * 修改浸润线通过渗压计采集数据计算的来
     */
    @ApiOperation("编辑操作")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:edit')")
    @Log(title = "浸润线通过渗压计采集数据计算的来", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusSaturationLineData busSaturationLineData)
    {
        return toAjax(busSaturationLineDataService.updateBusSaturationLineData(busSaturationLineData));
    }

    /**
     * 删除浸润线通过渗压计采集数据计算的来
     */
    /*@PreAuthorize("@ss.hasPermi('datacollect:saturationline:remove')")
    @ApiOperation("删除操作")
    @PreAuthorize("@ss.hasPermi('datacollect:saturationline:remove')")
    @Log(title = "浸润线通过渗压计采集数据计算的来", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busSaturationLineDataService.deleteBusSaturationLineDataByIds(ids));
    }*/
}
