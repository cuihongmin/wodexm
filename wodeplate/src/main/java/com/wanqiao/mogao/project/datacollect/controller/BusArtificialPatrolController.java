package com.wanqiao.mogao.project.datacollect.controller;

import java.util.List;

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
import com.wanqiao.mogao.project.datacollect.domain.BusArtificialPatrol;
import com.wanqiao.mogao.project.datacollect.service.IBusArtificialPatrolService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 人工巡检登记Controller
 *
 * @author zhangguangbin
 * @date 2021-05-10
 */
@RestController
@RequestMapping("/datacollect/artificialPatrol")
public class BusArtificialPatrolController extends BaseController {
    @Autowired
    private IBusArtificialPatrolService busArtificialPatrolService;

    /**
     * 查询人工巡检登记列表
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusArtificialPatrol busArtificialPatrol) {
        startPage();
        List<BusArtificialPatrol> list = busArtificialPatrolService.selectBusArtificialPatrolList(busArtificialPatrol);
        return getDataTable(list);
    }

    /**
     * 导出人工巡检登记列表
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:export')")
    @Log(title = "人工巡检登记", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusArtificialPatrol busArtificialPatrol) {
        List<BusArtificialPatrol> list = busArtificialPatrolService.selectBusArtificialPatrolList(busArtificialPatrol);
        ExcelUtil<BusArtificialPatrol> util = new ExcelUtil<BusArtificialPatrol>(BusArtificialPatrol.class);
        return util.exportExcel(list, "artificialPatrol");
    }

    /**
     * 获取人工巡检登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(busArtificialPatrolService.selectBusArtificialPatrolById(id));
    }

    /**
     * 新增人工巡检登记
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:add')")
    @Log(title = "人工巡检登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusArtificialPatrol busArtificialPatrol) {
        return toAjax(busArtificialPatrolService.insertBusArtificialPatrol(busArtificialPatrol));
    }

    /**
     * 修改人工巡检登记
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:edit')")
    @Log(title = "人工巡检登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusArtificialPatrol busArtificialPatrol) {
        return toAjax(busArtificialPatrolService.updateBusArtificialPatrol(busArtificialPatrol));
    }

    /**
     * 人工巡检解除预警
     */
    @Log(title = "人工巡检登记", businessType = BusinessType.UPDATE)
    @PostMapping("/relieveWarning")
    public AjaxResult relieveWarning(@RequestBody BusArtificialPatrol busArtificialPatrol) {
        BusArtificialPatrol artificialPatrol = new BusArtificialPatrol();
        artificialPatrol.setId(busArtificialPatrol.getId());
        artificialPatrol.setWarnState("1");
        artificialPatrol.setWarnRelieveTime(busArtificialPatrol.getWarnRelieveTime());
        artificialPatrol.setRelieveBy(busArtificialPatrol.getRelieveBy());
        artificialPatrol.setSafetyAssessment(busArtificialPatrol.getSafetyAssessment());
        artificialPatrol.setRelieveDetails(busArtificialPatrol.getRelieveDetails());
        return toAjax(busArtificialPatrolService.relieveWarning(artificialPatrol));
    }

    /**
     * 删除人工巡检登记
     */
    @PreAuthorize("@ss.hasPermi('datacollect:artificialPatrol:remove')")
    @Log(title = "人工巡检登记", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(busArtificialPatrolService.deleteBusArtificialPatrolByIds(ids));
    }
}
