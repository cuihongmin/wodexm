package com.wanqiao.mogao.project.publishfeedback.service.impl;

import java.util.List;
import com.wanqiao.mogao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanqiao.mogao.project.publishfeedback.mapper.BusSafetyPeopleMapper;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;
import com.wanqiao.mogao.project.publishfeedback.service.IBusSafetyPeopleService;

/**
 * 安全人员Service业务层处理
 * 
 * @author geshanghua
 * @date 2021-05-10
 */
@Service
public class BusSafetyPeopleServiceImpl implements IBusSafetyPeopleService 
{
    @Autowired
    private BusSafetyPeopleMapper busSafetyPeopleMapper;

    /**
     * 查询安全人员
     * 
     * @param id 安全人员ID
     * @return 安全人员
     */
    @Override
    public BusSafetyPeople selectBusSafetyPeopleById(Long id)
    {
        return busSafetyPeopleMapper.selectBusSafetyPeopleById(id);
    }

    /**
     * 查询安全人员列表
     * 
     * @param busSafetyPeople 安全人员
     * @return 安全人员
     */
    @Override
    public List<BusSafetyPeople> selectBusSafetyPeopleList(BusSafetyPeople busSafetyPeople)
    {
        return busSafetyPeopleMapper.selectBusSafetyPeopleList(busSafetyPeople);
    }

    /**
     * 新增安全人员
     * 
     * @param busSafetyPeople 安全人员
     * @return 结果
     */
    @Override
    public int insertBusSafetyPeople(BusSafetyPeople busSafetyPeople)
    {
        busSafetyPeople.setCreateTime(DateUtils.getNowDate());
        return busSafetyPeopleMapper.insertBusSafetyPeople(busSafetyPeople);
    }

    /**
     * 修改安全人员
     * 
     * @param busSafetyPeople 安全人员
     * @return 结果
     */
    @Override
    public int updateBusSafetyPeople(BusSafetyPeople busSafetyPeople)
    {
        return busSafetyPeopleMapper.updateBusSafetyPeople(busSafetyPeople);
    }

    /**
     * 批量删除安全人员
     * 
     * @param ids 需要删除的安全人员ID
     * @return 结果
     */
    @Override
    public int deleteBusSafetyPeopleByIds(Long[] ids)
    {
        return busSafetyPeopleMapper.deleteBusSafetyPeopleByIds(ids);
    }

    /**
     * 删除安全人员信息
     * 
     * @param id 安全人员ID
     * @return 结果
     */
    @Override
    public int deleteBusSafetyPeopleById(Long id)
    {
        return busSafetyPeopleMapper.deleteBusSafetyPeopleById(id);
    }
}
