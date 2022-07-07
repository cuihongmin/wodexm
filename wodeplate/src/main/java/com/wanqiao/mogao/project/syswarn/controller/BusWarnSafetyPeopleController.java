package com.wanqiao.mogao.project.syswarn.controller;

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
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSafetyPeople;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnSafetyPeopleService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 预警配置和安全员关系表Controller
 * 
 * @author cjj
 * @date 2021-05-10
 */
@RestController
@RequestMapping("/warnconfig/sprelat")
public class BusWarnSafetyPeopleController extends BaseController
{
    @Autowired
    private IBusWarnSafetyPeopleService busWarnSafetyPeopleService;

    /**
     * 查询预警配置和安全员关系表列表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusWarnSafetyPeople busWarnSafetyPeople)
    {
        startPage();
        List<BusWarnSafetyPeople> list = busWarnSafetyPeopleService.selectBusWarnSafetyPeopleList(busWarnSafetyPeople);
        return getDataTable(list);
    }

    /**
     * 导出预警配置和安全员关系表列表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:export')")
    @Log(title = "预警配置和安全员关系表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusWarnSafetyPeople busWarnSafetyPeople)
    {
        List<BusWarnSafetyPeople> list = busWarnSafetyPeopleService.selectBusWarnSafetyPeopleList(busWarnSafetyPeople);
        ExcelUtil<BusWarnSafetyPeople> util = new ExcelUtil<BusWarnSafetyPeople>(BusWarnSafetyPeople.class);
        return util.exportExcel(list, "sprelat");
    }

    /**
     * 获取预警配置和安全员关系表详细信息
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busWarnSafetyPeopleService.selectBusWarnSafetyPeopleById(id));
    }

    /**
     * 新增预警配置和安全员关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:add')")
    @Log(title = "预警配置和安全员关系表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusWarnSafetyPeople busWarnSafetyPeople)
    {
        return toAjax(busWarnSafetyPeopleService.insertBusWarnSafetyPeople(busWarnSafetyPeople));
    }

    /**
     * 修改预警配置和安全员关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:edit')")
    @Log(title = "预警配置和安全员关系表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusWarnSafetyPeople busWarnSafetyPeople)
    {
        return toAjax(busWarnSafetyPeopleService.updateBusWarnSafetyPeople(busWarnSafetyPeople));
    }

    /**
     * 删除预警配置和安全员关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:sprelat:remove')")
    @Log(title = "预警配置和安全员关系表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busWarnSafetyPeopleService.deleteBusWarnSafetyPeopleByIds(ids));
    }
}
