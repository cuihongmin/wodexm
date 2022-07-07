package com.wanqiao.mogao.project.datacollect.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import com.wanqiao.mogao.common.utils.SecurityUtils;
import com.wanqiao.mogao.common.utils.primarykey.WanqiaoPrimaryKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.datacollect.mapper.BusTailingClassifyMapper;
import com.wanqiao.mogao.project.datacollect.domain.BusTailingClassify;
import com.wanqiao.mogao.project.datacollect.service.IBusTailingClassifyService;

/**
 * 尾矿库分类Service业务层处理
 * 
 * @author zhangguangbin
 * @date 2021-05-08
 */
@Service
public class BusTailingClassifyServiceImpl implements IBusTailingClassifyService 
{
    @Autowired
    private BusTailingClassifyMapper busTailingClassifyMapper;

    /**
     * 查询尾矿库分类
     * 
     * @param id 尾矿库分类ID
     * @return 尾矿库分类
     */
    @Override
    public BusTailingClassify selectBusTailingClassifyById(Long id)
    {
        return busTailingClassifyMapper.selectBusTailingClassifyById(id);
    }

    /**
     * 查询尾矿库分类列表
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 尾矿库分类
     */
    @Override
    public List<BusTailingClassify> selectBusTailingClassifyList(BusTailingClassify busTailingClassify)
    {
        return busTailingClassifyMapper.selectBusTailingClassifyList(busTailingClassify);
    }

    /**
     * 新增尾矿库分类
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 结果
     */
    @Override
    public int insertBusTailingClassify(BusTailingClassify busTailingClassify)
    {
        busTailingClassify.setCreateTime(DateUtils.getNowDate());
        busTailingClassify.setId(WanqiaoPrimaryKeyUtils.getSerialNumberLong());
        busTailingClassify.setCreateTime(DateUtils.getNowDate());
        busTailingClassify.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return busTailingClassifyMapper.insertBusTailingClassify(busTailingClassify);
    }

    /**
     * 修改尾矿库分类
     * 
     * @param busTailingClassify 尾矿库分类
     * @return 结果
     */
    @Override
    public int updateBusTailingClassify(BusTailingClassify busTailingClassify)
    {
        busTailingClassify.setUpdateTime(DateUtils.getNowDate());
        busTailingClassify.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId().toString());
        return busTailingClassifyMapper.updateBusTailingClassify(busTailingClassify);
    }

    /**
     * 批量删除尾矿库分类
     * 
     * @param ids 需要删除的尾矿库分类ID
     * @return 结果
     */
    @Override
    public int deleteBusTailingClassifyByIds(Long[] ids)
    {
        return busTailingClassifyMapper.deleteBusTailingClassifyByIds(ids);
    }

    /**
     * 删除尾矿库分类信息
     * 
     * @param id 尾矿库分类ID
     * @return 结果
     */
    @Override
    public int deleteBusTailingClassifyById(Long id)
    {
        return busTailingClassifyMapper.deleteBusTailingClassifyById(id);
    }
}
