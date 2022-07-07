package com.wanqiao.mogao.project.equipmentindexrelat.controller;

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
import com.wanqiao.mogao.project.equipmentindexrelat.domain.BusEquipmentIndexRelat;
import com.wanqiao.mogao.project.equipmentindexrelat.service.IBusEquipmentIndexRelatService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 设备和指标关联关系Controller
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Api(value = "监测指标和设备关系", tags = "监测指标和设备关系")
@RestController
@RequestMapping("/equipmentindexrelat/equipmentindexrelat")
public class BusEquipmentIndexRelatController extends BaseController
{
    @Autowired
    private IBusEquipmentIndexRelatService busEquipmentIndexRelatService;

    /**
     * 查询设备和指标关联关系列表
     */
    @ApiOperation("查询设备和指标关联关系列表")
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        startPage();
        List<BusEquipmentIndexRelat> list = busEquipmentIndexRelatService.selectBusEquipmentIndexRelatList(busEquipmentIndexRelat);
        return getDataTable(list);
    }

    /**
     * 导出设备和指标关联关系列表
     */
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:export')")
    @Log(title = "设备和指标关联关系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        List<BusEquipmentIndexRelat> list = busEquipmentIndexRelatService.selectBusEquipmentIndexRelatList(busEquipmentIndexRelat);
        ExcelUtil<BusEquipmentIndexRelat> util = new ExcelUtil<BusEquipmentIndexRelat>(BusEquipmentIndexRelat.class);
        return util.exportExcel(list, "equipmentindexrelat");
    }

    /**
     * 获取设备和指标关联关系详细信息
     */
    @ApiOperation("获取设备和指标关联关系详细信息")
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busEquipmentIndexRelatService.selectBusEquipmentIndexRelatById(id));
    }

    /**
     * 新增设备和指标关联关系
     */
    @ApiOperation("新增设备和指标关联关系")
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:add')")
    @Log(title = "设备和指标关联关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        return toAjax(busEquipmentIndexRelatService.insertBusEquipmentIndexRelat(busEquipmentIndexRelat));
    }

    /**
     * 修改设备和指标关联关系
     */
    @ApiOperation("修改设备和指标关联关系")
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:edit')")
    @Log(title = "设备和指标关联关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusEquipmentIndexRelat busEquipmentIndexRelat)
    {
        return toAjax(busEquipmentIndexRelatService.updateBusEquipmentIndexRelat(busEquipmentIndexRelat));
    }

    /**
     * 删除设备和指标关联关系
     */
    @ApiOperation("删除设备和指标关联关系")
    @PreAuthorize("@ss.hasPermi('equipmentindexrelat:equipmentindexrelat:remove')")
    @Log(title = "设备和指标关联关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busEquipmentIndexRelatService.deleteBusEquipmentIndexRelatByIds(ids));
    }
}
