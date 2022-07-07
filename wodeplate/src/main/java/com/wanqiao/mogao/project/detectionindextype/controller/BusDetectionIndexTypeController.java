package com.wanqiao.mogao.project.detectionindextype.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import com.wanqiao.mogao.project.detectionindextype.domain.BusDetectionIndexType;
import com.wanqiao.mogao.project.detectionindextype.service.IBusDetectionIndexTypeService;
import com.wanqiao.mogao.project.sensor.classify.domain.BusSensorClassify;
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
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 监测指标类型Controller
 * 
 * @author lixiangping
 * @date 2021-05-11
 */
@Api(value ="监测指标类型接口", tags = "监测指标类型接口")
@RestController
@RequestMapping("/detectionindextype/type")
public class BusDetectionIndexTypeController extends BaseController
{
    @Autowired
    private IBusDetectionIndexTypeService busDetectionIndexTypeService;

    /**
     * 查询监测指标类型列表
     */
    @ApiOperation("获取列表（所有）")
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:list')")
    @GetMapping("/list")
    public List<Map> list()
    {
        List<Map> resultList = null;
        List<BusDetectionIndexType> busDetectionIndexTypeList = busDetectionIndexTypeService.selectBusDetectionIndexTypeList(null);
        if (busDetectionIndexTypeList != null && busDetectionIndexTypeList.size()>0) {
            resultList = new LinkedList<>();
            for (BusDetectionIndexType bsc :
                    busDetectionIndexTypeList) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", bsc.getId());
                map.put("label", bsc.getName());
                map.put("icon", "el-icon-s-platform");

                resultList.add(map);
            }
        }
        return resultList;
    }

    /**
     * 导出监测指标类型列表
     */
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:export')")
    @Log(title = "监测指标类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusDetectionIndexType busDetectionIndexType)
    {
        List<BusDetectionIndexType> list = busDetectionIndexTypeService.selectBusDetectionIndexTypeList(busDetectionIndexType);
        ExcelUtil<BusDetectionIndexType> util = new ExcelUtil<BusDetectionIndexType>(BusDetectionIndexType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取监测指标类型详细信息
     */
    @ApiOperation("获取类型详情")
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busDetectionIndexTypeService.selectBusDetectionIndexTypeById(id));
    }

    /**
     * 新增监测指标类型
     */
    @ApiOperation("新增操作")
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:add')")
    @Log(title = "监测指标类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusDetectionIndexType busDetectionIndexType)
    {
        busDetectionIndexType.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busDetectionIndexType.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        busDetectionIndexType.setCreateTime(DateUtils.getNowDate());
        return toAjax(busDetectionIndexTypeService.insertBusDetectionIndexType(busDetectionIndexType));
    }

    /**
     * 修改监测指标类型
     */
    @ApiOperation("根据id修改监控指标类型")
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:edit')")
    @Log(title = "监测指标类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusDetectionIndexType busDetectionIndexType)
    {
        return toAjax(busDetectionIndexTypeService.updateBusDetectionIndexType(busDetectionIndexType));
    }

    /**
     * 删除监测指标类型
     */
    @ApiOperation("根据id删除")
    @PreAuthorize("@ss.hasPermi('detectionindextype:type:remove')")
    @Log(title = "监测指标类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busDetectionIndexTypeService.deleteBusDetectionIndexTypeByIds(ids));
    }
}
