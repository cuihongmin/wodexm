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
import com.wanqiao.mogao.project.datacollect.domain.BusTailingClassify;
import com.wanqiao.mogao.project.datacollect.service.IBusTailingClassifyService;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;

/**
 * 尾矿库分类Controller
 * 
 * @author zhangguangbin
 * @date 2021-05-08
 */
@RestController
@RequestMapping("/datacollect/tailingClassify")
public class BusTailingClassifyController extends BaseController
{
    @Autowired
    private IBusTailingClassifyService busTailingClassifyService;

    /**
     * 查询尾矿库分类列表
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusTailingClassify busTailingClassify)
    {
        startPage();
        List<BusTailingClassify> list = busTailingClassifyService.selectBusTailingClassifyList(busTailingClassify);
        return getDataTable(list);
    }

    /**
     * 导出尾矿库分类列表
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:export')")
    @Log(title = "尾矿库分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusTailingClassify busTailingClassify)
    {
        List<BusTailingClassify> list = busTailingClassifyService.selectBusTailingClassifyList(busTailingClassify);
        ExcelUtil<BusTailingClassify> util = new ExcelUtil<BusTailingClassify>(BusTailingClassify.class);
        return util.exportExcel(list, "tailingClassify");
    }

    /**
     * 获取尾矿库分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busTailingClassifyService.selectBusTailingClassifyById(id));
    }

    /**
     * 新增尾矿库分类
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:add')")
    @Log(title = "尾矿库分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusTailingClassify busTailingClassify)
    {
        return toAjax(busTailingClassifyService.insertBusTailingClassify(busTailingClassify));
    }

    /**
     * 修改尾矿库分类
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:edit')")
    @Log(title = "尾矿库分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusTailingClassify busTailingClassify)
    {
        return toAjax(busTailingClassifyService.updateBusTailingClassify(busTailingClassify));
    }

    /**
     * 删除尾矿库分类
     */
    @PreAuthorize("@ss.hasPermi('datacollect:tailingClassify:remove')")
    @Log(title = "尾矿库分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busTailingClassifyService.deleteBusTailingClassifyByIds(ids));
    }
}
