package com.wanqiao.mogao.project.datacollect.service;

import java.util.List;
import com.wanqiao.mogao.project.datacollect.domain.BusTailingClassify;

/**
 * 尾矿库分类Service接口
 * 
 * @author zhangguangbin
 * @date 2021-05-08
 */
public interface IBusTailingClassifyService 
{
    /**
     * 查询尾矿库分类
     * 
     * @param id 尾矿库分类ID
     * @return 尾矿库分类
     */
    public BusTailingClassify selectBusTailingClassifyById(Long id);

    /**
     * 查询尾矿库分类列表
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 尾矿库分类集合
     */
    public List<BusTailingClassify> selectBusTailingClassifyList(BusTailingClassify busTailingClassify);

    /**
     * 新增尾矿库分类
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 结果
     */
    public int insertBusTailingClassify(BusTailingClassify busTailingClassify);

    /**
     * 修改尾矿库分类
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 结果
     */
    public int updateBusTailingClassify(BusTailingClassify busTailingClassify);

    /**
     * 批量删除尾矿库分类
     * 
     * @param ids 需要删除的尾矿库分类ID
     * @return 结果
     */
    public int deleteBusTailingClassifyByIds(Long[] ids);

    /**
     * 删除尾矿库分类信息
     * 
     * @param id 尾矿库分类ID
     * @return 结果
     */
    public int deleteBusTailingClassifyById(Long id);
}
