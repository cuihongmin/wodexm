package com.wanqiao.mogao.project.detectionindex.controller;

import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Log;
import com.wanqiao.mogao.framework.aspectj.lang.enums.BusinessType;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;
import com.wanqiao.mogao.project.detectionindex.domain.BusDetectionIndex;
import com.wanqiao.mogao.project.detectionindex.dto.BusDetectionIndexDTO;
import com.wanqiao.mogao.project.detectionindex.service.IBusDetectionIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 监测指标Controller
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Api(value = "监测指标接口", tags = "监测指标接口")
@RestController
@RequestMapping("/detectionindex/detectionindex")
public class BusDetectionIndexController extends BaseController
{
    @Autowired
    private IBusDetectionIndexService busDetectionIndexService;

    /**
     * 查询监测指标列表
     */
    @ApiOperation("获取全部监测指标")
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusDetectionIndex busDetectionIndex)
    {
        startPage();
        List<BusDetectionIndex> list = busDetectionIndexService.selectBusDetectionIndexList(busDetectionIndex);
        return getDataTable(list);
    }

    /**
     * 导出监测指标列表
     */
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:export')")
    @Log(title = "监测指标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDetectionIndex busDetectionIndex)
    {
        List<BusDetectionIndex> list = busDetectionIndexService.selectBusDetectionIndexList(busDetectionIndex);
        ExcelUtil<BusDetectionIndex> util = new ExcelUtil<BusDetectionIndex>(BusDetectionIndex.class);
        return util.exportExcel(list, "detectionindex");
    }

    /**
     * 获取监测指标详细信息
     */
    @ApiOperation("获取监测指标详细信息")
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busDetectionIndexService.selectBusDetectionIndexById(id));
    }

    /**
     * 新增监测指标
     */
    @ApiOperation("新增监测指标")
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:add')")
    @Log(title = "监测指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusDetectionIndexDTO busDetectionIndexDTO)
    {
        return toAjax(busDetectionIndexService.insertBusDetectionIndex(busDetectionIndexDTO));
    }

    /**
     * 修改监测指标
     */
    @ApiOperation("修改监测指标")
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:edit')")
    @Log(title = "监测指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDetectionIndexDTO busDetectionIndexDTO)
    {
        return toAjax(busDetectionIndexService.updateBusDetectionIndex(busDetectionIndexDTO));
    }

    /**
     * 删除监测指标
     */
    @ApiOperation("删除监测指标")
    @PreAuthorize("@ss.hasPermi('detectionindex:detectionindex:remove')")
    @Log(title = "监测指标", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busDetectionIndexService.deleteBusDetectionIndexByIds(ids));
    }
}
