package com.wanqiao.mogao.project.groupperiod.controller;

import java.util.List;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import com.wanqiao.mogao.project.groupperiod.domain.BusGroupManage;
import com.wanqiao.mogao.project.groupperiod.service.IBusGroupManageService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 群组管理Controller
 *
 * @author zhangguangbin
 * @date 2021-05-08
 */
@Api(value = "群组信息管理", tags = "群组信息管理")
@RestController
@RequestMapping("/groupperiod/groupManage")
public class BusGroupManageController extends BaseController {
    @Autowired
    private IBusGroupManageService busGroupManageService;

    /**
     * 查询群组管理列表
     */
    @ApiOperation("群组列表（全部）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前记录起始索引", required = false, dataType="String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = false, dataType="String"),
            @ApiImplicitParam(name = "orderByColumn", value = "排序列", required = false, dataType="String"),
            @ApiImplicitParam(name = "isAsc", value = "排序的方向 'desc' 或者 'asc'.", required = false, dataType="String")
    })
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusGroupManage busGroupManage,
                              String pageNum,
                              String pageSize,
                              String orderByColumn,
                              String isAsc) {
        startPage();
        List<BusGroupManage> list = busGroupManageService.selectBusGroupManageList(busGroupManage);
        return getDataTable(list);
    }

    /**
     * 导出群组管理列表
     */
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:export')")
    @Log(title = "群组管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusGroupManage busGroupManage) {
        List<BusGroupManage> list = busGroupManageService.selectBusGroupManageList(busGroupManage);
        ExcelUtil<BusGroupManage> util = new ExcelUtil<BusGroupManage>(BusGroupManage.class);
        return util.exportExcel(list, "groupManage");
    }

    /**
     * 获取群组管理详细信息
     */
    @ApiOperation("获取群组详情")
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(busGroupManageService.selectBusGroupManageById(id));
    }

    /**
     * 新增群组管理
     */
    @ApiOperation("新增群组")
    @ApiImplicitParam(name = "busGroupManage", value = "新增群组信息", dataType = "BusGroupManage")
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:add')")
    @Log(title = "群组管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusGroupManage busGroupManage) {
        busGroupManage.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busGroupManage.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        busGroupManage.setCreateTime(DateUtils.getNowDate());
        return toAjax(busGroupManageService.insertBusGroupManage(busGroupManage));
    }

    /**
     * 修改群组管理
     */
    @ApiOperation("修改群组信息")
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:edit')")
    @Log(title = "群组管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusGroupManage busGroupManage) {
        busGroupManage.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        busGroupManage.setUpdateTime(DateUtils.getNowDate());
        return toAjax(busGroupManageService.updateBusGroupManage(busGroupManage));
    }

    /**
     * 删除群组管理
     */
    @ApiOperation("删除群组群组")
    @PreAuthorize("@ss.hasPermi('groupperiod:groupManage:remove')")
    @Log(title = "群组管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(busGroupManageService.deleteBusGroupManageByIds(ids));
    }
}
