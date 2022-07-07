package com.wanqiao.mogao.project.syswarn.service;

import java.util.List;
import com.wanqiao.mogao.project.syswarn.domain.BusWarnSourceType;

/**
 * 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等Service接口
 * 
 * @author cjj
 * @date 2021-05-07
 */
public interface IBusWarnSourceTypeService 
{
    /**
     * 查询预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param id 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     */
    public BusWarnSourceType selectBusWarnSourceTypeById(Long id);

    /**
     * 查询预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等列表
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等集合
     */
    public List<BusWarnSourceType> selectBusWarnSourceTypeList(BusWarnSourceType busWarnSourceType);

    /**
     * 新增预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 结果
     */
    public int insertBusWarnSourceType(BusWarnSourceType busWarnSourceType);

    /**
     * 修改预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param busWarnSourceType 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * @return 结果
     */
    public int updateBusWarnSourceType(BusWarnSourceType busWarnSourceType);

    /**
     * 批量删除预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等
     * 
     * @param ids 需要删除的预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 结果
     */
    public int deleteBusWarnSourceTypeByIds(Long[] ids);

    /**
     * 删除预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等信息
     * 
     * @param id 预警源类型： 人工预警，库水位预警、干滩预警、浸润预警等ID
     * @return 结果
     */
    public int deleteBusWarnSourceTypeById(Long id);
}
