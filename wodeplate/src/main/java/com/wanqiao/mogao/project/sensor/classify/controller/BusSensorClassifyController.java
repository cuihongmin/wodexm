package com.wanqiao.mogao.project.sensor.classify.controller;

import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.poi.ExcelUtil;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Log;
import com.wanqiao.mogao.framework.aspectj.lang.enums.BusinessType;
import com.wanqiao.mogao.framework.web.controller.BaseController;
import com.wanqiao.mogao.framework.web.domain.AjaxResult;
import com.wanqiao.mogao.framework.web.page.TableDataInfo;
import com.wanqiao.mogao.project.sensor.classify.domain.BusSensorClassify;
import com.wanqiao.mogao.project.sensor.classify.enums.SensorClassifyStatus;
import com.wanqiao.mogao.project.sensor.classify.service.IBusSensorClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 传感器类型Controller
 * 
 * @author lixiangping
 * @date 2021-05-07
 */
@Api(value = "传感器设备类型", tags = "传感器设备类型")
@RestController
@RequestMapping("/sensorclassify/classify")
public class BusSensorClassifyController extends BaseController
{

    @Autowired
    private IBusSensorClassifyService busSensorClassifyService;

    /**
     * 修改传感器状态
     * @param id 传感设备id
     * @param status 修改状态
     * @return
     */
    @ApiOperation("修改传感设备状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType="long"),
            @ApiImplicitParam(name = "status", value = "传感器状态", required = true, dataType="int")
    })
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:edit')")
    @Log(title = "传感器类型", businessType = BusinessType.UPDATE)
    @PutMapping("/state/edit")
    public AjaxResult editState(Long id,
                           Integer status)
    {
        BusSensorClassify busSensorClassify = new BusSensorClassify();
        busSensorClassify.setId(id);
        busSensorClassify.setState(status);
        //修改时间、修改人
        busSensorClassify.setModifyBy(SecurityUtils.getLoginUser().getUser().getUserId());
        busSensorClassify.setModifyTime(DateUtils.getNowDate());
        //todo 设备状态下发()

        return toAjax(busSensorClassifyService.updateBusSensorClassify(busSensorClassify));
    }

    /**
     * 查询传感器类型列表
     * @return 传感设备列表
     */
    @ApiOperation("获取传感器设备列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", value = "当前记录起始索引", required = false, dataType="String"),
//            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", required = false, dataType="String"),
//            @ApiImplicitParam(name = "orderByColumn", value = "排序列", required = false, dataType="String"),
//            @ApiImplicitParam(name = "isAsc", value = "排序的方向 'desc' 或者 'asc'.", required = false, dataType="String")
//    })
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:list')")
    @GetMapping("/list")
    public List<Map> list()
    {
        List<Map> result = null;
        List<BusSensorClassify> list = busSensorClassifyService.selectBusSensorClassifyList(null);
        if (list != null && list.size()>0) {
            result = new LinkedList<>();
            for (BusSensorClassify bsc :
                    list) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", bsc.getId());
                map.put("label", bsc.getName());
                map.put("icon", "el-icon-s-platform");

                result.add(map);
            }
        }
        return result;
    }
//    public TableDataInfo list(@ApiIgnore BusSensorClassify busSensorClassify,
//                              String pageNum,
//                              String pageSize,
//                              String orderByColumn,
//                              String isAsc)
//    {
//        startPage();
//        List<BusSensorClassify> list = busSensorClassifyService.selectBusSensorClassifyList(busSensorClassify);
//        return getDataTable(list);
//    }

    /**
     * 导出传感器类型列表
     */
    @ApiOperation("导出传感器设备列表")
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:export')")
    @Log(title = "传感器类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(@RequestBody BusSensorClassify busSensorClassify)
    {
        List<BusSensorClassify> list = busSensorClassifyService.selectBusSensorClassifyList(busSensorClassify);
        ExcelUtil<BusSensorClassify> util = new ExcelUtil<BusSensorClassify>(BusSensorClassify.class);
        return util.exportExcel(list, "classify");
    }

    /**
     * 新增传感器类型
     */
    @ApiOperation("新增传感设备")
    @ApiImplicitParam(name = "busSensorClassify", value = "新增", dataType = "BusSensorClassify")
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:add')")
    @Log(title = "传感器类型", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult add(@RequestBody BusSensorClassify busSensorClassify)
    {
        //id生成策略
        busSensorClassify.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        //状态默认为禁用
        busSensorClassify.setState(SensorClassifyStatus.DISABLE.getCode());
        //设置记录创建人、创建时间
        busSensorClassify.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        busSensorClassify.setCreateTime(DateUtils.getNowDate());

        return toAjax(busSensorClassifyService.insertBusSensorClassify(busSensorClassify));
    }

    /**
     * 获取传感器类型详细信息
     */
    @ApiOperation("获取传感设备详细")
    @ApiImplicitParam(name = "id", value = "设备ID", required = true, dataType = "int", paramType = "path")
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busSensorClassifyService.selectBusSensorClassifyById(id));
    }

    /**
     * 修改传感器类型
     */
    @ApiOperation("修改传感设备(不支持修改设备状态)")
    @ApiImplicitParam(name = "busSensorClassify", value = "新增", dataType = "BusSensorClassify")
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:edit')")
    @Log(title = "传感器类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusSensorClassify busSensorClassify)
    {
        //修改时间、修改人
        busSensorClassify.setModifyBy(SecurityUtils.getLoginUser().getUser().getUserId());
        busSensorClassify.setModifyTime(DateUtils.getNowDate());
        return toAjax(busSensorClassifyService.updateBusSensorClassify(busSensorClassify));
    }

    /**
     * 删除传感器类型
     */
    @ApiOperation("批量删除传感器类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "传感设备ids", required = true, dataType="long", allowMultiple = true)
    })
    @PreAuthorize("@ss.hasPermi('sensorclassify:classify:remove')")
    @Log(title = "传感器类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busSensorClassifyService.deleteBusSensorClassifyByIds(ids));
    }
}
