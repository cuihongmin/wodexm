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
import com.wanqiao.mogao.project.syswarn.domain.BusWarnConfigWarnTypeRelat;
import com.wanqiao.mogao.project.syswarn.service.IBusWarnConfigWarnTypeRelatService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 预警配置和预警方式关系表Controller
 * 
 * @author cjj
 * @date 2021-05-10
 */
@RestController
@RequestMapping("/warnconfig/typerelat")
public class BusWarnConfigWarnTypeRelatController extends BaseController
{
    @Autowired
    private IBusWarnConfigWarnTypeRelatService busWarnConfigWarnTypeRelatService;

    /**
     * 查询预警配置和预警方式关系表列表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        startPage();
        List<BusWarnConfigWarnTypeRelat> list = busWarnConfigWarnTypeRelatService.selectBusWarnConfigWarnTypeRelatList(busWarnConfigWarnTypeRelat);
        return getDataTable(list);
    }

    /**
     * 导出预警配置和预警方式关系表列表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:export')")
    @Log(title = "预警配置和预警方式关系表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        List<BusWarnConfigWarnTypeRelat> list = busWarnConfigWarnTypeRelatService.selectBusWarnConfigWarnTypeRelatList(busWarnConfigWarnTypeRelat);
        ExcelUtil<BusWarnConfigWarnTypeRelat> util = new ExcelUtil<BusWarnConfigWarnTypeRelat>(BusWarnConfigWarnTypeRelat.class);
        return util.exportExcel(list, "typerelat");
    }

    /**
     * 获取预警配置和预警方式关系表详细信息
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busWarnConfigWarnTypeRelatService.selectBusWarnConfigWarnTypeRelatById(id));
    }

    /**
     * 新增预警配置和预警方式关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:add')")
    @Log(title = "预警配置和预警方式关系表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        return toAjax(busWarnConfigWarnTypeRelatService.insertBusWarnConfigWarnTypeRelat(busWarnConfigWarnTypeRelat));
    }

    /**
     * 修改预警配置和预警方式关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:edit')")
    @Log(title = "预警配置和预警方式关系表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusWarnConfigWarnTypeRelat busWarnConfigWarnTypeRelat)
    {
        return toAjax(busWarnConfigWarnTypeRelatService.updateBusWarnConfigWarnTypeRelat(busWarnConfigWarnTypeRelat));
    }

    /**
     * 删除预警配置和预警方式关系表
     */
    @PreAuthorize("@ss.hasPermi('warnconfig:typerelat:remove')")
    @Log(title = "预警配置和预警方式关系表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busWarnConfigWarnTypeRelatService.deleteBusWarnConfigWarnTypeRelatByIds(ids));
    }
}
