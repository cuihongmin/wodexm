package com.wanqiao.mogao.project.publishfeedback.controller;

import java.util.List;

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
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.publishfeedback.service.IBusSafetyPeopleService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 安全人员Controller
 * 
 * @author geshanghua
 * @date 2021-05-10
 */
@RestController
@RequestMapping("/safetypeople/list")
public class BusSafetyPeopleController extends BaseController
{
    @Autowired
    private IBusSafetyPeopleService busSafetyPeopleService;

    /**
     * 查询安全人员列表
     */
    @ApiOperation(value = "获取安全人员列表")
    @PreAuthorize("@ss.hasPermi('safetypeople:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusSafetyPeople busSafetyPeople)
    {
        startPage();
        List<BusSafetyPeople> list = busSafetyPeopleService.selectBusSafetyPeopleList(busSafetyPeople);
        return getDataTable(list);
    }

    /**
     * 导出安全人员列表
     */
    /*@PreAuthorize("@ss.hasPermi('safetypeople:list:export')")
    @Log(title = "安全人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusSafetyPeople busSafetyPeople)
    {
        List<BusSafetyPeople> list = busSafetyPeopleService.selectBusSafetyPeopleList(busSafetyPeople);
        ExcelUtil<BusSafetyPeople> util = new ExcelUtil<BusSafetyPeople>(BusSafetyPeople.class);
        return util.exportExcel(list, "list");
    }
*/
    /**
     * 获取安全人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('safetypeople:list:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busSafetyPeopleService.selectBusSafetyPeopleById(id));
    }

    /**
     * 新增安全人员
     */
    @PreAuthorize("@ss.hasPermi('safetypeople:list:add')")
    @Log(title = "安全人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusSafetyPeople busSafetyPeople)
    {
        return toAjax(busSafetyPeopleService.insertBusSafetyPeople(busSafetyPeople));
    }

    /**
     * 修改安全人员
     */
    @PreAuthorize("@ss.hasPermi('safetypeople:list:edit')")
    @Log(title = "安全人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusSafetyPeople busSafetyPeople)
    {
        return toAjax(busSafetyPeopleService.updateBusSafetyPeople(busSafetyPeople));
    }

    /**
     * 删除安全人员
     */
    @PreAuthorize("@ss.hasPermi('safetypeople:list:remove')")
    @Log(title = "安全人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busSafetyPeopleService.deleteBusSafetyPeopleByIds(ids));
    }
}
