package com.wanqiao.mogao.project.sensor.classify.mapper;

import com.wanqiao.mogao.project.sensor.classify.domain.BusSensorClassify;

import java.util.List;

/**
 * 传感器类型Mapper接口
 * 
 * @author lixiangping
 * @date 2021-05-07
 */
public interface BusSensorClassifyMapper 
{
    /**
     * 查询传感器类型
     * 
     * @param id 传感器类型ID
     * @return 传感器类型
     */
    public BusSensorClassify selectBusSensorClassifyById(Long id);

    /**
     * 查询传感器类型列表
     * 
     * @param busSensorClassify 传感器类型
     * @return 传感器类型集合
     */
    public List<BusSensorClassify> selectBusSensorClassifyList(BusSensorClassify busSensorClassify);

    /**
     * 新增传感器类型
     * 
     * @param busSensorClassify 传感器类型
     * @return 结果
     */
    public int insertBusSensorClassify(BusSensorClassify busSensorClassify);

    /**
     * 修改传感器类型
     * 
     * @param busSensorClassify 传感器类型
     * @return 结果
     */
    public int updateBusSensorClassify(BusSensorClassify busSensorClassify);

    /**
     * 删除传感器类型
     * 
     * @param id 传感器类型ID
     * @return 结果
     */
    public int deleteBusSensorClassifyById(Long id);

    /**
     * 批量删除传感器类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusSensorClassifyByIds(Long[] ids);
}
