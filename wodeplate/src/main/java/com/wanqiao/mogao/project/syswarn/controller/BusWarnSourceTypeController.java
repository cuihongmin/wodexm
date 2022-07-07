package com.wanqiao.mogao.project.syswarn.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSourceType;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnSourceTypeService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等Controller
 * 
 * @author cjj
 * @date 2021-05-07
 */
@Api(value = "预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等")
@RestController
@RequestMapping("/syswarn/warntype")
public class BusWarnSourceTypeController extends BaseController
{
    @Autowired
    private IBusWarnSourceTypeService busWarnSourceTypeService;

    /**
     * 查询预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等列表
     */
    @ApiModelProperty(value = "预警类型列表", notes = "人工预警，库水位预警、干滩预警、浸润预警等列表")
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusWarnSourceType busWarnSourceType)
    {
        startPage();
        List<BusWarnSourceType> list = busWarnSourceTypeService.selectBusWarnSourceTypeList(busWarnSourceType);
        return getDataTable(list);
    }

   /* *//**
     * 导出预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等列表
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:export')")
    @Log(title = "预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusWarnSourceType busWarnSourceType)
    {
        List<BusWarnSourceType> list = busWarnSourceTypeService.selectBusWarnSourceTypeList(busWarnSourceType);
        ExcelUtil<BusWarnSourceType> util = new ExcelUtil<BusWarnSourceType>(BusWarnSourceType.class);
        return util.exportExcel(list, "warntype");
    }

    *//**
     * 获取预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等详细信息
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busWarnSourceTypeService.selectBusWarnSourceTypeById(id));
    }

    *//**
     * 新增预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:add')")
    @Log(title = "预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusWarnSourceType busWarnSourceType)
    {
        return toAjax(busWarnSourceTypeService.insertBusWarnSourceType(busWarnSourceType));
    }

    *//**
     * 修改预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:edit')")
    @Log(title = "预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusWarnSourceType busWarnSourceType)
    {
        return toAjax(busWarnSourceTypeService.updateBusWarnSourceType(busWarnSourceType));
    }

    *//**
     * 删除预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     *//*
    @PreAuthorize("@ss.hasPermi('syswarn:warntype:remove')")
    @Log(title = "预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busWarnSourceTypeService.deleteBusWarnSourceTypeByIds(ids));
    }*/
}
