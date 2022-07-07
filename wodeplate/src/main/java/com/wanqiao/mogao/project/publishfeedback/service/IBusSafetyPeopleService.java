package com.wanqiao.mogao.project.publishfeedback.service;

import java.util.List;
import com.wanqiao.mogao.project.publishfeedback.domain.BusSafetyPeople;

/**
 * 安全人员Service接口
 * 
 * @author geshanghua
 * @date 2021-05-10
 */
public interface IBusSafetyPeopleService 
{
    /**
     * 查询安全人员
     * 
     * @param id 安全人员ID
     * @return 安全人员
     */
    public BusSafetyPeople selectBusSafetyPeopleById(Long id);

    /**
     * 查询安全人员列表
     * 
     * @param busSafetyPeople 安全人员
     * @return 安全人员集合
     */
    public List<BusSafetyPeople> selectBusSafetyPeopleList(BusSafetyPeople busSafetyPeople);

    /**
     * 新增安全人员
     * 
     * @param busSafetyPeople 安全人员
     * @return 结果
     */
    public int insertBusSafetyPeople(BusSafetyPeople busSafetyPeople);

    /**
     * 修改安全人员
     * 
     * @param busSafetyPeople 安全人员
     * @return 结果
     */
    public int updateBusSafetyPeople(BusSafetyPeople busSafetyPeople);

    /**
     * 批量删除安全人员
     * 
     * @param ids 需要删除的安全人员ID
     * @return 结果
     */
    public int deleteBusSafetyPeopleByIds(Long[] ids);

    /**
     * 删除安全人员信息
     * 
     * @param id 安全人员ID
     * @return 结果
     */
    public int deleteBusSafetyPeopleById(Long id);
}
